package com.gdut.course.dao;

import java.util.List;
import java.util.Map;

import com.gdut.course.base.IBaseDao;
import com.gdut.course.domain.Catalog;

public interface CatalogDao extends IBaseDao<Catalog> {
	public List<Catalog> list(String hql,Map<String,Object> alias);
}
