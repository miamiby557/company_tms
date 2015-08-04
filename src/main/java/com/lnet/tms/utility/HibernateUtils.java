package com.lnet.tms.utility;

import org.hibernate.HibernateException;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class HibernateUtils {

    private final static Logger logger = LoggerFactory.getLogger(HibernateUtils.class);

    public static void updateIncluded(ClassMetadata metadata, Object source, Object target, String... includedProperties) {
        if (source == null) return;

        for (String property : includedProperties) {

            try {
                Object value = metadata.getPropertyValue(source, property);
                metadata.setPropertyValue(target, property, value);
            } catch (HibernateException e) {
                e.printStackTrace();
                logger.error("updateIncluded error", e);
            }
        }
    }

    public static void updateExcluded(ClassMetadata metadata, Object source, Object target, String... excludedProperties) {
        if (source == null) return;

        List<?> filters = Arrays.asList(excludedProperties);

        for (String property : metadata.getPropertyNames()) {
            if (filters.contains(property) || property.equals("class") || property.equals(metadata.getIdentifierPropertyName()))
                continue;

            try {
                Object value = metadata.getPropertyValue(source, property);
                metadata.setPropertyValue(target, property, value);
            } catch (HibernateException e) {
                e.printStackTrace();
                logger.error("updateExcluded error", e);
            }
        }
    }
}
