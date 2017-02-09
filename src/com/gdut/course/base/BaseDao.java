package com.gdut.course.base;


import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.gdut.course.domain.Pager;
import com.gdut.course.util.SystemContext;

public class BaseDao<T> implements IBaseDao<T>{
	/**
	 * 默认的页面大小
	 */
	@Value("${pageSize}")
	private Integer pageSize;
	/**
	 * 默认的初始页
	 */
	@Value("${pageOffset}")
	private Integer pageOffset;
	/**
	 * 显示页码数量
	 */
	@Value("${pageCount}")
	private Integer pageCount;
	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> clz;
	@SuppressWarnings("unchecked")
	private Class<T> getClz(){
		if(null == clz){
			clz = (Class<T>) (((ParameterizedType) (this.getClass()
					.getGenericSuperclass()))
					.getActualTypeArguments()[0]);
		}
		return clz;
	}
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public T save(T t) {
		getSession().save(t);
		return t;
	}
	@Override
	public void delete(String id) {
		T t = this.get(id);
		if(null != t){
			getSession().delete(t);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public T get(String id) {
		return (T) getSession().get(getClz(), id);
	}
	@Override
	public void update(T t) {
		getSession().update(t);
	}
	//初始化hql语句
	private String initSort(String hql){
		String sort = SystemContext.getSort();
		String order = SystemContext.getOrder();
		if(null!=sort&&!"".equals(order)){
			hql += " order by "+sort;
			if("desc".equals(order)){
				hql +=" desc";
			}else{
				hql +=" asc";
			}
		}
		return hql;
	}
	//设置参数
	private void setParameters(Query query,Object[] args){
		if(null!=args&&args.length>0){
			for(int i = 0;i<args.length;i++){
				query.setParameter(i, args[i]);
			}
		}
	}
	private void setAliasParameters(Query query,Map<String,Object> alias){
		if(null!=alias){
			Set<String> keys = alias.keySet();
			for(String key:keys){
				Object obj = alias.get(key);
				if(obj instanceof Collection){
					query.setParameterList(key, (Collection)obj);
				}else{
					query.setParameter(key,obj);
				}
			}
		}
	}
	//设置分页参数
	private void setPager(Query query,Pager<T> pager){
		Integer pageOffset = SystemContext.getPageOffset();
		Integer pageSize = SystemContext.getPageSize();
		if(null == pageOffset){
			pageOffset = this.pageOffset;
		}
		if(null == pageSize){
			pageSize = this.pageSize;
		}
		pager.setCurrentPage(pageOffset);
		pager.setPageSize(pageSize);
		query.setFirstResult((pageOffset-1)*pageSize).setMaxResults(pageSize);
	}
	private String getCountHql(String hql,boolean isHql){
		String e = hql.substring(hql.indexOf("from"));
		String c = "select  count(*)" + e;
		if (isHql)
			c = c.replaceAll("fetch", "");
		return c;
	}
	//计算页码索引范围
	private void setPageRange(Pager pager){
		int currentPage = pager.getCurrentPage();
		Long totalPage = pager.getTotalPages();
		int beginPageIndex  = 0;
		int endPageIndex = 0;
		int total = Integer.valueOf(totalPage.toString());
		if(pageCount>total){
			beginPageIndex = 1;
			endPageIndex = total;
		}else{
			if(currentPage<pageCount/2){
				beginPageIndex = 1;
				endPageIndex = pageCount;
			}else{
				beginPageIndex = currentPage-(pageCount/2)+1;
				endPageIndex = beginPageIndex+pageCount-1;
			}
			if(endPageIndex>pageCount){
				endPageIndex = pageCount;
				beginPageIndex = endPageIndex-(pageCount-1);
			}
		}
		pager.setBeginPageIndex(beginPageIndex);
		pager.setEndPageIndex(endPageIndex);
	}
	public Object queryObject(String hql,Object[] args){
		return queryObject(hql, args, null);
	}
	public Object queryObject(String hql,Map<String,Object> alias){
		return queryObject(hql, null,alias);
	}
	public Object queryObject(String hql,Object[] args,Map<String,Object> alias){
		hql = initSort(hql);
		Query query = getSession().createQuery(hql);
		setParameters(query, args);
		setAliasParameters(query, alias);
		return query.uniqueResult();
	}
	public List<T> list(String hql,Object[] args){
		return list(hql,args,null);
	}
	public List<T> list(String hql,Map<String,Object> alias){
		return list(hql,null,alias);
	}
	public List<T> list(String hql,Object[] args,Map<String,Object> alias){
		hql  = initSort(hql);
		Query query = getSession().createQuery(hql);
		setParameters(query, args);
		setAliasParameters(query, alias);
		return (List<T>)query.list();
	}
	//分页查询
	public Pager<T> find(String hql,Object[] args){
		return find(hql,args,null);
	}
	public Pager<T> findByAlias(String hql,Map<String,Object> alias){
		return find(hql,null,alias);
	}
	public Pager<T> find(String hql,Object[] args,Map<String,Object> alias){
		hql = initSort(hql);
		String cql = getCountHql(hql, true);
		Query query = getSession().createQuery(hql);
		Query cquery = getSession().createQuery(cql);
		setParameters(query, args);
		setParameters(cquery,args);
		setAliasParameters(query, alias);
		setAliasParameters(cquery, alias);
		Pager<T> pager = new Pager<T>();
		setPager(query, pager);
		List<T> result = query.list();
		pager.setDatas(result); //页面数据
		Long total = (Long) cquery.uniqueResult();
		pager.setTotalAmount(total); //总记录数
		pager.setTotalPages((total+pager.getPageSize()-1)/pager.getPageSize()); //总页数
		setPageRange(pager);//计算页码索引范围
		return pager;
	}
}
