package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Course;
import com.lqg.service.CourseService;

@Service(value = "courseService")
@Transactional
public class CourseServiceImpl extends DaoSupportImpl<Course> implements CourseService{

}
