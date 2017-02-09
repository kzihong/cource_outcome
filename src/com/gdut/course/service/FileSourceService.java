package com.gdut.course.service;

import java.util.List;

import com.gdut.course.domain.FileSource;
import com.gdut.course.domain.Pager;

public interface FileSourceService {
	/**
	 * 上传文档文件
	 * @param model 文件资源对象
	 * @param catalog_id 文档目录id
	 */
	public void upload(FileSource source, String catalog_id);
	/**
	 * 根据文档目录获得文件列表（管理员分页)
	 * @param catalog_id 文档目录id
	 */
	public Pager<FileSource> getByCatalog(String catalog_id);
	/**
	 * 根据id查询文件
	 * @param id
	 * @return
	 */
	public FileSource getById(String id);
	/**
	 * 根据id删除文件
	 */
	public void delete(String id);
	/**
	 * 根据文档目录加载文件列表
	 * @param catalog_id
	 * @return
	 */
	public List<FileSource> listByCatalog(String catalog_id);
	/**
	 * 根据目录获得可用的站内链接
	 * @param catalog_id
	 * @return
	 */
	public List<FileSource> list_link(String catalog_id);
	/**
	 * 修改文件信息
	 * @param fileSource
	 */
	public void alter(FileSource fileSource);
	/**
	 * 根据url获得文件 
	 * @param url
	 * @return
	 */
	public FileSource getByUrl(String url);

}
