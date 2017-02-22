package Entities;

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
}
