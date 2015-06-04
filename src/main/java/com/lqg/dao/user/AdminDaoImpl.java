package com.lqg.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.dao.DaoSupport;
import com.lqg.model.user.Admin;

@Repository("adminDao")
@Transactional
public class AdminDaoImpl extends DaoSupport<Admin> implements AdminDao {

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Admin login(String username, String password) {
		if(username != null && password != null){
			String where = "where username=? and password=?";
			Object[] queryParams = {username,password};
			List<Admin> list = find(-1, -1, where, queryParams).getList();
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
