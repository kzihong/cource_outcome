package com.gdut.course.base;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.gdut.course.exception.ErrorException;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>,ServletRequestAware,ServletResponseAware,SessionAware {
	protected T model;
	public BaseAction(){
		try{
			ParameterizedType pt = (ParameterizedType)this.getClass().getGenericSuperclass();
			Class<T> clz = (Class<T>)pt.getActualTypeArguments()[0];
			model = clz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Resource(name="config")
	protected  Properties validate;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Map<String,Object> session;
	public T getModel(){
		return model;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;;
		
	}
}
