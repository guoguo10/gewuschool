package com.lqg.memcache;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

public class MemCachedTool {
	private ICacheManager <IMemcachedCache> manager;
	private IMemcachedCache cache;
	
	public MemCachedTool(){
		manager=CacheUtil.getCacheManager(IMemcachedCache.class,MemcachedCacheManager.class.getName());
		manager.setConfigFile("memcached.xml");
		manager.setResponseStatInterval(5*1000);
		manager.start();
		cache=manager.getCache("mclient_0");
	}
	/**
	 * ��ȡ����ӿ�
	 * @return
	 */
	public IMemcachedCache getCache(){
		return this.cache;
	}
	
	/**
	 * ���ݷ��뻺��
	 * @param key
	 * @param object
	 */
	public void put(String key, Object object){
		
		cache.put(key, object);
	}
	
	/** 
    * �ӻ����ж�ȡ���� 
    * @param key 
    * @return 
    */  
   public Object get(String key){  
       return cache.get(key);  
   }  

}
