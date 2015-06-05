package com.lqg.dao.impl;

import org.springframework.stereotype.Repository;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.AnswerDao;
import com.lqg.model.Answer;
@Repository("answerDao")
public class AnswerDaoImpl extends DaoSupportImpl<Answer> implements AnswerDao {

}
