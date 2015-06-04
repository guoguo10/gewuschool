package com.lqg.model;

import java.util.List;
/**
 * 封装model的类
 * @author Li Qiuguo
 * @param <T> 泛型参数
 */
public class PageModel<T> {
	private int totalRecords;//总共的记录数
	private List<T> list;//model的列别
	private int pageNo;//第几页
	private int pageSize;//每页的数目
	private int pages;//总共的页数
	/**
	 * 取回第一页
	 * @return 第一页
	 */
	public int getTopPageNo() {
		return 1;
	}
	/**
	 * 取前一页
	 * @return 前一页
	 */
	public int getPreviousPageNo() {
		if (pageNo <= 1) {
			return 1;
		}
		return pageNo -1;
	}
	/**
	 * 取下一页
	 * @return 下一页
	 */
	public int getNextPageNo() {
		if (pageNo >= getTotalPages()) {
			return getTotalPages() == 0 ? 1 : getTotalPages();
		}
		return pageNo + 1;
	}
	/**
	 * 取最后一页
	 * @return 最后一页
	 */
	public int getBottomPageNo() {
		return getTotalPages() == 0 ? 1 : getTotalPages();
	}
	/**
	 * 取总共页数
	 * @return
	 */
	public int getTotalPages() {
		return (totalRecords + pageSize - 1) / pageSize;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPages() {
		return getTotalPages();
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
}
