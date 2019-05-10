package com.ats.hradmin.common;
 
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

public class Constants {
 
	public static final String url="http://localhost:8096/";
	//public static final String url="http://132.148.143.124:8080/HrManagementSystem/";
	public static RestTemplate rest = new RestTemplate();
	public static final String imageSaveUrl = "/home/lenovo/Downloads/";
	//public static final String imageSaveUrl = "/opt/apache-tomcat-8.5.6/webapps/media/hr/";
	public static final String getImageSaveUrl = "http://192.168.2.14:8080/media/hr/"; 
	public static String[] values = { "jpg", "jpeg", "gif", "png" };
	public static String[] allextension = { "txt", "doc", "pdf", "xls", "jpg", "jpeg", "gif", "png" };
	 public static RestTemplate getRestTemplate() {
		rest=new RestTemplate();
		rest.getInterceptors().add(new BasicAuthorizationInterceptor("aaryatech", "Aaryatech@1cr"));
		return rest;

		} 
	 
	
}
