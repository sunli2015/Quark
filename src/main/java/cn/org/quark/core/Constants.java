package cn.org.quark.core;

/**
 * 系统级静态常量. 可通过mip.properties初始化,同时保持常量 static & final的特征.
 * 
 * @author calvin
 * @author lib
 * @see ConfigurableConstants
 * 
 */
public class Constants extends ConfigurableConstants {

	// 静态初始化读入springside.properties中的设置
	static {
		init("coral.properties");
	}

	/**
	 * 默认用户密码
	 */
	public final static String DEFAULT_PASSWD = getProperty(
			"constant.default.password", "123456");
	
	/**
	 * 从coral.properties中读取constant.message_bundle_key的值，
	 * 如果配置文件不存在或配置文件中不存在该值时，默认取值"messages"
	 */
	public final static String MESSAGE_BUNDLE_KEY = getProperty(
			"constant.message_bundle_key", "i18n/messages");

	public final static String ERROR_BUNDLE_KEY = getProperty(
			"constant.error_bundle_key", "i18n/errors");

	/**
	 * 通过地址栏传递的网站名称接受参数
	 */
	public final static String VAR_WEB_SITE_URL = getProperty(
			"constant.web_site_name", "website");

	/**
	 * 保存登录用户信息到session中的标志
	 */
	public final static String USER_IN_SESSION = getProperty(
			"constant.user_in_session", "loginer");
	
	public final static String JBPM_DEPLOY_IP = getProperty(
			"jbpm.deployer", "127.0.0.1");
	
	public static final Long STATUS_INVALID = new Long(0);

	public static final Long STATUS_VALID = new Long(1);
	
	public static final String ROOT_DEPT_ID = "1";//根部门ID
}
