package com.lqg.model.product;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import org.apache.struts2.json.annotations.JSON;
/**
 * bookItem 系列
 * @author Li Qiuguo
 */
public class BookItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// id
	private String name;// 名称
	private int level = 1;// 级别
	private Set<BookItem> children;// 子系列
	private BookItem parent;// 所在目录
	private BookItem parentNext=null;//父亲的sibling
	private Set<Video> videos = new TreeSet<Video>();// 相关视频
	private Book book;//所在书本
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
