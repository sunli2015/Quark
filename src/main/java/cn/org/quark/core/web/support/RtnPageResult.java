package cn.org.quark.core.web.support;

/**
 * 返回分页数据的数据结构
 * @author Leo
 * @version 1.0
 * @since 1.0
 * created by leo 2017-08-04
 * 
 */
public class RtnPageResult<T> extends RtnStatusResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Page<T> data = new Page<T>();
	public Page<T> getData() {
		return data;
	}
	public void setData(Page<T> data) {
		this.data = data;
	}
	
	
}
