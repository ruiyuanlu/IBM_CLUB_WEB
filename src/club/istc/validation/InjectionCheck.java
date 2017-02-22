package club.istc.validation;

/**
 * SQL注入检测
 */

public class InjectionCheck {
	String needcheck;
	boolean result;
	
	public InjectionCheck(String needcheck) {
		// TODO Auto-generated constructor stub
		this.needcheck=needcheck;
		try {
			result=checkString();
		} catch (NullPointerException e) {
			// 什么也没有，那么不会存在注入
			result=true;
			
		}
		
	}
	
/**
 * 包含有(* ' ; - + / % #)这些符号均会返回错误。
 */	
	private boolean checkString() {
        String str = needcheck.toLowerCase();
        String[] SqlStr = {"*","'",";","-","+","/","%","#","\""};//特殊字符
  
        for (int i = 0; i < SqlStr.length; i++) {
            if (str.indexOf(SqlStr[i]) >= 0) {
                return false;
            }
        }
        return true;
	}
	
	public boolean getResult(){
		return result;
	}

/**
 * 考虑到输入大量数据时用户不便于修正，(* ' ; - + / % #)这些符号如果出现在大量文本并提交时，会被系统修改为全角符号。
 */		

	public String replaceString(){
		char[] charArray = needcheck.toCharArray();
		for (int i = 0; i < needcheck.length(); i++) {
            int charIntValue = (int)charArray[i];
            if (charIntValue >= 33 && charIntValue <= 47) {
                charArray[i] = (char) (charIntValue + 65248);
            } 
		}
		return new String(charArray);
	}

}
