package com.gdut.course.dao;

import java.util.Map;

import com.gdut.course.base.IBaseDao;
import com.gdut.course.domain.User;

public interface UserDao extends IBaseDao<User> {
	public Object queryObject(String hql,Map<String,Object> alias);
}
