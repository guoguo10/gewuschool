package com.lqg.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Hibernate获取session的方法
 * @author Li Qiuguo
 */
public class HibernateUtils {
	// sessionFactory
	private static SessionFactory factory = null;
	// threadLocal类
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	// configure配置文件
	private static Configuration cfg = new Configuration();
	// 获取
	static {
		try {
			// 获取sessionFactory
			cfg.configure();
			// 建立SessionFactory
			factory = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * 获取session
	 * @return Session锟斤拷锟斤拷
	 */
	public static Session getSession() {
		// 获取threadLocal
		Session session = (Session) threadLocal.get();
		// 若是没有或者关闭
		if (session == null || !session.isOpen()) {
			if (factory == null) {
				rebuildSessionFactory();
			}
			// 打开sessionFactory
			session = (factory != null) ? factory.openSession() : null;
			threadLocal.set(session); 
		}
		return session;
	}
	/**
	 * 获取色素杀死哦那Factory
	 * @return SessionFactory锟斤拷锟斤拷
	 */
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	/**
	 * close Session
	 * @param session
	 */
	public static void closeSession() {
		// 获取Session
		Session session = (Session) threadLocal.get();
		// 删除
		threadLocal.remove();
		if (session != null) {
			if (session.isOpen()) {
				session.close(); // 关闭
			}
		}
	}
	/**
	 * 重建
	 */
	public static void rebuildSessionFactory() {
		try {
			// 配置
			cfg.configure();
			// 创建SessionFactory
			factory = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
}
