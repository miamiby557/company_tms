package com.lnet.tms.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import java.text.SimpleDateFormat;
public class RestObjectMapper extends ObjectMapper {

    public RestObjectMapper() {
        registerModule(new Hibernate4Module());
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
        configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
//        configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

}
