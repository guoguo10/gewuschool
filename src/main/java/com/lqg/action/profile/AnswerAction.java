package com.lqg.action.profile;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.Answer;
import com.lqg.model.PageModel;
import com.lqg.model.Parent;
import com.lqg.model.Student;
import com.lqg.model.Teacher;
import com.opensymphony.xwork2.ModelDriven;
/**
 * answerAction
 * @author Li Qiuguo
 *
 */
@Scope("prototype")
@Controller("answerAction")
public class AnswerAction extends BaseAction implements ModelDriven<Answer>{
	private static final long serialVersionUID = 1L;
	private static final String ANSWER = "answer";
	private static final String SAVEANSWER = "saveAnswer";
	private Answer answer = new Answer();
	@Override
	public Answer getModel() {
		return answer;
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
		if(answer.getQuestion() != null && answer.getQuestion().getId() != null){
			answer.setQuestion(questionDao.load(answer.getQuestion().getId()));
			answer.setCreateTime(new Date());
			 if(session.get("type").equals("student")){
				 Student student=(Student)session.get("user");
				 answer.setStudent(student);
			 }
			 if(session.get("type").equals("teacher")){
				Teacher teacher=(Teacher)session.get("user");
				 answer.setTeacher(teacher);
			 }
			 if(session.get("type").equals("parent")){
				 Parent parent=(Parent)session.get("user");
				 answer.setParent(parent);
			 }
			
		}
		
		answerDao.saveOrUpdate(answer);
		return SAVEANSWER;
	}
	/**
	 *列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		
		pageModel = answerDao.find(pageNo,pageSize);
		return LIST;
		}
	/**
	 * 编辑
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		if(answer.getId() != null && answer.getId() > 0){
			this.answer = answerDao.get(answer.getId());
		}
		return EDIT;
	}
	/**
	 * 删除
	 *  @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		if(answer.getId() != null && answer.getId() > 0){
			answerDao.delete(answer.getId());
			}
		return list();
	}
	/**
	 * 名字找回答
	 * @return String
	 * @throws Exception
	 */
	public String findByName() throws Exception {
		String where = "where name = ?";
		Object[] queryParams = {answer.getContent()};
		pageModel= answerDao.find(-1, -1, where, queryParams);
		
		return ANSWER;
	}
	
	
	// pageModel
	private PageModel<Answer> pageModel;
	
	
	public PageModel<Answer> getPageModel() {
		return pageModel;
	}
	public Answer getAnswer() {
		return answer;
	}
	
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
}
