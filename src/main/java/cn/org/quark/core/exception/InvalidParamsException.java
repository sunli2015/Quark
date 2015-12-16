package cn.org.quark.core.exception;

/**
 * 无效参数异常
 * @author Leo
 *
 */
public class InvalidParamsException extends DataAccessException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidParamsException(String msg) {
		super(msg);
	}
	public InvalidParamsException(String msg, Exception ex) {
		super(msg,ex);
	}
	
}
