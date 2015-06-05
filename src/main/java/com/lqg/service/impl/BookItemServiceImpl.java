package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.BookItem;
import com.lqg.service.BookItemService;

@Service(value = "bookItemService")
@Transactional
public class BookItemServiceImpl extends DaoSupportImpl<BookItem> implements BookItemService{

}
