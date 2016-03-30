package cn.org.quark.core.exception;

import cn.org.quark.core.common.RtnCode;


/**
 * 业务异常基类.
 *
 */
@SuppressWarnings("serial")
public class BizException extends RuntimeException {

	/**
	 * 
	 */
	private String messageKey;
	
	public BizException(String messageKey) {
		super(RtnCode.getErrMsg(messageKey));
		this.messageKey = messageKey;
	}

	public BizException(String messageKey ,String message) {
		super(message);
		this.messageKey = messageKey;
	}

	public BizException(String messageKey ,String message, Throwable cause) {
		super(message, cause);
		this.messageKey = message;
	}

	public BizException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 返回业务异常信息key
	 * 用于业务代码通过key值来取得业务异常描述
	 * @return
	 */
	public String getMessageKey() {
		return messageKey;
	}
	
}
