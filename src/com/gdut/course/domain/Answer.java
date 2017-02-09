package com.gdut.course.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 答案
 * @author David
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="T_ANSWER")
public class Answer implements Serializable {
	/**
	 * 答案类id
	 */
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	/**
	 * 答案内容
	 */
	@Column
	private String content;
	/**
	 * 对应用户
	 */
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	/**
	 * 对应问题
	 */
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;
	/**
	 * 提问时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
}
