package com.lqg.action.product;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lqg.action.BaseAction;
import com.lqg.model.Book;
import com.lqg.model.PageModel;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 书本的action
 * @author Li Qiuguo
 *
 */
@Scope("prototype")
@Controller("bookAction")
public class BookAction extends BaseAction implements ModelDriven<Book>{
	private static final long serialVersionUID = 1L;
	private static final String BOOK = "book";
	private Book book = new Book();
	@Override
	public Book getModel() {
		return book;
	}
	
	@Override
	public String add() throws Exception {
		
		return INPUT;
	}
	/**
	 * 保存书本
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception{
		
		bookDao.saveOrUpdate(book);
		return list();//放回书本的列表
	}
	/**
	 * 书本的列表
	 *  @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		
		pageModel = bookDao.find(pageNo,pageSize);
		return LIST;
		}
	/**
	 * 编辑
	 * @return String
	 * @throws Exception
	 */
	public String edit() throws Exception{
		if(book.getId() != null && book.getId() > 0){
			this.book = bookDao.get(book.getId());
		}
		return EDIT;
	}
	/**
	 * 删除
	 * @return String
	 * @throws Exception
	 */
	public String del() throws Exception{
		if(book.getId() != null && book.getId() > 0){
			bookDao.delete(book.getId());
			}
		return list();
	}
	/**
	 * 书名找书
	 *  @return String
	 * @throws Exception
	 */
	public String findByName() throws Exception {
		String where = "where name = ?";
		Object[] queryParams = {book.getName()};
		pageModel= bookDao.find(-1, -1, where, queryParams);
		
		return BOOK;
	}
	
	
	// 怕个Model
	private PageModel<Book> pageModel;
	
	
	public PageModel<Book> getPageModel() {
		return pageModel;
	}
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
}
