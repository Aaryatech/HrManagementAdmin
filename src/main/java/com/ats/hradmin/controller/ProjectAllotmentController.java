package com.ats.hradmin.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants; 
import com.ats.hradmin.model.EmployeeCategory;
import com.ats.hradmin.model.LoginResponse;

@Controller
@Scope("session")
public class ProjectAllotmentController {
	
	@RequestMapping(value = "/projectAllotment", method = RequestMethod.GET)
	public ModelAndView empDocAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("project/projectAllotment");

		try {
			
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", userObj.getCompanyId());
			EmployeeCategory[] employeeCategory = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpCategoryList", map, EmployeeCategory[].class);

			List<EmployeeCategory> employeeCategorylist = new ArrayList<EmployeeCategory>(
					Arrays.asList(employeeCategory));
 
			model.addObject("empCatList", employeeCategorylist);
			 
			//pallot_fromdt
			//pallot_todt
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
