package club.istc.validation;


/**
 * session错误时抛出的异常
 */

public class SessionErrorException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
    public SessionErrorException() {
		// TODO Auto-generated constructor stub
    	super("Session出现错误");
	}
	

}
