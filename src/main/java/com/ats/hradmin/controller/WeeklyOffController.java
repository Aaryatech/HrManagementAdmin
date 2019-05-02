package com.ats.hradmin.controller;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.ats.hradmin.leave.model.GetHoliday;
import com.ats.hradmin.leave.model.Holiday;
import com.ats.hradmin.model.GetWeeklyOff;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.Location;
import com.ats.hradmin.model.LoginResponse;
import com.ats.hradmin.model.WeeklyOff;

@Controller
@Scope("session")
public class WeeklyOffController {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);

	WeeklyOff editWeeklyOff = new WeeklyOff();

	@RequestMapping(value = "/addWeeklyOff", method = RequestMethod.GET)
	public ModelAndView addWeeklyOff(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/weekly_off_add");

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);

			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			for (int i = 0; i < locationList.size(); i++) {

				locationList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(locationList.get(i).getLocId())));
			}

			model.addObject("locationList", locationList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertWeeklyOff", method = RequestMethod.POST)
	public String submitInsertWeeklyOff(HttpServletRequest request, HttpServletResponse response) {

		try {
			System.out.println("inside submitInsertWeeklyOff");
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			String woDay = request.getParameter("woDay");
			String woPresently = request.getParameter("woPresently");
			String woRemarks = null;
			woRemarks = request.getParameter("woRemarks");
			String woType = request.getParameter("woType");

			String[] locIds = request.getParameterValues("locId");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < locIds.length; i++) {
				sb = sb.append(locIds[i] + ",");

			}
			String locIdList = sb.toString();
			locIdList = locIdList.substring(0, locIdList.length() - 1);

			Boolean ret = false;

			if (FormValidation.Validaton(woDay, "") == true) {

				ret = true;
			}

			if (FormValidation.Validaton(woPresently, "") == true) {

				ret = true;
			}

			if (ret == false) {

				WeeklyOff save = new WeeklyOff();
				save.setCompanyId(userObj.getCompanyId());
				save.setDelStatus(1);
				save.setIsActive(1);
				save.setLocId(locIdList);
				save.setWoType(woType);
				save.setWoRemarks(woRemarks);
				save.setWoIsUsed(1);
				save.setWoDay(woDay);
				save.setWoPresently(woPresently);
				save.setMakerEnterDatetime(dateTime);
				save.setMakerUserId(userObj.getUserId());

				WeeklyOff res = Constants.getRestTemplate().postForObject(Constants.url + "/saveWeeklyOff", save,
						WeeklyOff.class);

				if (res != null) {
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

		return "redirect:/addWeeklyOff";
	}

	@RequestMapping(value = "/showWeeklyOffList", method = RequestMethod.GET)
	public ModelAndView showWeeklyOffList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/weekly_off_list");

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			map.add("locIdList", userObj.getLocationIds());

			GetWeeklyOff[] holListArray = Constants.getRestTemplate().postForObject(Constants.url + "/getWeeklyOffList",
					map, GetWeeklyOff[].class);

			List<GetWeeklyOff> weekOffList = new ArrayList<>(Arrays.asList(holListArray));

			for (int i = 0; i < weekOffList.size(); i++) {

				weekOffList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(weekOffList.get(i).getWoId())));
			}

			model.addObject("weekOffList", weekOffList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/deleteWeeklyOff", method = RequestMethod.GET)
	public String deleteWeeklyOff(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("woId");
			String woId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("woId", woId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteWeeklyOff", map, Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showWeeklyOffList";
	}

	@RequestMapping(value = "/editWeeklyOff", method = RequestMethod.GET)
	public ModelAndView editWeeklyOff(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/weekly_off_edit");

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);

			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			for (int i = 0; i < locationList.size(); i++) {

				locationList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(locationList.get(i).getLocId())));
			}

			model.addObject("locationList", locationList);

			String base64encodedString = request.getParameter("woId");
			String woId = FormValidation.DecodeKey(base64encodedString);

			map = new LinkedMultiValueMap<>();
			map.add("woId", woId);
			editWeeklyOff = Constants.getRestTemplate().postForObject(Constants.url + "/getWeeklyOffById", map,
					WeeklyOff.class);
			model.addObject("editWeeklyOff", editWeeklyOff);

			List<Integer> locIdList = Stream.of(editWeeklyOff.getLocId().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			model.addObject("locIdList", locIdList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditInsertWeeklyOff", method = RequestMethod.POST)
	public String submitEditInsertWeeklyOff(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			String woDay = request.getParameter("woDay");
			String woPresently = request.getParameter("woPresently");
			String woRemarks = null;
			woRemarks = request.getParameter("woRemarks");
			String woType = request.getParameter("woType");

			String[] locIds = request.getParameterValues("locId");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < locIds.length; i++) {
				sb = sb.append(locIds[i] + ",");

			}
			String locIdList = sb.toString();
			locIdList = locIdList.substring(0, locIdList.length() - 1);

			Boolean ret = false;

			if (FormValidation.Validaton(woDay, "") == true) {

				ret = true;
			}

			if (FormValidation.Validaton(woPresently, "") == true) {

				ret = true;
			}

			if (ret == false) {

				editWeeklyOff.setLocId(locIdList);
				editWeeklyOff.setWoDay(woDay);
				editWeeklyOff.setWoPresently(woPresently);
				editWeeklyOff.setWoRemarks(woRemarks);
				editWeeklyOff.setWoType(woType);

				WeeklyOff res = Constants.getRestTemplate().postForObject(Constants.url + "/saveWeeklyOff",
						editWeeklyOff, WeeklyOff.class);

				if (res != null) {
					session.setAttribute("successMsg", "Record Update Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Update Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Update Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Update Record");
		}

		return "redirect:/showWeeklyOffList";
	}

}
