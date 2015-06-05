package com.lqg.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.VideoDao;
import com.lqg.model.PageModel;
import com.lqg.model.Video;
@Repository("videoDao")
@Transactional
public class VideoDaoImpl extends DaoSupportImpl<Video> implements VideoDao {

	
}
