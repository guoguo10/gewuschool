package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Collection;
import com.lqg.service.CollectionService;

@Service(value = "collectionService")
@Transactional
public class CollectionServiceImpl extends DaoSupportImpl<Collection> implements CollectionService{

}
