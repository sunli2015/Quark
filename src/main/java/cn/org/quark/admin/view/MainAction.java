package cn.org.quark.admin.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import cn.org.quark.core.web.struts2.BaseAction;
/**
 * 
 * @author Leo
 *
 */
@Service
@Scope("prototype")
public class MainAction extends BaseAction{

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
