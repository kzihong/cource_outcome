package com.gdut.course.service;

import com.gdut.course.domain.Pager;
import com.gdut.course.domain.Question;
import com.gdut.course.domain.User;

public interface QuestionService {
	/**
	 * 查询所有的问题
	 * @return
	 */
	public Pager<Question> findAll();
	/**
	 * 查询问题详情
	 */
	public Question getById(String id);
	/**
	 * 发布问题
	 * @param model
	 * @param user 
	 */
	public void save(Question model, User user);
}
