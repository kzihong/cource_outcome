package com.gdut.course.dao;

import java.util.List;
import java.util.Map;

import com.gdut.course.base.IBaseDao;
import com.gdut.course.domain.Answer;

public interface AnswerDao extends IBaseDao<Answer> {
	public List<Answer> list(String hql,Map<String,Object> alias);
}
