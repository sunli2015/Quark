package cn.org.quark.core.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Leo
 *
 */
public class RtnCode {
	public static final String SUCC = "0";
	public static final String PWD_OLDPWD_INCORRECT = "1001";
	public static final String INVALIDATE_PARAM = "1002";
	public static final String OTHER_ERROR = "9999";

	private final static Map<String,String> map = new HashMap<String,String>(){
		{
			put(SUCC, "成功");
			put(PWD_OLDPWD_INCORRECT, "旧密码不正确");
			put(INVALIDATE_PARAM, "无效参数");
			put(OTHER_ERROR, "未知异常");
		}
	};
	public static String getErrMsg(String code ){
		return map.get(code);
	}
}
