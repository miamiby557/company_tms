package com.lnet.tms.web;

import com.lnet.tms.common.JsonResult;
import com.lnet.tms.model.crm.CrmClient;
import com.lnet.tms.model.sys.SysUser;
import com.lnet.tms.service.CrudService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public abstract class CrudController<T, ID extends Serializable, S extends CrudService<T, ID, ?>> extends BaseController<T, ID, S> {


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult create(@RequestBody T model) {
        try {
            service.create(model);
            return JsonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/batchCreate", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult batchCreate(@RequestBody List<T> models) {
        try {
            service.create(models);
            return JsonResult.success(models);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult update(@RequestBody T model) {
        try {
            service.update(model);
            return JsonResult.success(model);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/batchUpdate", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult batchUpdate(@RequestBody List<T> models) {
        try {
            service.update(models);
            return JsonResult.success(models);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult delete(@RequestBody T model) {
        try {
            service.delete(model);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult batchDelete(@RequestBody List<T> models) {
        try {
            service.delete(models);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult deleteById(@RequestBody ID id) {
        try {
            service.deleteById(id);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
    @RequestMapping(value = "/batchDeleteById", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult batchDeleteById(@RequestBody List<ID> ids) {
        try {
            service.deleteById(ids);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }


    @RequestMapping(value = "/feeAuditPayable", method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult feeAuditPayable(@RequestBody List<ID> ids) {
        try {
            service.feeAuditPayable(ids);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/feeAuditReceivable",method = RequestMethod.POST)
    public
    @ResponseBody
    JsonResult feeAuditReceivable(@RequestBody List<ID> ids) {
        try {
            service.feeAuditReceivable(ids);
            return JsonResult.success();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.error(e.getMessage());
        }
    }
}
