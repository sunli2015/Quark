package cn.org.quark.core.web.support;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;

import cn.org.quark.core.BaseVo;
import cn.org.quark.core.common.RtnCode;
/**
 * 结果集
 * @author Leo
 *
 * @param <T>
 * @see cn.org.quark.core.web.support.RtnResult,cn.org.quark.core.web.support.RtnStatusResult
 */
@Deprecated
public class ResultData<T> extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code ;//返回码
	private String errMsg ;//错误信息
	private RtnData<T> data;//返回数据
	
	public ResultData(){
		this.code = RtnCode.SUCC ;
		this.errMsg = RtnCode.getErrMsg(code) ;
	}
	public ResultData(String code ){
		this.code = code ;
		this.errMsg = RtnCode.getErrMsg(code) ;
	}
	public ResultData(String code , String errMsg ){
		this.code = code ;
		this.errMsg = errMsg ;
	}
	public ResultData(String code , String errMsg , RtnData<T> data){
		this.code = code ;
		this.errMsg = errMsg ;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public RtnData<T> getData() {
		return data;
	}
	public void setData(RtnData<T> data) {
		this.data = data;
	}
	public Map<String,String> toMap(){
		try {
			Map<String, String> map = BeanUtilsBean.getInstance().describe(this);
			return map;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
