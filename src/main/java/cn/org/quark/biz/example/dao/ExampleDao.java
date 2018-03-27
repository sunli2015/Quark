package cn.org.quark.biz.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.org.quark.biz.example.model.Example;
import cn.org.quark.core.web.support.Page;
/**
 * example的DAO
 * @author Leo
 *
 */
public interface ExampleDao {
	/**
	 * 查询列表
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public List<Example> queryForList(@Param("example")Example example,@Param("page")Page page) throws Exception ;
	/**
	 * 查询数量
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public long queryForCount(@Param("example")Example example) throws Exception ;
	/**
	 * 保存数据
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int insert(Example example) throws Exception ; 
	/**
	 * 更新数据
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int update(Example example) throws Exception ;
	/**
	 * 通过ID查询Example
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Example queryById(String id) throws Exception ;
	/**
	 * 通过ID删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(String id) throws Exception ;
}
