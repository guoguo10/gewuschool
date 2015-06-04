package com.lqg.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 字符串工具
 * @author Li Qiuguo
 */
public class StringUitl {
	public static Random random = new Random();
	/**
	 * 获取时间的字符串
	 * @return 锟斤拷前时锟斤拷锟街凤拷
	 */
	public static String getStringTime(){
		Date date = new Date();//锟斤拷取锟斤拷前系统时锟斤拷
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");//锟斤拷锟矫革拷式锟斤拷锟斤拷式
		return sdf.format(date);//锟斤拷锟截革拷式锟斤拷锟斤拷锟绞憋拷锟�
	}
	/**
	 * 时间的order
	 * @return 锟斤拷锟斤拷锟斤拷
	 */
	public static String createOrderId(){
		StringBuffer sb = new StringBuffer();
		sb.append(getStringTime());
		for (int i = 0; i < 3; i++) {
			sb.append(random.nextInt(9));
		}
		return sb.toString();
	}
	/**
	 * 字符串是否符合要求
	 * @param s 锟斤拷证锟街凤拷
	 * @return 锟角凤拷锟斤拷效锟侥诧拷锟斤拷值
	 */
	public static boolean validateString(String s){
		if(s != null && s.trim().length() > 0){
			return true;
		}
		return false;
	}
	/**
	 * 是否为浮点数
	 * @param f 
	 * @return 
	 */
	public static boolean validateFloat(Float f){
		try {
			if(f != null && f > 0){
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
/*
 * 是否为整数
 */
	
	
	public static boolean validateInt(int f){
		try {
			if(f==(int)f){
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
}
