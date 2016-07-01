package cn.org.quark.core.web.springmvc;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.core.dao.EntityDao;
import cn.org.quark.core.dao.support.CriteriaSetup;
import cn.org.quark.core.dao.support.Page;
import cn.org.quark.core.utils.BeanUtils;
import cn.org.quark.core.utils.GenericsUtils;
import cn.org.quark.core.web.support.ResultData;
import cn.org.quark.core.web.support.RtnData;
import cn.org.quark.core.web.support.ServiceLocator;

/**
 * 
 * 
 */
@SuppressWarnings("unchecked")
abstract public class BaseEntityAction<T, M extends EntityDao<T>> extends BaseAction {

	private M entityManager; // Action管理Entity所用的manager.

	
	/**
	 * 获得EntityManager类进行CRUD操作,可以在子类重载.
	 * @throws Exception 
	 */
	protected M getEntityManager() throws Exception {
		prepare();
		Assert.notNull(entityManager, "Manager未能成功初始化");
		return entityManager;
	}
	/**
	 * 预处理entityManager
	 * @throws Exception
	 */
	public void prepare() throws Exception{
		// 根据M,反射获得符合M类型的manager
		Class managerClass = GenericsUtils.getSuperClassGenricType(getClass(), 1);
		List<Field> fields = BeanUtils.getFieldsByType(this,managerClass);
		Assert.isTrue(fields.size() == 1,
				"subclass's has not only one entity manager property.");
		try {
			entityManager = (M) ServiceLocator.getBean(fields.get(0).getName());
			Assert.notNull(entityManager,
					"subclass not inject manager to action sucessful.");
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}

	}

	protected static final String SUCCESS = "index";
	/**
	 * 转向主界面
	 * @return
	 */
	public String index(){
		return SUCCESS;
	}
	/**
	 * 获取列表数据
	 * @param param
	 * @param page
	 * @return
	 * @throws Exception
	 */
	protected ResultData<List<T>> list(Page page) throws Exception{
		return doListEntity(new CriteriaSetup(), page);
		
	}
	
	/**
	 * 获取列表数据
	 * @param criteriaSetup
	 * @param page
	 * @return
	 * @throws Exception
	 */
	protected ResultData<List<T>> doListEntity(CriteriaSetup criteriaSetup, Page page) throws Exception {
		prepare();
		Page<T> data = entityManager.pagedQuery(criteriaSetup, page.getCurPage(), page.getPageSize());
		return fill(data);
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public ResultData delete(String id) throws Exception{
		Assert.hasLength(id);
		prepare();
		entityManager.removeById(id);;
		return new ResultData();
	}
	/**
	 * 编辑
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public ResultData<T> edit(String id) throws Exception{
		Assert.hasLength(id);
		prepare();
		T entity = entityManager.get(id);
		resetEditEntity(entity);
		return fill(entity);
	}
	/**
	 * 重置编辑获取的实体
	 * @param entity
	 */
	protected void resetEditEntity(T entity){}
	/**
	 * 保存
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResultData<T> save(T entity) throws Exception{
		prepare();
		entityManager.save(entity);;
		return new ResultData<T>();
	}
	/**
	 * LIST结果填充
	 * @param data
	 * @return
	 */
	protected ResultData<List<T>> fill(Page<T> data){
		ResultData<List<T>> resultData = new ResultData<List<T>>();
		RtnData<List<T>> rtnData= new RtnData<List<T>>();
		rtnData.setData(data.getResult());
		rtnData.setTotal(data.getTotalCount());
		rtnData.setPageSize(data.getPageSize());
		resultData.setData(rtnData);
		return resultData;
	}
	/**
	 * 对象T结果填充
	 * @param data
	 * @return
	 */
	protected ResultData<T> fill(T data){
		ResultData<T> resultData = new ResultData<T>();
		RtnData<T> rtnData= new RtnData<T>();
		rtnData.setData(data);
		rtnData.setTotal(1);
		rtnData.setPageSize(1);
		resultData.setData(rtnData);
		return resultData;
	}
	
}
