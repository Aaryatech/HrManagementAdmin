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
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LoginResponse;
import com.ats.hradmin.model.project.ProjectType;

@Controller
@Scope("session")
public class ProjectController {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);

	ProjectType editproType = new ProjectType();

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

}
