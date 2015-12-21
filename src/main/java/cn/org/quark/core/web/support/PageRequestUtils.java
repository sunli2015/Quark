package cn.org.quark.core.web.support;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.UtilString;
/**
 * 
 * @author Leo
 *
 */
public class PageRequestUtils {
	private static Log logger = LogFactory.getLog(PageRequestUtils.class);
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static Page getPage(HttpServletRequest request){
		Page page = new Page();
		try{
			String _pageSize = request.getParameter(Page.FIELD_PAGESIZE);
			String _curPage = request.getParameter(Page.FIELD_CURPAGE);
			if(!UtilString.isEmpty(_pageSize)) page.setPageSize(Integer.valueOf(_pageSize));
			if(!UtilString.isEmpty(_curPage)) page.setCurPage(Integer.valueOf(_curPage));
		}catch(Exception ex){
			logger.error("分页请求异常", ex);
			return page;
		}
		return page;
	}
}
