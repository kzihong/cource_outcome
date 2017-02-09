package com.gdut.course.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.course.dao.AnswerDao;
import com.gdut.course.dao.QuestionDao;
import com.gdut.course.domain.Answer;
import com.gdut.course.domain.Question;
import com.gdut.course.domain.User;
import com.gdut.course.service.AnswerService;
@Service
@Transactional(readOnly=true)
public class AnswerServiceImpl implements AnswerService{
	@Resource
	private AnswerDao answerDao;
	@Resource
	private QuestionDao questionDao;
	@Override
	public List<Answer> getByQue(String id) {
		Map<String,Object> alias = new HashMap<String,Object>();
		String hql ="from Answer a where a.question.id=:id";
		alias.put("id", id);
		List<Answer> list = answerDao.list(hql, alias);
		return list;
	}
	@Override
	@Transactional(readOnly=false)
	public void save(Answer answer, User user,String question_id) {
		answer.setUser(user);
		Question question = questionDao.get(question_id);
		answer.setQuestion(question);
		answer.setSendDate(new Date());
		answerDao.save(answer);
	}

}
