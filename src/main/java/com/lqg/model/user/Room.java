package com.lqg.model.user;

import java.io.Serializable;
/**
 * ฟฮสา
 * @author Li Qiuguo
 */

public class Room implements Serializable{	
	private static final long serialVersionUID = 1L;
	// id
	private Integer id;
	//ฟฮสาร๛ณฦ
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
