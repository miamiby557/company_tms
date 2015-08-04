package com.lnet.tms.service.scm;

import com.lnet.tms.dao.scm.ScmCarrierDao;
import com.lnet.tms.model.scm.ScmCarrier;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.service.CrudService;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.base.BaseRegionService;
import com.lnet.tms.service.sys.SysFileService;
import com.lnet.tms.utility.DateUtils;
import com.lnet.tms.utility.GetPinyinUtil;
import com.lnet.tms.web.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ScmCarrierService extends CrudService<ScmCarrier,UUID,ScmCarrierDao>{
    @Autowired
    public void setBaseDao(ScmCarrierDao dao) {
        super.setDao(dao);
    }

    @Autowired
    private BaseRegionService baseRegionService;
    @Autowired
    private SysFileService sysFileService;
    @Autowired
    private FileManager fileManager;
    @Transactional
    @Override
    public UUID create(ScmCarrier model) {
        model.setCreateDate(DateUtils.getTimestampNow());
        model.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        if (model.getCityId() != null) {
            model.setCity(baseRegionService.get(model.getCityId()).getName());
        }
        model.setNamePinyin(GetPinyinUtil.getAllCharPinYin(model.getName()));
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        return super.create(model);
    }

    @Override
    @Transactional
    public void update(ScmCarrier model) {
        model.setModifyDate(DateUtils.getTimestampNow());
        model.setModifyUserId(IdentityUtils.getCurrentUser().getUserId());
        model.setBranchId(IdentityUtils.getCurrentUser().getBranchId());
        if(model.getCityId()!=null){
            model.setCity(baseRegionService.get(model.getCityId()).getName());
        }
        model.setNamePinyin(GetPinyinUtil.getAllCharPinYin(model.getName()));
        model.setSiteId(IdentityUtils.getCurrentUser().getSiteId());
        super.update(model);
    }

    @Transactional
    public List<UUID> upload(List<MultipartFile> files,UUID carrierId) {
        List<SysFile> sysFiles = fileManager.upload(files);
        List<UUID> sysFileIds = new ArrayList<>();
        if(sysFiles!=null&&sysFiles.size()>0){
            ScmCarrier scmCarrier = getDao().get(carrierId);
            String fileIds = scmCarrier.getCertificateAffixId()!=null?scmCarrier.getCertificateAffixId():"";
            for(SysFile sysFile:sysFiles){
                fileIds+=sysFile.getFileId().toString()+",";
                sysFileIds.add(sysFile.getFileId());
            }
            scmCarrier.setCertificateAffixId(fileIds);
            getDao().update(scmCarrier);
        }
        return sysFileIds;
    }
    @Transactional
    public void deleteFile(UUID carrierId,UUID fileId){
        sysFileService.deleteById(fileId);
        ScmCarrier carrier=getDao().get(carrierId);
        String fileIds = carrier.getCertificateAffixId();
        fileIds = fileIds.replaceAll(fileId.toString()+",","");//去掉已删除的文件ID
        carrier.setCertificateAffixId(fileIds);
        getDao().update(carrier);
    }

    @Override
    public void deleteById(UUID uuid) {
        ScmCarrier carrier = getDao().get(uuid);
        if(carrier.getCertificateAffixId()!=null){
            String fileIds = carrier.getCertificateAffixId().substring(0,carrier.getCertificateAffixId().length()-1);
            for(String id:fileIds.split(",")){
                sysFileService.deleteById(UUID.fromString(id));
            }
        }
        super.deleteById(uuid);
    }

}
