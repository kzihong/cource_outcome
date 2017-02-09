package com.gdut.course.service;

import com.gdut.course.domain.Pager;
import com.gdut.course.domain.User;
import com.gdut.course.domain.Work;

public interface WorkService  {
	/**
	 * 分页查找所有的作业信息
	 * @return
	 */
	public Pager<Work> findAll();
	/**
	 * 上传作业
	 * @param model
	 */
	public void upload(Work model,User user);
	/**
	 * 根据id获得作业信息
	 * @param id
	 * @return
	 */
	public Work getById(String id);

}
