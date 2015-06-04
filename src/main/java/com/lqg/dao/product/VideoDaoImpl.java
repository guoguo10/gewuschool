package com.lqg.dao.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.dao.DaoSupport;
import com.lqg.model.PageModel;
import com.lqg.model.product.Video;
@Repository("videoDao")
@Transactional
public class VideoDaoImpl extends DaoSupport<Video> implements VideoDao {

	/**
	 * 时间的排列前十
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Video> findBrowsedCount() {
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("browseTimes", "desc");
		PageModel<Video> pageModel = find(1, 10, orderby);
		return pageModel.getList();
	}
	/**
	 * 推荐 
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
	 * 前十感谢的视频
	 */
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Video> findThankCount() {
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("thankCount", "desc");
		PageModel<Video> pageModel = find(1, 10, orderby);
		return pageModel.getList();
	}
}
