
package com.lnet.tms.dao.dispatch;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.dispatch.DispatchVehicle;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DispatchVehicleDao extends CrudDao<DispatchVehicle, UUID> {

}