package cn.org.quark.core.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.org.quark.core.common.RtnCode;
import cn.org.quark.core.web.support.ResultData;
/**
 * 
 * @author Leo
 *
 */
public class CommonExceptionHandler implements HandlerExceptionResolver{
	private MappingJackson2JsonView  jsonView = new MappingJackson2JsonView();
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		
		 HandlerMethod handlerMethod = (HandlerMethod)handler;
		 ResponseBody res = handlerMethod.getMethodAnnotation(ResponseBody.class);
		 if(null == res){
			 Map<String, Object> model = new HashMap<String, Object>();  
		     model.put("exception", ex);  
			 return new ModelAndView("error/error", model);  
		 }
		 
		 ResultData resultData = null;
		 if(ex instanceof BizException) { 
			 BizException b = (BizException)ex;
			 resultData = new ResultData(b.getMessageKey(),b.getMessage());
         } else if(ex instanceof IllegalArgumentException) {  
        	resultData = new ResultData(RtnCode.INVALIDATE_PARAM,RtnCode.getErrMsg(RtnCode.INVALIDATE_PARAM)+":"+ex.getMessage());
         } else {  
        	resultData = new ResultData(RtnCode.OTHER_ERROR,RtnCode.getErrMsg(RtnCode.OTHER_ERROR)+":"+ex.getMessage());
         }  

		 ModelAndView mav =  new ModelAndView();
		 
		 jsonView.setAttributesMap(resultData.toMap());
		 mav.setView(jsonView);
		 return mav;  
	}

}
