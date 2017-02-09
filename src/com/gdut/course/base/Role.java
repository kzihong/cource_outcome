package com.gdut.course.base;

public enum Role {
	TEACHER("老师"),STUDENT("学生"),ADMIN("管理员");
	private String name;
	Role(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
}
