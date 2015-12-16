package cn.org.quark.core.local;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NamedThreadLocal;
/**
 * 数据线程同步类
 * @author leo
 *
 */
public class DataSynchronizedManager {
	private static Log logger = LogFactory.getLog(DataSynchronizedManager.class);
	@SuppressWarnings("unchecked")
	private static final ThreadLocal defaultData = new NamedThreadLocal("default_data");
	/**
	 * 
	 * @param <T>
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getData(String key){
		Map map = (Map)defaultData.get();
		if (map == null) {
			return null;
		}
		return (T)map.get(key);
	}
	/**
	 * 
	 * @param <T>
	 * @param key
	 * @param value
	 */
	@SuppressWarnings("unchecked")
	public static <T> void bindData(String key , T value){
		Map map = (Map)defaultData.get();
		// set ThreadLocal Map if none found
		if (map == null) {
			map = new HashMap();
			defaultData.set(map);
		}
		if (map.put(key, value) != null) {
			logger.error("Already value [" + map.get(key) + "] for key [" + key + "] bound to thread [" + Thread.currentThread().getName() + "]");
		}
	}
	/**
	 * 重新绑定
	 * @param <T>
	 * @param key
	 * @param value
	 */
	public static <T> void rebindData(String key , T value){
		unbindData(key);
		bindData(key,value);
	}
	/**
	 * 去除绑定
	 * @param <T>
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unbindData(String key) {
		Map map = (Map) defaultData.get();
		if (map == null) {
			return null;
		}
		Object value = map.remove(key);
		// Remove entire ThreadLocal if empty...
		if (map.isEmpty()) {
			defaultData.set(null);
		}
		if (value != null && logger.isTraceEnabled()) {
			logger.trace("Removed value [" + value + "] for key [" + key + "] from thread [" +Thread.currentThread().getName() + "]");
		}
		return (T)value;
	}
}
