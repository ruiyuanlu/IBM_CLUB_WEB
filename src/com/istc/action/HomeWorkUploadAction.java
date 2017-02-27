package com.istc.action;

import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityService.IntervieweeService;
import com.istc.Service.EntityService.MemberService;
import com.istc.Service.EntityService.UploadedFileService;
import com.istc.Validation.HomeWorkCheck;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by lurui on 2017/2/27 0027.
 */
@Controller("homeWorkUploadAction")
@Scope("prototype")
@AllowedMethods("homeWorkUpload")
public class HomeWorkUploadAction extends ActionSupport{

    private static final Integer buffSize = 2 << 10;//文件上传缓冲区大小: 2M

    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
    //提交过来的file的名字
    private String fileName;
    //提交过来的file的MIME类型, struts 会自动注入这个属性
    private String fileContentType;
    private String extend;
    //文件提交者的ID
    private String ownerID;

    @Resource(name = "uploadedFileService")
    private UploadedFileService uploadedFileService;
    @Resource(name = "memberService")
    private MemberService memberService;

    @Action(value = "homeWorkUpload",results = {
            @Result(name = SUCCESS, location = "jsp/fileupload.jsp"),
            @Result(name = INPUT, location = "fileupload", type = "redirect"),
            @Result(name = "invalid.token", location = "fileupload", type = "redirect")
        },
            interceptorRefs = {
                @InterceptorRef("tokenSession"),@InterceptorRef("defaultStack")
            }
    )
    public String homeWorkUpload(){
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            addFieldError("uploadError", "上传的文件不存在，请检查路径是否正确");
            return INPUT;
        }
        String rootPath = ServletActionContext.getServletContext().getRealPath("/uploadFiles/homeWorks");//获取绝对路径
        //检查并创建文件夹
        File dir = new File(rootPath);
        if(!dir.exists())dir.mkdir();
        String currentTime = new SimpleDateFormat("YYYY_MM_RR_hh_mm_ss_SSS").format(Calendar.getInstance());//获取当前时间
        String fileSaveName= currentTime + "-" + fileName + "." + extend;//存储名 = 时间戳 + 文件名 + 扩展名
        //上传
        File targetFile = new File(rootPath, fileSaveName);//创建一个空文件
        OutputStream os = null;
        try {
            os = new FileOutputStream(targetFile, false);
        } catch (FileNotFoundException e) {
            addFieldError("uploadError", "服务器创建文件异常, 请联系技术人员进行解决");
            try {
                is.close();
            } catch (IOException e1) {
                System.out.println("HomeWorkUploadAction中文件输入流关闭异常");
            }
        }
        //向服务器写入数据
        try {
            byte[] buff = new byte[buffSize];
            int i = is.read(buff, 0, buffSize);
            while( i != -1){
                os.write(buff, 0, i);
                i = is.read(buff, 0, buffSize);
            }
        } catch (IOException e) {
            addFieldError("uploadError", "文件上传失败!");
        }
        try {
            is.close();
            os.close();
        } catch (IOException e) {
            addFieldError("uploadError", "上传异常");
            System.out.println("上传文件关闭输入输出流时异常");
            return INPUT;
        }
        //向数据库中添加记录
        Member owner = memberService.get(ownerID);
        uploadedFileService.addFile(file, owner);
        return SUCCESS;
    }

    public void validateHomeWorkUpload(){
        HomeWorkCheck check = HomeWorkCheck.getInstance();
        if(!check.isFileExsits(file)){
            addFieldError("uploadError", "请选择要上传的文件!");
            return;
        }else if(!check.isFileLengthOK(file)){
            addFieldError("uploadError", "文件过大, 无法上传!");
            return;
        }else if((extend = check.getFileExtend(file)) == null){
            addFieldError("uploadError", "不支持上传所选文件格式的文件!");
            return;
        }
    }
}