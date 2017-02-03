package club.istc.validation;

/**
 * 检查QQ号是否合法
 */

public class QQCheck {
	
	String QQ;
	boolean result;
	
	public QQCheck(String QQ) {
		// TODO Auto-generated constructor stub
		this.QQ=QQ.trim();
		result=checkQQ();
		//System.out.println("QQ号检测结果是："+false);
	}
	
	private boolean checkQQ() {
		//检查qq号是否合法
		try {
			String regex = "[1-9][0-9]{4,}";
			result = QQ.matches(regex);
		} catch (Exception e) {
			// TODO: handle exception
			result=false;
		}
		return result;
	}
	
	public boolean getResult(){
		return result;
	}

}
