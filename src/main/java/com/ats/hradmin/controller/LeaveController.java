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
import com.ats.hradmin.leave.model.LeaveHistory;
import com.ats.hradmin.model.AccessRightModule;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveSummary;
import com.ats.hradmin.model.LeaveType;

@Controller
@Scope("session")
public class LeaveController {
	
	LeaveType editLeaveType= new LeaveType();
	
	List<AccessRightModule> moduleList = new ArrayList<>();

	//***************************************LeaveType*********************************************
	
	@RequestMapping(value = "/leaveTypeAdd", method = RequestMethod.GET)
	public ModelAndView empDocAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveTypeAdd");

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
	
	
	@RequestMapping(value = "/submitInsertLeaveType", method = RequestMethod.POST)
	public String submitInsertLeaveType(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			
			String compName = request.getParameter("1");
			String leaveTypeTitle = request.getParameter("leaveTypeTitle");
			String leaveShortTypeTitle = request.getParameter("leaveShortTypeTitle");
			int WprkingHrs =Integer.parseInt( request.getParameter("leaveWorlHrs"));
			int summId =Integer.parseInt( request.getParameter("summId"));
			String leaveColor = request.getParameter("leaveColor");
			String remark=null;

			System.out.println("color    "+leaveColor);
			int isStructured = Integer.parseInt( request.getParameter("isStructured"));
			try {
			 remark =  request.getParameter("remark");
			}
			catch (Exception e) {
				 remark =  "NA";
			}

			Boolean ret = false;
			
			if (FormValidation.Validaton(leaveTypeTitle, "") == true) {

				ret = true;
				System.out.println("locShortName" + ret);
			}
			if (FormValidation.Validaton(leaveShortTypeTitle, "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("leaveWorlHrs"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			if (FormValidation.Validaton(request.getParameter("leaveColor"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			
			if(ret == false)
			{
				
			LeaveType leaveSummary = new LeaveType();

			leaveSummary.setCompanyId(1);
			leaveSummary.setIsStructured(isStructured);
			leaveSummary.setLvColor(leaveColor);
			leaveSummary.setLvTitle(leaveTypeTitle);
			leaveSummary.setLvTitleShort(leaveShortTypeTitle);
			leaveSummary.setLvWorkingHrs(WprkingHrs);
			leaveSummary.setLvSumupId(summId);
			leaveSummary.setLvRmarks(remark);
			leaveSummary.setExInt1(1);
			leaveSummary.setExInt2(1);
			leaveSummary.setExInt3(1);
			leaveSummary.setExVar1("NA");
			leaveSummary.setExVar2("NA");
			leaveSummary.setExVar3("NA");
			leaveSummary.setIsActive(1);
			leaveSummary.setDelStatus(1);
			leaveSummary.setMakerUserId(1);
			leaveSummary.setMakerEnterDatetime(sf.format(date));

			

			LeaveType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveType", leaveSummary,
					LeaveType.class);
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showLeaveTypeList";
	

}
	
	@RequestMapping(value = "/showLeaveTypeList", method = RequestMethod.GET)
	public ModelAndView showLeaveSummaryList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveTypeList");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", 1);
			LeaveType[] leaveSummary = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveTypeList", map,
					LeaveType[].class);

			List<LeaveType> leaveSummarylist = new ArrayList<LeaveType>(Arrays.asList(leaveSummary));
			
			
			for (int i = 0; i < leaveSummarylist.size(); i++) {

				leaveSummarylist.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(leaveSummarylist.get(i).getLvTypeId())));
			}

			model.addObject("lvTypeList", leaveSummarylist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "/editLeaveType", method = RequestMethod.GET)
	public ModelAndView editLeaveType(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/editLeaveType");

		try {
			String base64encodedString = request.getParameter("typeId");
			String lvTypeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("lvTypeId", lvTypeId);
			editLeaveType = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveTypeById", map,
					LeaveType.class);
			model.addObject("editCompany", editLeaveType);
			
			
			map = new LinkedMultiValueMap<>();
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

	
	@RequestMapping(value = "/deleteLeaveType", method = RequestMethod.GET)
	public String deleteLeaveType(HttpServletRequest request, HttpServletResponse response) {

		try {

			String base64encodedString = request.getParameter("typeId");
			String typeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("lvTypeId", typeId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteLeaveType", map, Info.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showLeaveTypeList";
	}

	
	@RequestMapping(value = "/submitEditLeaveType", method = RequestMethod.POST)
	public String submitEditLeaveType(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			
			String compName = request.getParameter("1");
			String leaveTypeTitle = request.getParameter("leaveTypeTitle");
			String leaveShortTypeTitle = request.getParameter("leaveShortTypeTitle");
			int WprkingHrs =Integer.parseInt( request.getParameter("leaveWorlHrs"));
			int summId =Integer.parseInt( request.getParameter("summId"));
			String leaveColor = request.getParameter("leaveColor");
			String remark=null;
			System.out.println("color    "+leaveColor);
			int isStructured = Integer.parseInt( request.getParameter("isStructured"));
			try {
				 remark =  request.getParameter("remark");
				}
				catch (Exception e) {
					 remark =  "NA";
				}

		
			Boolean ret = false;
			
			if (FormValidation.Validaton(leaveTypeTitle, "") == true) {

				ret = true;
				System.out.println("locShortName" + ret);
			}
			if (FormValidation.Validaton(leaveShortTypeTitle, "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("leaveWorlHrs"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			if (FormValidation.Validaton(request.getParameter("leaveColor"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			
			if(ret == false)
			{
				
		
			editLeaveType.setCompanyId(1);
			editLeaveType.setIsStructured(isStructured);
			editLeaveType.setLvColor(leaveColor);
			editLeaveType.setLvTitle(leaveTypeTitle);
			editLeaveType.setLvTitleShort(leaveShortTypeTitle);
			editLeaveType.setLvWorkingHrs(WprkingHrs);
			editLeaveType.setLvSumupId(summId);
			editLeaveType.setLvRmarks(remark);
			editLeaveType.setMakerUserId(1);
			editLeaveType.setMakerEnterDatetime(sf.format(date));

			LeaveType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveType", editLeaveType,
					LeaveType.class);
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showLeaveTypeList";
	

}
	
	//******************************Apply for leave***********************************************

	@RequestMapping(value = "/showApplyForLeave", method = RequestMethod.GET)
	public ModelAndView showEmpList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/appplyForLeave");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			map.add("locIdList", 1);
			
			GetEmployeeInfo[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoList", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeDepartmentlist = new ArrayList<GetEmployeeInfo>(
					Arrays.asList(employeeDepartment));

			for (int i = 0; i < employeeDepartmentlist.size(); i++) {
						//System.out.println("employeeDepartmentlist.get(i).getEmpId()"+employeeDepartmentlist.get(i).getEmpId());
				employeeDepartmentlist.get(i).setExVar1(
						FormValidation.Encrypt(String.valueOf(employeeDepartmentlist.get(i).getEmpId())));
			}

			model.addObject("empList", employeeDepartmentlist);
			System.err.println("emp list is  "+employeeDepartment.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "/leaveApply", method = RequestMethod.GET)
	public ModelAndView showApplyLeave(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveApplication");

		try {
			String base64encodedString = request.getParameter("empId");
			String empId = FormValidation.DecodeKey(base64encodedString);

		
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);
			
			EmployeeInfo  editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpInfoById", map,
					EmployeeInfo.class);
			model.addObject("editEmp", editEmp);
			
			LeaveType[] leaveArray = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getLeaveTypeListIsStructure", LeaveType[].class);

			List<LeaveType> leaveTypeList = new ArrayList<>(Arrays.asList(leaveArray));

			model.addObject("leaveTypeList", leaveTypeList);

			
		 map = new LinkedMultiValueMap<>();
			map.add("empId",empId);
			
			LeaveHistory[] leaveHistory = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getLeaveHistoryList", map, LeaveHistory[].class);

			List<LeaveHistory> leaveHistoryList = new ArrayList<LeaveHistory>(
					Arrays.asList(leaveHistory));
			model.addObject("leaveHistoryList", leaveHistoryList);
			System.err.println("emp leaveHistoryList is  "+leaveHistoryList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	
}