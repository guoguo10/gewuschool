package com.lqg.action.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.Teacher;
import com.lqg.util.AppException;
import com.opensymphony.xwork2.ModelDriven;
/**
 * teacherAction
 * @author Li Qiuguo
 */
@Scope("prototype")
@Controller("teacherAction")
public class TeacherAction extends BaseAction implements ModelDriven<Teacher>{
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
		boolean unique = teacherDao.isUnique(teacher.getEmail());
		if(unique){
			teacherDao.save(teacher);
			return USER_LOGIN;
		}else{
			throw new AppException("閿熸枻鎷烽敓鐭紮鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓锟");
		}
	}
	/**
	 * 登陆
	 * @return
	 * @throws Exception
	 */
	public String logon() throws Exception{
		Teacher loginTeacher = teacherDao.login(teacher.getEmail(), teacher.getPassword());
		if(loginTeacher != null){
			session.put("teacher", loginTeacher);
		}else{
			addFieldError("", "閿熺煫浼欐嫹閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷锋癌浼欐嫹閿熼ズ鍑ゆ嫹閿燂拷");
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
	private Teacher teacher = new Teacher();
	// 再次密码
	private String repassword;
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	@Override
	public Teacher getModel() {
		return teacher;
	}
}
