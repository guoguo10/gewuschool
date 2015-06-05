package com.lqg.model;

import java.io.Serializable;
/**
	 * 路径
	 */
public class Route implements Serializable {	
	private static final long serialVersionUID = 1L;
	    // id
		private Integer id;
		// 视频
		private Video video;
		//评论
		private Comment comment;
		//问题
		private Question question;
		//回答
		private Answer answer;
		//学生
		private Student student;
		//老师
		private Teacher teacher;
		//家长
		private Parent parent;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
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
		
		public Comment getComment() {
			return comment;
		}
		public void setComment(Comment comment) {
			this.comment = comment;
		}
		public Question getQuestion() {
			return question;
		}
		public void setQuestion(Question question) {
			this.question = question;
		}
		public Video getVideo() {
			return video;
		}
		public void setVideo(Video video) {
			this.video = video;
		}
		public Answer getAnswer() {
			return answer;
		}
		public void setAnswer(Answer answer) {
			this.answer = answer;
		}

}
