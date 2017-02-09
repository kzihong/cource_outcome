package com.gdut.course.service;

import java.util.List;

import com.gdut.course.domain.Answer;
import com.gdut.course.domain.User;

public interface AnswerService {
	/**
	 * 根据问题找答案
	 * @param id
	 * @return
	 */
	public List<Answer> getByQue(String id);
	/**
	 * 问题答疑
	 * @param answer
	 * @param user
	 * @param question_id 
	 */
	public void save(Answer answer, User user, String question_id);
	
}
