package com.lqg.dao.impl;

import org.springframework.stereotype.Repository;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.CourseDao;
import com.lqg.model.Course;
@Repository("courseDao")
public class CourseDaoImpl extends DaoSupportImpl<Course> implements CourseDao {

}
