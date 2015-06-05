package com.lqg.model;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.json.annotations.JSON;
/**
 * 评论
 * @author Li Qiuguo
 */
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	//id
	private Integer id;
	//内容
	private String content;
	//评论的学生
	private Student student;
	//评论的老师
	private Teacher teacher;
	//评论的家长
	private Parent parent;
	//父评论
	private Comment parentComment;
	//子评论
	private Set<Comment> children;
	//评论的问题
	private Question question;
	//评论的回答
	private Answer answer;
	//评论的感谢
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
