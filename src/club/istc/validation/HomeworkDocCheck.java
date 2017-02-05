package club.istc.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HomeworkDocCheck {
	
	int maxlength;
	Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
	boolean lengthnotover=false;
	boolean formatmatch=false;
	boolean found=true;
	
	
	public HomeworkDocCheck(File file) {
		// TODO Auto-generated constructor stub
		maxlength=5242880;
		FILE_TYPE_MAP.put("255044462d312e350d0a", "pdf");
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); 
        FILE_TYPE_MAP.put("504b0304140006000800", "docx");  
        FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");
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
    
    private void checkSize(File file){
		if(file.length()<maxlength){
			lengthnotover=true;
		}
    }
    
    private void checkFileValidation(File file) throws FileNotFoundException,IOException{
        FileInputStream is = new FileInputStream(file);  
        byte[] b = new byte[10];  
        is.read(b, 0, b.length);  
        String fileCode = bytesToHexString(b);     
        Iterator<String> keyIter = this.FILE_TYPE_MAP.keySet().iterator();  
        while(keyIter.hasNext()){  
             String key = keyIter.next();  
             if(key.toLowerCase().startsWith(fileCode.toLowerCase()) || fileCode.toLowerCase().startsWith(key.toLowerCase())){ 
                formatmatch=true;
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
	
	
}
