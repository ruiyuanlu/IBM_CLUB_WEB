package com.istc.action;

import com.istc.Entities.Entity.Member;
import com.istc.Entities.Entity.Person;
import com.istc.Entities.Entity.UploadedFile;
import com.istc.Service.EntityService.MemberService;
import com.istc.Service.EntityService.UploadedFileService;
import com.istc.Utilities.FileUtils;
import com.istc.Validation.HomeWorkCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by lurui on 2017/2/27 0027.
 */
@Controller("homeWorkUploadAction")
@Scope("prototype")
@AllowedMethods({"fileUpload"})
public class FileUploadAndDownloadAction extends ActionSupport implements SessionAware{

    private static final String loginKey = "member";

    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File upload;
    //提交过来的file的名字
    private String uploadFileName;

    //提交过来的file的MIME类型, struts 会自动注入这个属性
    private String uploadContentType;
    private String extend;
    //文件提交者的ID
    private String ownerID;

    private Map<String, Object> sessoin;
    private InputStream inputStream;

    //工具
    private FileUtils fileUtil;

    @Resource(name = "uploadedFileService")
    private UploadedFileService uploadedFileService;
    @Resource(name = "memberService")
    private MemberService memberService;

    //下载配置
    private InputStream downloadStream;
    private int downloadFileID;
    private String downloadFileName;
    private File download;

    public FileUploadAndDownloadAction() {
        System.out.println("进入上传类的构造器");
        fileUtil = FileUtils.getInstance();
    }

    @Action(value = "fileUpload",results = {
            @Result(name = SUCCESS, location = "jsp/fileUploadSuccess.jsp" ),
            @Result(name = INPUT, location  = "jsp/fileUploadFail.jsp"),
        },
        interceptorRefs = {@InterceptorRef("fileUploadStack")}
    )
    public String fileUpload(){
        System.out.println("进入fileUpload");
        if (validateFileUpload().equals(INPUT)) return INPUT;
        try {
            inputStream = new FileInputStream(upload);
        } catch (FileNotFoundException e) {
            addFieldError("uploadError", "上传的文件不存在，请检查路径是否正确");
            return INPUT;
        }
        String currentTime = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_SSS").format(Calendar.getInstance().getTime());//获取当前时间
        extend = uploadFileName.substring(uploadFileName.lastIndexOf('.') + 1);
        String fileSaveName= currentTime + "-" + uploadFileName + "." + extend;//存储名 = 时间戳 + 文件名 + 扩展名
        //上传
        String rootPath = ServletActionContext.getServletContext().getRealPath("/uploadFiles/homeWorks");//获取绝对路径
        //检查并创建文件夹
        File dir = new File(rootPath);
        if(!dir.exists())dir.mkdirs();
        File targetFile = new File(rootPath+ '/' , fileSaveName);//创建一个空文件
        //将文件从暂存区复制到真正存储的区域
        fileUtil.copy(upload, targetFile);
        //向数据库中添加记录
        ownerID = ((Person)sessoin.get(loginKey)).getID();
        Member owner = memberService.get(ownerID);
        uploadedFileService.addFile(targetFile, uploadFileName, owner);

        InputStream servletInputStream = null;
        try {
            servletInputStream = ServletActionContext.getRequest().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(servletInputStream != null)servletInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileUtil.delete(upload);//删除缓存
        return SUCCESS;
    }

    @Action(value = "fileDownload", results = {@Result(name = SUCCESS, type = "stream",
            params = {
                "inputName", "downloadStream",
                "bufferSize", "4096",
                "contentDisposition", "attachment;filename=\"${downloadFileName}\";charset=UTF-8",
                "contentCharSet","UTF-8"}
    ),
        @Result(name = INPUT, location = "jsp/fileDownloadFail.jsp")
    })
    public String fileDownload(){
        UploadedFile file = uploadedFileService.getUploadedFileById(downloadFileID);
        if(file == null){
            addActionError("您要下载的文件不存在");
            return INPUT;
        }
        download = file.createFile();
        downloadFileName = file.getFileRealName();
        if(download == null || !download.exists() || !download.isFile()){
            addActionError("您要下载的文件不存在");
            return INPUT;
        }
        try {
            downloadStream = new FileInputStream(download);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String validateFileUpload(){
        System.out.println("进入fileUpload 检查");
        HomeWorkCheck check = HomeWorkCheck.getInstance();
        if(!check.isFileExsits(upload)){
            System.out.println("isFileExsits 检查");
            addFieldError("uploadError", "请选择要上传的文件!");
            return INPUT;
        }else if(!check.isFileLengthOK(upload)){
            System.out.println("isFileLengthOK 检查");

            addFieldError("uploadError", "文件过大, 无法上传!");
            return INPUT;
        }
        return SUCCESS;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.sessoin = map;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getDownloadStream() {
        return downloadStream;
    }

    public void setDownloadStream(InputStream downloadStream) {
        this.downloadStream = downloadStream;
    }

    public int getDownloadFileID() {
        return downloadFileID;
    }

    public void setDownloadFileID(int downloadFileID) {
        this.downloadFileID = downloadFileID;
    }

    public String getDownloadFileName() {
        try {
            return java.net.URLEncoder.encode(this.downloadFileName, "UTF-8").replaceAll("\\+", " ");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public File getDownload() {
        return download;
    }

    public void setDownload(File download) {
        this.download = download;
    }
}