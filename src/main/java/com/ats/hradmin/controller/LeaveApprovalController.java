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
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.GetLeaveApplyAuthwise;
import com.ats.hradmin.leave.model.LeaveDetail;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveTrail;
import com.ats.hradmin.model.LoginResponse;

@Controller
@Scope("session")
public class LeaveApprovalController {
	
	
	@RequestMapping(value = "/showLeaveApprovalByAuthority", method = RequestMethod.GET)
	public ModelAndView showLeaveApprovalByInitialAuthority(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveApproveByInitial");

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId",userObj.getEmpId());
			map.add("statusList", 1);
			map.add("authTypeId", 1);//1-initial,2-final
			map.add("currYrId",session.getAttribute("currYearId"));
			
			
			GetLeaveApplyAuthwise[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url +
					 "/getLeaveApplyListForAuth",map, GetLeaveApplyAuthwise[].class);
					
			 List<GetLeaveApplyAuthwise> leaveList = new ArrayList<GetLeaveApplyAuthwise>(Arrays.asList(employeeDoc));
			 System.out.println("lv leaveList list "+leaveList); 
			
			 

				for (int i = 0; i < leaveList.size(); i++) {

					leaveList.get(i).setCirculatedTo(FormValidation.Encrypt(String.valueOf(leaveList.get(i).getLeaveId())));
					leaveList.get(i).setLeaveTypeName(FormValidation.Encrypt(String.valueOf(leaveList.get(i).getEmpId())));

				}
				 model.addObject("leaveListForApproval",leaveList);
			 
	//for final		 
		 map = new LinkedMultiValueMap<>();
			map.add("empId",userObj.getEmpId());
			map.add("statusList","1,2");
			map.add("authTypeId", 2);
			map.add("currYrId",session.getAttribute("currYearId"));
			GetLeaveApplyAuthwise[] employeeDoc1 = Constants.getRestTemplate().postForObject(Constants.url +
						 "/getLeaveApplyListForAuth",map, GetLeaveApplyAuthwise[].class);
						
			List<GetLeaveApplyAuthwise> leaveList1 = new ArrayList<GetLeaveApplyAuthwise>(Arrays.asList(employeeDoc1));
			//System.out.println("lv leaveList list1 "+leaveList1.toString()); 
			for (int i = 0; i < leaveList1.size(); i++) {

				leaveList1.get(i).setCirculatedTo(FormValidation.Encrypt(String.valueOf(leaveList1.get(i).getLeaveId())));
				leaveList1.get(i).setLeaveTypeName(FormValidation.Encrypt(String.valueOf(leaveList1.get(i).getEmpId())));

			}
			int flag=1;
			for (int i = 0; i < leaveList1.size(); i++) {
				if(leaveList1.get(i).getEmpId()==userObj.getEmpId()) {
					flag=0;
					System.err.println(" matched");
					break;
				}
				
			}
			if(flag==1) {
				System.err.println(" not matched");
			 map = new LinkedMultiValueMap<>();
				map.add("empId",userObj.getEmpId());
				map.add("statusList","1,2");
				
				map.add("currYrId",session.getAttribute("currYearId"));
				LeaveDetail  editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getEmployeeLeaveByEmpId", map,
						LeaveDetail.class);
				System.out.println("edit emp::::"+editEmp.toString());
			
				if(editEmp!=null) {
					
					
					GetLeaveApplyAuthwise temp=new GetLeaveApplyAuthwise();
					
					temp.setCalYrId(editEmp.getCalYrId());
					temp.setEmpCode(editEmp.getEmpCode());
					temp.setEmpFname(editEmp.getEmpFname());
					temp.setEmpMname(editEmp.getEmpMname());
					temp.setEmpSname(editEmp.getEmpSname());
					temp.setLeaveDuration(editEmp.getLeaveDuration());
					temp.setLeaveFromdt(editEmp.getLeaveFromdt());
					temp.setLeaveTodt(editEmp.getLeaveTodt());
					temp.setLeaveTitle(editEmp.getLvTitle());
					temp.setLeaveNumDays(editEmp.getLeaveNumDays());
					leaveList1.add(temp);
					
				}
			
			}
			model.addObject("leaveListForApproval1",leaveList1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	
	@RequestMapping(value = "/approveLeaveByInitialAuth", method = RequestMethod.GET)
	public String insertLeave(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			
			int  empId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("empId")));
			int leaveId=Integer.parseInt(FormValidation.DecodeKey(request.getParameter("leaveId")));
			String stat=request.getParameter("stat");
			
	       System.err.println("link data :::"+empId+leaveId+stat);
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("leaveId", leaveId);
			map.add("status",stat);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/updateLeaveStatus", map, Info.class);

			if(info.isError()==false) {
				LeaveTrail lt = new LeaveTrail();
				
				
				lt.setEmpRemarks("null");
			
				lt.setLeaveId(leaveId);
				
				lt.setLeaveStatus(Integer.parseInt(stat));
				lt.setEmpId(empId);
				lt.setExInt1(1);
				lt.setExInt2(1);
				lt.setExInt3(1);
				lt.setExVar1("NA");
				lt.setExVar2("NA");
				lt.setExVar3("NA");
				
				lt.setMakerUserId(1);
				lt.setMakerEnterDatetime(sf.format(date));
				

				LeaveTrail res1 = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveTrail", lt,
						LeaveTrail.class);
				if(res1!=null) {
					
				 map = new LinkedMultiValueMap<>();
				map.add("leaveId",leaveId);
				map.add("trailId", res1.getTrailPkey());
				Info info1 = Constants.getRestTemplate().postForObject(Constants.url + "/updateTrailId", map, Info.class);


				}
			}
			
			 else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showLeaveApprovalByAuthority";
	

}
	
	
	
	
	

}
