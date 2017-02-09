package com.gdut.course.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdut.course.dao.CatalogDao;
import com.gdut.course.dao.FileSourceDao;
import com.gdut.course.domain.Catalog;
import com.gdut.course.domain.FileSource;
import com.gdut.course.service.CatalogService;
import com.gdut.course.util.FileUtil;
@Service
@Transactional(readOnly=true)
public class CatalogServiceImpl implements CatalogService {
	@Resource
	private CatalogDao catalogDao;
	@Resource
	private FileSourceDao fileSourceDao;
	@Resource
	private FileUtil fileUtil;
	@Override
	@Transactional(readOnly=false)
	public void create(Catalog catalog) {
		catalogDao.save(catalog);
		String url = fileUtil.createFile(catalog.getName()+".html"); //创建和子目录同名的html文件
		FileSource fileSource = new FileSource();
		fileSource.setCatalog(catalog);
		fileSource.setTitle(catalog.getName());
		fileSource.setType("html");
		fileSource.setUploadDate(new Date());
		fileSource.setUrl(url);
		fileSource.setVisiable(1);
		fileSourceDao.save(fileSource);
	}

	@Override
	public List<Catalog> list(String parent_id) {
		Map<String,Object> alias = new HashMap<String,Object>();
		String hql = "from Catalog c where c.parent_id=:parent_id";
		alias.put("parent_id",parent_id);
		List<Catalog> list = catalogDao.list(hql, alias);
		return list;
	}

	@Override
	@Transactional(readOnly=false)
	public void delCatalog(String id) {
		Map<String,Object> alias = new HashMap<String,Object>();
		String hql = "from FileSource f where f.catalog.id=:catalog_id";
		alias.put("catalog_id",id);
		List<FileSource> list = fileSourceDao.list(hql, alias);
		if(null!=list){
			for(FileSource source:list){
				fileUtil.delete(source.getUrl()); //删除文件
				fileSourceDao.delete(source.getId());
			}
		}
		catalogDao.delete(id);//删除目录
	}

}
