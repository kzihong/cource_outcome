package com.gdut.course.dao;

import java.util.List;
import java.util.Map;

import com.gdut.course.base.IBaseDao;
import com.gdut.course.domain.Pager;
import com.gdut.course.domain.Question;

public interface QuestionDao extends IBaseDao<Question> {
	public List<Question> list(String hql,Map<String,Object> alias);

	public Pager<Question> findByAlias(String hql, Map<String,Object> alias);
}
