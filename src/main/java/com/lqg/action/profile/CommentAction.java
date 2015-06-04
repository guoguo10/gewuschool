package com.lqg.action.profile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.PageModel;
import com.lqg.model.profile.Comment;
import com.lqg.model.user.Parent;
import com.lqg.model.user.Student;
import com.lqg.model.user.Teacher;
import com.lqg.util.StringUitl;
import com.opensymphony.xwork2.ModelDriven;
/**
 * commentAction
 * @author Li Qiuguo
 */
@Scope("prototype")
@Controller("commentAction")
public class CommentAction extends BaseAction implements ModelDriven<Comment>{
	private static final long serialVersionUID = 1L;

	private static final String QUESTION = "question";

	private static final String ANSWER = "answer";

	private static final String THANK = "thank";
	/**
	 * 列表
	 * @return String
	 * @throws Exception
	 */
	public String select() throws Exception {
		if(comment.getId() != null && comment.getId() > 0){
			comment = commentDao.get(comment.getId());
			commentDao.update(comment);
		}
		return SELECT;
	}
	/**
	 * id找列表
	 * @return String
	 * @throws Exception
	 */
	public String getByQuestionId() throws Exception{
		if(comment.getQuestion().getId() != null){
			String where = "where questionId = ?";
			Object[] queryParams = {comment.getQuestion().getId()};
			pageModel = commentDao.find(pageNo, pageSize, where, queryParams);
		}
		return LIST;
	}
	/**
	 *answerid找列表
	 * @return String
	 * @throws Exception
	 */
	public String getByAnswerId() throws Exception{
		if(comment.getAnswer().getId() != null){
			String where = "where answerId = ?";
			Object[] queryParams = {comment.getAnswer().getId()};
			pageModel = commentDao.find(pageNo, pageSize, where, queryParams);
		}
		return LIST;
	}
	/**
	 * thankid找列表
	 * @return String
	 * @throws Exception
	 */
	public String getByThankId() throws Exception{
		if(comment.getThank().getId() != null){
			String where = "where thankId = ?";
			Object[] queryParams = {comment.getThank().getId()};
			pageModel = commentDao.find(pageNo, pageSize, where, queryParams);
		}
		return LIST;
	}
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String saveByQuestion() throws Exception{
		
				if(comment.getQuestion() != null && comment.getQuestion().getId() != null){
					comment.setQuestion(questionDao.load(comment.getQuestion().getId()));
					comment.setCreateTime(new Date());
					 if(session.get("type").equals("student")){
						 Student student=(Student)session.get("user");
						 comment.setStudent(student);
					 }
					 if(session.get("type").equals("teacher")){
						Teacher teacher=(Teacher)session.get("user");
						 comment.setTeacher(teacher);
					 }
					 if(session.get("type").equals("parent")){
						 Parent parent=(Parent)session.get("user");
						comment.setParent(parent);
					 }
					
				}			
		
		commentDao.saveOrUpdate(comment);
		return QUESTION;
	}
	
	/**
	 * 保存answer的comment
	 * @return
	 * @throws Exception
	 */
	public String saveByAnswer() throws Exception{
		
				if(comment.getAnswer() != null && comment.getAnswer().getId() != null){
					comment.setAnswer(answerDao.load(comment.getAnswer().getId()));
					comment.setCreateTime(new Date());
					if(session.get("type").equals("student")){
						 Student student=(Student)session.get("user");
						 comment.setStudent(student);
					 }
					 if(session.get("type").equals("teacher")){
						Teacher teacher=(Teacher)session.get("user");
						 comment.setTeacher(teacher);
					 }
					 if(session.get("type").equals("parent")){
						 Parent parent=(Parent)session.get("user");
						comment.setParent(parent);
					 }
					
				}			
		
		commentDao.saveOrUpdate(comment);
		return ANSWER;
	}
	/**
	 * 保存thank的comment
	 * @return
	 * @throws Exception
	 */
	public String saveByThank() throws Exception{
		
				if(comment.getThank() != null && comment.getThank().getId() != null){
					comment.setThank(thankDao.load(comment.getThank().getId()));
					comment.setCreateTime(new Date());
					if(session.get("type").equals("student")){
						 Student student=(Student)session.get("user");
						 comment.setStudent(student);
					 }
					 if(session.get("type").equals("teacher")){
						Teacher teacher=(Teacher)session.get("user");
						 comment.setTeacher(teacher);
					 }
					 if(session.get("type").equals("parent")){
						 Parent parent=(Parent)session.get("user");
						comment.setParent(parent);
					 }
					
				}			
		
		commentDao.saveOrUpdate(comment);
		return THANK;
	}
	/**
	 * 新的comment
	 * @return
	 * @throws Exception
	 */
	public String findNewComment() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("createTime", "desc");
		pageModel = commentDao.find(1, 5, orderby );
		return "list";
	}
	/**
	 * thank的comment
	 * @return
	 * @throws Exception
	 */
	public String findThankComment() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("thankCount", "desc");
		pageModel = commentDao.find(1, 5, orderby );
		return "list";
	}
	/**
	 * 推荐的从门徒
	 * @return
	 * @throws Exception
	 */
	public String findCommendComment() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("createTime", "desc");
		String where = "where recommended = ?";
		Object[] queryParams = {true};
		pageModel = commentDao.find(where, queryParams, orderby, 1, 5);
		return "list";
	}
	/**
	 * 喜欢的从门徒
	 * @return
	 * @throws Exception
	 */
	public String findEnjoyComment() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("browserTimes", "desc");
		pageModel = commentDao.find(1, 5, orderby );
		return "list";
	}
	/**
	 * 内容的comment
	 * @return String
	 * @throws Exception
	 */
	public String findByContent() throws Exception {
		if(comment.getContent() != null){
			String where = "where name like ?";
			Object[] queryParams = {"%" + comment.getContent() + "%"};
			pageModel = commentDao.find(pageNo, pageSize, where, queryParams );
		}
		return LIST;
	}
	/**
	 * 时间的comment
	 * @return String
	 * @throws Exception
	 */
	public String findByBrowseTimes() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("browseTimes", "desc");
		pageModel = commentDao.find(1, 8, orderby );
		return "list";
	}
	/**
	 * 推荐的comment
	 *  @return String
	 * @throws Exception
	 */
	public String findByCommend() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("thankCount", "desc");
		String where = "where recommended = ?";
		Object[] queryParams = {true};
		pageModel = commentDao.find(where, queryParams, orderby, pageNo, pageSize);
		return "findList";
	}
	/**
	 * 感谢次数的从门徒
	 * @return String
	 * @throws Exception
	 */
	public String findByThankCount() throws Exception{
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("thankCount", "desc");
		pageModel = commentDao.find(1, 6, orderby );
		return "findList";
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public String add() throws Exception {
		return INPUT;
	}
	
	/**
	 * 列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		pageModel = commentDao.find(pageNo, pageSize);
		return LIST;
	}
	/**
	 * 编辑
	 *  @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		this.comment = commentDao.get(comment.getId());
		return EDIT;
	}
	/**
	 * 删除
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		commentDao.delete(comment.getId());
		return list();
		}
	
	/**
	 * 确保保存
	 */
	public void validateSave() {
		if(!StringUitl.validateString(comment.getContent())){
			addFieldError("name", "濞屸剝婀侀崘娆忓敶鐎癸拷");
		}
	}


	// modeldriven
	private Comment comment = new Comment();
	
	// map
	private Map<Integer, String> map;
	// pageModel
	private PageModel<Comment> pageModel;
	
	@Override
	public Comment getModel() {
		return comment;
	}
	public Map<Integer, String> getMap() {
		return map;
	}
	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}
	public PageModel<Comment> getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel<Comment> pageModel) {
		this.pageModel = pageModel;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
}
