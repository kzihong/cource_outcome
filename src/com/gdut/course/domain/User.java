package com.gdut.course.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.gdut.course.base.Role;

/**
 * 用户类
 * @author David
 *
 */
@Entity
@Table(name="T_USER")
public class User implements Serializable{
	/**
	 * 用户id
	 */
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	/**
	 * 用户名
	 */
	@Column
	private String loginName;
	/**
	 * 密码
	 */
	@Column
	private String password;
	/**
	 * 登陆时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	/**
	 * 角色
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
