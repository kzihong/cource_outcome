package com.gdut.course.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;

/**
 * Request的包装类
 * @author David
 *
 */
public class RequestParseWrapper extends JakartaMultiPartRequest {

	@Override
	public void parse(HttpServletRequest arg0, String savDire) throws IOException {
	}
	
}
