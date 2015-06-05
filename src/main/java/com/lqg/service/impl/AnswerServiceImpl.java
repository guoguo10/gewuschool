package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Answer;
import com.lqg.service.AnswerService;

@Service(value = "answerService")
@Transactional
public class AnswerServiceImpl extends DaoSupportImpl<Answer> implements AnswerService{

}
