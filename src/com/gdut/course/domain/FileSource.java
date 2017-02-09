package com.gdut.course.domain;

import java.io.Serializable;
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
import org.hibernate.annotations.ManyToAny;


/**
 * 文件资源类
 * @author David
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="T_FILESOURCE")
public class FileSource implements Serializable{
	/**
	 * 文件id
	 */
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	/**
	 * 文件地址
	 */
	@Column
	private String url;
	/**
	 * 文件标题
	 */
	@Column
	private String title;
	/**
	 * 文件类型
	 */
	@Column
	private String type;
	/**
	 * 文件上传时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date uploadDate;
	/**
	 * 是否显示(0,1)
	 */
	@Column
	private Integer visiable;
	/**
	 * 备注信息
	 */
	@Column
	private String description;
	/**
	 * 对应文档目录
	 */
	@ManyToOne
	@JoinColumn(name="catalog_id")
	private Catalog catalog;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getVisiable() {
		return visiable;
	}
	public void setVisiable(Integer visiable) {
		this.visiable = visiable;
	}
	
}
