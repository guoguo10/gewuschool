package com.lqg.action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.user.Parent;
import com.lqg.util.AppException;
import com.opensymphony.xwork2.ModelDriven;
/**
 * parentAction
 * @author Li Qiuguo
 */
@Scope("prototype")
@Controller("parentAction")
public class ParentAction extends BaseAction implements ModelDriven<Parent>{
	private static final long serialVersionUID = 1L;
	public String login() throws Exception{
		return USER_LOGIN;
	}
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		boolean unique = parentDao.isUnique(parent.getEmail());
		if(unique){
			parentDao.save(parent);
			return USER_LOGIN;
		}else{
			throw new AppException("该用户已经注册");
			}
	}
	/**
	 * 登陆
	 * @return
	 * @throws Exception
	 */
	public String logon() throws Exception{
		Parent loginParent = parentDao.login(parent.getEmail(), parent.getPassword());
		if(loginParent != null){//閿熸枻鎷烽敓閰殿煉鎷烽敓鏂ゆ嫹閿熻锟�	
			session.put("parent", loginParent);
		}else{
			addFieldError("", "閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋癌浼欐嫹閿熼ズ鍑ゆ嫹閿");
			return USER_LOGIN;
		}
		return INDEX;
	}
	/**
	 * 退出
	 * @return String
	 * @throws Exception
	 */
	public String logout() throws Exception{
		if(session != null && session.size() > 0){
			session.clear();
		}
		return INDEX;
	}
	// modelDriven
	private Parent parent = new Parent();
	// 再一次密码
	private String repassword;
	public Parent getParent() {
		return parent;
	}
	public void setParent(Parent parent) {
		this.parent = parent;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	@Override
	public Parent getModel() {
		return parent;
	}
}
