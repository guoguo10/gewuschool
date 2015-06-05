package com.lqg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.ParentDao;
import com.lqg.model.Parent;

@Repository("parentDao")
@Transactional
public class ParentDaoImpl extends DaoSupportImpl<Parent> implements ParentDao {

	
	
	
}
