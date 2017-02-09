package com.gdut.course.dto;

import java.util.Date;

import com.gdut.course.domain.FileSource;

/**
 * 文件dto
 * @author David
 */
public class FileSourceDto {
	/**
	 * 文件id
	 */
	private String id;
	/**
	 * 文件地址
	 */
	private String url;
	/**
	 * 文件标题
	 */
	private String title;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 文件上传时间
	 */
	private Date uploadDate;
	/**
	 * 是否可以显示
	 */
	private Integer visiable;
	/**
	 * 备注信息
	 */
	private String description;
	/**
	 * 对应的目录id
	 */
	private String catalog_id;
	public FileSourceDto(FileSource fileSource){
		this.id = fileSource.getId();
		this.title = fileSource.getTitle();
		this.catalog_id = fileSource.getCatalog().getId();
		this.description = fileSource.getDescription();
		this.type = fileSource.getType();
		this.uploadDate = fileSource.getUploadDate();
		this.url = fileSource.getUrl();
		this.visiable = fileSource.getVisiable();
	}
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
	public String getCatalog_id() {
		return catalog_id;
	}
	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}
	public Integer getVisiable() {
		return visiable;
	}
	public void setVisiable(Integer visiable) {
		this.visiable = visiable;
	}
	
}
