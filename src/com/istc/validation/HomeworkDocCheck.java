package com.istc.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 作业文件上传检验
 */

public class HomeworkDocCheck {
	
	int maxlength;
	Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
	boolean lengthnotover=false;
	boolean formatmatch=false;
	boolean found=true;
	String extend;
	
	
	public HomeworkDocCheck(File file) {
		//文件最大限制在5M
		maxlength=5242880;
		//允许的文件特征码
		FILE_TYPE_MAP.put("255044462d312e", "pdf");
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); 
        FILE_TYPE_MAP.put("504b0304140006000800", "docx");
        try {
        	checkSize(file);
            checkFileValidation(file);
		} catch (FileNotFoundException e) {  
            found=false;
        } catch (IOException e) {  
        	found=false; 
        } catch (NullPointerException e){
        	found=false;
        }
	}
	/**
	 * 用于获取文件的特征码
	 */
    private String bytesToHexString(byte[] src) {  
        StringBuilder stringBuilder = new StringBuilder();  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        for (int i = 0; i < src.length; i++) {  
            int v = src[i] & 0xFF;  
            String hv = Integer.toHexString(v);  
            if (hv.length() < 2) {  
                stringBuilder.append(0);  
            }  
            stringBuilder.append(hv);  
        }  
        return stringBuilder.toString();  
    }
	/**
	 * 文件大小检验
	 */
    private void checkSize(File file){
		if(file.length()<maxlength){
			lengthnotover=true;
		}
    }
	/**
	 * 文件格式检验
	 */
    private void checkFileValidation(File file) throws FileNotFoundException,IOException{
            FileInputStream is = new FileInputStream(file);
            byte[] b = new byte[10];  
            is.read(b, 0, b.length);  
            String fileCode = bytesToHexString(b);
            //其次检验文件特征码，如果是伪造的那么不予通过
            Iterator<String> keyIter = this.FILE_TYPE_MAP.keySet().iterator();
            while(keyIter.hasNext()){  
                 String key = keyIter.next();  
                 if(key.toLowerCase().startsWith(fileCode.toLowerCase()) || fileCode.toLowerCase().startsWith(key.toLowerCase())){ 
                    formatmatch=true;
                    extend=this.FILE_TYPE_MAP.get(key);
                    break;
                    }  
                 }
            is.close();
    } 

	public boolean isLengthnotover() {
		return lengthnotover;
	}

	public boolean isFormatmatch() {
		return formatmatch;
	}

	public boolean isFound() {
		return found;
	}
	public String getExtend() {
		return extend;
	}
	
}
