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

import com.ats.hradmin.claim.ClaimType;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.model.LeaveSummary;

@Controller
@Scope("session")
public class ClaimApplicationController {
	
	@RequestMapping(value = "/showClaimApply", method = RequestMethod.GET)
	public ModelAndView showClaimApply(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claimApply");

		try {
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", 1);
			
			
			ClaimType[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url +
					 "/getClaimList",map, ClaimType[].class);
					
			 List<ClaimType> claimTypeList = new ArrayList<ClaimType>(Arrays.asList(employeeDoc));
			 System.out.println("claimTypeList list "+claimTypeList.toString()); 
			 model.addObject("claimTypeList",claimTypeList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
