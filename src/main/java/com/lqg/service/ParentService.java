package com.lqg.service;

import com.lqg.base.DaoSupport;
import com.lqg.model.Parent;

public interface  ParentService extends DaoSupport<Parent> {
	public Parent login(String email, String password);
	public boolean isUnique(String email);
}
