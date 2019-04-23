package com.ats.hradmin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.model.LeaveSummary;

@Controller
@Scope("session")
public class LeaveApprovalController {
	
	
	@RequestMapping(value = "/showLeaveApprovalByInitialAuthority", method = RequestMethod.GET)
	public ModelAndView showLeaveApprovalByInitialAuthority(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveApproveByInitial");

		try {
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", 1);
			
			
			LeaveSummary[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url +
					 "/getLeaveSummaryList",map, LeaveSummary[].class);
					
			 List<LeaveSummary> sumList = new ArrayList<LeaveSummary>(Arrays.asList(employeeDoc));
			 System.out.println("lv sum list "+sumList); 
			 model.addObject("sumList",sumList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	@RequestMapping(value = "/showLeaveApprovalByFinalAuthority", method = RequestMethod.GET)
	public ModelAndView showLeaveApprovalByFinalAuthority(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveApproveByFinal");

		try {
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", 1);
			
			
			LeaveSummary[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url +
					 "/getLeaveSummaryList",map, LeaveSummary[].class);
					
			 List<LeaveSummary> sumList = new ArrayList<LeaveSummary>(Arrays.asList(employeeDoc));
			 System.out.println("lv sum list "+sumList); 
			 model.addObject("sumList",sumList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	

}
