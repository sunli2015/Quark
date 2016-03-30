package cn.org.quark.core.exception;
/**
 * 抽象的数据访问异常
 * @author Leo
 *
 */
public class DataAccessException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for DataAccessException.
	 * @param msg the detail message
	 */
	public DataAccessException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for DataAccessException.
	 * @param msg the detail message
	 * @param cause the root cause 
	 */
	public DataAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
