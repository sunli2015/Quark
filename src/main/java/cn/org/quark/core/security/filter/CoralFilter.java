package cn.org.quark.core.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import cn.org.quark.core.Constants;
import cn.org.quark.core.login.Loginer;
import cn.org.quark.core.security.manager.LoginerManager;

public class CoralFilter implements Filter {
	protected final Log logger = LogFactory.getLog(getClass());


	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (!(request instanceof HttpServletRequest)) {
			throw new ServletException("Can only process HttpServletRequest");
		}

		if (!(response instanceof HttpServletResponse)) {
			throw new ServletException("Can only process HttpServletResponse");
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession httpSession = null;
		try {
			httpSession = httpRequest.getSession(false);
		} catch (IllegalStateException ignored) {
		}

		if (httpSession != null) {
			if (httpSession.getAttribute(Constants.USER_IN_SESSION) == null) {

				SecurityContext sc = SecurityContextHolder.getContext();
				Authentication auth = sc.getAuthentication();
				if (auth != null && auth.getPrincipal() instanceof UserDetails) {
					UserDetails ud = (UserDetails) auth.getPrincipal();
					logger.info(" UserDetails.username:" + ud.getUsername());
					
					// 登录者信息
					Loginer loginer = loginerManager.getLoginerByLoginidAndPasswd(ud.getUsername());

					httpSession.setAttribute(Constants.USER_IN_SESSION, loginer);
				}
			}
			
			//将用户信息绑定到当前线程
//			LocalThreadValue.setValue(httpSession.getAttribute(Constants.USER_IN_SESSION));
		}
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig cfg) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Autowired
	private LoginerManager loginerManager;
}
