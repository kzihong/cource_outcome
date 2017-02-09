package com.gdut.course.dao;

import java.util.Map;

import com.gdut.course.base.IBaseDao;
import com.gdut.course.domain.Pager;
import com.gdut.course.domain.Work;


public interface WorkDao extends IBaseDao<Work>  {
	public Pager<Work> findByAlias(String hql,Map<String,Object> alias);
}
