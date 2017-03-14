//package com.istc.action;
//
//import com.istc.Entities.Entity.HomeWork;
//import com.istc.Entities.Entity.Member;
//import com.istc.Service.EntityService.MemberService;
//import com.istc.Service.EntityService.UploadedFileService;
//import com.istc.Validation.HomeWorkCheck;
//import com.opensymphony.xwork2.ActionSupport;
//import com.sun.istack.internal.Nullable;
//import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.convention.annotation.*;
//import org.apache.struts2.dispatcher.SessionMap;
//import org.apache.struts2.interceptor.SessionAware;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
//import javax.annotation.Resource;
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Administrator on 2017/3/10 0010.
// */
//@Controller("fileAction")
//@Scope("prototype")
//@AllowedMethods({"fileUpload","homeWorkDownload","homeWorkDelete", "fileUpload", "fileDownload", "fileDelete"})
//@ParentPackage("ajax")
//public class FileAction extends ActionSupport implements SessionAware {
//    private static final long serialVersionUID = 1L;
//    private static final Integer buffSize = 2 << 10;//文件上传缓冲区大小: 2M
//
//
//    private int fileID;
//    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
//    private File file;
//    //提交过来的file的名字
//    private String fileName;
//    private String extend;
//
//    private Map<String, Object> session;
//
//    //文件提交者的ID
//    private String ownerID;
//    private InputStream inputStream;
//    @Resource(name = "uploadedFileService")
//    private UploadedFileService uploadedFileService;
//    @Resource(name = "memberService")
//    private MemberService memberService;
//
//    public FileAction(){}
//
//    public String homeWorkDisplay(){
//        return ERROR;
//    }
//
//    @Action(value = "fileUpload",results = {
//            @Result(name = SUCCESS, location = "jsp/fileupload.jsp"),
//            @Result(name = INPUT, location = "fileupload", type = "redirect"),
//            @Result(name = "invalid.token", location = "fileupload", type = "redirect")
//    },
//            interceptorRefs = {
//                    @InterceptorRef("tokenSession"),@InterceptorRef("defaultStack")
//            }
//    )
//    public String homeWorkUpload(){
//        if(isAuthority("/Files/homeWorks")>0)return upload("/Files/homeWorks");
//        return ERROR;
//    }
//
//    @Action(value = "homeworkDownload",
//            results = {
//            @Result(name = SUCCESS,
//                    type = "stream",
//                    params = {
////                        "contentType", "${type}",
//                            "bufferSize", "1024",
//                            "contentDisposition", "attachment;filename=\"${fileName}\";charset=UTF-8",
//                            "contentCharSet","UTF-8"}),
//            @Result(name = INPUT,
//                    location = "errorPages/error.jsp")})
//    public String homeWorkDownload(){
//        if (isAuthority("/Files/homeWorks")>0)return download("/Files/homeWorks");
//        return ERROR;
//    }
//
//    @Action(value = "homeworkDelete",
//            results = {
//                    @Result(name = SUCCESS,location = "jsp/filedownload.jsp"),
//                    @Result(name = INPUT,location = "errorPages/error.jsp")})
//    public String homeWorkDelete(){
//        if (isAuthority("/Files/homeWorks")>0)return delete("/Files/homeWorks");
//        return ERROR;
//    }
//
//    public String sharedFileDisplay(){
//        return ERROR;
//    }
//
//    @Action(value = "sharedFileUpload",results = {
//            @Result(name = SUCCESS, location = "jsp/fileupload.jsp"),
//            @Result(name = INPUT, location = "fileupload", type = "redirect"),
//            @Result(name = "invalid.token", location = "fileupload", type = "redirect")
//    },
//            interceptorRefs = {
//                    @InterceptorRef("tokenSession"),@InterceptorRef("defaultStack")
//            }
//    )
//    public String sharedFileUpload(){
//        if (isAuthority("/Files/sharedFiles")>1)return upload("/Files/sharedFiles");
//        return ERROR;
//    }
//    @Action(value = "sharedFileDownload",
//            results = {
//                    @Result(name = SUCCESS,
//                            type = "stream",
//                            params = {
////                        "contentType", "${type}",
//                                    "bufferSize", "1024",
//                                    "contentDisposition", "attachment;filename=\"${fileName}\";charset=UTF-8",
//                                    "contentCharSet","UTF-8"}),
//                    @Result(name = INPUT, location = "errorPages/error.jsp")})
//    public String sharedFileDownload(){
//        if (isAuthority("/Files/sharedFiles")>0)return download("/Files/sharedFiles");
//        return ERROR;
//    }
//
//    @Action(value = "sharedFileDelete",
//            results = {
//                    @Result(name = SUCCESS,location = "jsp/filedownload.jsp"),
//                    @Result(name = INPUT,location = "errorPages/error.jsp")})
//    public String sharedFileDelete(){
//        if (isAuthority("/Files/sharedFiles")>1)return delete("/Files/sharedFiles");
//        return ERROR;
//    }
//
//    private int isAuthority(String path){
//        Map<String, Object> sessionMap = session;
//        Member member = (Member) sessionMap.get("member");
//        if (path.equals("/Files/sharedFiles")){
//            //File file = get(fileName);
//            if (member.getAuthority().compareTo(2)>0)return 2;
//            else if (member.getAuthority().equals(new Integer(1)))return 1;
//            addFieldError("authorityError","AuthorityNotEnough");
//            return 0;
//        }
//        if (path.equals("/Files/homeWorks")){
//            if(member.getAuthority().compareTo(2)>0)return 2;
//            HomeWork homeWork = new HomeWork();
//            homeWork.getHomeWorkID().setHomeWorkSubmitter(member);
//            if(member.equals(homeWork.getHomeWorkSubmitter()))return 1;
//            addFieldError("authorityError","AuthorityNotEnough");
//            return 0;
//        }
//        addFieldError("authorityError","AuthorityError");
//        return -1;
//    }
//
//    public List<File> fileListGet(){
//        List<File> list = null;
//        return list;
//    }
//
//    private String upload(String path){
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            addFieldError("uploadError", "上传的文件不存在，请检查路径是否正确");
//            return INPUT;
//        }
//        String rootPath = ServletActionContext.getServletContext().getRealPath(path);//获取绝对路径
//        //检查并创建文件夹
//        File dir = new File(rootPath);
//        if(!dir.exists())dir.mkdir();
//        String currentTime = new SimpleDateFormat("YYYY_MM_RR_hh_mm_ss_SSS").format(Calendar.getInstance());//获取当前时间
//        String fileSaveName= currentTime + "-" + fileName + "." + extend;//存储名 = 时间戳 + 文件名 + 扩展名
//        //上传
//        File targetFile = new File(rootPath, fileSaveName);//创建一个空文件
//        OutputStream os = null;
//        try {
//            os = new FileOutputStream(targetFile, false);
//        } catch (FileNotFoundException e) {
//            addFieldError("uploadError", "服务器创建文件异常, 请联系技术人员进行解决");
//            try {
//                inputStream.close();
//            } catch (IOException e1) {
//                System.out.println("HomeWorkUploadAction中文件输入流关闭异常");
//            }
//        }
//        //向服务器写入数据
//        try {
//            byte[] buff = new byte[buffSize];
//            int i = inputStream.read(buff, 0, buffSize);
//            while( i != -1){
//                if (os != null) os.write(buff, 0, i);
//                i = inputStream.read(buff, 0, buffSize);
//            }
//        } catch (IOException e) {
//            addFieldError("uploadError", "文件上传失败!");
//        }
//        try {
//            inputStream.close();
//            if (os != null) os.close();
//        } catch (IOException e) {
//            addFieldError("uploadError", "上传异常");
//            System.out.println("上传文件关闭输入输出流时异常");
//            return INPUT;
//        }
//        //向数据库中添加记录
//        Member owner = memberService.get(ownerID);
//        uploadedFileService.addFile(file, owner);
//        return SUCCESS;
//    }
//
//    public void validateUpload(){
//        HomeWorkCheck check = HomeWorkCheck.getInstance();
//        if(!check.isFileExsits(file)){
//            addFieldError("uploadError", "请选择要上传的文件!");
//        }else if(!check.isFileLengthOK(file)){
//            addFieldError("uploadError", "文件过大, 无法上传!");
//        }else if((extend = check.getFileExtend(file)) == null){
//            addFieldError("uploadError", "不支持上传所选文件格式的文件!");
//        }
//    }
//
//    private String download(String path){
//        try {
//            file = this.isFile(path);
//            if (file != null) {
//                inputStream = new FileInputStream(file);
//            }
//        }catch (NullPointerException e){
//            System.out.println("此处下载动作发现空指针，请检查文件名是否匹配或者文件是否存在！");
//            addFieldError("downloadError","下载文件为空指针");
//            return INPUT;
//        }catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("下载无法找到文件，请检查文件是否存在！");
//            addFieldError("downloadError","下载文件不存在");
//            return INPUT;
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("IO异常，下载动作无法完成！");
//            addFieldError("download","数据传输异常");
//            return INPUT;
//        }
//        return SUCCESS;
//    }
//
//    private String delete(String path){
//        try {
//            file = this.isFile(path);
//            if (file != null) file.delete();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("IO异常，删除动作无法完成！");
//            return INPUT;
//        }
//        return SUCCESS;
//    }
//    @Nullable
//    private File isFile(String path) throws IOException{
//        String rootPath = ServletActionContext.getServletContext().getRealPath(path);
//        String targetPath = rootPath+"\\"+fileName;
//        System.out.println("路径创建"+targetPath);
//        File f = new File(targetPath);
//        if(!f.exists()){
//            System.out.println("文件不存在");
//            addFieldError("error","The file is not found!");
//            return null;
//        }
//        return f;
//    }
//
//    public String getFileRealName() {
//        try {
//            //在之前的action过程中编码已经有所变化
//            // @contentDisposition "attachment;filename=\"${fileName}\";包含中文的fileName需要重新转回URL编码再返回才能
//            // 得到识别，不会出现显示'?'无法识别的情况
//            return java.net.URLEncoder.encode(this.fileName, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            return this.fileName;
//        }
//    }
//    public InputStream getInputStream() {
//        return inputStream;
//    }
//    public void setInputStream(InputStream inputStream) {
//        this.inputStream = inputStream;
//    }
//
//    public void setFileRealName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    @Override
//    public void setSession(Map<String, Object> map) {
//        this.session = map;
//    }
//
//    public Map<String, Object> getSession() {
//        return session;
//    }
//
//    public int getFileID() {
//        return fileID;
//    }
//
//    public void setFileID(int fileID) {
//        this.fileID = fileID;
//    }
//}
