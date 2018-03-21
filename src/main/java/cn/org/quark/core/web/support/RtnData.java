package cn.org.quark.core.web.support;

import cn.org.quark.core.BaseVo;
import cn.org.quark.core.dao.support.Page;
/**
 * 返回数据集合
 * @author Leo
 *
 * @param <T>
 */
@Deprecated
public class RtnData<T> extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T data ;
	private int pages;
	private long total;
	private int pageSize = Page.DEFAULT_PAGE_SIZE;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getPages() {
		pages = (int)total/pageSize;
		if(total%pageSize != 0 ) pages += 1;
		return pages;
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public void setPageSize(int pageSize){
		this.pageSize= pageSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	
}
