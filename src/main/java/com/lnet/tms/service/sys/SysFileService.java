package com.lnet.tms.service.sys;

import com.lnet.tms.dao.sys.SysFileDao;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Service
public class SysFileService extends CrudService<SysFile, UUID, SysFileDao> {

    @Autowired
    public void setBaseDao(SysFileDao dao) {
        super.setDao(dao);
    }

    @Transactional
    @Override
    public void deleteById(UUID fileId){
        if(fileId!=null) {
            SysFile sysFile = getDao().get(fileId);
            if (sysFile != null) {
                File file = new File(sysFile.getStoredPath());
                if (file.exists()) {
                    file.delete();
                }
                super.delete(sysFile);
            }
        }
    }

    public File getFileById(UUID fileId){
        SysFile sysFile = getDao().get(fileId);
        return new File(sysFile.getStoredPath());
    }
    @Transactional
    public List<Map<String, Object>> getFileList(UUID...uuids){
        List<SysFile> list = getDao().get(uuids);
        List<Map<String, Object>> result = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for(SysFile sysFile:list){
                Map<String, Object> map = new HashMap<>();
                map.put("name",sysFile.getFileName());
                map.put("fileId",sysFile.getFileId());
                result.add(map);
            }
        }
        return result;
    }

}
