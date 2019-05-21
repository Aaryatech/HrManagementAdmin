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
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.model.ActivityRevenueReport;
import com.ats.hradmin.model.EmployeeOnBenchReport;
import com.ats.hradmin.model.LoginResponse; 

@Controller
@Scope("session")
public class ReportsController {
	
	@RequestMapping(value = "/onbenchReport", method = RequestMethod.GET)
	public ModelAndView onbenchReport(HttpServletRequest request, HttpServletResponse response) {
	
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
		
		ModelAndView model = new ModelAndView("reports/onbenchReport");
		try {

			 String fromDate = request.getParameter("fromDate");
			 String toDate = request.getParameter("toDate");
			 
			 if(!fromDate.equalsIgnoreCase(null) || !toDate.equalsIgnoreCase(null)) {
				 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("fromDate", DateConvertor.convertToYMD(fromDate));
				map.add("toDate", DateConvertor.convertToYMD(toDate));
				map.add("locIds", userObj.getLocationIds());
				
				EmployeeOnBenchReport[] employeeOnBenchReport = Constants.getRestTemplate().postForObject(Constants.url + "/getOnBenchReport", map,
						EmployeeOnBenchReport[].class);
					
				List<EmployeeOnBenchReport> list = new ArrayList<>(Arrays.asList(employeeOnBenchReport));
				model.addObject("list", list);
				model.addObject("fromDate", fromDate);
				model.addObject("toDate", toDate);
			 }
				
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/activityRevenueReport", method = RequestMethod.GET)
	public ModelAndView activityRevenueReport(HttpServletRequest request, HttpServletResponse response) {
	
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
		
		ModelAndView model = new ModelAndView("reports/activityRevenueReport");
		try {
 
				/*MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
				map.add("compId", userObj.getCompanyId());
				
				ActivityRevenueReport[] activityRevenueReport = Constants.getRestTemplate().postForObject(Constants.url + "/revenueReportProjectCategoryWise", map,
						ActivityRevenueReport[].class);
					
				List<ActivityRevenueReport> list = new ArrayList<>(Arrays.asList(activityRevenueReport));
				model.addObject("list", list); */
			  
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
