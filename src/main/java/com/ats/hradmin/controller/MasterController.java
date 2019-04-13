package com.ats.hradmin.controller;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.model.Company; 
 
 
@Controller
@Scope("session")
public class MasterController {
	
	@RequestMapping(value = "/companyAdd", method = RequestMethod.GET)
	public ModelAndView companyAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/companyAdd");
	 
		try {
			 
			 
			Company[] company = Constants.getRestTemplate().getForObject(Constants.url + "/getCompanyList",
					Company[].class);

			List<Company> compList = new ArrayList<Company>(Arrays.asList(company));
			
			System.out.println(compList);
			System.out.println("asdfsdf");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/submitInsertCompany", method = RequestMethod.POST)
	public String insertCmsForm(@RequestParam("images") List<MultipartFile> images, HttpServletRequest request,
			HttpServletResponse response) {

		 
		try {
			HttpSession session = request.getSession();
			 
			String compName = request.getParameter("compName");
			
			Boolean ret = FormValidation.Validaton(compName, "mobile");
			
			System.out.println( ret );

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/companyAdd";
	}

}
