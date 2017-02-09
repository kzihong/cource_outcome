package com.gdut.course.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gdut.course.domain.User;
import com.gdut.course.util.Authority;
import com.gdut.course.util.Login;
import com.gdut.course.util.SystemContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String methodName = invocation.getProxy().getMethod();
		Map<String,Object> map = invocation.getInvocationContext().getSession();
		HttpServletRequest request = (HttpServletRequest)
				invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		Class<?> clz= invocation.getAction().getClass();
		Method method = clz.getMethod(methodName, null);
		if(clz.isAnnotationPresent(Login.class)||
				method.isAnnotationPresent(Login.class)){
			if(null == map.get("user")){
				request.setAttribute("errorCode", "0005");
				request.setAttribute("errorMessage", "请先登录");
				return "homePage"; //未登陆返回首页;
			}
		}
		System.out.println(map.get("user"));
		if(method.isAnnotationPresent(Authority.class)){
			Authority author = method.getAnnotation(Authority.class);
			String role = author.role();
			if(null == map.get("user")||
					(!role.contains(((User)map.get("user")).getRole().toString()))){
				request.setAttribute("errorCode", "0006");
				request.setAttribute("errorMessage", "你没有权限");
				return "homePage";//没有权限返回首页
			}
		}
		String result =  invocation.invoke();
		//清空ThrealLocal
		SystemContext.removeOrder();
		SystemContext.removePageOffset();
		SystemContext.removePageSize();
		SystemContext.removeRealPath();
		SystemContext.removeSort();
		return result;
	}

}
