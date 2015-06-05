package com.lqg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.TeacherDao;
import com.lqg.model.Teacher;

@Repository("teacherDao")
@Transactional
public class TeacherDaoImpl extends DaoSupportImpl<Teacher> implements TeacherDao {

	
	
	
}
