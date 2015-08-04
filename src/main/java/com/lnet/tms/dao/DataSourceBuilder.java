package com.lnet.tms.dao;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.transform.ResultTransformer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataSourceBuilder {

    private static void restrict(Junction junction, DataSourceRequest.FilterDescriptor filter, Class<?> clazz) {
        String operator = filter.getOperator();
        String field = filter.getField();
        Object value = filter.getValue();
        boolean ignoreCase = filter.isIgnoreCase();

        try {
            Class<?> type = new PropertyDescriptor(field, clazz).getPropertyType();
            if (type == double.class || type == Double.class) {
                value = Double.parseDouble(value.toString());
            }if (type == UUID.class) {
                if(!"in".equals(operator)&&value!=null)
                value = UUID.fromString(value.toString());
            } else if (type == float.class || type == Float.class) {
                value = Float.parseFloat(value.toString());
            } else if (type == long.class || type == Long.class) {
                value = Long.parseLong(value.toString());
            } else if (type == int.class || type == Integer.class) {
                value = Integer.parseInt(value.toString());
            } else if (type == short.class || type == Short.class) {
                value = Short.parseShort(value.toString());
            } else if (type == boolean.class || type == Boolean.class) {
                value = Boolean.parseBoolean(value.toString());
            } else if (type == Date.class||type== Timestamp.class) {
                //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String input = value.toString();
                value = df.parse(input);
            }
        } catch (IntrospectionException e) {
        } catch (NumberFormatException nfe) {
        } catch (ParseException e) {
        }

        if(value!=null){
            switch (operator) {
                case "eq":
                    if (value instanceof String) {
                        junction.add(Restrictions.ilike(field, value.toString(), MatchMode.EXACT));
                    }else if (value instanceof UUID) {
                        junction.add(Restrictions.eq(field, value));
                    } else {
                        junction.add(Restrictions.eq(field, value));
                    }
                    break;
                case "neq":
                    if (value instanceof String) {
                        junction.add(Restrictions.not(Restrictions.ilike(field, value.toString(), MatchMode.EXACT)));
                    }else if (value instanceof UUID) {
                        junction.add(Restrictions.ne(field, value));
                    }  else {
                        junction.add(Restrictions.ne(field, value));
                    }
                    break;
                case "gt":
                    junction.add(Restrictions.gt(field, value));
                    break;
                case "gte":
                    junction.add(Restrictions.ge(field, value));
                    break;
                case "lt":
                    junction.add(Restrictions.lt(field, value));
                    break;
                case "lte":
                    junction.add(Restrictions.le(field, value));
                    break;
                case "startswith":
                    junction.add(getLikeExpression(field, value.toString(), MatchMode.START, ignoreCase));
                    break;
                case "endswith":
                    junction.add(getLikeExpression(field, value.toString(), MatchMode.END, ignoreCase));
                    break;
                case "contains":
                    junction.add(getLikeExpression(field, value.toString(), MatchMode.ANYWHERE, ignoreCase));
                    break;
                case "doesnotcontain":
                    junction.add(Restrictions.not(Restrictions.ilike(field, value.toString(), MatchMode.ANYWHERE)));
                    break;
                case "in":
                    if(value instanceof ArrayList){
                        if(((ArrayList) value).get(0) instanceof String) {
                            List<String> list = (ArrayList) value;
                            List<UUID> UUIDList = new ArrayList<UUID>();
                            try{
                                for (String s : list) {
                                    UUIDList.add(UUID.fromString(s));
                                }
                                junction.add(Restrictions.in(field, UUIDList));
                            }catch (Exception e){
                                junction.add(Restrictions.in(field, list));
                            }
                        }else if(((ArrayList) value).get(0) instanceof Integer){
                            List<Integer> list = (ArrayList)value;
                            junction.add(Restrictions.in(field, list));
                        }
                    }
                    break;
            }
        }else{
            junction.add(Restrictions.isNull(field));
        }

    }

    private static Criterion getLikeExpression(String field, String value, MatchMode mode, boolean ignoreCase) {
        SimpleExpression expression = Restrictions.like(field, value, mode);

        if (ignoreCase) {
            expression = expression.ignoreCase();
        }

        return expression;
    }

    private static void filter(Criteria criteria, DataSourceRequest.FilterDescriptor filter, Class<?> clazz) {
        if (filter != null) {
            List<DataSourceRequest.FilterDescriptor> filters = filter.getFilters();

            if (!filters.isEmpty()) {
                Junction junction = Restrictions.conjunction();

                if (!filter.getFilters().isEmpty() && filter.getLogic().equals("or")) {
                    junction = Restrictions.disjunction();
                }

                for (DataSourceRequest.FilterDescriptor entry : filters) {
                    if (!entry.getFilters().isEmpty()) {
                        filter(criteria, entry, clazz);
                    } else {
                        restrict(junction, entry, clazz);
                    }
                }

                criteria.add(junction);
            }
        }
    }

    private static void sort(Criteria criteria, List<DataSourceRequest.SortDescriptor> sort) {
        if (sort != null && !sort.isEmpty()) {
            for (DataSourceRequest.SortDescriptor entry : sort) {
                String field = entry.getField();
                String dir = entry.getDir();

                if (dir.equals("asc")) {
                    criteria.addOrder(Order.asc(field));
                } else if (dir.equals("desc")) {
                    criteria.addOrder(Order.desc(field));
                }
            }
        }
    }

    @SuppressWarnings({"serial", "unchecked"})
    private static Map<String, Object> calculateAggregates(Criteria criteria, List<DataSourceRequest.AggregateDescriptor> aggregates) {
        return (Map<String, Object>) criteria
                .setProjection(createAggregatesProjection(aggregates))
                .setResultTransformer(new ResultTransformer() {
                    @Override
                    public Object transformTuple(Object[] value, String[] aliases) {
                        Map<String, Object> result = new HashMap<>();

                        for (int i = 0; i < aliases.length; i++) {
                            String alias = aliases[i];
                            Map<String, Object> aggregate;

                            String name = alias.split("_")[0];
                            if (result.containsKey(name)) {
                                ((Map<String, Object>) result.get(name)).put(alias.split("_")[1], value[i]);
                            } else {
                                aggregate = new HashMap<>();
                                aggregate.put(alias.split("_")[1], value[i]);
                                result.put(name, aggregate);
                            }
                        }

                        return result;
                    }

                    @SuppressWarnings("rawtypes")
                    @Override
                    public List transformList(List collection) {
                        return collection;
                    }
                })
                .list()
                .get(0);
    }

    private static ProjectionList createAggregatesProjection(List<DataSourceRequest.AggregateDescriptor> aggregates) {
        ProjectionList projections = Projections.projectionList();
        for (DataSourceRequest.AggregateDescriptor aggregate : aggregates) {
            String alias = aggregate.getField() + "_" + aggregate.getAggregate();
            switch (aggregate.getAggregate()) {
                case "count":
                    projections.add(Projections.count(aggregate.getField()), alias);
                    break;
                case "sum":
                    projections.add(Projections.sum(aggregate.getField()), alias);
                    break;
                case "average":
                    projections.add(Projections.avg(aggregate.getField()), alias);
                    break;
                case "min":
                    projections.add(Projections.min(aggregate.getField()), alias);
                    break;
                case "max":
                    projections.add(Projections.max(aggregate.getField()), alias);
                    break;
            }
        }
        return projections;
    }

    private static long total(Criteria criteria) {
        long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.ROOT_ENTITY);

        return total;
    }

    private static void page(Criteria criteria, int take, int skip) {
        criteria.setMaxResults(take);
        criteria.setFirstResult(skip);
    }

    private static Map<String, Object> aggregate(List<DataSourceRequest.AggregateDescriptor> aggregates, DataSourceRequest.FilterDescriptor filters, Session session, Class<?> clazz) {
        Criteria criteria = session.createCriteria(clazz);

        filter(criteria, filters, clazz);

        return calculateAggregates(criteria, aggregates);
    }


    private static List<?> groupBy(DataSourceRequest request, List<?> items, List<DataSourceRequest.GroupDescriptor> group, Class<?> clazz, final Session session, List<SimpleExpression> parentRestrictions) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ArrayList<Map<String, Object>> result = new ArrayList<>();

        if (!items.isEmpty() && group != null && !group.isEmpty()) {
            List<List<SimpleExpression>> restrictions = new ArrayList<>();

            DataSourceRequest.GroupDescriptor descriptor = group.get(0);
            List<DataSourceRequest.AggregateDescriptor> aggregates = descriptor.getAggregates();

            final String field = descriptor.getField();

            Method accessor = new PropertyDescriptor(field, clazz).getReadMethod();

            Object groupValue = accessor.invoke(items.get(0));

            List<Object> groupItems = createGroupItem(request, group.size() > 1, clazz, session, result, aggregates, field, groupValue, parentRestrictions);

            List<SimpleExpression> groupRestriction = new ArrayList<>(parentRestrictions);
            groupRestriction.add(Restrictions.eq(field, groupValue));
            restrictions.add(groupRestriction);

            for (Object item : items) {
                Object currentValue = accessor.invoke(item);

                if (!groupValue.equals(currentValue)) {
                    groupValue = currentValue;
                    groupItems = createGroupItem(request, group.size() > 1, clazz, session, result, aggregates, field, groupValue, parentRestrictions);

                    groupRestriction = new ArrayList<>(parentRestrictions);
                    groupRestriction.add(Restrictions.eq(field, groupValue));
                    restrictions.add(groupRestriction);
                }
                groupItems.add(item);
            }

            if (group.size() > 1) {
                Integer counter = 0;
                for (Map<String, Object> g : result) {
                    g.put("items", groupBy(request, (List<?>) g.get("items"), group.subList(1, group.size()), clazz, session, restrictions.get(counter++)));
                }
            }
        }

        return result;
    }

    private static List<Object> createGroupItem(DataSourceRequest request, Boolean hasSubgroups, Class<?> clazz, final Session session, ArrayList<Map<String, Object>> result,
                                                List<DataSourceRequest.AggregateDescriptor> aggregates,
                                                final String field,
                                                Object groupValue,
                                                List<SimpleExpression> aggregateRestrictions) {

        Map<String, Object> groupItem = new HashMap<>();
        List<Object> groupItems = new ArrayList<>();

        result.add(groupItem);

        if (groupValue instanceof Date) { // format date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = formatter.format(((Date) groupValue).getTime());
            groupItem.put("value", formattedDate);
        } else {
            groupItem.put("value", groupValue);
        }

        groupItem.put("field", field);
        groupItem.put("hasSubgroups", hasSubgroups);

        if (aggregates != null && !aggregates.isEmpty()) {
            Criteria criteria = session.createCriteria(clazz);

            filter(criteria, request.getFilter(), clazz); // filter the set by the selected criteria

            SimpleExpression currentRestriction = Restrictions.eq(field, groupValue);

            if (aggregateRestrictions != null && !aggregateRestrictions.isEmpty()) {
                for (SimpleExpression simpleExpression : aggregateRestrictions) {
                    criteria.add(simpleExpression);
                }
            }
            criteria.add(currentRestriction);

            groupItem.put("aggregates", calculateAggregates(criteria, aggregates));
        } else {
            groupItem.put("aggregates", new HashMap<String, Object>());
        }
        groupItem.put("items", groupItems);
        return groupItems;
    }

    private static List<?> group(DataSourceRequest request, final Criteria criteria, final Session session, final Class<?> clazz) {
        List<?> result = new ArrayList<Object>();
        List<DataSourceRequest.GroupDescriptor> group = request.getGroup();

        if (group != null && !group.isEmpty()) {
            try {
                result = groupBy(request, criteria.list(), group, clazz, session, new ArrayList<SimpleExpression>());
            } catch (IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | HibernateException
                    | IntrospectionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    public static DataSourceResult build(DataSourceRequest request, Session session, Class<?> clazz) {
        Criteria criteria = session.createCriteria(clazz);

        filter(criteria, request.getFilter(), clazz);

        long total = total(criteria);

        sort(criteria, sortDescriptors(request));

        page(criteria, request.getTake(), request.getSkip());

        DataSourceResult result = new DataSourceResult();

        result.setTotal(total);

        List<DataSourceRequest.GroupDescriptor> groups = request.getGroup();

        if (groups != null && !groups.isEmpty()) {
            result.setData(group(request, criteria, session, clazz));
        } else {
            result.setData(criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list());
        }

        List<DataSourceRequest.AggregateDescriptor> aggregates = request.getAggregate();
        if (aggregates != null && !aggregates.isEmpty()) {
            result.setAggregates(aggregate(aggregates, request.getFilter(), session, clazz));
        }

        return result;
    }

    private static List<DataSourceRequest.SortDescriptor> sortDescriptors(DataSourceRequest request) {
        List<DataSourceRequest.SortDescriptor> sort = new ArrayList<>();

        List<DataSourceRequest.GroupDescriptor> groups = request.getGroup();
        List<DataSourceRequest.SortDescriptor> sorts = request.getSort();

        if (groups != null) {
            sort.addAll(groups);
        }

        if (sorts != null) {
            sort.addAll(sorts);
        }
        return sort;
    }
}
