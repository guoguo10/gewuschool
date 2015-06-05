package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Focus;
import com.lqg.service.FocusService;

@Service(value = "focusService")
@Transactional
public class FocusServiceImpl extends DaoSupportImpl<Focus> implements FocusService{

}
