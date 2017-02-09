package com.gdut.course.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gdut.course.base.BaseAction;
import com.gdut.course.domain.Answer;
import com.gdut.course.domain.Pager;
import com.gdut.course.domain.Question;
import com.gdut.course.domain.User;
import com.gdut.course.domain.Work;
import com.gdut.course.dto.QuestionDto;
import com.gdut.course.dto.WorkDto;
import com.gdut.course.service.AnswerService;
import com.gdut.course.service.QuestionService;
import com.gdut.course.service.WorkService;
import com.gdut.course.util.Authority;
import com.gdut.course.util.Login;
import com.gdut.course.util.SystemContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 在线交流控制层
 * @author David
 *
 */
@Controller("online")
@Scope("prototype")
@SuppressWarnings("serial")
@Login
public class OnlineAction extends BaseAction<Question> {
	@Resource
	private QuestionService questionService;
	@Resource
	private AnswerService answerService;
	@Resource
	private WorkService workService;
	/**
	 * 答疑问题列表
	 * @return
	 */
	public void quesList(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String pageSize = request.getParameter("pageSize");
		String pageOffset = request.getParameter("pageOffset");
		if(null!=pageSize)
			SystemContext.setPageSize(Integer.valueOf(pageSize));
		if(null!=pageOffset)
			SystemContext.setPageOffset(Integer.valueOf(pageOffset));
		//设置排序方式
		SystemContext.setOrder("desc");
		SystemContext.setSort("q.sendDate");
		Pager<Question> pager = questionService.findAll();
		Pager<QuestionDto> pagerDto = new Pager<QuestionDto>();
		pagerDto.setCurrentPage(pager.getCurrentPage());
		pagerDto.setBeginPageIndex(pager.getBeginPageIndex());
		pagerDto.setEndPageIndex(pager.getEndPageIndex());
		pagerDto.setPageSize(pager.getPageSize());
		pagerDto.setTotalAmount(pager.getTotalAmount());
		pagerDto.setTotalPages(pager.getTotalPages());
		List<QuestionDto> listDto = new ArrayList<QuestionDto>();
		for(Question question:pager.getDatas()){
			listDto.add(new QuestionDto(question));
		}
		pagerDto.setDatas(listDto);
		map.put("errorCode","0000");
		Type type = new TypeToken<Pager<QuestionDto>>(){
		}.getType();
		try{
			String pagerJson = gson.toJson(pagerDto,type);
			System.out.println(pagerJson);
			map.put("pager_list", pagerJson);
			String json = gson.toJson(map);
			json = json.replace("\\", "");
			json = json.replace("\"{", "{");
			json = json.replace("}\"", "}");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * 问题详情
	 */
	public String quesInfo(){
		String id = model.getId();
		Question question = questionService.getById(id);
		List<Answer> list = answerService.getByQue(id);
		request.setAttribute("errorCode","0000");
		request.setAttribute("question",question);
		request.setAttribute("answer_list",list);
		return "questionInfo";
	}
	/**
	 * 发布问题
	 */
	@Authority(role="STUDENT,ADMIN")
	public void sendQues(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		if(null==model.getTitle()){
			map.put("errorCode","0010");
			map.put("errorMesssage",validate.get("0010"));
			String json = gson.toJson(map);
			try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if(null==model.getContent()){
			request.setAttribute("errorCode","0011");
			request.setAttribute("errorMessage",validate.get("0011"));
			String json = gson.toJson(map);
			try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		User user = (User)session.get("user");
		questionService.save(model,user);
		map.put("errorCode","0000");
		String json = gson.toJson(map);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 问题答疑
	 */
	@Authority(role="TEACHER,ADMIN")
	public void answerQues(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		Answer answer = new Answer();
	    if(null == model.getContent()){
	    	map.put("errorCode","0012");
	    	map.put("errorMessage",validate.get("0012"));
	    }else{
	    	answer.setContent(model.getContent());
	    	User user = (User)session.get("user");
	    	String question_id = model.getId(); //问题id
	    	answerService.save(answer,user,question_id);
	    	map.put("errorCode","0000");
	    }
	    String json = gson.toJson(map);
	    response.setContentType("application/json;charset=UTF-8");
	    try{
	    	response.getWriter().write(json);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	/**
	 * 作业列表(分页)
	 */
	public void workList(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String pageSize = request.getParameter("pageSize");
		String pageOffset = request.getParameter("pageOffset");
		if(null != pageSize)
			SystemContext.setPageSize(Integer.valueOf(pageSize));
		if(null !=pageOffset)
			SystemContext.setPageOffset(Integer.valueOf(pageOffset));
		//设置排序方式
		SystemContext.setOrder("desc");
		SystemContext.setSort("w.sendDate");
		Pager<Work> pager = workService.findAll();
		Pager<WorkDto> pagerDto = new Pager<WorkDto>();
		List<Work> list = pager.getDatas();
		List<WorkDto> listDto = new ArrayList<WorkDto>();
		for(Work work:list){
			listDto.add(new WorkDto(work));
		}
		pagerDto.setCurrentPage(pager.getCurrentPage());
		pagerDto.setBeginPageIndex(pager.getBeginPageIndex());
		pagerDto.setEndPageIndex(pager.getEndPageIndex());
		pagerDto.setPageSize(pager.getPageSize());
		pagerDto.setTotalAmount(pager.getTotalAmount());
		pagerDto.setTotalPages(pager.getTotalPages());
		pagerDto.setDatas(listDto);
		map.put("errorCode","0000");
		Type type = new TypeToken<Pager<WorkDto>>(){
		}.getType();
		try{
			String pagerJson = gson.toJson(pagerDto,type);
			System.out.println(pagerJson);
			map.put("pager_list", pagerJson);
			String json = gson.toJson(map);
			json = json.replace("\\", "");
			json = json.replace("\"{", "{");
			json = json.replace("}\"", "}");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 发布作业
	 */
	public void uploadWork(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		response.setContentType("application/json;charset=UTF-8");
		if(null == model.getTitle()){
			map.put("errorCode", "0030");
			map.put("errorMessage",validate.get("0030"));
			String json = gson.toJson(map);
			try{
				response.getWriter().write(json);
			}catch(IOException e){
				throw new RuntimeException(e);
			}
		}
		if(null == model.getContent()){
			map.put("errorCode", "0031");
			map.put("errorMessage", validate.get("0031"));
			String json = gson.toJson(map);
			try{
				response.getWriter().write(json);
			}catch(IOException e){
				throw new RuntimeException(e);
			}
		}
		//新建一个作业类
		Work work = new Work();
		work.setContent(model.getContent());
		work.setTitle(model.getTitle());
		workService.upload(work,(User)session.get("user"));
		map.put("errorCode", "0000");
		String json = gson.toJson(map);
		try{
			response.getWriter().write(json);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 作业详情
	 */
	public String workInfo(){
		String id = model.getId();
		Work work = workService.getById(id);
		request.setAttribute("errorCode","0000");
		request.setAttribute("work", work);
		return "workInfo";
	}
	/**
	 * 跳转到在线交流页面
	 */
	public String toOnline(){
		return "contect";
	}
}
