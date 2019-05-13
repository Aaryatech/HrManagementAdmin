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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
  
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.krakpi.model.FinancialYear;
import com.ats.hradmin.krakpi.model.GetEmpKra;
import com.ats.hradmin.krakpi.model.GetEmpKraKpiCount;
import com.ats.hradmin.krakpi.model.Kra;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.LeaveType;
import com.ats.hradmin.model.LoginResponse;
  
@Controller
@Scope("session")
public class KraKpiController {
	
	
	@RequestMapping(value = "/showEmpKraKpiCountList", method = RequestMethod.GET)
	public ModelAndView showEmpKraKpiCountList(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("krakpi/krakpiCountList");
		try {
			
			  FinancialYear[] employeeDoc = Constants.getRestTemplate().getForObject(Constants.url + "/getFinYearList",FinancialYear[].class);
			  
			  List<FinancialYear> employeeList = new
			  ArrayList<FinancialYear>(Arrays.asList(employeeDoc));
		      model.addObject("finYrList",employeeList);
		      
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}

	@RequestMapping(value = "/empInfoCountList", method = RequestMethod.GET)
	public @ResponseBody List<GetEmpKraKpiCount> empInfoCountList(HttpServletRequest request, HttpServletResponse response) {

		  List<GetEmpKraKpiCount> employeeInfoList=new ArrayList<GetEmpKraKpiCount>();
		try {
			
		
			int status=Integer.parseInt(request.getParameter("status"));
			int finYrId=Integer.parseInt(request.getParameter("finYrId"));
			
			  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("status",status);
			  map.add("finYrId",finYrId);
			   
			  GetEmpKraKpiCount[] employeeInfo = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKraKpiCount",map,
					  GetEmpKraKpiCount[].class);
			   
			  employeeInfoList = new ArrayList<GetEmpKraKpiCount>(Arrays.asList(employeeInfo));
			  for (int i = 0; i < employeeInfoList.size(); i++) {

				  employeeInfoList.get(i).setEmpMname(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getEmpId())));
				}
			  System.err.println("emp List is:"+employeeInfoList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeInfoList;
	}
	  
	@RequestMapping(value = "/showAddKra", method = RequestMethod.GET)
	public ModelAndView showAddKra(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("krakpi/addKra");
		try {
			List<GetEmpKra> employeeInfoList=new ArrayList<GetEmpKra>();
 			String base64encodedString = request.getParameter("empId");
 			System.out.println("base64encodedString:"+base64encodedString);
			String empId = FormValidation.DecodeKey(base64encodedString);			
			String finYrId = request.getParameter("finYrId");	
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);

			GetEmployeeInfo empInfo = Constants.getRestTemplate().postForObject(Constants.url + "/GetEmployeeInfo", map,
					GetEmployeeInfo.class);
			model.addObject("empInfo", empInfo);
			
			map = new LinkedMultiValueMap<>();
			map.add("finYrId", finYrId);

			FinancialYear finYr = Constants.getRestTemplate().postForObject(Constants.url + "/getFinYearByFinYrId", map,
					FinancialYear.class);
			model.addObject("finYr", finYr);
			
			map = new LinkedMultiValueMap<>();
			  map.add("empId",empId);
			  map.add("finYrId",finYrId);
			   
			  GetEmpKra[] kraList = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKraList",map,
					  GetEmpKra[].class);
			   
			  employeeInfoList = new ArrayList<GetEmpKra>(Arrays.asList(kraList));
			  for (int i = 0; i < employeeInfoList.size(); i++) {

				  employeeInfoList.get(i).setExvar1(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getKraId())));
				}
			  
			  model.addObject("kraList", employeeInfoList);
			  System.err.println("kraList List is:"+employeeInfoList.toString());

		      
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}

	
	@RequestMapping(value = "/submitInsertKra", method = RequestMethod.POST)
	public String submitInsertKra(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			 
			String kra_title = request.getParameter("kra_title");
			 
			String empId = request.getParameter("empId");
			String finYrId = request.getParameter("finYrId");
 			String remark = null;
	 
 			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(kra_title, "") == true) {

				ret = true;
				System.out.println("kra_title" + ret);
			}
			 
			if (ret == false) {

				Kra leaveSummary = new Kra();
				leaveSummary.setAcceptedDate(sf.format(date));
				leaveSummary.setAcceptedFlag(0);
				leaveSummary.setApprovedBy(0);
				leaveSummary.setApprovedFlag(0);
				leaveSummary.setApprovedDate(sf.format(date));
				leaveSummary.setEmpId(Integer.parseInt(empId));
				leaveSummary.setYearId(Integer.parseInt(finYrId));
				leaveSummary.setKraTitle(kra_title);
				 
				
				leaveSummary.setRemark(remark); 
				leaveSummary.setExInt1(1);
				leaveSummary.setExInt2(1);
				leaveSummary.setExInt3(1);
				leaveSummary.setExVar1("NA");
				leaveSummary.setExVar2("NA");
				leaveSummary.setExVar3("NA");
				leaveSummary.setIsActive(1);
				leaveSummary.setDelStatus(1);
				leaveSummary.setMakerUserId(userObj.getUserId());
				leaveSummary.setMakerEnterDatetime(sf.format(date));

				LeaveType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveType",
						leaveSummary, LeaveType.class);

				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Inserted Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showLeaveTypeList";

	}

}
