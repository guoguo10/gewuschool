package com.lqg.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import com.lqg.model.Sex;
import com.lqg.model.product.UploadFile;
/**
 * �ҳ�
 * @author Li Qiuguo
 */
public class Parent implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;//id
	private String username;//�û���
	private String nickname;//�ǳ�
	private String password;// ����
	private String sex;//�Ա�
	private String birthday;//����
	private String introduction;//����
	private UploadFile image;//ͷ��ͼƬ
	private String email;// �ʼ�
	private String active="0";//�Ƿ񼤻�
   private String randomCode; //������
	private Set<Student> children = new TreeSet<Student>();//����
	
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
	public Set<Student> getChildren() {
		return children;
	}
	public void setChildren(Set<Student> children) {
		this.children = children;
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
