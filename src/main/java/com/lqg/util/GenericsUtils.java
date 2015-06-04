package com.lqg.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * 泛型获取
 * @author Li Qiuguo
 */
public class GenericsUtils {
	/**
	 * 获取泛型的参数类
	 * @param clazz
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	public static Class getGenericType(Class clazz){
		Type genType = clazz.getGenericSuperclass();//获取参数的父类
		if (!(genType instanceof ParameterizedType)) {    
			             return Object.class;    
		}
		Type[] types = ((ParameterizedType) genType).getActualTypeArguments();
		
		if (!(types[0] instanceof Class)) {
            return Object.class;   
        } 
		return (Class) types[0];
	}
	/**
	 * 获取泛型的名称
	 * @param clazz
	 * @return string
	 */
	@SuppressWarnings("unchecked")
	public static String getGenericName(Class clazz){
		return clazz.getSimpleName();
	}
}
