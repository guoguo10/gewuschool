package com.lqg.action.interceptor;

import java.util.Map;

import com.lqg.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * �����ߵ�¼������
 * @author Li Qiguo
 *
 */
public class StudentLoginInteceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();// ��ȡActionContext
		Map<String, Object> session = context.getSession();// ��ȡMap���͵�session
		if(session.get("student") != null){// �ж��û��Ƿ��¼ 
			return invocation.invoke();// ����ִ�з���
		}
		return BaseAction.USER_LOGIN;// ���ص�¼
	}
}
