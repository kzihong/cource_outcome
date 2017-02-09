package com.gdut.course.dto;

import java.util.Date;

import com.gdut.course.domain.Work;

public class WorkDto {
	/**
	 * 作业类id
	 */
	private String id;
	/**
	 * 作业标题
	 */
	private String title;
	/**
	 * 作业内容
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
	public WorkDto(Work work){
		this.id = work.getId();
		this.content = work.getContent();
		this.sendDate = work.getSendDate();
		this.title = work.getTitle();
		this.user_id = work.getUser().getId();
		this.user_name = work.getUser().getLoginName();
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
