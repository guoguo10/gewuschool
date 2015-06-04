package com.lqg.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Hibernate��ȡsession�ķ���
 * @author Li Qiuguo
 */
public class HibernateUtils {
	// sessionFactory
	private static SessionFactory factory = null;
	// threadLocal��
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	// configure�����ļ�
	private static Configuration cfg = new Configuration();
	// ��ȡ
	static {
		try {
			// ��ȡsessionFactory
			cfg.configure();
			// ����SessionFactory
			factory = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace(); 
		}
	}
	/**
	 * ��ȡsession
	 * @return Session����
	 */
	public static Session getSession() {
		// ��ȡthreadLocal
		Session session = (Session) threadLocal.get();
		// ����û�л��߹ر�
		if (session == null || !session.isOpen()) {
			if (factory == null) {
				rebuildSessionFactory();
			}
			// ��sessionFactory
			session = (factory != null) ? factory.openSession() : null;
			threadLocal.set(session); 
		}
		return session;
	}
	/**
	 * ��ȡɫ��ɱ��Ŷ��Factory
	 * @return SessionFactory����
	 */
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	/**
	 * close Session
	 * @param session
	 */
	public static void closeSession() {
		// ��ȡSession
		Session session = (Session) threadLocal.get();
		// ɾ��
		threadLocal.remove();
		if (session != null) {
			if (session.isOpen()) {
				session.close(); // �ر�
			}
		}
	}
	/**
	 * �ؽ�
	 */
	public static void rebuildSessionFactory() {
		try {
			// ����
			cfg.configure();
			// ����SessionFactory
			factory = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
}
