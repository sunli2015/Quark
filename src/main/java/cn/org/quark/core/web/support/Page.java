package cn.org.quark.core.web.support;

import java.util.ArrayList;
import java.util.List;

import cn.org.quark.core.BaseVo;

/**
 * 
 * @author Leo
 *
 */
public class Page<T> extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_PAGESIZE = 10;//默认显示条数
	
	private int curPage = 1; // 当前页数
	private int pageSize = DEFAULT_PAGESIZE; // 每页显示记录的条数
	private long total = 0; // 总的记录条数
	private int pages = 0; // 总的页数s
	
	private int startIndex = 0;
	private List<T> data = new ArrayList<T>(0);//数据
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPages() {
		int page = (int) (total/pageSize);
		if(total%pageSize != 0){
			page += 1;
		}
		pages = page;
		return pages;
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getStartIndex() {
		startIndex = (curPage-1)*pageSize;
		return startIndex;
	}
}
