package com.gdut.course.test;

import org.junit.Test;

import com.gdut.course.domain.Answer;
import com.gdut.course.domain.Pager;
import com.gdut.course.domain.Question;
import com.gdut.course.domain.User;
import com.gdut.course.util.SystemContext;

public class OnlineTest extends BaseTest { 
	@Test
	public void simpleTest(){
		String str = null;
		System.out.println("OK");
		try{
			System.out.println(Integer.valueOf(str));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testQuesList(){
		Integer pageSize = 7;
		Integer pageOffset = 1;
		SystemContext.setPageSize(pageSize);
		SystemContext.setPageOffset(pageOffset);
		//设置排序方式
		SystemContext.setOrder("desc");
		SystemContext.setSort("q.sendDate");
		Pager<Question> pager = questionService.findAll();
		System.out.println("beginPageIndex:"+pager.getBeginPageIndex()+"\n"+
		"endPageIndex："+pager.getEndPageIndex()+"\n"+
		"currentPage:"+pager.getCurrentPage()+"\n"+
		"pageSize:"+pager.getPageSize()+"\n"+
		"totalAmount:"+pager.getTotalAmount()+"\n"+
		"totalPages:"+pager.getTotalPages());
	}
	@Test
	public void testSendQues(){
		for(int i = 0;i<6;i++){
			Question question = new Question();
			question.setContent("你有病吗?");
			question.setTitle("论爱与正义");
			User user = userService.getByLoginName("华德义");
			questionService.save(question, user);
		}
	}
	@Test
	public void testQuesInfo(){
		String id = "40284b81500259c301500259c6e50000";
		Question question = questionService.getById(id);
		System.out.println("title:"+question.getTitle()+"\n"+
		"content:"+question.getContent());
	}
	@Test
	public void testAnswerQues(){
		Answer answer = new Answer();
		answer.setContent("事实是没有");
		User user = userService.getByLoginName("华德义");
		String id = "40284b81500259c301500259c6e50000";
		answerService.save(answer, user, id);
	}
}
