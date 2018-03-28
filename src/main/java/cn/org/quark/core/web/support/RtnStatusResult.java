package cn.org.quark.core.web.support;

import cn.org.quark.core.BaseVo;
import cn.org.quark.core.common.RtnCode;

/**
 * 返回状态结果
 * @author Leo
 * @version 1.0
 * @since 1.0
 * created by leo 2017-08-08
 */
public class RtnStatusResult extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code ;//返回码
	private String errMsg ;//错误信息
	public RtnStatusResult(){
		this.code = RtnCode.SUCC ;
		this.errMsg = RtnCode.getErrMsg(code) ;
	}
	public RtnStatusResult(String code ){
		this.code = code ;
		this.errMsg = RtnCode.getErrMsg(code) ;
	}
	public String getCode() {
		return code;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}