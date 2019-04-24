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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.common.VpsImageUpload;
import com.ats.hradmin.leave.model.CalenderYear;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.EmployeDoc;
import com.ats.hradmin.model.EmployeeDepartment;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveSummary;
import com.ats.hradmin.model.LeaveType;
import com.ats.hradmin.model.LoginResponse;

 
@Controller
@Scope("session")
public class MasterEmpController {
	LeaveSummary editSummary= new LeaveSummary();
	
	@RequestMapping(value = "/empDocAdd", method = RequestMethod.GET)
	public ModelAndView empDocAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empDocAdd");

		try {
			EmployeDoc[] employeeDoc = Constants.getRestTemplate().getForObject(Constants.url +
			 "/getEmpDocList", EmployeDoc[].class);
			
			 List<EmployeDoc> employeeList = new ArrayList<EmployeDoc>(Arrays.asList(employeeDoc));
			 System.out.println(employeeList); 
			 model.addObject("employeeList",employeeList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/leaveSummaryAdd", method = RequestMethod.GET)
	public ModelAndView leaveSummaryAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveSummeryAdd");

		try {
			/*
			 * EmployeDoc[] employeeDoc =
			 * Constants.getRestTemplate().getForObject(Constants.url + "/getEmpDocList",
			 * EmployeDoc[].class);
			 * 
			 * List<EmployeDoc> employeeList = new
			 * ArrayList<EmployeDoc>(Arrays.asList(employeeDoc));
			 * System.out.println(employeeList);
			 * model.addObject("employeeList",employeeList);
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/submitInsertLeaveSummary", method = RequestMethod.POST)
	public String submitInsertLeaveSummary(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			
			String compName = request.getParameter("1");
			String leaveSumName = request.getParameter("leaveSumName");
			String leaveSumShortName = request.getParameter("leaveSumShortName");

			Boolean ret = false;
			
			/*
			 * if (FormValidation.Validaton(compName, "") == true) {
			 * 
			 * ret = true; System.out.println("locName" + ret); }
			 */
			if (FormValidation.Validaton(leaveSumName, "") == true) {

				ret = true;
				System.out.println("locShortName" + ret);
			}
			if (FormValidation.Validaton(leaveSumShortName, "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if(ret == false)
			{
				
			LeaveSummary leaveSummary = new LeaveSummary();

			leaveSummary.setCompanyId(1);
			leaveSummary.setLvSumupTitle(leaveSumName);
			leaveSummary.setLvSumupTitleShort(leaveSumShortName);
			leaveSummary.setIsActive(1);
			leaveSummary.setDelStatus(1);
			leaveSummary.setMakerUserId(1);
			leaveSummary.setMakerEnterDatetime(sf.format(date));

			

				LeaveSummary res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveSummary", leaveSummary,
						LeaveSummary.class);
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showCompanyList";
	}
	@RequestMapping(value = "/showLeaveSummaryList", method = RequestMethod.GET)
	public ModelAndView showLeaveSummaryList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leaveSummaryList");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", 1);
			LeaveSummary[] leaveSummary = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveSummaryList", map,
					LeaveSummary[].class);

			List<LeaveSummary> leaveSummarylist = new ArrayList<LeaveSummary>(Arrays.asList(leaveSummary));
			
			model.addObject("lvsummaryList", leaveSummarylist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/editLeaveSummary", method = RequestMethod.GET)
	public ModelAndView editLeaveSummary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/edit_Leave_summary");

		try {
			String base64encodedString = request.getParameter("typeId");
			String lvTypeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("lvTypeId", lvTypeId);
			editSummary = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveSummaryById", map,
					LeaveSummary.class);
			model.addObject("editSummary", editSummary);
			
			
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

	
	@RequestMapping(value = "/deleteLeaveSummary", method = RequestMethod.GET)
	public String deleteLeaveSummary(HttpServletRequest request, HttpServletResponse response) {

		try {

			String base64encodedString = request.getParameter("lvSumupId");
			String lvSumupId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("lvSumupId", lvSumupId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteLeaveSummary", map, Info.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showLeaveSummaryList";
	}
	
	@RequestMapping(value = "/empInfoHistory", method = RequestMethod.GET)
	public ModelAndView empInfoHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empHistory");
		
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("locationId", userObj.getLocationIds());
			CalenderYear[] calenderYear = Constants.getRestTemplate().getForObject(Constants.url + "/getCalculateYearList",
					CalenderYear[].class);
			  List<CalenderYear> calYearList = new ArrayList<CalenderYear>(Arrays.asList(calenderYear));

			  
			  EmployeeInfo[] employeeInfo = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpInfoByLocId",map,
					  EmployeeInfo[].class);
			  
			  List<EmployeeInfo> employeeInfoList = new ArrayList<EmployeeInfo>(Arrays.asList(employeeInfo));
			  System.out.println(employeeInfoList);
			  model.addObject("calYearList",calYearList);
			  model.addObject("employeeInfoList",employeeInfoList);
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/empInfoHistoryList", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeInfo> empInfoHistoryList(HttpServletRequest request, HttpServletResponse response) {

		  List<EmployeeInfo> employeeInfoList=new ArrayList<EmployeeInfo>();
		try {
		
			int empId=Integer.parseInt(request.getParameter("empId"));
			int calYrId=Integer.parseInt(request.getParameter("calYrId"));
			
			  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("empId",empId);
			  map.add("calYrId",calYrId);
			  
			  EmployeeInfo[] employeeInfo = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpInfoByLocIdAndEmp",map,
					  EmployeeInfo[].class);
			  
			  employeeInfoList = new ArrayList<EmployeeInfo>(Arrays.asList(employeeInfo));
			  System.out.println("List : "+employeeInfoList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeInfoList;
	}
	
	@RequestMapping(value = "/empDetailHistory", method = RequestMethod.GET)
	public ModelAndView empDetailHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empDetailHistory");

		try {
			/*
			 * EmployeDoc[] employeeDoc =
			 * Constants.getRestTemplate().getForObject(Constants.url + "/getEmpDocList",
			 * EmployeDoc[].class);
			 * 
			 * List<EmployeDoc> employeeList = new
			 * ArrayList<EmployeDoc>(Arrays.asList(employeeDoc));
			 * System.out.println(employeeList);
			 * model.addObject("employeeList",employeeList);
			 */
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
