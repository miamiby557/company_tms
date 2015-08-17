package com.lnet.tms.service.sys;

import com.lnet.tms.dao.base.BaseRegionDao;
import com.lnet.tms.dao.sys.SysOrganizationDao;
import com.lnet.tms.model.sys.SysOrganization;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.utility.GetPinyinUtil;
import com.lnet.tms.utility.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SysOrganizationService extends CrudService<SysOrganization, UUID, SysOrganizationDao> {

    @Autowired
    public void setBaseDao(SysOrganizationDao dao) {
        super.setDao(dao);
    }
    @Autowired
    private BaseRegionDao baseRegionDao;
    @Override
    public UUID create(SysOrganization model) {
        model.setNamePinyin(GetPinyinUtil.getAllCharPinYin(model.getName()));
    if(!StringUtils.isEmpty(model.getCityId())){
        model.setCity(baseRegionDao.get(model.getCityId()).getName());
    }
    return super.create(model);
}

    @Override
    public void update(SysOrganization model) {
        model.setNamePinyin(GetPinyinUtil.getAllCharPinYin(model.getName()));
        if(!StringUtils.isEmpty(model.getCityId())){
            model.setCity(baseRegionDao.get(model.getCityId()).getName());
        }
        super.update(model);
    }

    @Transactional
    public List<Map<String, Object>> getOrganizationTree()
    {
        List<Map<String, Object>> tree = new ArrayList<>();
        List<SysOrganization> roots = getDao().getAllByField("SUPERIOR_ORGANIZATION_ID", null);
        for (SysOrganization org: roots)
        {

        }
        return tree;
    }

    private void BuildTree(List<Map<String, Object>> tree, SysOrganization organization, int level)
    {
    }

    private Map<String, Object> toMap(SysOrganization organization)
    {
        Map<String, Object> map = new HashMap<>();
        return map;
    }
}
