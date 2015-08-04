package com.lnet.tms.web.${package};

import com.lnet.tms.model.${package}.${modelName};
import com.lnet.tms.service.${package}.${modelName}Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lnet.tms.web.CrudController;
import com.lnet.tms.utility.HibernateAwareObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/${camelModelName}")
public class ${modelName}Controller extends CrudController<${modelName}, ${modelIdType}, ${modelName}Service> {

    @Autowired
    private HibernateAwareObjectMapper mapper;
    @Autowired
    public void setBaseService(${modelName}Service service) {
        super.setService(service);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
      return "${package}/${camelModelName}/index";
    }

    @RequestMapping("/create")
    public String create() {
     return "${package}/${camelModelName}/create";
    }

    @RequestMapping(value="/modify/{id}")
    public String modify(@PathVariable("id") ${modelIdType} id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String ${modelName}Json =mapper.writeValueAsString(service.get(id));
        model.addAttribute("${modelName}Json",${modelName}Json);
        return "${package}/${camelModelName}/modify";
    }

    @RequestMapping(value="/detail/{id}")
        public String detail(@PathVariable("id") ${modelIdType} id, ModelMap model) throws JsonProcessingException {
        model.addAttribute(service.get(id));
        String ${modelName}Json =mapper.writeValueAsString(service.get(id));
        model.addAttribute("${modelName}Json",${modelName}Json);
        return "${package}/${camelModelName}/modify";
    }
}