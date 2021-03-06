package com.lqg.action;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;































import com.lqg.dao.AdminDao;
import com.lqg.dao.AnswerDao;
import com.lqg.dao.BookDao;
import com.lqg.dao.BookItemDao;
import com.lqg.dao.CollectionDao;
import com.lqg.dao.CommentDao;
import com.lqg.dao.CourseDao;
import com.lqg.dao.FocusDao;
import com.lqg.dao.ParentDao;
import com.lqg.dao.QuestionDao;
import com.lqg.dao.RoomDao;
import com.lqg.dao.RouteDao;
import com.lqg.dao.StudentDao;
import com.lqg.dao.TeacherDao;
import com.lqg.dao.ThankDao;
import com.lqg.dao.UploadFileDao;
import com.lqg.dao.VideoDao;
import com.lqg.model.Admin;
import com.lqg.model.Parent;
import com.lqg.model.Student;
import com.lqg.model.Teacher;
import com.opensymphony.xwork2.ActionSupport;
/**
 * baseAction
 * @author Li Qiuguo
 */
public class BaseAction extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware {
	private static final long serialVersionUID = 1L;
	protected Integer id;
	protected Integer[] ids;
	protected int pageNo = 1;
	protected int pageSize = 8;
	
	public static final String LIST = "list";
	public static final String EDIT = "edit";
	public static final String ADD = "add";
	public static final String SELECT = "select";
	public static final String QUERY = "query";
	public static final String LEFT = "left";
	public static final String RIGHT = "right";
	public static final String INDEX = "index";
	public static final String MAIN = "main";
	public static final String MANAGER = "manager";
	public static final String TOP = "top";
	public static final String REG = "reg";
	public static final String USER_LOGIN = "userLogin";
	public static final String ADMIN_LOGIN = "adminLogin";
	public static final String LOGOUT = "logout";
	
	// 获取老师
	public Teacher getLoginTeacher(){
		if(session.get("teacher") != null){
			return (Teacher) session.get("teacher");
		}
		return null;
	}
	//获取学生
	public Student getLoginStudent(){
		if(session.get("student") != null){
			return (Student) session.get("student");
		}
		return null;
	}
	//获取家长
	public Parent getLoginParent(){
		if(session.get("parent") != null){
			return (Parent) session.get("parent");
		}
		return null;
	}

	// 获取管理员
	public Admin getLoginAdmin(){
		if(session.get("admin") != null){
			return (Admin) session.get("admin");
		}
		return null;
	}
	
	
	// 注入个各种Dao
	@Autowired
	protected BookDao bookDao;
	@Autowired
	protected BookItemDao bookItemDao;
	@Autowired
	protected CommentDao commentDao;
	@Autowired
	protected CourseDao courseDao;
	@Autowired
	protected ThankDao thankDao;
	@Autowired
	protected UploadFileDao uploadFileDao;
	@Autowired
	protected VideoDao videoDao;
	
	@Autowired
	protected AdminDao adminDao;
	@Autowired
	protected ParentDao parentDao;
	@Autowired
	protected TeacherDao teacherDao;
	@Autowired
	protected StudentDao studentDao;
	@Autowired
	protected RoomDao roomDao;
	
	//profile
	@Autowired
	protected AnswerDao answerDao;
	@Autowired
	protected CollectionDao collectionDao;
	@Autowired
	protected FocusDao focusDao;
	@Autowired
	protected QuestionDao questionDao;
	@Autowired
	protected RouteDao routeDao;
	
	
	
	// Map的request
	protected Map<String, Object> request;
	// Map的session
	protected Map<String, Object> session;
	// Map的application
	protected Map<String, Object> application;
	
	@Override
	public void setRequest(Map<String, Object> request) {
		
		this.request = request;
	}
	@Override
	public void setApplication(Map<String, Object> application) {
	
		this.application = application;
	}
	@Override
	public void setSession(Map<String, Object> session) {
	
		this.session = session;
	}
	
	// index
	public String index() throws Exception {
		return INDEX;
	}
	public String manager() throws Exception {
		return MANAGER;
	}
	public String main() throws Exception {
		return MAIN;
	}
	public String add() throws Exception {
		return ADD;
	}
	public String select() throws Exception {
		return SELECT;
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	public String top() throws Exception {
		return TOP;
	}
	public String left() throws Exception {
		return LEFT;
	}
	public String right() throws Exception {
		return RIGHT;
	}
	public String reg() throws Exception{
		return REG;
	}
	public String query() throws Exception{
		return QUERY;
	}
	// getter潞脥settter路艙路拧
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
