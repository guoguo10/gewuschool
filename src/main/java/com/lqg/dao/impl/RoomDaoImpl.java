package com.lqg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.dao.RoomDao;
import com.lqg.model.Room;
@Repository("roomDao")
public class RoomDaoImpl extends DaoSupportImpl<Room> implements RoomDao {
	

}
