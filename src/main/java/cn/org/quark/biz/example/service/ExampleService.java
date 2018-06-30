package cn.org.quark.biz.example.service;

import cn.org.quark.biz.example.model.Example;
import cn.org.quark.core.web.support.JqGridPage;
import cn.org.quark.core.web.support.Page;
import cn.org.quark.core.web.support.RtnPageResult;
import cn.org.quark.core.web.support.RtnResult;
import cn.org.quark.core.web.support.RtnStatusResult;
/**
 * example的service
 * @author Leo
 *
 */
public interface ExampleService {
	/**
	 * 
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public JqGridPage<Example> query(Example example,JqGridPage<Example> page) throws Exception;
	/**
	 * 保存
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public RtnStatusResult save(Example example) throws Exception ;
	/**
	 * 根据ID查询Example
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	public RtnResult<Example> queryById(String id) throws Exception ;
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public RtnStatusResult delete(String id) throws Exception ;
	
}
