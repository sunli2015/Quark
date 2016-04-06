package cn.org.quark.core.web.springmvc;
//
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.struts2.StrutsStatics;
//import org.springframework.util.Assert;
//
//import cn.org.quark.core.Constants;
//import cn.org.quark.core.login.Loginer;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
//
///**
// * 最基础的一个Controller，供继承
// * 
// */
//
abstract public class BaseAction {
//	protected Log logger = LogFactory.getLog(getClass());
//
//	private static final long serialVersionUID = 7789982561394923307L;
//
//	private ActionContext context; // Webwork的ActionContext
//
//	/**
//	 * 获得Webwork的ActionContext
//	 */
//	public ActionContext getActionContext() {
//		return context == null ? context = ActionContext.getContext() : context;
//	}
//
//	/**
//	 * 获得当前Action的名称
//	 */
//	public String getActionName() {
//		return getActionContext().getName();
//	}
//
//	/**
//	 * 获得当前session
//	 */
//	public Map getSession() {
//		return getActionContext().getSession();
//	}
//	
//	/**
//	 * 获得当前Http Servlet Request
//	 */
//	public HttpServletRequest getHttpServletRequest() {
//		return (HttpServletRequest) getActionContext().get(StrutsStatics.HTTP_REQUEST);
//	}
//	
//	/**
//	 * 获得当前Http Servlet response
//	 */
//	public HttpServletResponse getHttpServletResponse() {
//		return (HttpServletResponse) getActionContext().get(StrutsStatics.HTTP_RESPONSE);
//	}
//	
//	/**
//	 * 根据指定的页面参数名称，获取页面传递来的参数值
//	 * 
//	 * @param parameter
//	 * @return 单个对象
//	 */
//	protected Object getParameterValue(String parameter) {
//		Object[] parameterArray = getParamenterArray(parameter);
//		if (parameterArray != null && parameterArray.length == 1) {
//			return parameterArray[0];
//		} else {
//			return null;
//		}
//	}
//	
//	/**
//	 * 根据指定的页面参数名称，获取页面传递来的参数值
//	 * 
//	 * @param parameter
//	 * @return 数组对象
//	 */
//	protected Object[] getParamenterArray(String parameter) {
//		return (Object[]) (getActionContext().getParameters().get(parameter));
//	}
//	/**
//	 * 登录者信息
//	 * @return
//	 */
//	protected Loginer getLoginer(){
//		Loginer loginer = (Loginer) getSession().get(Constants.USER_IN_SESSION);
//		Assert.notNull(loginer,"Object loginer not exist!");
//		return loginer;
//	}
}
