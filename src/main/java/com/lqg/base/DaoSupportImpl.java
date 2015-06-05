package com.lqg.base;

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
 * Dao鐨勫疄鐜�
 * @author Li Qiuguo
 * @param <T>
 */
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImpl<T>  implements DaoSupport<T>{
	// 鑾峰彇娉涘瀷鐨勫弬鏁扮殑绫
	protected Class<T> entityClass = GenericsUtils.getGenericType(this.getClass());
	// HibernateTemplate鐨勬敞鍏	
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
	//浜嬪姟鐨勪紶杈撴帶鍒�
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T get(Serializable entityId) {
		return (T) getTemplate().get(this.entityClass, entityId);
	}
	//浜嬪姟鐨勪紶杈撴帶鍒�
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public T load(Serializable entityId) {
		return (T) getTemplate().load(this.entityClass, entityId);
	}
	//浜嬪姟鐨勪紶杈撴帶鍒�
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Object uniqueResult(final String hql,final Object[] queryParams) {
		return getTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				//session鏌ヨhql璇彞
				Query query = session.createQuery(hql);
				//璁剧疆鍙傛暟
				setQueryParams(query, queryParams);
				//杩斿洖涓嶉噸澶嶇殑缁撴灉
				return query.uniqueResult();
			}
		});
	}
	//浜嬪姟鐨勪紶杈撴帶鍒�
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public long getCount() {
		String hql = "select count(*) from " + GenericsUtils.getGenericName(this.entityClass);
		return (Long)uniqueResult(hql,null);
	}
	//浜嬪姟鐨勪紶杈撴帶鍒�
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
	//浜嬪姟鐨勪紶杈撴帶鍒�
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
	 * 鎬荤殑鍙傛暟鏌ヨ鏂规硶
	 * @param where sql鐨勭鍚堟潯浠�
	 * @param queryParams hql鐨勫弬鏁�
	 *  @param orderby 鎺掑垪鐨勯『搴�	 * @param pageNo 绗嚑椤�
	 * @param maxResult 姣忛〉鐨勫ぇ灏�	 * return PageModel
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public PageModel<T> find(final String where, final Object[] queryParams,
			final Map<String, String> orderby, final int pageNo,
			final int maxResult) {
		//璁剧疆pageModel灏佽
		final PageModel<T> pageModel = new PageModel<T>();
		//璁剧疆绗嚑椤
		pageModel.setPageNo(pageNo);
		//姣忛〉鐨勫ぇ灏
		pageModel.setPageSize(maxResult);
		getTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = new StringBuffer().append("from ")
								.append(GenericsUtils.getGenericName(entityClass))//绫诲埆
								.append(" ")//绌烘牸						
								.append(where == null ? "" : where)//鏉′欢
								.append(createOrderBy(orderby))//鎺掑簭
								.toString();//杞负string
				Query query = session.createQuery(hql);//鏌ヨ
				setQueryParams(query,queryParams);//璁剧疆鍙傛暟
				List<T> list = null; 
				if(maxResult < 0 && pageNo < 0){
					list = query.list();//
				}else{
					list = query.setFirstResult(getFirstResult(pageNo, maxResult))//璁剧疆寮�ご椤�
								.setMaxResults(maxResult)//璁剧疆鍙栨牱鏁扮洰
								.list();//缁撴灉
					//
					hql = new StringBuffer().append("select count(*) from ")//sql璇彞
									.append(GenericsUtils.getGenericName(entityClass))//瀹炰綋
									.append(" ")//绌烘牸							
									.append(where == null ? "" : where)//鏉′欢
									.toString();//杞负string
					query = session.createQuery(hql);//鏌ヨ
					setQueryParams(query,queryParams);//璁剧疆鍙傛暟
					int totalRecords = ((Long) query.uniqueResult()).intValue();//鎬荤殑鏁扮洰
					pageModel.setTotalRecords(totalRecords);//璁剧疆totoalRecords
				}
				pageModel.setList(list);//pageModel灏佽
				return null;
			}
		});
		return pageModel;
		}
	/**
	 * 鑾峰彇绗嚑椤�	 * @param pageNo 绗嚑椤�
	 * @param maxResult 姣忛〉澶у皬
	 * @return 寮�ご鏁扮洰
	 */
	protected int getFirstResult(int pageNo,int maxResult){
		int firstResult = (pageNo-1) * maxResult;
		return firstResult < 0 ? 0 : firstResult;
	}
	/**
	 * query 璁剧疆鎬時ams
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
	 * orderby鎺掑簭
	 * @param orderby
	 * @return 鎬荤殑sql濡備綍*/
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
	 * 鑾峰彇session
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
