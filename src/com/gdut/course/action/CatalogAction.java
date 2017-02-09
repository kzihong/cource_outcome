package com.gdut.course.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gdut.course.base.BaseAction;
import com.gdut.course.domain.Catalog;
import com.gdut.course.service.CatalogService;
import com.gdut.course.util.Login;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 目录控制层
 * @author David
 *
 */
@SuppressWarnings("serial")
@Controller("catalog")
@Scope("prototype")
public class CatalogAction extends BaseAction<Catalog> {
	@Resource
	private CatalogService catalogService;
	/**
	 * 添加子目录
	 * @return
	 */
	public void create(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Gson gson = new Gson();
		if(null == model.getName()||"".equals(model.getName().trim())){
			map.put("errorCode", "0020");
			map.put("errorMessage",validate.get("0020"));
		}else{
			catalogService.create(model);
			map.put("errorCode","0000");
		}
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ActionContext.getContext().getValueStack().pop();
		ValueStack valueStack;
	}
	/**
	 * 查询子目录列表(管理员)
	 */
	public void query(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Gson gson = new Gson();
		String parent_id = model.getParent_id();
		List<Catalog> list = catalogService.list(parent_id);
		map.put("errorCode","0000");
		map.put("catalog_list",list);
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询子目录列表(用户)
	 */
	public String query_user(){
		String target = request.getParameter("target");  //对应目标页面
		if(null==target||"".equals(target.trim())){
			return "homePage";//返回首页
		}
		String parent_id = model.getParent_id();
		List<Catalog> list = catalogService.list(parent_id);
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("catalog_list",list);
		return target;
	}
	/**
	 * 删除子目录
	 */
	public void delete(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String id = model.getId();
		catalogService.delCatalog(id);
		map.put("errorCode","0000");
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 目录侧栏页面
	 */
	public String side(){
		return "side";
	}
}
