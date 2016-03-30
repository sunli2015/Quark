package cn.org.quark.core.web.support;

import cn.org.quark.core.BaseVo;
/**
 * 返回数据集合
 * @author Leo
 *
 * @param <T>
 */
public class RtnData<T> extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T data ;
	private int pages;
	private int total;
	private int PAGE_SIZE = 10;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getPages() {
		pages = total/PAGE_SIZE;
		if(total%PAGE_SIZE != 0 ) pages += 1;
		return pages;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	

}
