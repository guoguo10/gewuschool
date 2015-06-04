package com.lqg.action.profile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.PageModel;
import com.lqg.model.profile.Question;
import com.lqg.model.user.Parent;
import com.lqg.model.user.Student;
import com.lqg.model.user.Teacher;
import com.opensymphony.xwork2.ModelDriven;
/**
 * questionAction
 * @author Li Qiuguo
 *
 */
@Scope("prototype")
@Controller("questionAction")
public class QuestionAction extends BaseAction implements ModelDriven<Question>{
	private static final long serialVersionUID = 1L;
	private static final String QUESTION = "question";
	private static final String QUESTIONS = "questions";
	private static final String MOREQUESTIONS = "moreQuestions";
	private static final String LIMITEDQUESTIONS = "limitedQuestions";
	private Question question = new Question();
	@Override
	public Question getModel() {
		return question;
	}
	
	@Override
	public String add() throws Exception {
		
		return INPUT;
	}
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		//
				if(question.getVideo() != null && question.getVideo().getId() != null){
					question.setVideo(videoDao.load(question.getVideo().getId()));
					question.setCreateTime(new Date());

					 if(session.get("type").equals("student")){
						 Student student=(Student)session.get("user");
						 question.setStudent(student);
					 }
					 if(session.get("type").equals("teacher")){
						Teacher teacher=(Teacher)session.get("user");
						 question.setTeacher(teacher);
					 }
					 if(session.get("type").equals("parent")){
						 Parent parent=(Parent)session.get("user");
						 question.setParent(parent);
					 }
				}
				
		
		questionDao.saveOrUpdate(question);
		return QUESTION;
	}
	/**
	 *列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		
		pageModel = questionDao.find(pageNo,pageSize);
		return LIST;
		}
	/**
	 * 编辑
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		if(question.getId() != null && question.getId() > 0){
			this.question = questionDao.get(question.getId());
		}
		return EDIT;
	}
	/**
	 * 删除
	 *  @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		if(question.getId() != null && question.getId() > 0){
			questionDao.delete(question.getId());
			}
		return list();
	}
	/**
	 * 视频的评论
	 *  @return String
	 * @throws Exception
	 */
	public String findByVideo() throws Exception {
		String where = "where videoId = ?";
		Object[] queryParams = {question.getVideo().getId()};
		Map<String, String> orderby = new  HashMap<String,String>();
		orderby.put("id", "desc");
		pageModel = questionDao.find( where, queryParams,orderby,pageNo, pageSize);//閹笛嗩攽閺屻儴顕楅弬瑙勭《
		return LIMITEDQUESTIONS;
	}
	

	/**
	 * 名字的视频
	 * @return String
	 * @throws Exception
	 */
	public String findByName() throws Exception {
		String where = "where name = ?";
		Object[] queryParams = {question.getContent()};
		pageModel= questionDao.find(-1, -1, where, queryParams);
		
		return QUESTIONS;
	}
	
	// 怕个Model
	private PageModel<Question> pageModel;
	
	
	public PageModel<Question> getPageModel() {
		return pageModel;
	}
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}

	
	
}
