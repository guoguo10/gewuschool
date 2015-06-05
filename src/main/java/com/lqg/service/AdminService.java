package com.lqg.service;

import com.lqg.base.DaoSupport;
import com.lqg.model.Admin;

public interface  AdminService extends DaoSupport<Admin> {
	public Admin login(String username, String password);
	public boolean isUnique(String username);
}
