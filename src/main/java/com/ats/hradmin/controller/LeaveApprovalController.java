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
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.GetLeaveApplyAuthwise;
import com.ats.hradmin.leave.model.GetLeaveStatus;
import com.ats.hradmin.leave.model.LeaveDetail;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveTrail;
import com.ats.hradmin.model.LoginResponse;

@Controller
@Scope("session")
public class LeaveApprovalController {

	@RequestMapping(value = "/showLeaveApprovalByAuthority", method = RequestMethod.GET)
	public ModelAndView showLeaveApprovalByInitialAuthority(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveApproveByInitial");

		// for pending
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", userObj.getEmpId());
			map.add("currYrId", session.getAttribute("currYearId"));

			GetLeaveApplyAuthwise[] employeeDoc = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getLeaveApplyListForPending", map, GetLeaveApplyAuthwise[].class);

			List<GetLeaveApplyAuthwise> leaveList = new ArrayList<GetLeaveApplyAuthwise>(Arrays.asList(employeeDoc));

			for (int i = 0; i < leaveList.size(); i++) {

				leaveList.get(i).setCirculatedTo(FormValidation.Encrypt(String.valueOf(leaveList.get(i).getLeaveId())));
				leaveList.get(i).setLeaveTypeName(FormValidation.Encrypt(String.valueOf(leaveList.get(i).getEmpId())));
				leaveList.get(i).setLeaveFromdt(DateConvertor.convertToDMY(leaveList.get(i).getLeaveFromdt()));
				leaveList.get(i).setLeaveTodt(DateConvertor.convertToDMY(leaveList.get(i).getLeaveTodt()));
			}
			model.addObject("leaveListForApproval", leaveList);
			model.addObject("list1Count", leaveList.size());

			// for Info

			model.addObject("empIdOrig", userObj.getEmpId());

			map = new LinkedMultiValueMap<>();
			map.add("empId", userObj.getEmpId());

			map.add("currYrId", session.getAttribute("currYearId"));
			GetLeaveApplyAuthwise[] employeeDoc1 = Constants.getRestTemplate().postForObject(
					Constants.url + "/getLeaveApplyListForInformation", map, GetLeaveApplyAuthwise[].class);

			List<GetLeaveApplyAuthwise> leaveList1 = new ArrayList<GetLeaveApplyAuthwise>(Arrays.asList(employeeDoc1));

			for (int i = 0; i < leaveList1.size(); i++) {

				leaveList1.get(i)
						.setCirculatedTo(FormValidation.Encrypt(String.valueOf(leaveList1.get(i).getLeaveId())));
				leaveList1.get(i)
						.setLeaveTypeName(FormValidation.Encrypt(String.valueOf(leaveList1.get(i).getEmpId())));
				leaveList1.get(i).setLeaveFromdt(DateConvertor.convertToDMY(leaveList1.get(i).getLeaveFromdt()));
				leaveList1.get(i).setLeaveTodt(DateConvertor.convertToDMY(leaveList1.get(i).getLeaveTodt()));
			}

			System.out.println("lv leaveList list1 info " + leaveList1.toString());
			System.out.println("lv leaveList list pending " + leaveList.toString());

			model.addObject("list2Count", leaveList1.size());
			model.addObject("leaveListForApproval1", leaveList1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	
	@RequestMapping(value = "/approveLeaveByInitialAuth", method = RequestMethod.GET)
	public ModelAndView approveLeaveByInitialAuth(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("leave/leaveApprovalRemark");
		try {

			
			int empId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("empId")));
			int leaveId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("leaveId")));
			String stat = request.getParameter("stat");
			
			model.addObject("empId",empId);
			model.addObject("leaveId", leaveId);
			model.addObject("stat", stat);
			
			
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("leaveId",leaveId);
			  GetLeaveStatus[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpInfoListByTrailEmpId", map,GetLeaveStatus[].class);
			  
			  List<GetLeaveStatus> employeeList = new
			  ArrayList<GetLeaveStatus>(Arrays.asList(employeeDoc));
		      model.addObject("employeeList",employeeList);
		      
		      
		      MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
		      map1.add("leaveId",leaveId);

			 GetLeaveApplyAuthwise lvEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveApplyDetailsByLeaveId", map1,
					 GetLeaveApplyAuthwise.class);
				model.addObject("lvEmp", lvEmp);
				System.out.println("emp leave details"+lvEmp.toString());
		      
			
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}
	
	
	@RequestMapping(value = "/approveLeaveByInitialAuth1", method = RequestMethod.POST)
	public String approveLeaveByInitialAuth1(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			int empId = Integer.parseInt((request.getParameter("empId")));
			int leaveId = Integer.parseInt((request.getParameter("leaveId")));
			String stat = request.getParameter("stat");
			String remark = null;
			try {
				 remark =  request.getParameter("remark");
				}
				catch (Exception e) {
					 remark =  "NA";
				}
			int stat1=Integer.parseInt(stat);
			
           String msg=null;

             if(stat1==2 ||  stat1==3) {
	            msg="Approved";
                }else if(stat1==8 ||  stat1==9) {
            	 msg="Rejected";
               }else if(stat1==7){
            	   msg="Cancelled";
                   }
			System.err.println("link data :::" + empId + leaveId + stat);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("leaveId", leaveId);
			map.add("status", stat);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/updateLeaveStatus", map,
					Info.class);

			if (info.isError() == false) {
				LeaveTrail lt = new LeaveTrail();

				lt.setEmpRemarks(remark);

				lt.setLeaveId(leaveId);

				lt.setLeaveStatus(Integer.parseInt(stat));
				lt.setEmpId(empId);
				lt.setExInt1(1);
				lt.setExInt2(1);
				lt.setExInt3(1);
				lt.setExVar1("NA");
				lt.setExVar2("NA");
				lt.setExVar3("NA");

				lt.setMakerUserId(userObj.getUserId());
				lt.setMakerEnterDatetime(sf.format(date));

				LeaveTrail res1 = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveTrail", lt,
						LeaveTrail.class);
				if (res1 != null) {

					map = new LinkedMultiValueMap<>();
					map.add("leaveId", leaveId);
					map.add("trailId", res1.getTrailPkey());
					Info info1 = Constants.getRestTemplate().postForObject(Constants.url + "/updateTrailId", map,
							Info.class);
					
					if (info1.isError() == false) {
						session.setAttribute("successMsg", "Record "+msg+" Successfully");
					} else {
						session.setAttribute("errorMsg", "Failed to "+msg+" Record");
					}
					

				}
			}

			else {
				session.setAttribute("errorMsg", "Failed to "+msg+" Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showLeaveApprovalByAuthority";

	}
	
	
	@RequestMapping(value = "/showLeaveHistList", method = RequestMethod.GET)
	public ModelAndView showClaimList(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("leave/empLeaveHistory");
		try {

			  List<LeaveDetail> employeeInfoList=new ArrayList<LeaveDetail>();
			int empId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("empId")));
			System.err.println("emp idis "+empId);
			MultiValueMap<String, Object>  map = new LinkedMultiValueMap<>();
				map.add("empId",empId);
			 
		LeaveDetail[] employeeInfo = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveListByEmp",map,
						  LeaveDetail[].class);
				   
				  employeeInfoList = new ArrayList<LeaveDetail>(Arrays.asList(employeeInfo));
				  for (int i = 0; i < employeeInfoList.size(); i++) {

					  employeeInfoList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getLeaveId())));
					}
				  model.addObject("leaveHistoryList",employeeInfoList);
			
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		  
	}
	
	@RequestMapping(value = "/showLeaveHistDetailList", method = RequestMethod.GET)
	public ModelAndView showLeaveHistDetailList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/empLeaveHistoryDetail");

		try {
		
			String base64encodedString = request.getParameter("leaveId");			
			String leaveId = FormValidation.DecodeKey(base64encodedString);			
			  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("leaveId",leaveId);
			  GetLeaveStatus[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpInfoListByTrailEmpId", map,GetLeaveStatus[].class);
			  
			  List<GetLeaveStatus> employeeList = new
			  ArrayList<GetLeaveStatus>(Arrays.asList(employeeDoc));
		      model.addObject("employeeList",employeeList);
		      
		      MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
		      map1.add("leaveId",leaveId);

			 GetLeaveApplyAuthwise lvEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveApplyDetailsByLeaveId", map1,
					 GetLeaveApplyAuthwise.class);
				model.addObject("lvEmp", lvEmp);
				System.out.println("emp leave details"+lvEmp.toString());
		   

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	

}
