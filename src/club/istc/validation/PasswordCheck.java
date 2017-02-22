package club.istc.validation;

/**
 * 检查密码是否合法
 */

public class PasswordCheck {
	
	String password;
	boolean result;
	
	public PasswordCheck(String password) {
		// TODO Auto-generated constructor stub
		this.password=password.trim();
		result=checkpassword();
	}
	
	/**
	 * 检查密码是否合法
	 */
	private boolean checkpassword() {
		String regex = "[a-zA-Z0-9_]{6,30}";//密码中只允许使用数字、字母和下划线，长度不小于6位，不大于30位
		result = password.matches(regex);
		return result;
	}
	
	public boolean getResult(){
		return result;
	}

}
