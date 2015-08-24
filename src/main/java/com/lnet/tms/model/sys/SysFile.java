package com.lnet.tms.model.sys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "SYS_FILE", schema = "LNET_TMS", catalog = "")
public class SysFile {
    private UUID fileId;
    private String fileName;
    private String storedPath;
    private String extension;
    private Boolean isImage;
    private String contentType;
    private UUID createUserId;
    private Timestamp createDate;
    private String fileCode;
    private int uploadType;

    @Id
    @Column(name = "FILE_ID", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    public UUID getFileId() {
        return fileId;
    }

    public void setFileId(UUID fileId) {
        this.fileId = fileId;
    }


    @Basic
    @Column(name = "FILE_NAME", nullable = false, insertable = true, updatable = true)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "STORED_PATH", nullable = true, insertable = true, updatable = true)
    public String getStoredPath() {
        return storedPath;
    }

    public void setStoredPath(String storedPath) {
        this.storedPath = storedPath;
    }

    @Basic
    @Column(name = "EXTENSION", nullable = true, insertable = true, updatable = true)
    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Basic
    @Column(name = "IS_IMAGE", nullable = true, insertable = true, updatable = true, precision = 0)
    public Boolean getIsImage() {
        return isImage;
    }

    public void setIsImage(Boolean isImage) {
        this.isImage = isImage;
    }

    @Basic
    @Column(name = "CONTENT_TYPE", nullable = true, insertable = true, updatable = true)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "CREATE_USER_ID", nullable = true, insertable = true, updatable = true)
    public UUID getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(UUID createUserId) {
        this.createUserId = createUserId;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "FILE_CODE", nullable = true, insertable = true, updatable = true)

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    @Basic
    @Column(name = "UPLOAD_TYPE", nullable = true, insertable = true, updatable = true)
    public int getUploadType() {
        return uploadType;
    }

    public void setUploadType(int uploadType) {
        this.uploadType = uploadType;
    }
}
