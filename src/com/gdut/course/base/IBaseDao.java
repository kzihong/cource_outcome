package com.gdut.course.base;

public interface IBaseDao<T> {
	public T save(T t);
	public void delete(String id);
	public T get(String id);
	public void update(T t);
}
