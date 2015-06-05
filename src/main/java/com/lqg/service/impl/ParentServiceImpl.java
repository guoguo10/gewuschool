package com.lqg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Parent;
import com.lqg.service.ParentService;

@Service(value = "parentService")
@Transactional
public class ParentServiceImpl extends DaoSupportImpl<Parent> implements ParentService{
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Parent login(String email, String password) {
		if(email != null && password != null){
			String where = "where email=? and password=?";
			Object[] queryParams = {email,password};
			List<Parent> list = find(-1, -1, where, queryParams).getList();
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean isUnique(String email) {
		List list = getTemplate().find("from Parent where email = ?", email);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
}
