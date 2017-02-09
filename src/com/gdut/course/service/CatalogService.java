package com.gdut.course.service;

import java.util.List;

import com.gdut.course.domain.Catalog;

public interface CatalogService {
	/**
	 * 创建子目录
	 * @param model
	 */
	public void create(Catalog catalog);
	/**
	 * 查询子目录列表
	 */
	public List<Catalog> list(String parent_id);
	/**
	 * 删除子目录
	 * @param id
	 */
	public void delCatalog(String id);
}
