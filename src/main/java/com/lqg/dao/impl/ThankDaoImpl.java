package com.lqg.dao.impl;

import org.springframework.stereotype.Repository;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.ThankDao;
import com.lqg.model.Thank;
@Repository("thankDao")
public class ThankDaoImpl extends DaoSupportImpl<Thank> implements ThankDao {

}
