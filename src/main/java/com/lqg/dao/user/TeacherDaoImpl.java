package com.lqg.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.dao.DaoSupport;
import com.lqg.model.user.Teacher;

@Repository("teacherDao")
@Transactional
public class TeacherDaoImpl extends DaoSupport<Teacher> implements TeacherDao {

	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Teacher login(String email, String password) {
		if(email != null && password != null){
			String where = "where email=? and password=?";
			Object[] queryParams = {email,password};
			List<Teacher> list = find(-1, -1, where, queryParams).getList();
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
		List list = getTemplate().find("from Teacher where email = ?", email);
		if(list != null && list.size() > 0){
			return false;
		}
		return true;
	}
	
	
}
