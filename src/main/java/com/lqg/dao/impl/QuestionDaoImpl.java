package com.lqg.dao.impl;

import org.springframework.stereotype.Repository;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.QuestionDao;
import com.lqg.model.Question;
@Repository("questionDao")
public class QuestionDaoImpl extends DaoSupportImpl<Question> implements QuestionDao {

}
