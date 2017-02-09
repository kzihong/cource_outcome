package com.gdut.course.test;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gdut.course.service.AnswerService;
import com.gdut.course.service.CatalogService;
import com.gdut.course.service.QuestionService;
import com.gdut.course.service.UserService;


public abstract class BaseTest {
	protected static ClassPathXmlApplicationContext context;
	protected static UserService userService;
	protected static QuestionService questionService;
	protected static AnswerService answerService;
	protected static CatalogService catalogService;
	@Before
	public void start(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = (UserService)context.getBean("userServiceImpl");
		questionService = (QuestionService)context.getBean("questionServiceImpl");
		answerService = (AnswerService)context.getBean("answerServiceImpl");
		catalogService = (CatalogService)context.getBean("catalogServiceImpl");
	}
}
