/*package com.ats.hradmin.common;
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ats.hradmin.model.Info;
 

public class VpsImageUpload {
	
	 
	
	//public static final String galleryImage= "/home/devour/apache-tomcat-9.0.12/webapps/mongistoreuploads/";
	public static final String galleryImage= "/home/lenovo/Documents/pdf/";
	 
	public Info saveUploadedImge(MultipartFile file,String uploadPath,String imageName,String[] allowExt,int isResize, int width, int hieght
			, int isCheckSize, int imageSizeMax) throws IOException {

				Info info = new Info();
				
				 try {
					 String extension = FilenameUtils.getExtension(file.getOriginalFilename());
					 
					 if ( ArrayUtils.contains( allowExt, extension.toLowerCase() ) ) {
						
							Path path = Paths.get(uploadPath + imageName);
				
							byte[] bytes = file.getBytes();
				 
								System.out.println("Inside Image Type =1");
				
								//path = Paths.get(uploadPath + imageName);
				
								System.out.println("Path= " + path.toString() + "" +file.getSize());
				
							 
				
							Files.write(path, bytes);
						  
						
						info.setError(false);
						info.setMsg("Upload Successfully ");
					 }else {
						 	info.setError(true);
							info.setMsg("Error While Uploading Image");
					 }
						
				 }catch (Exception e) {
					 
					e.printStackTrace();
					info.setError(true);
					info.setMsg("Error While Uploading Image");
				}
			return info;

		}
	 
	public void saveUploadedFiles(MultipartFile file, int imageType, String imageName) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {

				System.out.println("int if");
				continue;

			}

			Path path = Paths.get(galleryImage + imageName);

			byte[] bytes = file.getBytes();

			if (imageType == 1) {
				System.out.println("Inside Image Type =1");

				path = Paths.get(galleryImage + imageName);

				System.out.println("Path= " + path.toString());

			}  

			Files.write(path, bytes);

		}
	//}

}
*/