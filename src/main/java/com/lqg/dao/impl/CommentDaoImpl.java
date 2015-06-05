package com.lqg.dao.impl;

import org.springframework.stereotype.Repository;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.CommentDao;
import com.lqg.model.Comment;
@Repository("commentDao")
public class CommentDaoImpl extends DaoSupportImpl<Comment> implements CommentDao {

}
