package com.gdut.course.test;

import org.junit.Test;

import com.gdut.course.base.Role;
import com.gdut.course.domain.User;
import com.gdut.course.util.Encrypter;

public class UserTest extends BaseTest {
	@Test
	public void testLogin(){
		String loginName = "华德义";
		String password = "5678956";
		User user = userService.login(loginName, password);
		System.out.println(user.getId()+"\n"+user.getLoginName()+
				"\n"+user.getPassword()+"\n"+user.getDate()+"\n"+user.getRole());
	}
	@Test
	public void testRegister(){
		User user  = new User();
		user.setLoginName("华德义");
		user.setPassword("5678956");
		user.setRole(Role.STUDENT);
		userService.register(user);
	}
	@Test
	public void testValidate(){
		String loginName="华德义";
		User user = userService.getByLoginName(loginName);
		System.out.println(user.getId()+"\n"+user.getLoginName()+
				"\n"+user.getPassword()+"\n"+user.getDate()+"\n"+user.getRole());
	}
	@Test
	public void testEncode(){
		Encrypter encrypter = new Encrypter();
		String loginName = "华德义";
		String password = "5678956";
		System.out.println(encrypter.encodePwd(loginName, password));
	}
}
