package com.lqg.model;

import java.io.Serializable;
/**
	 * ��ע
	 */
public class Focus implements Serializable {	
	private static final long serialVersionUID = 1L;
	// id
		private Integer id;
	 // ����
		private Question question;
		//ѧ��
		private Student student;
		//��ʦ
		private Teacher teacher;
		//�ҳ�
		private Parent parent;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		
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

}