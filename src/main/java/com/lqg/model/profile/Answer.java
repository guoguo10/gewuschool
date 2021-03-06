package com.lqg.model.profile;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.json.annotations.JSON;

import com.lqg.model.profile.Comment;
import com.lqg.model.user.Parent;
import com.lqg.model.user.Student;
import com.lqg.model.user.Teacher;
/**
	 * 回答
	 */
public class Answer implements Serializable {	
	private static final long serialVersionUID = 1L;
     	//id
		private Integer id;
		// 内容
		private String content;
		// 问题
		private Question question;
		//回答的学生
		private Student student;
		//回答的老师
		private Teacher teacher;
		//回答的家长
		private Parent parent;
		//评论
		private Set<Comment> comments=new TreeSet<Comment>();
		private Date createTime = new Date();
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		@JSON(serialize=false)
		public Question getQuestion() {
			return question;
		}
		public void setQuestion(Question question) {
			this.question = question;
		}
		public Student getStudent() {
			return student;
		}
		public void setStudent(Student student) {
			this.student = student;
		}
		public Teacher getTeacher() {
			return teacher;
		}
		public void setTeacher(Teacher teacher) {
			this.teacher = teacher;
		}
		public Parent getParent() {
			return parent;
		}
		public void setParent(Parent parent) {
			this.parent = parent;
		}
		public Set<Comment> getComments() {
			return comments;
		}
		public void setComments(Set<Comment> comments) {
			this.comments = comments;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

}
