package com.lqg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.BookDao;
import com.lqg.model.Book;
@Repository("bookDao")
public class BookDaoImpl extends DaoSupportImpl<Book> implements BookDao {
	

}
