package com.ats.hradmin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.common.VpsImageUpload;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.DocList;
import com.ats.hradmin.model.EmployeDoc;
import com.ats.hradmin.model.LoginResponse;

@Controller
@Scope("session")
public class DocUploadController {

	List<DocList> docList = new ArrayList<DocList>();

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.GET)
	public ModelAndView uploadDocument(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/uploadDocument");

		try {

			String base64encodedString = request.getParameter("empId");
			String empId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);

			DocList[] doc = Constants.getRestTemplate().postForObject(Constants.url + "/getdocListByEmpId", map,
					DocList[].class);

			docList = new ArrayList<DocList>(Arrays.asList(doc));
			model.addObject("docList", docList);
			model.addObject("empId", base64encodedString);
			model.addObject("docUrl", Constants.getImageSaveUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertEmpDoc", method = RequestMethod.POST)
	public String submitInsertEmpDoc(@RequestParam("doc") List<MultipartFile> doc, HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		try {

			VpsImageUpload upload = new VpsImageUpload();
			
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			LoginResponse obj = (LoginResponse) session.getAttribute("UserDetail");
			List<EmployeDoc> list = new ArrayList<>();

			String base64encodedString = request.getParameter("empId");
			int empId = Integer.parseInt(FormValidation.DecodeKey(base64encodedString));
			
			int failed=0;
			
			for (int j = 0; j < docList.size(); j++) {

				System.out.println(doc.get(j).getOriginalFilename());

				String imageName = new String();

				if (doc.get(j).getOriginalFilename() == "" && docList.get(j).getIsRequired() == 1
						&& docList.get(j).getImageName().equals("0")) {
					failed=1;
					System.out.println("in if");
					break;
					
				} else if (doc.get(j).getOriginalFilename() != "") {

					System.out.println("doc.get(j).getOriginalFilename() != \"\"");
					imageName = dateTimeInGMT.format(date) + "_" + doc.get(j).getOriginalFilename();

					try {
						upload.saveUploadedImge(doc.get(j), Constants.imageSaveUrl, imageName, Constants.allextension,
								0, 0, 0, 0, 0);

						EmployeDoc employeDoc = new EmployeDoc();
						employeDoc.setDocId(docList.get(j).getDocId());
						employeDoc.setDoctypeId(docList.get(j).getDoctypeId());
						employeDoc.setIsActive(1);
						employeDoc.setDelStatus(1);
						employeDoc.setMakerUserId(obj.getUserId());
						employeDoc.setEmpId(empId);
						employeDoc.setMakerEnterDatetime(sf.format(date));
						employeDoc.setDocImage(imageName);
						list.add(employeDoc);

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}else if (doc.get(j).getOriginalFilename() == "" && docList.get(j).getIsRequired() == 1
						&& !docList.get(j).getImageName().equals("0")) {
 
					System.out.println("else");
					try {
						 
						EmployeDoc employeDoc = new EmployeDoc();
						employeDoc.setDocId(docList.get(j).getDocId());
						employeDoc.setDoctypeId(docList.get(j).getDoctypeId());
						employeDoc.setIsActive(1);
						employeDoc.setDelStatus(1);
						employeDoc.setMakerUserId(obj.getUserId());
						employeDoc.setMakerEnterDatetime(sf.format(date));
						employeDoc.setEmpId(empId);
						employeDoc.setDocImage(docList.get(j).getImageName());
						list.add(employeDoc);

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

			}

			
			
			if (failed == 0 ) {
				
				EmployeDoc[] res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpDocList", list,
						EmployeDoc[].class);
				session.setAttribute("successMsg", "Record Insert Successfully");
			} else {
				
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Insert Record");
		}
		return "redirect:/showEmpList";
	}

}
