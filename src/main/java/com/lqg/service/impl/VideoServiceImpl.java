package com.lqg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.PageModel;
import com.lqg.model.Video;
import com.lqg.service.VideoService;

@Service(value = "videoService")
@Transactional
public class VideoServiceImpl extends DaoSupportImpl<Video> implements VideoService{
	/**
	 * 閺冨爼妫块惃鍕笓閸掓澧犻崡锟�	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Video> findBrowsedCount() {
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("browseTimes", "desc");
		PageModel<Video> pageModel = find(1, 10, orderby);
		return pageModel.getList();
	}
	/**
	 * 閹恒劏宕�
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Video> findCommend() {
		String where = "where recommended=?";
		Object[] parames = {true};
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("createTime", "desc");
		PageModel<Video> pageModel = find(where,parames ,orderby,1,10);
		return pageModel.getList();
	}
	/**
	 * 閸撳秴宕勯幇鐔婚樋閻ㄥ嫯顫嬫０锟�	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Video> findThankCount() {
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("thankCount", "desc");
		PageModel<Video> pageModel = find(1, 10, orderby);
		return pageModel.getList();
	}
}
