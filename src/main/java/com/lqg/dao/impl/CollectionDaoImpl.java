package com.lqg.dao.impl;

import org.springframework.stereotype.Repository;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.CollectionDao;
import com.lqg.model.Collection;
@Repository("collectionDao")
public class CollectionDaoImpl extends DaoSupportImpl<Collection> implements CollectionDao {

}
