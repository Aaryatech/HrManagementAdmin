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

import com.ats.hradmin.claim.ClaimDetail;
import com.ats.hradmin.common.AcessController;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.GetAuthorityIds;
import com.ats.hradmin.leave.model.GetHoliday;
import com.ats.hradmin.leave.model.GetLeaveStatus;
import com.ats.hradmin.leave.model.Holiday;
import com.ats.hradmin.leave.model.LeaveDetail;
import com.ats.hradmin.leave.model.LeaveHistory;
import com.ats.hradmin.model.AccessRightModule;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.HolidayAndWeeklyOff;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveApply;
import com.ats.hradmin.model.LeaveCount;
import com.ats.hradmin.model.LeaveSummary;
import com.ats.hradmin.model.LeaveTrail;
import com.ats.hradmin.model.LeaveType;
import com.ats.hradmin.model.LoginResponse;
import com.ats.hradmin.model.WeeklyOff;

@Controller
@Scope("session")
public class LeaveController {

	LeaveType editLeaveType = new LeaveType();

	List<AccessRightModule> moduleList = new ArrayList<>();
	

	@RequestMapping(value = "/checkUniqueLeave", method = RequestMethod.GET)
	public @ResponseBody Info checkUniqueLeave(HttpServletRequest request, HttpServletResponse response) {

		Info info = new Info();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("inputValue");
			int valueType = Integer.parseInt(request.getParameter("valueType"));
 			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			

			map.add("inputValue", inputValue);
			map.add("valueType", valueType);
 			map.add("isEditCall", isEdit);
 
			info = Constants.getRestTemplate().postForObject(Constants.url + "checkUniqueLeave", map, Info.class);
			System.err.println("Info Response  " + info.toString());

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}


	// ***************************************LeaveType*********************************************

	@RequestMapping(value = "/leaveTypeAdd", method = RequestMethod.GET)
	public ModelAndView empDocAdd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
		try {
			
			List<AccessRightModule> newModuleList = (List<AccessRightModule>) session.getAttribute("moduleJsonList");
			Info view = AcessController.checkAccess("leaveTypeAdd", "showLeaveTypeList",0, 1,0, 0,
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
		
			 model = new ModelAndView("leave/leaveTypeAdd");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", userObj.getCompanyId());

			LeaveSummary[] employeeDoc = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getLeaveSummaryList", map, LeaveSummary[].class);

			List<LeaveSummary> sumList = new ArrayList<LeaveSummary>(Arrays.asList(employeeDoc));
			System.out.println("lv sum list " + sumList);
			model.addObject("sumList", sumList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertLeaveType", method = RequestMethod.POST)
	public String submitInsertLeaveType(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			

			String compName = request.getParameter("1");
			String leaveTypeTitle = request.getParameter("leaveTypeTitle");
			String leaveShortTypeTitle = request.getParameter("leaveShortTypeTitle");
			int WprkingHrs = Integer.parseInt(request.getParameter("leaveWorlHrs"));
			int summId = Integer.parseInt(request.getParameter("summId"));
			String leaveColor = request.getParameter("leaveColor");
			String remark = null;

			System.out.println("color    " + leaveColor);
			int isStructured = Integer.parseInt(request.getParameter("isStructured"));
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
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

			if (ret == false) {

				LeaveType leaveSummary = new LeaveType();

				leaveSummary.setCompanyId(userObj.getCompanyId());
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

	@RequestMapping(value = "/showLeaveTypeList", method = RequestMethod.GET)
	public ModelAndView showLeaveSummaryList(HttpServletRequest request, HttpServletResponse response) {

		
		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			List<AccessRightModule> newModuleList = (List<AccessRightModule>) session.getAttribute("moduleJsonList");
			Info view = AcessController.checkAccess("showLeaveTypeList", "showLeaveTypeList", 1, 0, 0, 0,
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

			 model = new ModelAndView("leave/leaveTypeList");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			LeaveType[] leaveSummary = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveTypeListIsStructure",
					map, LeaveType[].class);

			List<LeaveType> leaveSummarylist = new ArrayList<LeaveType>(Arrays.asList(leaveSummary));

			for (int i = 0; i < leaveSummarylist.size(); i++) {

				leaveSummarylist.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(leaveSummarylist.get(i).getLvTypeId())));
			}

			model.addObject("lvTypeList", leaveSummarylist);
			Info add = AcessController.checkAccess("showLeaveTypeList", "showLeaveTypeList", 0, 1, 0,
					0, newModuleList);
			Info edit = AcessController.checkAccess("showLeaveTypeList", "showLeaveTypeList", 0, 0, 1,
					0, newModuleList);
			Info delete = AcessController.checkAccess("showLeaveTypeList", "showLeaveTypeList", 0, 0, 0,
					1, newModuleList);

			if (add.isError() == false) {
				System.out.println(" add   Accessable ");
				model.addObject("addAccess", 0);

			}
			if (edit.isError() == false) {
				System.out.println(" edit   Accessable ");
				model.addObject("editAccess", 0);
			}
			if (delete.isError() == false) {
				System.out.println(" delete   Accessable ");
				model.addObject("deleteAccess", 0);

			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/editLeaveType", method = RequestMethod.GET)
	public ModelAndView editLeaveType(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

	
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
try {
			
			List<AccessRightModule> newModuleList = (List<AccessRightModule>) session.getAttribute("moduleJsonList");
			Info view = AcessController.checkAccess("editLeaveType", "showLeaveTypeList",0, 0,1,0,
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				  model = new ModelAndView("leave/editLeaveType");
			String base64encodedString = request.getParameter("typeId");
			String lvTypeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("lvTypeId", lvTypeId);
			editLeaveType = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveTypeById", map,
					LeaveType.class);
			model.addObject("editCompany", editLeaveType);

			map = new LinkedMultiValueMap<>();
			map.add("compId", userObj.getCompanyId());

			LeaveSummary[] employeeDoc = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getLeaveSummaryList", map, LeaveSummary[].class);

			List<LeaveSummary> sumList = new ArrayList<LeaveSummary>(Arrays.asList(employeeDoc));
			System.out.println("lv sum list " + sumList);
			model.addObject("sumList", sumList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/deleteLeaveType", method = RequestMethod.GET)
	public String deleteLeaveType(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		List<AccessRightModule> newModuleList = (List<AccessRightModule>) session.getAttribute("moduleJsonList");
		String a = null;
		Info view = AcessController.checkAccess("deleteLeaveType", "showLeaveTypeList", 0, 0, 0,1,
				newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/showLeaveTypeList";

			}

			else {
				a = "redirect:/accessDenied";

			String base64encodedString = request.getParameter("typeId");
			String typeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("lvTypeId", typeId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteLeaveType", map, Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Record Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	@RequestMapping(value = "/submitEditLeaveType", method = RequestMethod.POST)
	public String submitEditLeaveType(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			String compName = request.getParameter("1");
			String leaveTypeTitle = request.getParameter("leaveTypeTitle");
			String leaveShortTypeTitle = request.getParameter("leaveShortTypeTitle");
			int WprkingHrs = Integer.parseInt(request.getParameter("leaveWorlHrs"));
			int summId = Integer.parseInt(request.getParameter("summId"));
			String leaveColor = request.getParameter("leaveColor");
			String remark = null;
			System.out.println("color    " + leaveColor);
			int isStructured = Integer.parseInt(request.getParameter("isStructured"));
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
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

			if (ret == false) {

				editLeaveType.setCompanyId(userObj.getCompanyId());
				editLeaveType.setIsStructured(isStructured);
				editLeaveType.setLvColor(leaveColor);
				editLeaveType.setLvTitle(leaveTypeTitle);
				editLeaveType.setLvTitleShort(leaveShortTypeTitle);
				editLeaveType.setLvWorkingHrs(WprkingHrs);
				editLeaveType.setLvSumupId(summId);
				editLeaveType.setLvRmarks(remark);
				editLeaveType.setMakerUserId(userObj.getUserId());
				editLeaveType.setMakerEnterDatetime(sf.format(date));

				LeaveType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveType",
						editLeaveType, LeaveType.class);

				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Updated Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Update Record");
				}
			} else {
				session.setAttribute("errorMsg", "Failed to Update Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showLeaveTypeList";

	}

	// ******************************Apply for leave***********************************************

	 
	@RequestMapping(value = "/showApplyForLeave", method = RequestMethod.GET)
	public ModelAndView showEmpList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/appplyForLeave");

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map.add("companyId", userObj.getCompanyId());
			map.add("locIdList", userObj.getLocationIds());
			map.add("empId", userObj.getEmpId());

			GetEmployeeInfo[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoAuthWise", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeDepartmentlist = new ArrayList<GetEmployeeInfo>(
					Arrays.asList(employeeDepartment));

			int flag = 1;

			map = new LinkedMultiValueMap<>();
			map.add("empId", userObj.getEmpId());

			GetEmployeeInfo editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/GetEmployeeInfo", map,
					GetEmployeeInfo.class);
			model.addObject("editEmp", editEmp);
			for (int i = 0; i < employeeDepartmentlist.size(); i++) {
				if (employeeDepartmentlist.get(i).getEmpId() == userObj.getEmpId()) {
					flag = 0;
					System.err.println(" matched");
					break;
				}

			}
			if (flag == 1) {
				System.err.println("not matched");
				GetEmployeeInfo temp = new GetEmployeeInfo();
				temp.setCompanyId(editEmp.getCompanyId());
				temp.setCompanyName(editEmp.getCompanyName());
				temp.setEmpCategory(editEmp.getEmpCategory());
				temp.setEmpCatId(editEmp.getEmpCatId());
				temp.setEmpCode(editEmp.getEmpCode());
				temp.setEmpDept(editEmp.getEmpDept());
				temp.setEmpDeptId(editEmp.getEmpDeptId());
				temp.setEmpEmail(editEmp.getEmpEmail());
				temp.setEmpFname(editEmp.getEmpFname());
				temp.setEmpId(editEmp.getEmpId());
				temp.setEmpMname(editEmp.getEmpMname());
				temp.setEmpMobile1(editEmp.getEmpMobile1());
				temp.setEmpPrevExpYrs(editEmp.getEmpPrevExpYrs());
				temp.setEmpRatePerhr(editEmp.getEmpRatePerhr());
				temp.setEmpSname(editEmp.getEmpSname());
				temp.setEmpType(editEmp.getEmpType());
				temp.setEmpTypeId(editEmp.getEmpTypeId());
				employeeDepartmentlist.add(temp);
			}

			for (int i = 0; i < employeeDepartmentlist.size(); i++) {
				// System.out.println("employeeDepartmentlist.get(i).getEmpId()"+employeeDepartmentlist.get(i).getEmpId());
				employeeDepartmentlist.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(employeeDepartmentlist.get(i).getEmpId())));
			}

			model.addObject("empList", employeeDepartmentlist);
			System.err.println("emp list is  " + employeeDepartment.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	List<LeaveHistory> leaveHistoryList = new ArrayList<LeaveHistory>();
	
	@RequestMapping(value = "/leaveApply", method = RequestMethod.GET)
	public ModelAndView showApplyLeave(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveApplication");
		HttpSession session = request.getSession();
		Date date = new Date();
		try {
			String base64encodedString = request.getParameter("empId");
			String empId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);

			EmployeeInfo editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpInfoById", map,
					EmployeeInfo.class);
			model.addObject("editEmp", editEmp);

			/*
			 * LeaveType[] leaveArray = Constants.getRestTemplate()
			 * .getForObject(Constants.url + "/getLeaveTypeListIsStructure",
			 * LeaveType[].class);
			 * 
			 * List<LeaveType> leaveTypeList = new ArrayList<>(Arrays.asList(leaveArray));
			 * 
			 * model.addObject("leaveTypeList", leaveTypeList);
			 */
			model.addObject("empId", empId);

			map = new LinkedMultiValueMap<>();
			map.add("empId", empId);
			map.add("currYrId", session.getAttribute("currYearId"));

			LeaveHistory[] leaveHistory = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getLeaveHistoryList", map, LeaveHistory[].class);

			 leaveHistoryList = new ArrayList<LeaveHistory>(Arrays.asList(leaveHistory));
			model.addObject("leaveHistoryList", leaveHistoryList);
			System.err.println("emp leaveHistoryList is  " + leaveHistoryList.toString());

			model.addObject("lvsId", leaveHistoryList.get(0).getLvsId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/getHolidayAndWeeklyOffList", method = RequestMethod.GET)
	public @ResponseBody HolidayAndWeeklyOff getHolidayAndWeeklyOffList(HttpServletRequest request,
			HttpServletResponse response) {

		HolidayAndWeeklyOff HolidayAndWeeklyOff = new HolidayAndWeeklyOff();

		try {
			String empId = request.getParameter("empId");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			System.out.println(map);
			Holiday[] holi = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getHolidayByEmpIdAndFromDateTodate", map, Holiday[].class);
			List<Holiday> li = new ArrayList<>(Arrays.asList(holi));

			WeeklyOff[] wee = Constants.getRestTemplate().postForObject(Constants.url + "/getWeeklyOffListByEmpId", map,
					WeeklyOff[].class);
			List<WeeklyOff> list = new ArrayList<>(Arrays.asList(wee));
			HolidayAndWeeklyOff.setHolidayList(li);
			HolidayAndWeeklyOff.setWeeklyList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return HolidayAndWeeklyOff;
	}
	
	@RequestMapping(value = "/calholidayWebservice", method = RequestMethod.GET)
	public @ResponseBody LeaveCount calholidayWebservice(HttpServletRequest request,
			HttpServletResponse response) {

		LeaveCount leaveCount = new LeaveCount();

		try {
			String empId = request.getParameter("empId");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("toDate", DateConvertor.convertToYMD(toDate));
			System.out.println(map);
			leaveCount  = Constants.getRestTemplate()
					.postForObject(Constants.url + "/calculateHolidayBetweenDate", map, LeaveCount.class); 
 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return leaveCount;
	}

	@RequestMapping(value = "/insertLeave", method = RequestMethod.POST)
	public String insertLeave(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			// String compName = request.getParameter("1");
			String leaveDateRange = request.getParameter("leaveDateRange");
			String dayTypeName = request.getParameter("dayTypeName");
			float noOfDays = Float.parseFloat(request.getParameter("noOfDays"));
			int leaveTypeId = Integer.parseInt(request.getParameter("leaveTypeId"));

			System.out.println("leaveTypeId" + leaveTypeId);
			int noOfDaysExclude = Integer.parseInt(request.getParameter("noOfDaysExclude"));
			int empId = Integer.parseInt(request.getParameter("empId"));

			// get Authority ids

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);

			GetAuthorityIds editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getAuthIdByEmpId",
					map, GetAuthorityIds.class);
			int stat = 0;
			if (editEmp.getFinAuthEmpId() == userObj.getEmpId()) {
				stat = 3;
			} else if (editEmp.getIniAuthEmpId() == userObj.getEmpId()) {
				stat = 2;
			} else {
				stat = 1;
			}
			System.out.println("stat is " + stat);
			String remark = null;

			String[] arrOfStr = leaveDateRange.split("to", 2);

			System.out.println("dayType" + dayTypeName);

			try {
				remark = request.getParameter("leaveRemark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(leaveDateRange, "") == true) {

				ret = true;
				System.out.println("leaveDateRange" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("noOfDays"), "") == true) {

				ret = true;
				System.out.println("noOfDays" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("noOfDaysExclude"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("leaveTypeId"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (ret == false) {

				LeaveApply leaveSummary = new LeaveApply();

				leaveSummary.setCalYrId((int) session.getAttribute("currYearId"));
				leaveSummary.setEmpId(empId);
				leaveSummary.setFinalStatus(1);
				leaveSummary.setLeaveNumDays(noOfDays);
				leaveSummary.setCirculatedTo("1");
				leaveSummary.setLeaveDuration(dayTypeName);
				leaveSummary.setLeaveEmpReason(remark);
				leaveSummary.setLvTypeId(leaveTypeId);
				leaveSummary.setLeaveFromdt(DateConvertor.convertToYMD(arrOfStr[0].toString().trim()));
				leaveSummary.setLeaveTodt(DateConvertor.convertToYMD(arrOfStr[1].toString().trim()));

				leaveSummary.setExInt1(stat);
				leaveSummary.setExInt2(1);
				leaveSummary.setExInt3(1);
				leaveSummary.setExVar1("NA");
				leaveSummary.setExVar2("NA");
				leaveSummary.setExVar3("NA");
				leaveSummary.setIsActive(1);
				leaveSummary.setDelStatus(1);
				leaveSummary.setMakerUserId(userObj.getUserId());
				leaveSummary.setMakerEnterDatetime(sf.format(date));

				LeaveApply res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveApply",
						leaveSummary, LeaveApply.class);

				if (res != null) {
					LeaveTrail lt = new LeaveTrail();

					lt.setEmpRemarks(remark);
					System.err.println("res.getLeaveId()" + res.getLeaveId());
					lt.setLeaveId(res.getLeaveId());

					lt.setLeaveStatus(stat);
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
						map.add("leaveId", res.getLeaveId());
						map.add("trailId", res1.getTrailPkey());
						Info info = Constants.getRestTemplate().postForObject(Constants.url + "/updateTrailId", map,
								Info.class);
						if (info.isError() == false) {
							session.setAttribute("successMsg", "Record Inserted Successfully");
						} else {
							session.setAttribute("errorMsg", "Failed to Insert Record");
						}

					} 
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showApplyForLeave";

	}

	@RequestMapping(value = "/chkNumber", method = RequestMethod.GET)
	public @ResponseBody String chkNumber(HttpServletRequest request, HttpServletResponse response) {
		//LeaveHistory editEmp = new LeaveHistory();
		
		String balance = new String();
		
		try {
			//System.out.println("In ChkNumber");

			int leaveTypeId = Integer.parseInt(request.getParameter("inputValue"));
			//int lvsId = Integer.parseInt(request.getParameter("lvsId"));

			System.out.println("In ChkNumber" + leaveTypeId);

			//System.out.println("In ChkNumber" + lvsId);

			/*MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("leaveTypeId", leaveTypeId);
			map.add("lvsId", lvsId);

			editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveHistoryByLeaveTypeId", map,
					LeaveHistory.class);*/

			for(int i = 0; i<leaveHistoryList.size() ; i++) {
				if(leaveTypeId==leaveHistoryList.get(i).getLvTypeId()) {
					balance=String.valueOf(leaveHistoryList.get(i).getBalLeave()+leaveHistoryList.get(i).getLvsAllotedLeaves()-leaveHistoryList.get(i).getSactionLeave()-leaveHistoryList.get(i).getAplliedLeaeve());
				}
			}
				
				
			//System.err.println("data is" + editEmp.toString());
		} catch (Exception e) {
			e.printStackTrace();
			balance="0";
		}

		return balance;
	}

	
	

	
	
}