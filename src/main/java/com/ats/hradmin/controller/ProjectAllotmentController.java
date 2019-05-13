package com.ats.hradmin.controller;

import java.text.DateFormat;
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
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.model.EmployeeCategory;
import com.ats.hradmin.model.EmployeeFreeBsyList;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.GetProjectHeader;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.Location;
import com.ats.hradmin.model.LoginResponse;
import com.ats.hradmin.model.ProjectAllotment;
import com.ats.hradmin.model.ProjectHeader;
import com.ats.hradmin.model.project.ProjectType;

@Controller
@Scope("session")
public class ProjectAllotmentController {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String dateTime = dateFormat.format(now);

	@RequestMapping(value = "/projectAllotment", method = RequestMethod.GET)
	public ModelAndView empDocAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("project/projectAllotment");

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", userObj.getCompanyId());
			EmployeeCategory[] employeeCategory = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpCategoryList", map, EmployeeCategory[].class);
			List<EmployeeCategory> employeeCategorylist = new ArrayList<EmployeeCategory>(
					Arrays.asList(employeeCategory));

			map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);

			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			map = new LinkedMultiValueMap<>();
			map.add("projectId", 1);
			GetProjectHeader projectInfo = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getProjectDetailById", map, GetProjectHeader.class);

			model.addObject("projectInfo", projectInfo);
			model.addObject("empCatList", employeeCategorylist);
			model.addObject("locationList", locationList);
			model.addObject("locationIds", userObj.getLocationIds().split(","));

			employeeFreeBsyList = new EmployeeFreeBsyList();
			
			
			freelist = new ArrayList<>();
			ProjectAllotment[] projectAllotment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getAllocatedEmployeeList", map, ProjectAllotment[].class);
			
			bsyList = new ArrayList<>(Arrays.asList(projectAllotment));
			
			model.addObject("bsyList", bsyList);
			employeeFreeBsyList.setBsyList(bsyList);
			employeeFreeBsyList.setFreeList(freelist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	EmployeeFreeBsyList employeeFreeBsyList = new EmployeeFreeBsyList();
	List<ProjectAllotment> bsyList = new ArrayList<>();
	List<EmployeeInfo> freelist = new ArrayList<>();

	String fromDate ;
	String toDate;
	int worktime;
	
	@RequestMapping(value = "/getFreeEmployeeList", method = RequestMethod.GET)
	public @ResponseBody List<EmployeeInfo> getFreeEmployeeList(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			int catId = Integer.parseInt(request.getParameter("catId"));
			worktime = Integer.parseInt(request.getParameter("worktime"));
			String locationId = request.getParameter(("locationId"));
			 fromDate = request.getParameter("fromDate");
			 toDate = request.getParameter("toDate");
			 
			if (!locationId.equals("0")) {
				locationId = locationId.substring(1, locationId.length() - 1);
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("catId", catId);
			map.add("worktime", worktime);
			map.add("companyId", userObj.getCompanyId());
			map.add("fromDate", DateConvertor.convertToYMD(fromDate));
			map.add("locationIds", locationId);
			map.add("toDate", DateConvertor.convertToYMD(toDate));

			System.out.println(map);

			EmployeeInfo[] employeeInfo = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getFullTimeFreeEmpList", map, EmployeeInfo[].class);
			freelist = new ArrayList<EmployeeInfo>(Arrays.asList(employeeInfo));
 
			DateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			 Date fDate = sf.parse(fromDate);
			 Date tDate = sf.parse(toDate);
			
			 /*System.out.println(employeeFreeBsyList.getBsyList());
			 System.out.println(freelist);*/
			 
			for (int i = 0; i < employeeFreeBsyList.getBsyList().size(); i++) {

				for (int j = 0; j < freelist.size(); j++) {

					if (employeeFreeBsyList.getBsyList().get(i).getEmpId() == freelist.get(j).getEmpId()) {

						Date afDate = sf.parse(employeeFreeBsyList.getBsyList().get(i).getPallotFromdt());
						 Date atDate = sf.parse(employeeFreeBsyList.getBsyList().get(i).getPallotTodt());
						  
							 if(((fDate.compareTo(afDate) >= 0 && fDate.compareTo(atDate) <= 0)|| (tDate.compareTo(afDate) >= 0 && tDate.compareTo(atDate) <= 0)
									 || (afDate.compareTo(fDate) >= 0 && afDate.compareTo(tDate) <= 0)|| (atDate.compareTo(fDate) >= 0 && atDate.compareTo(tDate) <= 0)) && 
									 employeeFreeBsyList.getBsyList().get(i).getExInt1()==2) {
								 freelist.remove(j);
									break;
							 }
							 
						  
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return freelist;
	}

	@RequestMapping(value = "/moveEmp", method = RequestMethod.GET)
	public @ResponseBody EmployeeFreeBsyList moveEmp(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			int empId = Integer.parseInt(request.getParameter("empId"));
			int selectWorkType = Integer.parseInt(request.getParameter("selectWorkType"));
			float hours = Float.parseFloat(request.getParameter("hours"));

			/*for (int i = 0; i < freelist.size(); i++) {

				if (freelist.get(i).getEmpId() == empId) {
					//employeeFreeBsyList.getBsyList().add(freelist.get(i));
					ProjectAllotment save = new ProjectAllotment(); 
					save.setEmpId(empId);
					save.setPallotFromdt(fromDate);
					save.setPallotTodt(toDate);
					save.setPallotDailyHrs(9);
					save.setDelStatus(1);
					save.setIsActive(1);
					save.setMakerUserId(userObj.getUserId());
					save.setMakerEnterDatetime(dateTime);
					save.setEmpFname(freelist.get(i).getEmpFname());
					save.setEmpMname(freelist.get(i).getEmpMname());
					save.setEmpSname(freelist.get(i).getEmpSname());
					freelist.remove(i);
					employeeFreeBsyList.getBsyList().add(save);
					break;
				}
			}*/
			 
					//employeeFreeBsyList.getBsyList().add(freelist.get(i));
					ProjectAllotment save = new ProjectAllotment(); 
					save.setEmpId(freelist.get(empId).getEmpId());
					save.setPallotFromdt(fromDate);
					save.setPallotTodt(toDate); 
					if(hours>=9) {
						save.setPallotDailyHrs(9); 
					}else {
						save.setPallotDailyHrs(hours); 
					}
					
					save.setDelStatus(1);
					save.setIsActive(1);
					save.setMakerUserId(userObj.getUserId());
					save.setMakerEnterDatetime(dateTime);
					save.setEmpFname(freelist.get(empId).getEmpFname());
					save.setEmpMname(freelist.get(empId).getEmpMname());
					save.setEmpSname(freelist.get(empId).getEmpSname());
					save.setExInt1(selectWorkType);
					freelist.remove(empId);
					employeeFreeBsyList.getBsyList().add(save);
				 
			

			employeeFreeBsyList.setBsyList(bsyList);
			employeeFreeBsyList.setFreeList(freelist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeFreeBsyList;
	}

	@RequestMapping(value = "/deleteEmp", method = RequestMethod.GET)
	public @ResponseBody EmployeeFreeBsyList deleteEmp(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			int empId = Integer.parseInt(request.getParameter("empId"));

			/*for (int i = 0; i < employeeFreeBsyList.getBsyList().size(); i++) {

				if (employeeFreeBsyList.getBsyList().get(i).getEmpId() == empId) {

					employeeFreeBsyList.getBsyList().remove(i);
					break;
				}
			}*/
			
		 employeeFreeBsyList.getBsyList().remove(empId);
				 
			employeeFreeBsyList.setBsyList(bsyList);
			employeeFreeBsyList.setFreeList(freelist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeFreeBsyList;
	}

	@RequestMapping(value = "/submitProjectAllotment", method = RequestMethod.POST)
	public String submitProjectAllotment(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			String leaveDateRange = request.getParameter("leaveDateRange");
			int fullHalfwork = Integer.parseInt(request.getParameter("fullHalfwork"));
			int projectId = Integer.parseInt(request.getParameter("projectId"));

			Boolean ret = false;

			String[] dates = leaveDateRange.split(" to ");

			if (ret == false) {

				if (employeeFreeBsyList.getBsyList().size() > 0) {

					List<ProjectAllotment> list = new ArrayList<>();

					for (int i = 0; i < employeeFreeBsyList.getBsyList().size(); i++) {

						employeeFreeBsyList.getBsyList().get(i).setProjectId(projectId);
						//employeeFreeBsyList.getBsyList().get(i).setExInt1(fullHalfwork);
						employeeFreeBsyList.getBsyList().get(i).setPallotFromdt(DateConvertor.convertToYMD(employeeFreeBsyList.getBsyList().get(i).getPallotFromdt()));
						employeeFreeBsyList.getBsyList().get(i).setPallotTodt(DateConvertor.convertToYMD(employeeFreeBsyList.getBsyList().get(i).getPallotTodt()));
					}

					Info res = Constants.getRestTemplate().postForObject(Constants.url + "/projectAllotmentSave", employeeFreeBsyList.getBsyList(),
							Info.class);

					if (res.isError() == false) {
						session.setAttribute("successMsg", "Project Allotment Successfully");
					} else {
						session.setAttribute("errorMsg", "Failed to Project Allotment");
					}
				}else {
					session.setAttribute("errorMsg", "Allocate atleast one employee ");
				}
			} else {
				session.setAttribute("errorMsg", "Failed to Project Allotment");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/projectAllotment";

	}

}
