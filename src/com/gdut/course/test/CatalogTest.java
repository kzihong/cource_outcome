package com.gdut.course.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Test;

import com.gdut.course.domain.Catalog;

public class CatalogTest extends BaseTest{
	@Test
	public void testCreate() throws UnsupportedEncodingException{
		Catalog catalog = new Catalog();
		String name = new String("教学课件".getBytes(),"UTF-8");
		catalog.setName(name);
		catalog.setParent_id("02");
		catalogService.create(catalog);
	}
	@Test
	public void testDelete(){
		String[] ids = new String[]{
				"40284b81501d136001501d1362810000","40284b81501d163801501d163b390000"
		};
		for(String id :ids){
			catalogService.delCatalog(id);
		}
	}
}
