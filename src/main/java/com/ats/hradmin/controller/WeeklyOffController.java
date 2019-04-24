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

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.GetHoliday;
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

		ModelAndView model = new ModelAndView("leave/weekly_off_list");

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			GetHoliday[] holListArray = Constants.getRestTemplate().postForObject(Constants.url + "/getHolidayList",
					map, GetHoliday[].class);

			List<GetHoliday> holList = new ArrayList<>(Arrays.asList(holListArray));

			for (int i = 0; i < holList.size(); i++) {

				holList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(holList.get(i).getHolidayId())));
			}

			model.addObject("holList", holList);
			// System.out.println("HolidayList" + holList.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}
