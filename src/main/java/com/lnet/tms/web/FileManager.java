package com.lnet.tms.web;


import com.lnet.tms.model.otd.OtdOrderReceiptPic;
import com.lnet.tms.model.otd.OtdTransportOrder;
import com.lnet.tms.model.sys.SysFile;
import com.lnet.tms.service.IdentityUtils;
import com.lnet.tms.service.otd.OtdOrderReceiptPicService;
import com.lnet.tms.service.otd.OtdTransportOrderService;
import com.lnet.tms.service.sys.SysFileService;
import com.lnet.tms.utility.DateUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import sun.org.mozilla.javascript.internal.regexp.SubString;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    @Autowired
    private OtdTransportOrderService otdTransportOrderService;
    @Autowired
    private OtdOrderReceiptPicService otdOrderReceiptPicService;

    private String rootPath;
    private String reportPath;
    private String picPath;

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
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
    private SysFile reportSaveToDisk(MultipartFile file,String fileCode) throws IOException {
        SysFile sysFile = new SysFile();
        String filename = file.getOriginalFilename();

        Path storePath = Paths.get(reportPath,filename);
        File destFile = new File(storePath.toUri());

        sysFile.setFileId(UUID.randomUUID());
        sysFile.setContentType(file.getContentType());
        sysFile.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        sysFile.setCreateDate(DateUtils.getTimestampNow());
        sysFile.setFileName(filename);
        sysFile.setIsImage(isImage(file.getContentType()));
        String extension = FilenameUtils.getExtension(sysFile.getFileName());
        sysFile.setExtension(extension);
        sysFile.setFileCode(fileCode);
        sysFile.setUploadType(1);

        if (!destFile.getParentFile().exists()) destFile.getParentFile().mkdirs();
        file.transferTo(destFile);
        sysFile.setStoredPath(storePath.toString());
        return sysFile;
    }

    private OtdOrderReceiptPic picSaveToDisk(String fileName){
        OtdOrderReceiptPic otdOrderReceiptPic=new OtdOrderReceiptPic();
        String picName=fileName.substring(fileName.indexOf("-")+1);
        OtdTransportOrder otdTransportOrder= otdTransportOrderService.getByField("clientOrderNumber",picName);

        otdOrderReceiptPic.setOrderReceiptPicId(UUID.randomUUID());
        otdOrderReceiptPic.setTransportOrderId(otdTransportOrder.getTransportOrderId());
        otdOrderReceiptPic.setPicName(fileName + ".jpg");
        otdOrderReceiptPic.setPicPath(picPath+"/" + fileName + ".jpg");
        //otdOrderReceiptPic.setCreateUserId(IdentityUtils.getCurrentUser().getUserId());
        otdOrderReceiptPic.setCreateDate(DateUtils.getTimestampNow());

        return otdOrderReceiptPic;
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
    public SysFile ReportUpload(MultipartFile file,String fileCode) throws IOException {
        SysFile sysFile= reportSaveToDisk(file,fileCode);
        sysFileService.create(sysFile);
        return sysFile;
    }
    public File PicUpload(String picName){
        File file=null;
        OtdOrderReceiptPic otdOrderReceiptPic=picSaveToDisk(picName);
        otdOrderReceiptPicService.create(otdOrderReceiptPic);
        file=new File(otdOrderReceiptPic.getPicPath());
        return file;
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
