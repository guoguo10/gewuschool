package com.lqg.dao.impl;

import org.springframework.stereotype.Repository;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.BookItemDao;
import com.lqg.model.BookItem;
@Repository("bookItemDao")
public class BookItemDaoImpl extends DaoSupportImpl<BookItem> implements BookItemDao {

}
