package com.gdut.course.test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.gdut.course.domain.User;
import com.google.gson.Gson;

public class FileTest {
	@Test
	public void testUpload(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpClientContext context = new HttpClientContext();
		HttpResponse response;
		try{
			String url = "http://localhost:8080/course/file_upload.action";
			HttpPost httpPost = new HttpPost(url);
			HttpEntity entity;
			StringBody catalog_id = new StringBody("40284b81501d136001501d1362810000");
			StringBody title = new StringBody("课程视频(5)",Charset.forName("UTF-8"));
			File file = new File("C:/Users/David/Desktop/就是我了.txt");
			FileBody source = new FileBody(file);
			entity = MultipartEntityBuilder.create().setCharset(Charset.forName("UTF-8"))
					.addPart("catalog_id", catalog_id)
					.addPart("title",title)
					.addPart("upload",source).build();
			httpPost.setEntity(entity);
			response = httpClient.execute(httpPost,context);
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void jsonText(){
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		User user = new User();
		user.setDate(new Date());
		map.put("hellow",user);
		System.out.println(gson.toJson(map));
	}
}
