package com.lnet.tms.service.${package};

import com.lnet.tms.dao.${package}.${modelName}Dao;
import com.lnet.tms.model.${package}.${modelName};
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ${modelName}Service extends CrudService<${modelName}, ${modelIdType}, ${modelName}Dao> {

    @Autowired
    public void setBaseDao(${modelName}Dao dao) {
        super.setDao(dao);
    }

    // your service methods
}
