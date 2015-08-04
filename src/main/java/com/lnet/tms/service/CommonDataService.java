package com.lnet.tms.service;

import com.lnet.tms.dao.sys.SysListDao;
import com.lnet.tms.dao.sys.SysOrganizationDao;
import com.lnet.tms.model.sys.SysList;
import com.lnet.tms.model.sys.SysListItem;
import com.lnet.tms.model.sys.SysOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CommonDataService {
    @Autowired
    private SysListDao sysListDao;

    @Autowired
    private SysOrganizationDao sysOrganizationDao;

    public Map getSysList() {
        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        for (SysList list : sysListDao.getAll()) {
            // build items
            List<Map<String, Object>> items = new ArrayList<>();
            for(SysListItem item: list.getItems()) {
                Map<String, Object> map = new HashMap<>();
                map.put("text", item.getName());
                map.put("value", item.getValue());
                items.add(map);
            }

            result.put(list.getCode(), items);
        }

        return result;
    }

    public List getSysOrganizations()
    {
        return sysOrganizationDao.getAll();
    }

}
