package com.gdut.course.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.course.dao.UserDao;
import com.gdut.course.domain.User;
import com.gdut.course.service.UserService;
import com.gdut.course.util.Encrypter;

@Service
@Transactional(readOnly=true)
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	@Resource
	private Encrypter encrypter;
	@Override
	public User login(String loginName, String password) {
		Map<String,Object> alias = new HashMap<String,Object>();
		password = encrypter.encodePwd(loginName, password);
		String hql = "from User u where u.loginName=:loginName and u.password=:password";
		alias.put("loginName",loginName);
		alias.put("password",password);
		User user = (User)userDao.queryObject(hql, alias);
		return user;
	}
	@Override
	@Transactional(readOnly=false)
	public void register(User user) {
		String loginName = user.getLoginName();
		String password = user.getPassword();
		password = encrypter.encodePwd(loginName, password);
		user.setPassword(password);
		user.setDate(new Date());
		userDao.save(user);
	}
	@Override
	public User getByLoginName(String loginName) {
		Map<String,Object> alias = new HashMap<String,Object>();
		String hql = "from User u where u.loginName=:loginName";
		alias.put("loginName",loginName);
		User user = (User)userDao.queryObject(hql, alias);
		return user;
	}
}
