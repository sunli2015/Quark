package cn.org.quark.core.web.support;

/**
 * 返回数据的数据结构
 * @author Leo
 * @version 1.0
 * @since 1.0
 * created by leo 2017-08-04
 * 
 */
public class RtnResult<T> extends RtnStatusResult{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T data = null;//返回数据
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
