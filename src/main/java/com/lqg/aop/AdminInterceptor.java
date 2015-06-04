package com.lqg.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lqg.memcache.MemCachedTool;
import com.lqg.model.user.Admin;

@Aspect 
public class AdminInterceptor {
	public static final Logger log=Logger.getLogger(AdminInterceptor.class);
	
	//将缓存客户端工具类MemcahcedTool编织进来
	@Autowired
	private MemCachedTool memcachedCache;
	
	/*
	 * 定义poincut
	 */
	@Pointcut("execution(* com.lqg.dao.user.AdminDaoImpl.*(..))")
	public void aPointcut(){
		
	}
	
	 /** 
     * 环绕装备 用于拦截查询 如果缓存中有数据，直接从缓存中读取；否则从数据库读取并将结果放入缓存 
     *  
     * @param call 
     * @param name 
     * @return 
     */  
    @Around("aPointcut()&&args(username,password)")  
    public Admin doFindUserByNameAround(ProceedingJoinPoint call, String username,String password) {  
       Admin admin = null;  
        if (memcachedCache.getCache().containsKey("findAdminByName_" + username)) {  
            admin = (Admin) memcachedCache.get("findAdminByName_" + username);  
            log.debug("从缓存中读取！findAdminByName_" + username);  
           System.out.println("从缓存中读取！findAdminByName_" + username); 
        } else {  
            try {              	
                admin = (Admin) call.proceed();  
                if (admin != null) {  
                    memcachedCache.put("findAdminByName_" + username, admin);  
                    log.debug("缓存装备被执行：findAdminByName_" + username);  
                    System.out.println("缓存装备被执行：findAdminByName_" + username);
                }  
            } catch (Throwable e) {  
                e.printStackTrace();  
            }  
        }  
        return admin;  
    }  

}
