package com.lqg.model;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.json.annotations.JSON;
/**
 * ����
 * @author Li Qiuguo
 */
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	//id
	private Integer id;
	//����
	private String content;
	//���۵�ѧ��
	private Student student;
	//���۵���ʦ
	private Teacher teacher;
	//���۵ļҳ�
	private Parent parent;
	//������
	private Comment parentComment;
	//������
	private Set<Comment> children;
	//���۵�����
	private Question question;
	//���۵Ļش�
	private Answer answer;
	//���۵ĸ�л
	private Thank thank;
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
	
	public Student getStudent() {
		return  student;
	}
	public void setStudent(Student student) {
		this.student=student;
	}
	public Teacher getTeacher() {
		return  teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher=teacher;
	}
	public Parent getParent() {
		return  parent;
	}
	public void setParent(Parent parent) {
		this.parent=parent;
	}
	@JSON(serialize=false)
	public Comment getParentComment() {
		return  parentComment;
	}
	public void setParentComment(Comment parentComment) {
		this.parentComment=parentComment;
	}
	public Set<Comment> getChildren() {
		return  children;
	}
	public void setChildren(Set<Comment> children) {
		this.children=children;
	}
	@JSON(serialize=false)
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	@JSON(serialize=false)
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	@JSON(serialize=false)
	public Thank getThank() {
		return thank;
	}
	public void setThank(Thank thank) {
		this.thank = thank;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	}
