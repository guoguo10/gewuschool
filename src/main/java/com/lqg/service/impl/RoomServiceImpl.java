package com.lqg.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.base.DaoSupportImpl;
import com.lqg.model.Room;
import com.lqg.service.RoomService;

@Service(value = "roomService")
@Transactional
public class RoomServiceImpl extends DaoSupportImpl<Room> implements RoomService{
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Room findByName(String name) {
		String where = "where name = ?";
		Object[] queryParams = {name};
		List<Room> list = find(-1, -1, where, queryParams).getList();
		return list.get(0);
	}
}
