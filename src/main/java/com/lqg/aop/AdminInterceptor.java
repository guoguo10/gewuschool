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
	
	//������ͻ��˹�����MemcahcedTool��֯����
	@Autowired
	private MemCachedTool memcachedCache;
	
	/*
	 * ����poincut
	 */
	@Pointcut("execution(* com.lqg.dao.user.AdminDaoImpl.*(..))")
	public void aPointcut(){
		
	}
	
	 /** 
     * ����װ�� �������ز�ѯ ��������������ݣ�ֱ�Ӵӻ����ж�ȡ����������ݿ��ȡ����������뻺�� 
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
            log.debug("�ӻ����ж�ȡ��findAdminByName_" + username);  
           System.out.println("�ӻ����ж�ȡ��findAdminByName_" + username); 
        } else {  
            try {              	
                admin = (Admin) call.proceed();  
                if (admin != null) {  
                    memcachedCache.put("findAdminByName_" + username, admin);  
                    log.debug("����װ����ִ�У�findAdminByName_" + username);  
                    System.out.println("����װ����ִ�У�findAdminByName_" + username);
                }  
            } catch (Throwable e) {  
                e.printStackTrace();  
            }  
        }  
        return admin;  
    }  

}
