package com.gdut.course.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 文件工具类
 * @author David
 *
 */
@Component
public class FileUtil {
	/**
	 * 存储资源文件地址
	 */
	@Value("${fileUrl}")
	private String fileUrl;
	/**
	 * contentType配置
	 */
	@Resource(name="config")
	private Properties contentType;
	/**
	 * 存放html关联文件的地址
	 */
	@Value("${fileUrl_html}")
	private String fileUrl_html;
	//获得扩展名
	private String getExt(String fileName){
		return fileName.substring(fileName.indexOf("."));
	}
	//获得新文件的url
	private String getNewUrl(String fileName){
		String newName = UUID.randomUUID().toString().replace("-","")+getExt(fileName);
		int code = newName.hashCode();
		String dir = Integer.toHexString(code&0xf);
		return dir+"/"+newName;
	}
	@SuppressWarnings("resource")
	//上传本地资源文件
	public String upload(File file,String fileName){
		InputStream in = null;
		OutputStream out = null;
		String newUrl = getNewUrl(fileName);
		try{
			in = new FileInputStream(file);
			File newFile = new File(fileUrl+newUrl.substring(0,newUrl.indexOf("/")));
			if(!newFile.exists()){
				newFile.mkdirs();
			}
			out = new FileOutputStream(fileUrl+newUrl);
			byte[] b = new byte[1024];
			int len = -1;
			while((len = in.read(b))!=-1){
				out.write(b,0,len);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			try {
				if(null!=in)
					in.close();
				if(null!=out)
					out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return newUrl;
	}
	//上传html关联文件到项目文件夹
	@SuppressWarnings("resource")
	public String  uploadForHtml(HttpServletRequest request,File file,String fileName){
		String newName = UUID.randomUUID().toString().replace("-","")+getExt(fileName);
		String projPath = request.getServletContext().getRealPath(fileUrl_html);
		InputStream in = null;
		OutputStream out = null;
		String path = null;
		try{
			projPath = projPath.replace("\\", "/");
			path=projPath+"/"+newName;
			in = new FileInputStream(file);
			out = new FileOutputStream(path);
			byte[] b = new byte[1024];
			int len = -1;
			while((len = in.read(b))!=-1){
				out.write(b,0,len);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			try {
				if(null!=in)
					in.close();
				if(null!=out)
					out.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return fileUrl_html+"/"+newName;
	}
	//创建空文件
	public String createFile(String fileName){
		String newUrl = getNewUrl(fileName);
		File newFile = new File(fileUrl+newUrl.substring(0,newUrl.indexOf("/")));
		if(!newFile.exists()){
			newFile.mkdirs();
		}
		newFile = new File(fileUrl+newUrl);
		try {
			newFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newUrl;
	}
	//删除文件
	public void delete(String url){
		File file = new File(fileUrl+url);
		if(file.exists()){
			file.delete();
		}
	}
	//读取文件
	public void read(String title,String url,boolean isDownload,HttpServletResponse resp){
		File file = new File(fileUrl+url);
		String fileName = file.getName();
		if(file.exists()){
			if(!fileName.contains(".txt")){ //非文本类型文件
				String ext = fileName.substring(fileName.indexOf(".")+1);
				String type = "";
				if(null!=(type=(String)contentType.get(ext))){ 
					resp.setContentType(type);
				}else if(isDownload){ //未定义类型的文件默认变为下载
					try {
						title = URLEncoder.encode(title,"utf-8");
					} catch (UnsupportedEncodingException e) {
						throw new RuntimeException();
					}
					resp.setHeader("Content-Disposition", "attachment;filename="+title+"."+ext);
				}else{ //标志为非下载的文件
					
				}
				InputStream in = null;
				try{
					in = new FileInputStream(file);
					byte[]  b = new byte[1024];
					int len = -1;
					while((len=in.read(b))!=-1){
						resp.getOutputStream().write(b,0,len);
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try {
						if(null!=in)
							in.close();
						resp.getOutputStream().close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}else{
				resp.setCharacterEncoding("UTF-8");
				BufferedReader reader = null;
				try{
					reader = new BufferedReader(new InputStreamReader(
							new FileInputStream(file),"UTF-8"));
					String line = null;
					while(null!=(line=reader.readLine())){
						//输入回车转义符
						resp.getWriter().print(line); 
						resp.getWriter().print("\n");
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try {
						if(null!=reader)
							reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	//修改html文件
	public  void alterHtml(String url,String content) {
		File file = new File(fileUrl+url);
		PrintWriter writer = null;
		try{
			writer = new PrintWriter(file,"UTF-8");
			writer.println(content);
			writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(null!=writer)
					writer.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
