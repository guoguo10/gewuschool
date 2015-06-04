package com.lqg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别
 * @author Li Qiuguo
 */
public enum Sex {
	BOY{
		@Override
		public String getName() {
			return "男";
		}
	},
	GIRL{
		@Override
		public String getName() {
			return "女";
		}
	},
	ALL{
		@Override
		public String getName() {
			return "未知";
		}
	};
	/**
	 * 获取性别
	 * @return 男或者女	 */
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (Sex sex : Sex.values()) {
			list.add(sex.getName());
		}
		return list;
	}
}
