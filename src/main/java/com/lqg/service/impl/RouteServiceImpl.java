package com.lqg.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Route;
import com.lqg.service.RouteService;

@Service(value = "routeService")
@Transactional
public class RouteServiceImpl extends DaoSupportImpl<Route> implements RouteService{

}
