package com.lqg.service;

import com.lqg.base.DaoSupport;
import com.lqg.model.Student;

public interface  StudentService extends DaoSupport<Student> {
	public Student login(String email, String password);
	public boolean isUnique(String email);
}
