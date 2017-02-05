package club.istc.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import club.istc.validation.HomeworkDocCheck;

import com.opensymphony.xwork2.ActionSupport;

public class HomeworkDocAction extends ActionSupport{
	
	/**
	 * 成员管理自己作业文件的上传和下载
	 */
	
	private static final long serialVersionUID = 1L;
    
	    private File file;
	    //提交过来的file的名字
	    private String fileFileName;
	    //提交过来的file的MIME类型
	    private String fileContentType;
	    
	    @Override
	    public String execute() throws Exception{
	    	try {
	    //可以加上一个当目录不存在时自动创建目录的代码
		        String root = ServletActionContext.getServletContext().getRealPath("/file/homework"); 
		        InputStream is = new FileInputStream(file);
		        File homeworkdir=new File(root);
		        if (!homeworkdir.exists()) {
					homeworkdir.mkdirs();
				}
		        Date date = new Date();
		        SimpleDateFormat timestamp=new SimpleDateFormat("yyyyMMddHHmmss");
		        OutputStream os = new FileOutputStream(new File(root, timestamp.format(date)+"-"+fileFileName));
		        System.out.println("fileFileName: " + fileFileName);
		// 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
		        System.out.println("file: " + file.getName());
		        System.out.println("file: " + file.getPath());
		        byte[] buffer = new byte[500];
		        while(-1 != (is.read(buffer, 0, buffer.length))){
		            os.write(buffer);
		        }
		        os.close();
		        is.close();
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
			}
	    	if (!curfile.isFormatmatch()) {
				addFieldError("fileerror", "格式不匹配，请重新选择！");
			}
	    	if (!curfile.isLengthnotover()) {
	    		addFieldError("fileerror", "文件大小超过了5M！");
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
