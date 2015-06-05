package com.lqg.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
/**
 * 课程
 * @author Li Qiuguo
 */
public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	// id
	private Integer id;
	//名称
	private String name;
	//书本集合
	private Set<Book> books=new TreeSet<Book>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Book> getBooks() {
		return  books;
	}
	public void setBooks(Set<Book> books) {
		this.books=books;
	}
}
