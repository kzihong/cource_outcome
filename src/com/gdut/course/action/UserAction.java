package com.gdut.course.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gdut.course.base.BaseAction;
import com.gdut.course.base.Role;
import com.gdut.course.domain.User;
import com.gdut.course.service.UserService;
import com.gdut.course.util.Authority;

/**
 * 用户控制层
 * @author David
 *
 */
@SuppressWarnings("serial")
@Controller("user")
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	@Resource
	private UserService userService;
	/**
	 * 用户登录
	 */
	public String login(){
		String password = model.getPassword();
		String loginName = model.getLoginName();
		Role role = model.getRole();
		if(null == loginName||"".equals(loginName.trim())){
			request.setAttribute("errorCode","0001");
			request.setAttribute("errorMessage",validate.get("0001"));
			return "homePage";
		}
		if(null == password||"".equals(password.trim())){
			request.setAttribute("errorCode","0002");
			request.setAttribute("errorMessage",validate.get("0002"));
			return "homePage";
		}
		User user = userService.login(loginName, password);
		if(null == user||!user.getRole().equals(role)){
			request.setAttribute("errorCode","0003");
			request.setAttribute("errorMessage",validate.get("0003"));
		}else{
			session.put("user",user);
			request.setAttribute("errorCode","0000");
		}
		return "homePage";
	}
	/**
	 * 用户注册
	 */
	public String register(){
		String password = model.getPassword();
		String loginName = model.getLoginName();
		Role role = model.getRole();
		if(null == loginName||"".equals(loginName.trim())){
			request.setAttribute("errorCode","0001");
			request.setAttribute("errorMessage",validate.get("0001"));
			return "homePage";
		}
		if(null == password||"".equals(password.trim())){
			request.setAttribute("errorCode","0002");
			request.setAttribute("errorMessage",validate.get("0002"));
			return "homePage";
		}
		if(null == role){
			request.setAttribute("errorCode", "0004");
			request.setAttribute("errorMessage",validate.get("0004"));
			return "homePage";
		}
		if(null!=userService.getByLoginName(loginName)){
			request.setAttribute("errorCode","0005");
			request.setAttribute("errorMessage", validate.get("0005"));
			return "homePage";
		}
		userService.register(model);
		request.setAttribute("errorCode","0000");
		session.put("user", model);
		return "homePage";
	}
	/**
	 * 退出登录
	 */
	public String loginOut(){
		session.clear();
		session.put("user",null);
		return "homePage"; //返回首页
	}
	/**
	 * 管理员页面
	 */
	@Authority(role="ADMIN")
	public String admin(){
		return "admin";
	}
}
