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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.CalenderYear;
import com.ats.hradmin.leave.model.GetHoliday;
import com.ats.hradmin.leave.model.Holiday;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.Location;
import com.ats.hradmin.model.LoginResponse;

@Controller
@Scope("session")
public class LeaveHolidayController {
	Holiday editHoliday = new Holiday();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);

	@RequestMapping(value = "/holidayAdd", method = RequestMethod.GET)
	public ModelAndView holidayAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/holiday");

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

			// getCalculateYearList

			/*
			 * CalenderYear[] calculateYear = Constants.getRestTemplate()
			 * .getForObject(Constants.url + "/getCalculateYearListIsCurrent",
			 * CalenderYear[].class);
			 * 
			 * List<CalenderYear> yearList = new ArrayList<>(Arrays.asList(calculateYear));
			 * 
			 * model.addObject("yearList", yearList);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertHoliday", method = RequestMethod.POST)
	public String submitInsertHoliday(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			CalenderYear calculateYear = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getCalculateYearListIsCurrent", CalenderYear.class);

			String dateRange = request.getParameter("dateRange");
			String[] arrOfStr = dateRange.split("to", 2);

			String holidayRemark = request.getParameter("holidayRemark");
			String holidayTitle = request.getParameter("holidayTitle");

			String[] locIds = request.getParameterValues("locId");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < locIds.length; i++) {
				sb = sb.append(locIds[i] + ",");

			}
			String locIdList = sb.toString();
			locIdList = locIdList.substring(0, locIdList.length() - 1);

			int holidayId = 0;
			try {
				holidayId = Integer.parseInt(request.getParameter("holidayId"));
			} catch (Exception e) {
				holidayId = 0;
			}

			Boolean ret = false;

			if (FormValidation.Validaton(dateRange, "") == true) {

				ret = true;

			}

			if (FormValidation.Validaton(holidayRemark, "") == true) {

				ret = true;

			}

			if (ret == false) {

				Holiday holiday = new Holiday();

				holiday.setCalYrId((int) session.getAttribute("currYearId"));
				holiday.setCompanyId(userObj.getCompanyId());
				holiday.setDelStatus(1);

				holiday.setExVar1("NA");
				holiday.setExVar2(holidayTitle);
				holiday.setExVar3("NA");
				holiday.setHolidayFromdt(DateConvertor.convertToYMD(arrOfStr[0].toString().trim()));
				holiday.setHolidayTodt(DateConvertor.convertToYMD(arrOfStr[1].toString().trim()));

				holiday.setHolidayRemark(holidayRemark);
				holiday.setIsActive(1);
				holiday.setLocId(locIdList);
				holiday.setMakerEnterDatetime(dateTime);
				holiday.setMakerUserId(userObj.getUserId());

				Holiday res = Constants.getRestTemplate().postForObject(Constants.url + "/saveHoliday", holiday,
						Holiday.class);

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

		return "redirect:/showHolidayList";
	}

	@RequestMapping(value = "/editHoliday", method = RequestMethod.GET)
	public ModelAndView editHoliday(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/holiday_edit");

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

			// getCalculateYearList

			/*
			 * CalenderYear[] calculateYear = Constants.getRestTemplate()
			 * .getForObject(Constants.url + "/getCalculateYearListIsCurrent",
			 * CalenderYear[].class);
			 * 
			 * List<CalenderYear> yearList = new ArrayList<>(Arrays.asList(calculateYear));
			 * 
			 * model.addObject("yearList", yearList);
			 */
			String base64encodedString = request.getParameter("holidayId");
			String holidayId = FormValidation.DecodeKey(base64encodedString);

			map = new LinkedMultiValueMap<>();
			map.add("holidayId", holidayId);
			editHoliday = Constants.getRestTemplate().postForObject(Constants.url + "/getHolidayById", map,
					Holiday.class);
			model.addObject("editHoliday", editHoliday);

			List<Integer> locIdList = Stream.of(editHoliday.getLocId().split(",")).map(Integer::parseInt)
					.collect(Collectors.toList());

			model.addObject("locIdList", locIdList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/showHolidayList", method = RequestMethod.GET)
	public ModelAndView showHolidayList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/holiday_list");

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

	@RequestMapping(value = "/submitEditHoliday", method = RequestMethod.POST)
	public String submitEditHoliday(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			String dateRange = request.getParameter("dateRange");
			String[] arrOfStr = dateRange.split("to", 2);

			String holidayRemark = request.getParameter("holidayRemark");
			String holidayTitle = request.getParameter("holidayTitle");

			String[] locIds = request.getParameterValues("locId");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < locIds.length; i++) {
				sb = sb.append(locIds[i] + ",");

			}
			String locIdList = sb.toString();
			locIdList = locIdList.substring(0, locIdList.length() - 1);

			Boolean ret = false;

			if (FormValidation.Validaton(dateRange, "") == true) {

				ret = true;

			}

			if (FormValidation.Validaton(holidayTitle, "") == true) {

				ret = true;

			}

			if (ret == false) {

				editHoliday.setHolidayFromdt(DateConvertor.convertToYMD(arrOfStr[0].toString().trim()));
				editHoliday.setHolidayTodt(DateConvertor.convertToYMD(arrOfStr[1].toString().trim()));
				editHoliday.setHolidayRemark(holidayRemark);
				editHoliday.setLocId(locIdList);

				Holiday res = Constants.getRestTemplate().postForObject(Constants.url + "/saveHoliday", editHoliday,
						Holiday.class);

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
			session.setAttribute("errorMsg", "Failed to Update Record");
		}

		return "redirect:/showHolidayList";
	}

	@RequestMapping(value = "/deleteHoliday", method = RequestMethod.GET)
	public String deleteHoliday(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("holidayId");
			String holidayId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("holidayId", holidayId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteHoliday", map, Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showHolidayList";
	}

}
