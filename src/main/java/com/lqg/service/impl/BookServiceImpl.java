package com.lqg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Book;
import com.lqg.service.BookService;

@Service(value = "bookService")
@Transactional
public class BookServiceImpl extends DaoSupportImpl<Book> implements BookService{
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Book findByName(String name) {
		String where = "where name = ?";
		Object[] queryParams = {name};
		List<Book> list = find(-1, -1, where, queryParams).getList();
		return list.get(0);
	}
}
