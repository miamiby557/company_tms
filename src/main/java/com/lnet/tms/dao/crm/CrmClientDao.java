package com.lnet.tms.dao.crm;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.crm.CrmClient;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ParameterMode;
import java.sql.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Repository
public class CrmClientDao extends CrudDao<CrmClient, UUID> {
    @Transactional
    public List testProc(){
        /*try {
            CallableStatement statement= sessionFactory.openStatelessSession().connection().prepareCall("{call testproc(?)}");
            statement.setInt(0,0);
            statement.registerOutParameter(0, Types.INTEGER);
            statement.execute();
            Integer r = statement.getInt(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        Connection conn = null;
        try {
            CallableStatement callableStatement  =getSession().disconnect().prepareCall("{call test_proc()}");
            //CallableStatement callableStatement = conn.prepareCall("{call test_proc()}");
           /* callableStatement.setString(1,"1");
            callableStatement.registerOutParameter(0,Types.INTEGER);*/
            callableStatement.execute();
            callableStatement.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getSession().createSQLQuery("{call test_proc()}").list();
        return null;
    }
}
