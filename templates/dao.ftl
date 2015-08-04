
package com.lnet.tms.dao.${package};

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.${package}.${modelName};
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ${modelName}Dao extends CrudDao<${modelName}, ${modelIdType}> {

}