package cn.org.quark.admin.view;

import org.springframework.beans.factory.annotation.Autowired;

import cn.org.quark.admin.manager.UserManager;
import cn.org.quark.core.login.Loginer;
import cn.org.quark.core.utils.UtilString;
import cn.org.quark.core.web.struts2.BaseAction;
import cn.org.quark.core.web.support.JsonData;
/**
 * 修改密码
 * @author Leo
 *
 */
public class PwdAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public String execute() throws Exception {
		Loginer loginer =null;//getLoginer();
		setOid(loginer.getUserid());
		if(!UtilString.isEmpty(oldPwd)){
			if(userManager.isRightPwd(oid,oldPwd)){
				if(newPwd.equals(newPwdAgain)){
					userManager.changePwd(oid, newPwd);
//					this.addActionMessage("密码修改成功！");
				}
			}else{
//				this.addActionMessage("旧密码输入错误！");
			}
		}
		return "SUCCESS";
	}
	protected JsonData jsonData = new JsonData();
	public String modifyPwd(){
		return "modifyPwd";
	}
	private String oid;
	private String oldPwd;
	private String newPwd;
	private String newPwdAgain;

	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getNewPwdAgain() {
		return newPwdAgain;
	}
	public void setNewPwdAgain(String newPwdAgain) {
		this.newPwdAgain = newPwdAgain;
	}
	@Autowired
	private UserManager userManager;
	public UserManager getUserManager() {
		return userManager;
	}
	public JsonData getJsonData() {
		return jsonData;
	}
	public void setJsonData(JsonData jsonData) {
		this.jsonData = jsonData;
	}
	
}
