package com.gdut.course.dao;

import java.util.List;
import java.util.Map;

import com.gdut.course.base.IBaseDao;
import com.gdut.course.domain.FileSource;
import com.gdut.course.domain.Pager;

public interface FileSourceDao extends IBaseDao<FileSource> {
	public Pager<FileSource> findByAlias(String hql,Map<String,Object> alias);
	public List<FileSource> list(String hql,Map<String,Object> alias);
	public Object queryObject(String hql,Map<String,Object> alias);
}
