package com.gdut.course.serviceImpl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.course.dao.WorkDao;
import com.gdut.course.domain.Pager;
import com.gdut.course.domain.Question;
import com.gdut.course.domain.User;
import com.gdut.course.domain.Work;
import com.gdut.course.service.WorkService;
@Service
@Transactional(readOnly=true)
public class WorkServiceImpl implements WorkService{
	@Resource
	private WorkDao workDao;
	@Override
	public Pager<Work> findAll() {
		String hql ="from Work w";
		return workDao.findByAlias(hql, null);
	}
	@Override
	@Transactional(readOnly=false)
	public void upload(Work work,User user) {
		work.setSendDate(new Date());
		work.setUser(user);
		workDao.save(work);
	}
	@Override
	public Work getById(String id) {
		Work work = workDao.get(id);
		return work;
	}
}
