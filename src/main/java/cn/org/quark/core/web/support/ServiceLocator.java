package cn.org.quark.core.web.support;

import org.springframework.context.ApplicationContext;

import cn.org.quark.core.exception.BizException;

/**
 * 配合自定义ContextLoaderListener使用的工具类
 * @author lib
 * @see cn.org.coral.core.web.listener.ContextLoaderListener
 */
public class ServiceLocator {

	/**
	 * spring ApplicationContext
	 */
	private static ApplicationContext context;
	private static String ctx;
	
	public static String getCtx() {
		return ctx;
	}

	public static void setCtx(String ctx) {
		ServiceLocator.ctx = ctx;
	}

	protected ServiceLocator() { // 不允许实例化，全部使用static函数。
		
	}

	public static Object getBean(String beanName){
		try {
			return context.getBean(beanName);
		} catch (Exception e) {
			throw new BizException("spring fail loading bean ....",e);
		}
	}

	/**
	 * @return the context
	 */
	public static ApplicationContext getContext() {
		return context;
	}
	/**
	 * @param context the context to set
	 */
	public static void setContext(ApplicationContext context) {
		ServiceLocator.context = context;
	}
}

