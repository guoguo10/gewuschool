package com.lqg.service;

import java.util.List;

import com.lqg.base.DaoSupport;
import com.lqg.model.Video;

public interface  VideoService extends DaoSupport<Video> {
	public List<Video> findCommend();
	public List<Video> findBrowsedCount();
	public List<Video> findThankCount();
}
