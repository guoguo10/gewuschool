package com.lqg.service;

import com.lqg.base.DaoSupport;
import com.lqg.model.Book;

public interface  BookService extends DaoSupport<Book> {
	public Book findByName(String name);
}
