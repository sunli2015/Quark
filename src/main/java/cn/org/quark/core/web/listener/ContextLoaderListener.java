package cn.org.quark.core.web.listener;

import java.net.MalformedURLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.org.quark.core.web.support.ServiceLocator;

/**
 * 自己实现一个简单的Singleton，扩展ContextLoaderListener类，在Web系统启动时压入Singleton。
 *     新的ContextLoaderListener类重载如下，ContextUtil中包含一个静态的ApplicationContext变量：
 *     
 * @author marc
 *
 */
public class ContextLoaderListener extends
		org.springframework.web.context.ContextLoaderListener {
	private Logger logger = LoggerFactory.getLogger(ContextLoaderListener.class);
	/* (non-Javadoc)
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
				
		//配合自定义ContextLoaderListener使用的工具类
		ServiceLocator.setContext(ctx);
		try {
			String path = context.getResource("/").getPath();
			String contextPath = path.substring(0, path.lastIndexOf("/"));
			contextPath = contextPath.substring(contextPath.lastIndexOf("/"));
			ServiceLocator.setCtx(contextPath);
		} catch (MalformedURLException e) {
			logger.error("ContextLoaderListener初始化异常！",e);
			e.printStackTrace();
		}
	}

}
