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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
 
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.Setting;

@Controller
@Scope("session")
public class SettingController {
	

	@RequestMapping(value = "/showModLimitList", method = RequestMethod.GET)
	public ModelAndView showModLimit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("Setting/modLimit");

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("limitKey", "LEAVELIMIT");
			Setting setlimit = Constants.getRestTemplate().postForObject(Constants.url + "/getSettingByKey", map,
					Setting.class);
			model.addObject("setlimit", setlimit);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/updateLeaveLimit", method = RequestMethod.GET)
	public @ResponseBody Info updateLeaveLimit(HttpServletRequest request, HttpServletResponse response) {

		Info info = new Info();
		  
		try {
			System.err.println("in  updateStatus is ");
			String temp=(request.getParameter("temp"));
			String setId=(request.getParameter("setId"));
			
			  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("settingId",setId);
			  map.add("val",temp);
			  
				  info = Constants.getRestTemplate().postForObject(Constants.url + "/updateSetting", map, Info.class);
				System.err.println("info is "+info.toString());
				

		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	

}
