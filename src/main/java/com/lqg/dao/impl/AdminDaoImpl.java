package com.lqg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.AdminDao;
import com.lqg.model.Admin;

@Repository("adminDao")
@Transactional
public class AdminDaoImpl extends DaoSupportImpl<Admin> implements AdminDao {

	
	
	
}
