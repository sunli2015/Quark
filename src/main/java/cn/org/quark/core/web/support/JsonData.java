package cn.org.quark.core.web.support;

import java.util.ArrayList;
import java.util.List;

import cn.org.quark.core.BaseVo;
/**
 * 
 * @author Leo
 *
 * @param <T>
 */
public class JsonData<T> extends BaseVo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String OPER_SUCC = "1";//成功
	public static final String OPER_FAIL = "0";//失败
	public static final String OPER_ERR = "-1";//异常
	/**
	 * QUERY
	 */
	private int page = 0;
	private long total = 0;
	private List<T> rows = new ArrayList<T>() ;
	/**
	 * UPDATE
	 */
	private String operResult = OPER_SUCC;
	private String operMsg = "";
	/**
	 * QUERY ONE RECORD
	 */
	private T entity ;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public String getOperResult() {
		return operResult;
	}
	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}
	public String getOperMsg() {
		return operMsg;
	}
	public void setOperMsg(String operMsg) {
		this.operMsg = operMsg;
	}
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	
}
