package com.ats.hradmin.common;
 
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

public class Constants {
 
	public static final String url="http://localhost:8096/";
	public static RestTemplate rest = new RestTemplate();
	public static final String imageSaveUrl = "/home/lenovo/Downloads/media";
	public static String[] values = { "jpg", "jpeg", "gif", "png" };
	public static Object getImageSaveUrl;
	public static String[] allextension;
	
	 public static RestTemplate getRestTemplate() {
		rest=new RestTemplate();
		rest.getInterceptors().add(new BasicAuthorizationInterceptor("aaryatech", "Aaryatech@1cr"));
		return rest;

		} 
	 
	
}
