package com.gdut.course.dto;

import java.util.Date;

import com.gdut.course.domain.Question;

/**
 * 问题dto类
 * @author David
 *
 */
public class QuestionDto {
	/**
	 * 问题类id
	 */
	private String id;
	/**
	 * 问题标题
	 */
	private String title;
	/**
	 * 问题内容
	 */
	private String content;
	/**
	 * 提问时间
	 */
	private Date sendDate;
	/**
	 * 对应用户id
	 */
	private String user_id;
	/**
	 * 对应用户名
	 */
	private String user_name;
	public QuestionDto(Question question){
		this.content = question.getContent();
		this.id = question.getId();
		this.sendDate = question.getSendDate();
		this.title = question.getTitle();
		this.user_id = question.getUser().getId();
		this.user_name = question.getUser().getLoginName();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}
