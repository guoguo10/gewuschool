package com.lqg.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
/**
 * 鑰佸笀
 * @author Li Qiuguo
 */
public class Teacher implements Serializable{
	private static final long serialVersionUID = 1L;
	// id
	private Integer id;
	//鏄电О
		private String nickname;
    //濮撳悕
		private String username;
	//瀵嗙爜
		private String password;
	//鎬у埆
		private String sex;
	//鐢熸棩
		private String birthday;
	//瀛︽牎
		private String school;
		//鍠滄鐨勮绋
		private String aptcourses;
		//浠嬬粛
		private String introduction;
		//澶村儚鍥剧墖顣
		private UploadFile image;
		//璇惧
		private Room room;
		// 閭欢
		private String email;
		//鏄惁婵�椿	
		private String active="0";
		// 婵�椿鐮�
	    private String randomCode; 
	    //瀛︾敓
		private Set<Student> students = new TreeSet<Student>();
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getSchool() {
			return school;
		}
		public void setSchool(String school) {
			this.school = school;
		}
		public String getAptcourses() {
			return aptcourses;
		}
		public void setAptcourses(String aptcourses) {
			this.aptcourses = aptcourses;
		}
		public String getIntroduction() {
			return introduction;
		}
		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}
		public UploadFile getImage() {
			return image;
		}
		public void setImage(UploadFile image) {
			this.image = image;
		}
		
		public Room getRoom() {
			return room;
		}
		public void setRoom(Room room) {
			this.room = room;
		}
		public Set<Student> getStudents() {
			return students;
		}
		public void setStudents(Set<Student> students) {
			this.students = students;
		}
		public String getActive() {
			return active;
		}
		public void setActive(String active) {
			this.active = active;
		}
		public String getRandomCode() {
			return randomCode;
		}
		public void setRandomCode(String randomCode) {
			this.randomCode = randomCode;
		}
}
