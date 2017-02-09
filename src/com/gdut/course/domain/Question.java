package com.gdut.course.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;


/**
 * 问题类
 * @author David
 *
 */
@Entity
@Table(name="T_QUESTION")
public class Question {
	/**
	 * 问题类id
	 */
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	/**
	 * 问题标题
	 */
	@Column
	private String title;
	/**
	 * 问题内容
	 */
	@Column
	private String content;
	/**
	 * 提问时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDate;
	/**
	 * 对应用户
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	
}
