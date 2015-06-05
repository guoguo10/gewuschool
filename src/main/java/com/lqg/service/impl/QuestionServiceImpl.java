package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Question;
import com.lqg.service.QuestionService;

@Service(value = "questionService")
@Transactional
public class QuestionServiceImpl extends DaoSupportImpl<Question> implements QuestionService{

}
