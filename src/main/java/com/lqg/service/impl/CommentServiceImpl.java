package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Admin;
import com.lqg.model.Comment;
import com.lqg.service.AdminService;
import com.lqg.service.CommentService;

@Service(value = "commentService")
@Transactional
public class CommentServiceImpl extends DaoSupportImpl<Comment> implements CommentService{

}
