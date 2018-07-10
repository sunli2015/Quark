package cn.org.quark.biz.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.org.quark.biz.example.model.Example;
import cn.org.quark.biz.example.service.ExampleService;
import cn.org.quark.core.common.RtnCode;
import cn.org.quark.core.web.support.JqGridPage;
import cn.org.quark.core.web.support.RtnResult;
import cn.org.quark.core.web.support.RtnStatusResult;

@Controller
@RequestMapping("/biz/example")
public class ExampleController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ExampleService exampleService;
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JqGridPage<Example> list(Example example,JqGridPage<Example> page ){
		JqGridPage<Example> resultData = new JqGridPage<Example>();
		try {
			resultData = exampleService.query(example,page);
		} catch (Exception e) {
			resultData.setCode(RtnCode.OTHER_ERROR);
			resultData.setErrMsg(""+e.getMessage());
		}
		return resultData;
	}
	/**
	 * 
	 * @param example
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public RtnStatusResult save(Example example){
		RtnStatusResult resultData = new RtnStatusResult();
		try {
			resultData = exampleService.save(example);
			resultData.setResult("true");
			resultData.setMessage("保存成功");
		} catch (Exception e) {
			resultData.setCode(RtnCode.OTHER_ERROR);
			resultData.setErrMsg(""+e.getMessage());
			logger.error("save ex",e);
		} 
		return resultData;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public RtnResult<Example> edit(String id){
		RtnResult<Example> resultData = new RtnResult<Example>();
		try {
			resultData = exampleService.queryById(id);
		} catch (Exception e) {
			resultData.setCode(RtnCode.OTHER_ERROR);
			resultData.setErrMsg(""+e.getMessage());
		} 
		return resultData;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public RtnStatusResult delete(String id){
		RtnStatusResult result = new RtnStatusResult();
		try {
			result = exampleService.delete(id);
		} catch (Exception e) {
			result.setCode(RtnCode.OTHER_ERROR);
			result.setErrMsg(""+e.getMessage());
		} 
		return result;
	}
}
