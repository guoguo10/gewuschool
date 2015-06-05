package com.lqg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.AdminDao;
import com.lqg.model.Admin;
import com.lqg.service.AdminService;

@Service(value = "adminService")
@Transactional
public class AdminServiceImpl extends DaoSupportImpl<Admin> implements AdminService{
	@Autowired
	public AdminDao adminDao;
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Admin login(String username, String password) {
		if(username != null && password != null){
			String where = "where username=? and password=?";
			Object[] queryParams = {username,password};
			List<Admin> list =adminDao. find(-1, -1, where, queryParams).getList();
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean isUnique(String username) {
		List list = getTemplate().find("from Admin where username = ?", username);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
}
