package com.lqg.action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.user.Student;
import com.lqg.util.AppException;
import com.opensymphony.xwork2.ModelDriven;
/**
 * studentAction
 * @author Li Qiuguo
 */
@Scope("prototype")
@Controller("studentAction")
public class StudentAction extends BaseAction implements ModelDriven<Student>{
	private static final long serialVersionUID = 1L;
	private static final String ACCOUNT = "account";
	private static final String CLASS = "class";
	public String login() throws Exception{
		return USER_LOGIN;
	}
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		boolean unique = studentDao.isUnique(student.getEmail());
		if(unique){
			studentDao.save(student);
			return USER_LOGIN;
		}else{
			throw new AppException("闁跨喐鏋婚幏鐑芥晸閻偂绱幏鐑芥晸閺傘倖瀚归柨鐔告灮閹风兘鏁撻敓锟�);//闁跨喐鏋婚幏鐑芥晸濞层儴绻戞导娆愬妞ょ敻鏁撻弬銈嗗闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔荤窛閿燂拷");
		}
	}
	/**
	 * 登陆
	 * @return
	 * @throws Exception
	 */
	public String logon() throws Exception{
		
		Student loginStudent = studentDao.login(student.getEmail(), student.getPassword());
		if(loginStudent != null){
			session.put("student", loginStudent);
			}else{
			addFieldError("", "闁跨喓鐓导娆愬闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱鐧屾导娆愬闁跨喖銈洪崙銈嗗闁跨噦鎷");
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
		return LOGOUT;
	}
	public String reg()throws Exception{
		return "input";
	}
	public String home() throws Exception{
		
		if( session.get("student")!= null){
			return INDEX;
		}else{
			addFieldError("", "闁跨喓鐓导娆愬闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱鐧屾导娆愬闁跨喖銈洪崙銈嗗闁跨噦鎷");
			return USER_LOGIN;
		}
		
	}
	
public String account() throws Exception{
		
	if( session.get("student")!= null){
		return ACCOUNT;
	}else{
		addFieldError("", "闁跨喓鐓导娆愬闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱鐧屾导娆愬闁跨喖銈洪崙銈嗗闁跨噦鎷");
		return USER_LOGIN;
	}
	}
public String myClass() throws Exception{
	
	if( session.get("student")!= null){
		return CLASS;
	}else{
		addFieldError("", "闁跨喓鐓导娆愬闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔告灮閹烽攱鐧屾导娆愬闁跨喖銈洪崙銈嗗闁跨噦鎷");
		return USER_LOGIN;
	}
	}
	// modelDriven
private Student student = new Student();
	// 再次密码
	private String repassword;
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	@Override
	public Student getModel() {
		return student;
	}
	
}
