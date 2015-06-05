package com.lqg.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.json.annotations.JSON;
/**
 * 感谢
 * @author Li Qiuguo
 */
public class Thank implements Serializable{
	private static final long serialVersionUID = 1L;
	//id
	private Integer id;
	//内容
	private String content;
	//视频
	private Video video;
	//学生
	private Student student;
	//老师
	private Teacher teacher;
	//家长
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
	public Video getVideo() {
		return  video;
	}
	public void setVideo(Video video) {
		this.video=video;
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
