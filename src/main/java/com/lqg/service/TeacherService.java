package com.lqg.service;

import com.lqg.base.DaoSupport;
import com.lqg.model.Teacher;

public interface  TeacherService extends DaoSupport<Teacher> {
	public Teacher login(String email, String password);
	public boolean isUnique(String email);
}
