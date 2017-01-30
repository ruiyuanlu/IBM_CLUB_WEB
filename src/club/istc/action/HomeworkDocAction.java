package club.istc.action;

import java.io.*;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeworkDocAction extends ActionSupport{
	
	/**
	 * 成员管理自己作业文件的上传和下载
	 */
	
	private static final long serialVersionUID = 1L;
    
	 //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	    private File file;
	    
	    //提交过来的file的名字
	    private String fileFileName;
	    
	    //提交过来的file的MIME类型
	    private String fileContentType;

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
	    
	    @Override
	    public String execute(){
	    	try {
	    //可以加上一个当目录不存在时自动创建目录的代码
		        String root = ServletActionContext.getServletContext().getRealPath("/upload"); 
		        InputStream is = new FileInputStream(file);
		        OutputStream os = new FileOutputStream(new File(root, fileFileName));
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
			} catch (Exception e) {
				// TODO: handle exception
				return INPUT;
			}

	    }
	}
