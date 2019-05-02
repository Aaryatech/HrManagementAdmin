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
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.claim.ClaimType;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.model.Customer;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.GetProjectHeader;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.Location;
import com.ats.hradmin.model.LoginResponse;
import com.ats.hradmin.model.ProjectHeader;
import com.ats.hradmin.model.ProjectTrail;
import com.ats.hradmin.model.project.ProjectType;

@Controller
@Scope("session")
public class ProjectController {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);

	ProjectType editproType = new ProjectType();

	ProjectHeader editProjectHeader = new ProjectHeader();

	@RequestMapping(value = "/projectTypeAdd", method = RequestMethod.GET)
	public ModelAndView projectTypeAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("project/project_type_add");

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertProjectType", method = RequestMethod.POST)
	public String submitInsertProjectType(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			String projectTypeTitle = request.getParameter("proTypetitle");
			String projectTypeTitleShort = request.getParameter("proShortTypeTitle");

			String projectColor = request.getParameter("projectColor");
			String remark = null;
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(projectTypeTitle, "") == true) {

				ret = true;

			}
			if (FormValidation.Validaton(projectTypeTitleShort, "") == true) {

				ret = true;

			}

			if (FormValidation.Validaton(request.getParameter("projectColor"), "") == true) {

				ret = true;

			}

			if (ret == false) {

				ProjectType save = new ProjectType();
				save.setProjectTypeRemarks(remark);
				save.setProjectTypeColor(projectColor);
				save.setProjectTypeTitleShort(projectTypeTitleShort);
				save.setProjectTypeTitle(projectTypeTitle);

				save.setCompanyId(userObj.getCompanyId());

				save.setDelStatus(1);
				save.setIsActive(1);
				save.setMakerUserId(userObj.getUserId());
				save.setMakerEnterDatetime(dateTime);

				ProjectType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveProjectType", save,
						ProjectType.class);
				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Insert Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/projectTypeAdd";

	}

	@RequestMapping(value = "/showProjectTypeList", method = RequestMethod.GET)
	public ModelAndView showProjectTypeList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("project/project_type_list");

		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			System.out.println(userObj.getCompanyId());

			ProjectType[] projectTypeListArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getProjectListByCompanyId", map, ProjectType[].class);

			List<ProjectType> projectTypelist = new ArrayList<ProjectType>(Arrays.asList(projectTypeListArray));
			System.out.println(projectTypelist.toString());

			for (int i = 0; i < projectTypelist.size(); i++) {

				projectTypelist.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(projectTypelist.get(i).getProjectTypeId())));
			}

			model.addObject("projectTypelist", projectTypelist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/editProjectType", method = RequestMethod.GET)
	public ModelAndView editProjectType(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("project/project_type_edit");

		try {

			String base64encodedString = request.getParameter("projectTypeId");
			String projectTypeId = FormValidation.DecodeKey(base64encodedString);
			// System.out.println("claimTypeId" + claimTypeId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("projectTypeId", projectTypeId);
			editproType = Constants.getRestTemplate().postForObject(Constants.url + "/getProjectById", map,
					ProjectType.class);
			model.addObject("editproType", editproType);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditProjectType", method = RequestMethod.POST)
	public String submitEditProjectType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			String projectTypeTitle = request.getParameter("proTypetitle");
			String projectTypeTitleShort = request.getParameter("proShortTypeTitle");

			String projectColor = request.getParameter("projectColor");
			String remark = null;
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(projectTypeTitle, "") == true) {

				ret = true;

			}
			if (FormValidation.Validaton(projectTypeTitleShort, "") == true) {

				ret = true;

			}

			if (FormValidation.Validaton(request.getParameter("projectColor"), "") == true) {

				ret = true;

			}

			if (ret == false) {
				editproType.setProjectTypeColor(projectColor);
				editproType.setProjectTypeRemarks(remark);
				editproType.setProjectTypeTitle(projectTypeTitle);

				editproType.setProjectTypeTitleShort(projectTypeTitleShort);

				ProjectType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveProjectType",
						editproType, ProjectType.class);
				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Update Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Update Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Update Record");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Insert");
		}
		return "redirect:/showProjectTypeList";
	}

	@RequestMapping(value = "/deleteProjectType", method = RequestMethod.GET)
	public String deleteProjectType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("projectTypeId");
			String projectTypeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("projectTypeId", projectTypeId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteProjectType", map,
					Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showProjectTypeList";
	}

	@RequestMapping(value = "/addProjectHeader", method = RequestMethod.GET)
	public ModelAndView addProjectHeader(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("project/project_header");

		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			ProjectType[] projectTypeListArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getProjectListByCompanyId", map, ProjectType[].class);

			List<ProjectType> projectTypelist = new ArrayList<ProjectType>(Arrays.asList(projectTypeListArray));

			model.addObject("projectTypelist", projectTypelist);

			Customer[] custListArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getCustListByCompanyId", map, Customer[].class);

			List<Customer> custlist = new ArrayList<Customer>(Arrays.asList(custListArray));

			model.addObject("custlist", custlist);

			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);

			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));
			model.addObject("locationList", locationList);

			map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			map.add("locIdList", userObj.getLocationIds());

			GetEmployeeInfo[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoList", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeDepartmentlist = new ArrayList<GetEmployeeInfo>(
					Arrays.asList(employeeDepartment));

			model.addObject("empList", employeeDepartmentlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertProjectHeader", method = RequestMethod.POST)
	public String submitInsertProjectHeader(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			int empId = Integer.parseInt(request.getParameter("empId"));
			int locId = Integer.parseInt(request.getParameter("locId"));
			int projectTypeId = Integer.parseInt(request.getParameter("projectTypeId"));
			int custId = Integer.parseInt(request.getParameter("custId"));
			String projectTitle = request.getParameter("projectTitle");
			String projectDesc = request.getParameter("projectDesc");
			String projectCity = request.getParameter("projectCity");
			/* String dateRange = request.getParameter("dateRange"); */
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");

			int project_est_manhrs = Integer.parseInt(request.getParameter("project_est_manhrs"));
			int project_est_budget = Integer.parseInt(request.getParameter("project_est_budget"));

			// String[] arrOfStr = dateRange.split("to", 2);

			String remark = null;
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(projectTitle, "") == true) {

				ret = true;

			}
			if (FormValidation.Validaton(projectDesc, "") == true) {

				ret = true;

			}

			if (FormValidation.Validaton(request.getParameter("projectCity"), "") == true) {

				ret = true;

			}

			if (ret == false) {

				ProjectHeader save = new ProjectHeader();

				save.setCompanyId(userObj.getCompanyId());

				save.setDelStatus(1);
				save.setIsActive(1);
				save.setMakerUserId(userObj.getUserId());
				save.setMakerEnterDatetime(dateTime);
				save.setCustId(custId);
				save.setLocId(locId);

				save.setProjectCity(projectCity);

				save.setProjectCompletion(0);
				save.setProjectDesc(projectDesc);
				save.setProjectEstBudget(project_est_budget);
				save.setProjectEstManhrs(project_est_manhrs);
				/*
				 * save.setProjectEstStartdt(DateConvertor.convertToYMD(arrOfStr[0].toString().
				 * trim()));
				 * save.setProjectEstEnddt(DateConvertor.convertToYMD(arrOfStr[1].toString().
				 * trim()));
				 */

				save.setProjectEstStartdt(DateConvertor.convertToYMD(fromDate));
				save.setProjectEstEnddt(DateConvertor.convertToYMD(toDate));
				save.setProjectStatus("aaa");
				save.setProjectTypeId(projectTypeId);
				save.setProjectManagerEmpId(empId);

				save.setProjectTitle(projectTitle);

				ProjectHeader res = Constants.getRestTemplate().postForObject(Constants.url + "/saveProjectHeader",
						save, ProjectHeader.class);
				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Insert Successfully");

					ProjectTrail projectTrail = new ProjectTrail();
					projectTrail.setDelStatus(1);
					projectTrail.setIsActive(1);
					projectTrail.setMakerEnterDatetime(dateTime);
					projectTrail.setMakerUserId(userObj.getUserId());
					projectTrail.setProjectCompletion(0);
					projectTrail.setProjectId(res.getProjectId());
					projectTrail.setProjectRemarks("NULL");
					projectTrail.setProjectStatus("Null");

					ProjectTrail res1 = Constants.getRestTemplate().postForObject(Constants.url + "/saveProjectTrail",
							projectTrail, ProjectTrail.class);
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showProjectHeaderList";

	}

	@RequestMapping(value = "/showProjectHeaderList", method = RequestMethod.GET)
	public ModelAndView showProjectHeaderList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("project/project_header_list");

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			GetProjectHeader[] proHeaderArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getProjectAllListByCompanyId", map, GetProjectHeader[].class);
			List<GetProjectHeader> projectHeaderList = new ArrayList<GetProjectHeader>(Arrays.asList(proHeaderArray));

			for (int i = 0; i < projectHeaderList.size(); i++) {

				projectHeaderList.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(projectHeaderList.get(i).getProjectId())));
			}

			model.addObject("projectHeaderList", projectHeaderList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
	public String deleteProject(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("projectId");
			String projectId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("projectId", projectId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteProjectHeader", map,
					Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showProjectHeaderList";
	}

	@RequestMapping(value = "/editProject", method = RequestMethod.GET)
	public ModelAndView editProject(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("project/project_header_edit");

		try {

			String base64encodedString = request.getParameter("projectId");
			String projectId = FormValidation.DecodeKey(base64encodedString);
			// System.out.println("claimTypeId" + claimTypeId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("projectId", projectId);
			editProjectHeader = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getProjectHeaderByProjectId", map, ProjectHeader.class);
			model.addObject("editProjectHeader", editProjectHeader);

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			ProjectType[] projectTypeListArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getProjectListByCompanyId", map, ProjectType[].class);

			List<ProjectType> projectTypelist = new ArrayList<ProjectType>(Arrays.asList(projectTypeListArray));

			model.addObject("projectTypelist", projectTypelist);

			Customer[] custListArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getCustListByCompanyId", map, Customer[].class);

			List<Customer> custlist = new ArrayList<Customer>(Arrays.asList(custListArray));

			model.addObject("custlist", custlist);

			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);

			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));
			model.addObject("locationList", locationList);

			map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			map.add("locIdList", userObj.getLocationIds());

			GetEmployeeInfo[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoList", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeDepartmentlist = new ArrayList<GetEmployeeInfo>(
					Arrays.asList(employeeDepartment));

			model.addObject("empList", employeeDepartmentlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditProjectHeader", method = RequestMethod.POST)
	public String submitEditProjectHeader(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			int empId = Integer.parseInt(request.getParameter("empId"));
			int locId = Integer.parseInt(request.getParameter("locId"));
			int projectTypeId = Integer.parseInt(request.getParameter("projectTypeId"));
			int custId = Integer.parseInt(request.getParameter("custId"));
			String projectTitle = request.getParameter("projectTitle");
			String projectDesc = request.getParameter("projectDesc");
			String projectCity = request.getParameter("projectCity");
			/* String dateRange = request.getParameter("dateRange"); */
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");

			int project_est_manhrs = Integer.parseInt(request.getParameter("project_est_manhrs"));
			int project_est_budget = Integer.parseInt(request.getParameter("project_est_budget"));

			// String[] arrOfStr = dateRange.split("to", 2);

			Boolean ret = false;

			if (FormValidation.Validaton(projectTitle, "") == true) {

				ret = true;

			}
			if (FormValidation.Validaton(projectDesc, "") == true) {

				ret = true;

			}

			if (FormValidation.Validaton(request.getParameter("projectCity"), "") == true) {

				ret = true;

			}

			if (ret == false) {

				editProjectHeader.setCompanyId(userObj.getCompanyId());

				editProjectHeader.setCustId(custId);
				editProjectHeader.setLocId(locId);

				editProjectHeader.setProjectCity(projectCity);

				editProjectHeader.setProjectCompletion(0);
				editProjectHeader.setProjectDesc(projectDesc);
				editProjectHeader.setProjectEstBudget(project_est_budget);
				editProjectHeader.setProjectEstManhrs(project_est_manhrs);
				/*
				 * save.setProjectEstStartdt(DateConvertor.convertToYMD(arrOfStr[0].toString().
				 * trim()));
				 * save.setProjectEstEnddt(DateConvertor.convertToYMD(arrOfStr[1].toString().
				 * trim()));
				 */

				editProjectHeader.setProjectEstStartdt(DateConvertor.convertToYMD(fromDate));
				editProjectHeader.setProjectEstEnddt(DateConvertor.convertToYMD(toDate));
				editProjectHeader.setProjectStatus("aaa");
				editProjectHeader.setProjectTypeId(projectTypeId);
				editProjectHeader.setProjectManagerEmpId(empId);

				editProjectHeader.setProjectTitle(projectTitle);

				ProjectHeader res = Constants.getRestTemplate().postForObject(Constants.url + "/saveProjectHeader",
						editProjectHeader, ProjectHeader.class);
				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Insert Successfully");

				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showProjectHeaderList";

	}

}
