package club.istc.validation;

/**
 * SQL注入检测，不允许表单中输入任何SQL语句中的关键词和符号，一旦存在则返回FALSE
 */

public class InjectionCheck {
	String needcheck;
	boolean result;
	
//	public void validate(Object arg0){
//		// TODO Auto-generated method stub
//		String fieldName = getFieldName();
//		try{
//		Object value = this.getFieldValue(fieldName,arg0); 
//		this.needcheck=(String)value;
//		if (!checkString(needcheck)) 
//			addFieldError(fieldName, arg0);
//		}
//		catch(ValidationException exception){
//			addFieldError(fieldName, arg0);
//		}
//	}
	
	public InjectionCheck(String needcheck) {
		// TODO Auto-generated constructor stub
		this.needcheck=needcheck;
		result=checkString();
		//System.out.println("SQL注入的验证结果是："+result);
	}
	
	public boolean checkString() {
        String str2 = needcheck.toLowerCase();
        String[] SqlStr1 = {"and","exec","execute","insert","select","delete","update","count","drop","chr","mid","master","truncate","char","declare","sitename","net user","xp_cmdshell","like","and","exec","execute","insert","create","drop","table","from","grant","use","group_concat","column_name","information_schema.columns","table_schema","union","where","select","delete","update","order","by","count","chr","mid","master","truncate","char","declare","or"};//词语
        //String[] SqlStr2 = {"*","'",";","-","--","+","//","/","%","#"};//特殊字符
  
       for (int i = 0; i < SqlStr1.length; i++) {
            if (str2.indexOf(SqlStr1[i])>=0) {
                return false;
            }
        }
//        for (int i = 0; i < SqlStr2.length; i++) {
//            if (str2.indexOf(SqlStr2[i]) >= 0) {
//                return false;
//            }
//        }
        return true;
	}
	
	public boolean getResult(){
		return result;
	}

}
