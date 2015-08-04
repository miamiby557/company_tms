package com.lnet.tms.web;


import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.sys.SysFileService;
import com.lnet.tms.utility.DateUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileManager {

    @Autowired
    private SysFileService sysFileService;

    private String rootPath = "";

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    private SysFile saveToDisk(MultipartFile file) throws IOException {
        SysFile sysFile = new SysFile();
        sysFile.setFileId(UUID.randomUUID());
        sysFile.setContentType(file.getContentType());
        sysFile.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        sysFile.setCreateDate(DateUtils.getTimestampNow());
        sysFile.setFileName(file.getOriginalFilename());
        sysFile.setIsImage(isImage(file.getContentType()));
        String extension = FilenameUtils.getExtension(sysFile.getFileName());
        sysFile.setExtension(extension);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateFolder = dateFormat.format(new Date());

        String filename = sysFile.getFileId().toString() + "." + extension;
        Path storePath = Paths.get(rootPath, dateFolder, filename);

        File destFile = new File(storePath.toUri());
        if (!destFile.getParentFile().exists()) destFile.getParentFile().mkdirs();
        file.transferTo(destFile);
        sysFile.setStoredPath(storePath.toString());
        return sysFile;
    }

    public boolean isImage(String contentType) {
        String type = contentType.split("/")[0];
        return type.equals("image");
    }

    public SysFile upload(MultipartFile file) throws IOException {
        SysFile sysFile = saveToDisk(file);
        sysFileService.create(sysFile);
        return sysFile;
    }

    public List<SysFile> upload(List<MultipartFile> files) {
        List<SysFile> sysFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                if(file.getOriginalFilename()!=null&&!"".equals(file.getOriginalFilename()))
                sysFiles.add(saveToDisk(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sysFileService.create(sysFiles);
        return sysFiles;
    }

    public void remove(String file) throws IOException {
        Files.deleteIfExists(Paths.get(file));
    }

}
