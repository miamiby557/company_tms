
package com.lnet.tms.dao.rpt;

import com.lnet.tms.dao.CrudDao;
import com.lnet.tms.model.rpt.RptViewClientMonthsum;
import com.lnet.tms.model.rpt.RptViewOtdMonthsum;
import org.hibernate.sql.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RptViewClientMonthsumDao extends CrudDao<RptViewClientMonthsum, UUID> {

}