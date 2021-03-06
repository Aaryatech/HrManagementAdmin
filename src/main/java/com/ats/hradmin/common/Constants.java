package com.ats.hradmin.common;
 
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

public class Constants {
 
	public static final String url="http://localhost:8093/";
	//public static final String url="http://ifbthrms.infrabeat.com:8181/HrManagementSystem/";
	//public static final String url="http://132.148.143.124:8080/HrManagementSystem/";
	public static RestTemplate rest = new RestTemplate(); 
	//public static final String imageSaveUrl = "/home/lenovo/Downloads/";
	public static final String imageSaveUrl = "/opt/tomcat/webapps/hr/";
	public static final String getImageSaveUrl = "http://ifbthrms.infrabeat.com:8181/hr/"; 
	//public static final String getImageSaveUrl = "http://10.0.0.4:8181/hr/";
	public static String[] values = { "jpg", "jpeg", "gif", "png" };
	public static String[] allextension = { "txt", "doc", "pdf", "xls", "jpg", "jpeg", "gif", "png" };
	 public static RestTemplate getRestTemplate() {
		rest=new RestTemplate();
		rest.getInterceptors().add(new BasicAuthorizationInterceptor("aaryatech", "Aaryatech@1cr"));
		return rest;

		} 
	 
	
}
