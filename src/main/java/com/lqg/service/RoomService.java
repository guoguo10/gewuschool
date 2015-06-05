package com.lqg.service;

import com.lqg.base.DaoSupport;
import com.lqg.model.Room;

public interface  RoomService extends DaoSupport<Room> {
	public Room findByName(String name);
}
