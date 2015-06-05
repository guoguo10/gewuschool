package com.lqg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.StudentDao;
import com.lqg.model.Student;

@Repository("studentDao")
@Transactional
public class StudentDaoImpl extends DaoSupportImpl<Student> implements StudentDao {

	
	
}
