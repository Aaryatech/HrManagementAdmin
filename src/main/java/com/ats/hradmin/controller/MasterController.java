package com.ats.hradmin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
 
 
@Controller
@Scope("session")
public class MasterController {
	
	@RequestMapping(value = "/companyAdd", method = RequestMethod.GET)
	public ModelAndView companyAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/companyAdd");
	 
		try {
			 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return model;
	}
	
	@RequestMapping(value = "/submitInsertCompany", method = RequestMethod.POST)
	public String insertCmsForm(@RequestParam("images") List<MultipartFile> images, HttpServletRequest request,
			HttpServletResponse response) {

		 
		try {
			HttpSession session = request.getSession();
			 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/companyAdd";
	}

}
