package cn.org.quark.biz.example.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.org.quark.biz.example.dao.ExampleDao;
import cn.org.quark.biz.example.model.Example;
import cn.org.quark.biz.example.service.ExampleService;
import cn.org.quark.core.web.support.Page;
import cn.org.quark.core.web.support.RtnPageResult;
import cn.org.quark.core.web.support.RtnResult;
import cn.org.quark.core.web.support.RtnStatusResult;

@Service
public class ExampleServiceImpl implements ExampleService{
	@Autowired
	private ExampleDao exampleDao;
	@Override
	public RtnPageResult<Example> query(Example example,Page<Example> page) throws Exception {
		RtnPageResult<Example> rtnData = new RtnPageResult<Example>();
		
		List<Example> data = exampleDao.queryForList(example,page);
		long total = exampleDao.queryForCount(example);
		page.setData(data);
		page.setTotal(total);
		
		rtnData.setData(page);;
		return rtnData;
	}
	@Override
	public RtnStatusResult save(Example example) throws Exception {
		Assert.notNull(example, "example为NULL");
		RtnStatusResult rtnResult = new RtnStatusResult();
		if(StringUtils.isBlank(example.getOid())){
			exampleDao.insert(example);
		} else {
			exampleDao.update(example);
		}
		return rtnResult;
	}
	@Override
	public RtnResult<Example> queryById(String id) throws Exception {
		Assert.hasLength(id, "id为NULL");
		RtnResult<Example> rtnResult = new RtnResult<Example>();
		Example example = exampleDao.queryById(id);
		rtnResult.setData(example);
		return rtnResult;
	}
	@Override
	public RtnStatusResult delete(String id) throws Exception {
		Assert.hasLength(id, "id为NULL");
		RtnStatusResult result = new RtnStatusResult();
		exampleDao.delete(id);
		return result ;
	}

}
