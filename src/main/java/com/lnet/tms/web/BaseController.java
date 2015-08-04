package com.lnet.tms.web;

import com.lnet.tms.common.DataSourceRequest;
import com.lnet.tms.common.DataSourceResult;
import com.lnet.tms.common.JsonResult;
import com.lnet.tms.service.BaseService;
import com.lnet.tms.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class BaseController<T, ID extends Serializable, S extends BaseService<T, ID, ?>> {

    protected final Class<T> persistentClass;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected S service;

    protected void setService(S service) {
        this.service = service;
    }

    public BaseController() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @RequestMapping(value = "/getDataSource", method = RequestMethod.POST)
    public
    @ResponseBody
    DataSourceResult getDataSource(@RequestBody DataSourceRequest request) {
        return service.getDataSource(request);
    }

    @RequestMapping("/{id}")
    public
    @ResponseBody
    T getById(@PathVariable ID id) {
        return service.get(id);
    }

    @RequestMapping("/getListItems")
    public
    @ResponseBody
    List<Map<String, Object>> getListItems(String textField, String valueField) {
        return service.getListItems(textField, valueField);
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public
    @ResponseBody
    void export(String fileName, String base64, String contentType, HttpServletResponse response) throws IOException {

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType(contentType);

         byte[] data = DatatypeConverter.parseBase64Binary(base64);

        response.setContentLength(data.length);
        response.getOutputStream().write(data);
        response.flushBuffer();
    }

}
