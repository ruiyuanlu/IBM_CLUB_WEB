package club.istc.validation;

/**
 * 检查Password号是否合法
 */

public class PasswordCheck {
	
	String password;
	boolean result;
	
	public PasswordCheck(String password) {
		// TODO Auto-generated constructor stub
		this.password=password.trim();
		result=checkpassword();
	}
	
	private boolean checkpassword() {
		//检查password号是否合法

		String regex = "[a-zA-Z0-9_]{6,30}";
		result = password.matches(regex);
		return result;
	}
	
	public boolean getResult(){
		return result;
	}

}
