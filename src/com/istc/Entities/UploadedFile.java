package com.istc.Entities;

/**
 * Created by lurui on 2017/2/2 0002.
 */

import javax.persistence.*;
import java.util.Calendar;

/**
 * Used to represent and manage uploaded files
 */
@Entity
public class UploadedFile {
    @Id
    private int fileID;
    private String fileName;
    /**文件扩展名used to save filename extension;*/
    private String nameExtention;

    private int requiredAuthority;
    private String filePath;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Calendar uploadTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_member")
    private Member fileOnwer;

    @Version
    int fileVersion;

    public UploadedFile() {
    }

    public Calendar getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Calendar uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNameExtention() {
        return nameExtention;
    }

    public void setNameExtention(String nameExtention) {
        this.nameExtention = nameExtention;
    }

    public int getRequiredAuthority() {
        return requiredAuthority;
    }

    public void setRequiredAuthority(int requiredAuthority) {
        this.requiredAuthority = requiredAuthority;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(int file_version) {
        this.fileVersion = file_version;
    }

    public Member getFileOnwer() {
        return fileOnwer;
    }

    public void setFileOnwer(Member fileOnwer) {
        this.fileOnwer = fileOnwer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UploadedFile)) return false;

        UploadedFile that = (UploadedFile) o;

        if (fileID != that.fileID) return false;
        if (requiredAuthority != that.requiredAuthority) return false;
        if (fileVersion != that.fileVersion) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (nameExtention != null ? !nameExtention.equals(that.nameExtention) : that.nameExtention != null)
            return false;
        if (filePath != null ? !filePath.equals(that.filePath) : that.filePath != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        return fileOnwer != null ? fileOnwer.equals(that.fileOnwer) : that.fileOnwer == null;

    }

    @Override
    public int hashCode() {
        int result = fileID;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (nameExtention != null ? nameExtention.hashCode() : 0);
        result = 31 * result + requiredAuthority;
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (fileOnwer != null ? fileOnwer.hashCode() : 0);
        result = 31 * result + fileVersion;
        return result;
    }
}
