package com.lqg.action.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import com.lqg.util.*;
/**
 * 拦截跳转页面
 * @author Li Qiguo
 *
 */
public class UserLoginInteceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();// 获取ActionContext
		Map<String, Object> session = context.getSession();// 获取session
		// 获取HttpServletRequest
	     HttpServletRequest req = ServletActionContext.getRequest();
	            	       String url=req.getHeader("referer");  
	                        
	                        if(url==null || url.equals(""))  
	                            {  
	                                url="";  
	                                String path=req.getContextPath();  
	                                String actionName=invocation.getProxy().getActionName();  
	                                String nameSpace=invocation.getProxy().getNamespace();  
	                                if(StringUitl.validateString(nameSpace))  
	                                    {  
	                                        url = url+path+nameSpace;  
	                                          
	                                    }  
	                                if(StringUitl.validateString(actionName))  
	                                    {  
	                                        url = url+"/"+actionName+".action"+"?";  
	                                    }  
	                                          	            	
	                	          
	                                Map<String,String[]> zzMap = req.getParameterMap();  
	                                if(zzMap!=null)  
	                                    {  
	                                        for(String s:zzMap.keySet())  
	                                            {  
	                                                String[] value=zzMap.get(s);  
	                                                for(String val:value)  
	                                                    {  
	                                                        url=url+s+"="+val+"&";  
	                                                    }  
	                                                  
	                                            }  
	                                    }  
	                                
	                               
	                             }   
	                        session.put("prePage", url);   
	                  
	       
		return invocation.invoke();// 继续调整
	}
}
