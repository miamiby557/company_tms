package com.lnet.tms.service.crm;

import com.lnet.tms.dao.crm.CrmClientDao;
import com.lnet.tms.model.crm.CrmClient;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.base.BaseRegionViewService;
import com.lnet.tms.service.sys.SysFileService;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.GetPinyinUtil;
import com.lnet.tms.web.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Created by admin on 2015/5/14.
 */
@Service
public class CrmClientService extends CrudService<CrmClient, UUID,CrmClientDao> {
    @Autowired
    public void setBaseDao(CrmClientDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private FileManager fileManager;
    @Autowired
    private SysFileService sysFileService;
    @Autowired
    private BaseRegionViewService baseRegionViewService;
    @Override
    public UUID create(CrmClient model) {
        /*model.setCreateDate(DateUtils.getTimestampNow());*/
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        if(model.getCityId()!=null){
            model.setCity(baseRegionViewService.get(model.getCityId()).getName());
        }
        model.setNamePinyin(GetPinyinUtil.getAllCharPinYin(model.getName()));
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        return super.create(model);
    }

    @Override
    public void deleteById(UUID uuid) {
        CrmClient client = getDao().get(uuid);
        sysFileService.deleteById(client.getContractDocumentId());
        super.deleteById(uuid);
    }

    @Override
    public void update(CrmClient model) {
        /*model.setModifyDate(DateUtils.getTimestampNow());
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());*/
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        if(model.getCityId()!=null){
            model.setCity(baseRegionViewService.get(model.getCityId()).getName());
        }
        model.setNamePinyin(GetPinyinUtil.getAllCharPinYin(model.getName()));
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        super.update(model);
    }

    @Transactional
    public UUID upload(List<MultipartFile> files, UUID clientId, Model model) throws IOException {
        SysFile sysFile = fileManager.upload(files.get(0));
        CrmClient crmClient = getDao().get(clientId);
        if(crmClient.getContractDocumentId()!=null){
            sysFileService.deleteById(crmClient.getContractDocumentId());
        }
        crmClient.setContractDocumentId(sysFile.getFileId());
        getDao().update(crmClient);
        return sysFile.getFileId();
    }
}
