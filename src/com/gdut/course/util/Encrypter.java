package com.gdut.course.util;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class Encrypter {
	/**
	 * MD5密码加密
	 * @param loginName
	 * @param password
	 * @return
	 */
	public String encodePwd(String loginName,String password){
		int hashCode = loginName.hashCode();
		String code = "";
		try{
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bs = password.getBytes();
			digest.update(bs);
			for(byte b:digest.digest(bs)){
				int a = (b+hashCode)&0xff;
				if(a<16){
					a+=16;
				}
				code+=Integer.toHexString(a);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return code;
	}
}
