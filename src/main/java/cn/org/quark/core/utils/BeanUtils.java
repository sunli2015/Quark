package cn.org.quark.core.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.PropertyUtilsBean;
/**
 * BeanUtil的工具类
 * 基于commons-beanutils-1.7版本
 * @author Leo
 *
 */
public class BeanUtils {
	/**
	 * VO拷贝
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	public static void updateObject(Object dest,Object orig) throws Exception{
		updateObject(dest,orig,null);
	}
	/**
	 * MAP 转BEAN对象
	 * 注：MAP中的字段与BEAN的字段要一致
	 * @param dest
	 * @param orig
	 * @throws Exception
	 */
	public static void mapToBean(Object dest,Map<String,String> orig) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		for(Map.Entry<String, String> entry:orig.entrySet()){
			String key = entry.getKey();
			map.put(key.replaceFirst(".", String.valueOf(key.charAt(0)).toLowerCase()), entry.getValue());
		}

		BeanUtilsBean.getInstance().populate(dest, map);
	}
	/**
	 * VO拷贝，可排除某些属性
	 * @param dest
	 * @param orig
	 * @param exclude 排除的属性列表
	 * @throws Exception
	 */
	public static void updateObject(Object dest,Object orig,List<String> excludeFields) throws Exception{
		PropertyUtilsBean propertyUtils = BeanUtilsBean.getInstance().getPropertyUtils();
		PropertyDescriptor[] origPropertys = propertyUtils.getPropertyDescriptors(orig);
		for(PropertyDescriptor origProperty : origPropertys){
			String originFieldName = origProperty.getName(); // origin字段名称
			if(excludeFields != null && excludeFields.contains(originFieldName)) continue;
			if(!propertyUtils.isReadable(orig, originFieldName))
				continue;
			if(!propertyUtils.isWriteable(dest, originFieldName))
				continue;
			Class originFieldType = origProperty.getPropertyType(); // origin字段类型
			if (originFieldType == null 
					|| originFieldType.equals(Class.class)
					|| isCollection(originFieldType)) {
				continue;
			}
			
			Object originFieldValue = propertyUtils.getProperty(orig, originFieldName);
			if(null != originFieldValue && !"".equals(originFieldValue)){
//				System.out.println(originFieldName+":"+originFieldValue);
				propertyUtils.setProperty(dest, originFieldName, originFieldValue);
			}
		}
	}
	/**
	 * 集合类型
	 * @param type
	 * @return
	 */
	public static boolean isCollection(Class type) {
		if (type == null || type.equals(java.lang.Object.class)) {
			return false;
		} else if (type.equals(java.util.Collection.class)) {
			return true;
		}
		Class[] superInterface = type.getInterfaces();
		for (int i = 0; i < superInterface.length; i++) {
			if (isCollection(superInterface[i]))
				return true;
		}
		Class superClass = type.getSuperclass();
		if (isCollection(superClass))
			return true;
		return false;
	}
	/**
	 * 按Filed的类型取得Field列表.
	 */
	public static List<Field> getFieldsByType(Object object, Class type) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isAssignableFrom(type)) {
				list.add(field);
			}
		}
		return list;
	}
	/**
	 * 获取属性
	 * @param bean
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static Object getProperty(Object bean, String name) throws Exception{
		BeanUtilsBean beanUtil = BeanUtilsBean.getInstance();
		PropertyUtilsBean propertyUtils = beanUtil.getPropertyUtils();
		return propertyUtils.getProperty(bean, name);
	}
	/**
	 * 通过CLASS和其字段名称，获取字段CLASS类型
	 * @param bean
	 * @param name
	 * @return
	 */
	public static Class getPropertyType(Class bean , String name){
		BeanUtilsBean beanUtil = BeanUtilsBean.getInstance();
		PropertyUtilsBean propertyUtils = beanUtil.getPropertyUtils();
		PropertyDescriptor[] propDescs = propertyUtils.getPropertyDescriptors(bean);
		
		for(PropertyDescriptor propDesc :propDescs ){
			if(propDesc.getName().equals(name)){
				return propDesc.getPropertyType();
			}
		}
		return null;
	}
}
