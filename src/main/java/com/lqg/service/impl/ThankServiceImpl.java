package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Thank;
import com.lqg.service.ThankService;

@Service(value = "thankService")
@Transactional
public class ThankServiceImpl extends DaoSupportImpl<Thank> implements ThankService{

}
