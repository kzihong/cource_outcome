package com.gdut.course.daoImpl;

import org.springframework.stereotype.Repository;

import com.gdut.course.base.BaseDao;
import com.gdut.course.dao.UserDao;
import com.gdut.course.domain.User;
@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao{

}
