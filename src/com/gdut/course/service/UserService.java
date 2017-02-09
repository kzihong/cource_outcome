package com.gdut.course.service;

import com.gdut.course.domain.User;

public interface UserService {
	/**
	 * 登陆
	 * @param loginName 用户名
	 * @param password 密码
	 * @return
	 */
	public User login(String loginName,String password);
	/**
	 * 检测账号是否可用
	 */
	public User getByLoginName(String loginName);
	/**
	 * 注册
	 * @param user 用户
	 */
	public void register(User user);
}
