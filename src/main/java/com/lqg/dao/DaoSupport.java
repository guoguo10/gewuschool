package com.lqg.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lqg.model.PageModel;
import com.lqg.util.GenericsUtils;
/**
 * Dao的实现
 * @author Li Qiuguo
 * @param <T>
 */
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupport<T>  implements BaseDao<T>{
	// 获取泛型的参数的类
	protected Class<T> entityClass = GenericsUtils.getGenericType(this.getClass());
	// HibernateTemplate的注入
	@Autowired
	protected HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getTemplate() {
		return hibernateTemplate;
	}
	@Override
	public void delete(Serializable ... ids) {
		for (Serializable id : ids) {
			T t = (T) getTemplate().load(this.entityClass, id);
			getTemplate().delete(t);
		}
	}
	//事务的传输控制	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T get(Serializable entityId) {
		return (T) getTemplate().get(this.entityClass, entityId);
	}
	//事务的传输控制
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T load(Serializable entityId) {
		return (T) getTemplate().load(this.entityClass, entityId);
	}
	//事务的传输控制
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Object uniqueResult(final String hql,final Object[] queryParams) {
		return getTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				//session查询hql语句
				Query query = session.createQuery(hql);
				//设置参数
				setQueryParams(query, queryParams);
				//返回不重复的结果
				return query.uniqueResult();
			}
		});
	}
	//事务的传输控制
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public long getCount() {
		String hql = "select count(*) from " + GenericsUtils.getGenericName(this.entityClass);
		return (Long)uniqueResult(hql,null);
	}
	//事务的传输控制
	@Override
	public void save(Object obj) {
		getTemplate().save(obj);
		getTemplate().flush();
	}
	@Override
	public void saveOrUpdate(Object obj) {
		getTemplate().saveOrUpdate(obj);
		getTemplate().flush();
	}
	//事务的传输控制
	@Override
	public void update(Object obj) {
		getTemplate().update(obj);
		getTemplate().flush();
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(final int pageNo, int maxResult) {
		return find(null, null, null, pageNo, maxResult);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(int pageNo, int maxResult,
			Map<String, String> orderby) {
		return find(null, null, orderby, pageNo, maxResult);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(int pageNo, int maxResult, String where,
			Object[] queryParams) {
		return find(where, queryParams, null, pageNo, maxResult);
	}
	/**
	 * 总的参数查询方法
	 * @param where sql的符合条件
	 * @param queryParams hql的参数
	 *  @param orderby 排列的顺序
	 * @param pageNo 第几页
	 * @param maxResult 每页的大小
	 * return PageModel
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(final String where, final Object[] queryParams,
			final Map<String, String> orderby, final int pageNo,
			final int maxResult) {
		//设置pageModel封装
		final PageModel<T> pageModel = new PageModel<T>();
		//设置第几页
		pageModel.setPageNo(pageNo);
		//每页的大小
		pageModel.setPageSize(maxResult);
		getTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = new StringBuffer().append("from ")
								.append(GenericsUtils.getGenericName(entityClass))//类别
								.append(" ")//空格						
								.append(where == null ? "" : where)//条件
								.append(createOrderBy(orderby))//排序
								.toString();//转为string
				Query query = session.createQuery(hql);//查询
				setQueryParams(query,queryParams);//设置参数
				List<T> list = null; 
				if(maxResult < 0 && pageNo < 0){
					list = query.list();//
				}else{
					list = query.setFirstResult(getFirstResult(pageNo, maxResult))//设置开头页
								.setMaxResults(maxResult)//设置取样数目
								.list();//结果
					//
					hql = new StringBuffer().append("select count(*) from ")//sql语句
									.append(GenericsUtils.getGenericName(entityClass))//实体
									.append(" ")//空格							
									.append(where == null ? "" : where)//条件
									.toString();//转为string
					query = session.createQuery(hql);//查询
					setQueryParams(query,queryParams);//设置参数
					int totalRecords = ((Long) query.uniqueResult()).intValue();//总的数目
					pageModel.setTotalRecords(totalRecords);//设置totoalRecords
				}
				pageModel.setList(list);//pageModel封装
				return null;
			}
		});
		return pageModel;
		}
	/**
	 * 获取第几页
	 * @param pageNo 第几页
	 * @param maxResult 每页大小
	 * @return 开头数目
	 */
	protected int getFirstResult(int pageNo,int maxResult){
		int firstResult = (pageNo-1) * maxResult;
		return firstResult < 0 ? 0 : firstResult;
	}
	/**
	 * query 设置怕rams
	 * @param query
	 * @param queryParams
	 */
	protected void setQueryParams(Query query, Object[] queryParams){
		if(queryParams!=null && queryParams.length>0){
			for(int i=0; i<queryParams.length; i++){
				query.setParameter(i, queryParams[i]);
			}
		}
	}
	/**
	 * orderby排序
	 * @param orderby
	 * @return 总的sql如何*/
	protected String createOrderBy(Map<String, String> orderby){
		StringBuffer sb = new StringBuffer("");
		if(orderby != null && orderby.size() > 0){
			sb.append(" order by ");
			for(String key : orderby.keySet()){
				sb.append(key).append(" ").append(orderby.get(key)).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
	/**
	 * 获取session
	 * @return
	 */
	protected Session getSession(){
		return (!this.hibernateTemplate.isAllowCreate() ?
		    SessionFactoryUtils.getSession(this.hibernateTemplate.getSessionFactory(), false) :
				SessionFactoryUtils.getSession(
						this.hibernateTemplate.getSessionFactory(),
						this.hibernateTemplate.getEntityInterceptor(),
						this.hibernateTemplate.getJdbcExceptionTranslator()));
	}
}
