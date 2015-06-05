package com.lqg.action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.Admin;
import com.opensymphony.xwork2.ModelDriven;
/**
 * �û�Action
 * @author Li Qiuguo
 */
@Scope("prototype")
@Controller("adminAction")
public class AdminAction extends BaseAction implements ModelDriven<Admin>{
	private static final long serialVersionUID = 1L;
	public String login() throws Exception{
		return ADMIN_LOGIN;
	}
	/**
	 * ��¼ 
	 * @return
	 * @throws Exception
	 */
	public String logon() throws Exception{
		//��֤�û���������
		Admin loginAdmin = adminDao.login(admin.getUsername(), admin.getPassword());
		if(loginAdmin != null){//ͨ����֤
			session.put("admin", loginAdmin);//������Ա��Ϣ������Session������
		}else{
			addFieldError("", "�û��������벻��ȷ��");//���ش�����ʾ��Ϣ
			return ADMIN_LOGIN;//���غ�̨��¼ҳ��
		}
		return MANAGER;//���غ�̨����ҳ��
	}
	/**
	 * �˳�
	 * @return String
	 * @throws Exception
	 */
	public String logout() throws Exception{
		if(session != null && session.size() > 0){
			session.clear();
		}
		return INDEX;
	}
	// �û�����
	private Admin admin = new Admin();
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	@Override
	public Admin getModel() {
		return admin;
	}
}
