package com.lqg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Student;
import com.lqg.service.StudentService;

@Service(value = "sudentService")
@Transactional
public class StudentServiceImpl extends DaoSupportImpl<Student> implements StudentService{
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Student login(String email, String password) {
		if(email != null && password != null){
			String where = "where email=? and password=?";
			Object[] queryParams = {email,password};
			List<Student> list = find(-1, -1, where, queryParams).getList();
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
		List list = getTemplate().find("from Student where email= ?", email);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
	
}
