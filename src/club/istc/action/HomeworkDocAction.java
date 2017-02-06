package club.istc.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import club.istc.validation.HomeworkDocCheck;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 成员管理自己作业文件的上传和下载
 */


public class HomeworkDocAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
    
	    private File file;
	    //提交过来的file的名字
	    private String fileFileName;
	    //提交过来的file的MIME类型
	    private String fileContentType;
	    
	    @Override
	    public String execute() throws Exception{
	    	try {
	    		//设置文件存储目录
		        String root = ServletActionContext.getServletContext().getRealPath("/file/homework"); 
		        InputStream is = new FileInputStream(file);
		        //如果目录不存在则自动创建
		        File homeworkdir=new File(root);
		        if (!homeworkdir.exists()) {
					homeworkdir.mkdirs();
				}
		        //设置时间戳，格式为yyyyMMddHHmmss
		        Date date = new Date();
		        SimpleDateFormat timestamp=new SimpleDateFormat("yyyyMMddHHmmss");
		        //文件格式为“yyyyMMddHHmmss-[文件名].[文件扩展名]”
		        File targetFile=new File(root, timestamp.format(date)+"-"+fileFileName);
		        OutputStream os = new FileOutputStream(targetFile);
		        String targetpath=targetFile.getPath();
		        //文件上传
		        byte[] bytes = new byte[1024];  
		        int i = is.read(bytes,0,1024);
		        while(i!=-1)  
		        {  
		            os.write(bytes,0,i);  
		            i = is.read(bytes,0,1024);  
		        }  
		        os.close();
		        is.close();
		        try {
		        	//如果上传的文件不是pdf格式，那么转换为可以在线预览的html版本
					if (!targetFile.getName().toLowerCase().endsWith(".pdf")) {
						WordOnlineConverter.canExtractImage(targetpath);
					}
				} catch (Exception e) {
					// TODO: handle exception
					addFieldError("fileerror", "创建在线预览版本失败！");
				}
		        
		        return SUCCESS;
			} 
	    	catch (FileNotFoundException e) {
				// TODO: handle exception
				addFieldError("fileerror", "文件上传失败！");
				return INPUT;
			}
	    }
	    
	    @Override
	    public void validate(){
	    	HomeworkDocCheck curfile = new HomeworkDocCheck(file);
	    	if (!curfile.isFound()) {
				addFieldError("fileerror", "您还未选择文件！");
				return;
			}
	    	if (!curfile.isFormatmatch()) {
				addFieldError("fileerror", "格式不匹配，请重新选择！");
				return;
			}
	    	if (!curfile.isLengthnotover()) {
	    		addFieldError("fileerror", "文件大小超过了5M！");
	    		return;
			}
	    }
	    
	    public File getFile(){
	        return file;
	    }

	    public void setFile(File file){
	        this.file = file;
	    }

	    public String getFileFileName(){
	        return fileFileName;
	    }

	    public void setFileFileName(String fileFileName){
	        this.fileFileName = fileFileName;
	    }

	    public String getFileContentType(){
	        return fileContentType;
	    }

	    public void setFileContentType(String fileContentType){
	        this.fileContentType = fileContentType;
	    }
	}
