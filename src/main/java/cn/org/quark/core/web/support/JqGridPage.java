package cn.org.quark.core.web.support;

import java.util.List;

/**
 * jqGrid返回
 * @author Leo
 *
 */
public class JqGridPage<T> extends RtnStatusResult{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int prev;//前一页
	private int next;//后一页
	private int pageNo;
	private int pageSize = 10;
	private int last;//总页数
	private long count;//总记录数
	private List<T> list;//数据
	
	
	private int startIndex = 0;
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getLast() {
		int page = (int) (count/pageSize);
		if(count%pageSize != 0){
			page += 1;
		}
		last = page;
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public int getPrev() {
		prev = pageNo - 1;
		if(prev < 1) prev = 1;
		return prev;
	}

	public int getNext() {
		next = pageNo + 1;
		if(next > getLast()){
			next = last;
		}
		return next;
	}
	public int getStartIndex() {
		startIndex = (pageNo-1)*pageSize;
		return startIndex;
	}
	
}
