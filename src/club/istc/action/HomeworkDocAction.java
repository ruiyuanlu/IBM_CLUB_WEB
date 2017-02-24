package club.istc.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;

import club.istc.validation.HomeworkDocCheck;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 成员管理自己作业文件的上传和下载
 */
@Action(
		value="HomeworkDocAction", 
        results={ 
				@Result(name="input", location="fileupload.jsp"),
				@Result(name="success", location="fileupload", type="redirect"),
				@Result(name="invalid.token", location="fileupload", type="redirect")
        },
        interceptorRefs={ 
			    @InterceptorRef("tokenSession"),  
			    @InterceptorRef("defaultStack")  
        }
) 
public class HomeworkDocAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
    
	    private File file;
	    //提交过来的file的名字
	    private String fileFileName;
	    //提交过来的file的MIME类型
	    private String fileContentType;
	    
	    private String extend;
	    
	    @Override
	    public String execute(){
	    	String targetpath="";
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
		        SimpleDateFormat timestamp=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		        //文件格式为“yyyyMMddHHmmss-[文件名].[文件扩展名]”
		        File targetFile=new File(root, timestamp.format(date)+"-"+fileFileName+"."+extend);
		        OutputStream os = new FileOutputStream(targetFile);
		        targetpath=targetFile.getPath();
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
				System.out.println(targetpath);
				if (!extend.equals("pdf")) {
					WordOnlineConverter.canExtractImage(targetpath,extend);
				}
		        return SUCCESS;
			} 
	    	catch (FileNotFoundException e) {
				// TODO: handle exception
				addFieldError("fileerror", "文件上传失败！");
				return INPUT;
			}
	    	catch (IOException e) {
				// TODO: handle exception
	    		addFieldError("fileerror", "创建在线预览版本失败！");
	    		return INPUT;
			} 
	    	catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				addFieldError("fileerror", "创建在线预览版本失败！");
				return INPUT;
			} 
	    	catch (TransformerException e) {
				// TODO Auto-generated catch block
				addFieldError("fileerror", "创建在线预览版本失败！");
				return INPUT;
			}
	    	catch (Exception e) {
	    		addFieldError("fileerror", "未知错误，请联系管理员解决问题。");
				return INPUT;
			}
	    }
	    
	    @Override
	    public void validate(){
	    	HomeworkDocCheck curfile = new HomeworkDocCheck(file);
	    	extend=curfile.getExtend();
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
