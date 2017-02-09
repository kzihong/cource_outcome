package com.gdut.course.serviceImpl;


import java.util.Date;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.course.dao.QuestionDao;
import com.gdut.course.domain.Pager;
import com.gdut.course.domain.Question;
import com.gdut.course.domain.User;
import com.gdut.course.service.QuestionService;
@Service
@Transactional(readOnly=true)
public class QuestionServiceImpl implements QuestionService {
	@Resource
	private QuestionDao questionDao;

	@Override
	public Pager<Question> findAll() {
		String hql = "from Question q ";
		Pager<Question> pager = questionDao.findByAlias(hql, null);
		return pager;
	}

	@Override
	public Question getById(String id) {
		Question question = questionDao.get(id);
		return question;
	}

	@Override
	@Transactional(readOnly=false)
	public void save(Question question,User user) {
		question.setSendDate(new Date());
		question.setUser(user);
		questionDao.save(question);
	}
}
