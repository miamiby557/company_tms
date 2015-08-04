package com.lnet.tms.utility;

import org.apache.commons.beanutils.converters.DateTimeConverter;

import java.util.Date;

/**
 * Created by admin on 2015/5/18.
 */
public class DateConverter extends DateTimeConverter {
    @Override
    @SuppressWarnings("rawtypes")
    protected Class getDefaultType() {
        return Date.class;
    }
    public DateConverter() {
    }

    public DateConverter(Object defaultValue) {
        super(defaultValue);
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Object convertToType(Class arg0, Object arg1) throws Exception {
        if (arg1 == null) {
            return null;
        }
        String value = arg1.toString().trim();
        if (value.length() == 0) {
            return null;
        }
        return super.convertToType(arg0, arg1);
    }
}
