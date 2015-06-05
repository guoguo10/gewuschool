package com.lqg.model;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.json.annotations.JSON;
/**
 * bookItem 绯诲垪
 * @author Li Qiuguo
 */
public class BookItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// id
	private String name;// 鍚嶇О
	private int level = 1;// 绾у埆
	private Set<BookItem> children;// 瀛愮郴鍒�
	private BookItem parent;// 鎵�湪鐩綍
	private BookItem parentNext=null;//鐖朵翰鐨剆ibling
	private Set<Video> videos = new TreeSet<Video>();// 鐩稿叧瑙嗛
	private Book book;//鎵�湪涔︽湰
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
	public Set<BookItem> getChildren() {
		return children;
	}
	public void setChildren(Set<BookItem> children) {
		this.children = children;
	}
	@JSON(serialize=false)
	public BookItem getParent() {
		return parent;
	}
	public void setParent(BookItem parent) {
		this.parent = parent;
	}
	public Set<Video> getVideos() {
		return videos;
	}
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@JSON(serialize=false)
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@JSON(serialize=false)
	public BookItem getParentNext() {
		return parentNext;
	}
	public void setParentNext(BookItem parentNext) {
		this.parentNext = parentNext;
	}
}
