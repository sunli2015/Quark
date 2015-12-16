package cn.org.quark.core;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author Leo
 *
 */
public class BaseVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String toString(){
		StringBuilder sb = new StringBuilder();
    	String str = "";
        // 获取Bean的所有方法
        Method[] methods = this.getClass().getMethods();
        try {
			// 遍历，取Getter方法
			for (Method method : methods) {
			    String methodName = method.getName();
			    if ("getClass".equals(methodName)) continue;
			    if (!methodName.startsWith("get") && !methodName.startsWith("is")) continue ;
			    String fieldName = "";
			    if(methodName.startsWith("get")){
			        fieldName = methodName.substring(3);
			    } else {
			    	fieldName = methodName.substring(2);
			    }
			    fieldName = fieldName.substring(0, 1).toLowerCase()+ fieldName.substring(1);
			    sb.append(fieldName).append("=").append(method.invoke(this)).append(",");
			}
			str = sb.substring(0,sb.length()-1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        return "{"+str+"}";
	}
}
