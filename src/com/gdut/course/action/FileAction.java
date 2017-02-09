package com.gdut.course.action;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gdut.course.base.BaseAction;
import com.gdut.course.domain.FileSource;
import com.gdut.course.domain.Pager;
import com.gdut.course.dto.FileSourceDto;
import com.gdut.course.service.FileSourceService;
import com.gdut.course.util.Authority;
import com.gdut.course.util.FileUtil;
import com.gdut.course.util.SystemContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 文档文件控制层
 * @author David
 *
 */
@SuppressWarnings("serial")
@Controller("file")
@Scope("prototype")
public class FileAction extends BaseAction<FileSource> {
	@Resource
	private FileUtil fileUtil;
	@Resource
	private FileSourceService fileSourceService;
	@Value("${fileUrl_html}")
	private String fileUrl_html;
	//文件
	private File upload;
	//文件名
	private String uploadFileName;
	//文件类型
	private String uploadContentType;
	
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	/**
	 * 上传本地文件到目录
	 * @return
	 * @throws MyException 
	 */
	@Authority(role="ADMIN")
	public void upload() throws Exception{
		//Map<String,Object> map = new HashMap<String,Object>();
		//Gson gson = new Gson();
		String catalog_id = request.getParameter("catalog_id");
		String file_title = request.getParameter("title");
		model.setTitle(file_title);
		String newUrl = null;
		if(null!=upload){
			model.setType(uploadFileName.substring(uploadFileName.indexOf(".")+1));
			newUrl = fileUtil.upload(upload, uploadFileName);
		}else{//如果文件为空，创建个新的html文件
			model.setType("html");
			newUrl = fileUtil.createFile(file_title+".html");
		}
		model.setUrl(newUrl);
		fileSourceService.upload(model,catalog_id);
		//返回一个script函数
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script type='text/javascript'>");
		out.print("parent.window.uploadCallBack('新建文件成功');");
		out.print("</script>");
//		map.put("errorCode","0000");
//		String json = gson.toJson(map);
//		response.setContentType("application/json;charset=UTF-8");
//		try{
//			response.getWriter().write(json);
//		}catch(IOException e){
//			e.printStackTrace();
//		}
	}
	/**
	 * 上传html关联文件(与CKEditor配合使用)
	 */
	@Authority(role="ADMIN")
	public void upload_html(){
		String callback = request.getParameter("CKEditorFuncNum");
		System.out.println("callback:"+callback);
		PrintWriter out = null;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(uploadContentType.contains("image/jpeg")||uploadContentType.contains("image/png")
				||uploadContentType.contains("image/x-png")||uploadContentType.contains("image/png")
				||uploadContentType.contains("image/bmp")){
		}else{
			out.print("<script type=\"text/javascript\">");
			out.print("window.parent.CKEDITOR.tools.callFunction("+callback+",'',"+
			"'文件格式不正确(必须为.jpg/.gif/.bmp/.png文件)');");
			out.print("</script>");
			return;
		}
		String path = fileUtil.uploadForHtml(request,upload, uploadFileName);
		out.print("<script type=\"text/javascript\">");
		out.print("window.parent.CKEDITOR.tools.callFunction("+callback+",'"+request.getContextPath()+
		path+"','')");
		out.print("</script>");
	}
	/**
	 * 根据目录获取文件列表(管理员)
	 */
	@Authority(role="ADMIN")
	public void pagerList(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String catalog_id = request.getParameter("catalog_id");
		String pageSize = request.getParameter("pageSize");
		String pageOffset = request.getParameter("pageOffset");
		if(null!=pageSize)
			SystemContext.setPageSize(Integer.valueOf(pageSize));
		if(null!=pageOffset)
			SystemContext.setPageOffset(Integer.valueOf(pageOffset));
		//设置排序方式
		SystemContext.setOrder("asc");
		SystemContext.setSort("f.uploadDate");
		Pager<FileSource> pager = fileSourceService.getByCatalog(catalog_id);
		Pager<FileSourceDto> pagerDto = new Pager<FileSourceDto>();
		pagerDto.setCurrentPage(pager.getCurrentPage());
		pagerDto.setBeginPageIndex(pager.getBeginPageIndex());
		pagerDto.setEndPageIndex(pager.getEndPageIndex());
		pagerDto.setPageSize(pager.getPageSize());
		pagerDto.setTotalAmount(pager.getTotalAmount());
		pagerDto.setTotalPages(pager.getTotalPages());
		List<FileSourceDto> listDto = new ArrayList<FileSourceDto>();
		for(FileSource source:pager.getDatas()){
			FileSourceDto dto = new FileSourceDto(source);
			listDto.add(dto);
		}
		pagerDto.setDatas(listDto);
		map.put("errorCode","0000");
		Type type = new TypeToken<Pager<FileSourceDto>>() {
		}.getType();
		try {
			String pagerJson = gson.toJson(pagerDto,type);
			System.out.println(pagerJson);
			map.put("pager_list", pagerJson);
			String json = gson.toJson(map);
			json = json.replace("\\", "");
			json = json.replace("\"{", "{");
			json = json.replace("}\"", "}");
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据目录获得可用的站内链接
	 */
	@Authority(role="ADMIN")
	public void url_list(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String catalog_id = request.getParameter("catalog_id");
		List<FileSource> list_link = fileSourceService.list_link(catalog_id);
		List<FileSourceDto> list = new ArrayList<FileSourceDto>();
		for(FileSource fileSource:list_link){
			list.add(new FileSourceDto(fileSource));
		}
		map.put("errorCode","0000");
		map.put("list_link",list);
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据目录加载文件(用户)
	 */
	public void load(){
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		String catalog_id = request.getParameter("catalog_id");
		List<FileSource> list = fileSourceService.listByCatalog(catalog_id);
		List<FileSourceDto> listDto = new ArrayList<FileSourceDto>();
		for(FileSource fileSource:list){
			listDto.add(new FileSourceDto(fileSource));
		}
		map.put("errorCode","0000");
		map.put("file_list",listDto);
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除文件(批量)
	 */
	@Authority(role="ADMIN")
	public void delete(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String[] ids =request.getParameterValues("id");
		for(String id:ids){
			FileSource fileSource = fileSourceService.getById(id);
			fileUtil.delete(fileSource.getUrl());
			fileSourceService.delete(id);
		}
		map.put("errorCode","0000");
		response.setContentType("application/json;charset=UTF-8");
		String json = gson.toJson(map);
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取文件
	 */
	public void read(){
		String url = model.getUrl();//获取文件url路径
		boolean isDownload = true;
		if(null!=request.getParameter("isDownload")){
		 isDownload = request.getParameter("isDownload").equals("false")?false:true;//是否是下载文件（默认为是)
		}
		FileSource source = fileSourceService.getByUrl(url);
		try {
			fileUtil.read(source.getTitle(),url,isDownload,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改文件(html文件)
	 */
	@Authority(role="ADMIN")
	public void alter(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String id = model.getId();
		FileSource fileSource = fileSourceService.getById(id);
		if(!fileSource.getType().equals("html")){ //不是html文件
			map.put("errorCode","0030");
			map.put("errorMessage",validate.get("0030"));
		}else{
			String content = request.getParameter("content");//修改的内容
			fileUtil.alterHtml(fileSource.getUrl(),content);
			map.put("errorCode","0000");
		}
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改文件信息
	 */
	@Authority(role="ADMIN")
	public void alter_mesg(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		fileSourceService.alter(model);
		map.put("errorCode", "0000");
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 文件列表页面
	 */
	public String list(){
		String catalog_id = request.getParameter("catalog_id");
		request.setAttribute("catalog_id",catalog_id);
		return "fileList";
	}
	/**
	 * html关联图片列表
	 */
	@Authority(role="ADMIN")
	public void photo(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String relativePath = fileUrl_html.substring(fileUrl_html.indexOf("/")+1);
		String absolutePath= request.getServletContext().getRealPath(fileUrl_html);
		File dir = new File(absolutePath);
		if(!dir.exists()||!dir.isDirectory()){
			dir.mkdirs();
		}
		//得到文件夹下所有图片文件
		File[] list_file = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return (pathname.getName().endsWith(".jpg")||pathname.getName().endsWith(".png")
						||pathname.getName().endsWith(".tif")||pathname.getName().endsWith(".gif")
						||pathname.getName().endsWith(".jpeg"));
			}
		});
		List<String> list_name = new ArrayList<String>();
		for(File file:list_file){
			list_name.add(file.getName());
			System.out.println(file.getName());
		}
		map.put("errorCode","0000");
		map.put("photo_list", list_name);
		map.put("path",relativePath);
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try{
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 删除html关联文件
	 */
	@Authority(role="ADMIN")
	public void delete_html(){
		Map<String,Object> map = new HashMap<String,Object>();
		Gson gson = new Gson();
		String files = request.getParameter("delete_files");
		String[] del_files = files.split(",");
		String absolutePath= request.getServletContext().getRealPath(fileUrl_html);
		for(String file_url:del_files){
			File del_file = new File(absolutePath+"/"+file_url);
			del_file.delete();
		}
		map.put("errorCode","0000");
		String json = gson.toJson(map);
		response.setContentType("application/json;charset=UTF-8");
		try{
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
