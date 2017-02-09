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
import com.gdut.course.domain.Pager;
import com.gdut.course.service.FileSourceService;

@Service
@Transactional(readOnly=true)
public class FileSourceServiceImpl implements FileSourceService {
	@Resource
	private FileSourceDao fileSourceDao;
	@Resource
	private CatalogDao catalogDao;
	@Override
	@Transactional(readOnly=false)
	public void upload(FileSource source, String catalog_id) {
		source.setUploadDate(new Date());
		Catalog catalog = catalogDao.get(catalog_id);
		source.setCatalog(catalog);
		fileSourceDao.save(source);
	}
	@Override
	public Pager<FileSource> getByCatalog(String catalog_id) {
		Map<String,Object> alias = new HashMap<String,Object>();
		String hql = "from FileSource f where f.catalog.id=:catalogId";
		alias.put("catalogId",catalog_id);
		return fileSourceDao.findByAlias(hql, alias);
	}
	@Override
	public FileSource getById(String id) {
		return fileSourceDao.get(id);
	}
	@Override
	@Transactional(readOnly=false)
	public void delete(String id) {
		fileSourceDao.delete(id);
	}
	@Override
	public List<FileSource> listByCatalog(String catalog_id) {
		Map<String,Object> alias = new HashMap<String,Object>();
		String hql = "from FileSource f where f.catalog.id=:catalogId";
		alias.put("catalogId",catalog_id);
		return fileSourceDao.list(hql, alias);
	}
	@Override
	public List<FileSource> list_link(String catalog_id) {
		Map<String,Object> alias = new HashMap<String,Object>();
		String hql = "from FileSource f where f.catalog.id=:catalogId and f.visiable=0";
		alias.put("catalogId",catalog_id);
		return fileSourceDao.list(hql,alias);
	}
	@Override
	@Transactional(readOnly=false)
	public void alter(FileSource fileSource) {
		String id = fileSource.getId();
		FileSource oldObj = fileSourceDao.get(id);
		if(null!=oldObj){
			if(null!=fileSource.getDescription()){
				oldObj.setDescription(fileSource.getDescription());
			}
			if(null!=fileSource.getTitle()&&!"".equals(fileSource.getTitle().trim())){
				oldObj.setTitle(fileSource.getTitle());
			}
			if(null!=fileSource.getVisiable()){
				oldObj.setVisiable(fileSource.getVisiable());
			}
			fileSourceDao.update(oldObj);
		}
	}
	@Override
	public FileSource getByUrl(String url) {
		Map<String,Object> alias = new HashMap<String,Object>();
		String hql = "from FileSource f where f.url=:url";
		alias.put("url",url);
		return (FileSource)fileSourceDao.queryObject(hql, alias);
	}

}
