package com.lqg.action.product;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.PageModel;
import com.lqg.model.product.BookItem;
import com.opensymphony.xwork2.ModelDriven;
/**
 * bookItem action
 * @author Li Qiuguo
 *
 */
@Scope("prototype")
@Controller("bookItemAction")
public class BookItemAction extends BaseAction implements ModelDriven<BookItem>{
	private static final long serialVersionUID = 1L;
	private static final String SECTIONS = "sections";
	private BookItem bookItem = new BookItem();
	
	
	@Override
	public BookItem getModel() {
		return bookItem;
	}
	
	@Override
	public String add() throws Exception {
		if(pid != null && pid > 0 ){
			BookItem parent = bookItemDao.load(pid);
			// 设置level
			bookItem.setLevel(parent.getLevel() + 1);
		}
		return INPUT;
	}
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		if(pid != null && pid > 0 ){
			bookItem.setParent(bookItemDao.load(pid));
		}
		bookItemDao.saveOrUpdate(bookItem);
		return list();
	}
	/**
	 * 查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		Object[] params = null;
		String where;
		if(pid != null && pid > 0 ){
			where = "where parent.id =?";
			params = new Integer[]{pid};
		}else{
			where = "where parent is null";
		}
		pageModel = bookItemDao.find(pageNo,pageSize,where,params);
		return LIST;
	}
	/**
	 * 编辑
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		if(bookItem.getId() != null && bookItem.getId() > 0){
			this.bookItem = bookItemDao.get(bookItem.getId());
		}
		return EDIT;
	}
	/**
	 * 删除
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		if(bookItem.getId() != null && bookItem.getId() > 0){
			bookItemDao.delete(bookItem.getId());
		}
		return list();
	}
	public String findByBook() throws Exception{
			
		 String where = "where  book.id =? and level =?";
		 Object[] params = new Integer[]{bookId,1};
		
		pageModel = bookItemDao.find(pageNo,pageSize,where,params);
		return SECTIONS;
	}
	
	public String select() throws Exception {
		if(bookItem.getId() != null && bookItem.getId() > 0){
			bookItem = bookItemDao.get(bookItem.getId());
		}
		return SELECT;
	}
	
	
	// 父亲节点id
	private Integer pid;
	// 自己的节点id
	private Integer bookId;
	private PageModel<BookItem> pageModel;
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public PageModel<BookItem> getPageModel() {
		return pageModel;
	}
	public BookItem getBookItem() {
		return bookItem;
	}
	public void setBookItem(BookItem bookItem) {
		this.bookItem = bookItem;
	}
}
