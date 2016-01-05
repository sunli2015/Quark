package cn.org.quark.core.web.filter;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;
/**
 * web filter
 * contains: spring characterEncodingFilter
 * 用于解决IFRAME框架调用
 * @author Leo
 * @version 1.0
 */
public class WebFilter extends CharacterEncodingFilter{
//	private static final String XFRAME_OPTIONS_DENY = "DENY";//拒绝
	private static final String XFRAME_OPTIONS_SAMEORIGIN = "SAMEORIGIN";//相同域名页面的 frame 中展示
//	private static final String XFRAME_OPTIONS_ALLOW = "ALLOW-FROM";//允许
	private String xframeOptions = XFRAME_OPTIONS_SAMEORIGIN;
	/**
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
/*		Collection<String> list = response.getHeaderNames();
		for(String name : list){
			System.out.println(name+"::"+response.getHeader(name));
		}*/
		response.addHeader("x-frame-options",xframeOptions);
		super.doFilterInternal(request, response, filterChain);
	}
	/**
	 * 
	 * @param xframeOptions
	 */
	public void setXframeOptions(String xframeOptions) {
		this.xframeOptions = xframeOptions;
	}
	
}
