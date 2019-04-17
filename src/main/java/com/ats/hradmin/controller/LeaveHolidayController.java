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

@Controller
@Scope("session")
public class LeaveHolidayController {
	Holiday editHoliday = new Holiday();

	@RequestMapping(value = "/holidayAdd", method = RequestMethod.GET)
	public ModelAndView holidayAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/holiday");

		try {

			Company[] companyArray = Constants.getRestTemplate().getForObject(Constants.url + "/getCompanyList",
					Company[].class);

			List<Company> compList = new ArrayList<>(Arrays.asList(companyArray));

			model.addObject("compList", compList);

			System.out.println(compList); // System.out.println("asdfsdf");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);

			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			for (int i = 0; i < locationList.size(); i++) {

				locationList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(locationList.get(i).getLocId())));
			}

			model.addObject("locationList", locationList);

			// getCalculateYearList

			CalenderYear[] calculateYear = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getCalculateYearListIsCurrent", CalenderYear[].class);

			List<CalenderYear> yearList = new ArrayList<>(Arrays.asList(calculateYear));

			model.addObject("yearList", yearList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertHoliday", method = RequestMethod.POST)
	public String submitInsertHoliday(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();

			String dateRange = request.getParameter("dateRange");
			String[] arrOfStr = dateRange.split("to", 2);
			System.out.println("111" + arrOfStr[0].toString().trim());
			System.out.println("222" + arrOfStr[1].toString().trim());

			String holidayRemark = request.getParameter("holidayRemark");

			int calYrId = Integer.parseInt(request.getParameter("calYrId"));
			int companyId = Integer.parseInt(request.getParameter("companyId"));

			int locId = Integer.parseInt(request.getParameter("locId"));

			int holidayId = 0;
			try {
				holidayId = Integer.parseInt(request.getParameter("holidayId"));
			} catch (Exception e) {
				holidayId = 0;
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			String curDate = dateFormat.format(new Date());
			String dateTime = dateFormat.format(now);

			Boolean ret = false;

			if (FormValidation.Validaton(dateRange, "") == true) {

				ret = true;
				System.out.println("holidayFromdt" + ret);
			}

			if (FormValidation.Validaton(holidayRemark, "") == true) {

				ret = true;
				System.out.println("holidayRemark" + ret);
			}

			if (ret == false) {

				Holiday holiday = new Holiday();

				holiday.setCalYrId(calYrId);
				holiday.setCompanyId(companyId);
				holiday.setDelStatus(1);
				holiday.setExInt1(1);

				holiday.setExInt2(1);
				holiday.setExInt3(1);
				holiday.setExVar1("NA");
				holiday.setExVar2("NA");
				holiday.setExVar3("NA");
				holiday.setHolidayFromdt(DateConvertor.convertToYMD(arrOfStr[0].toString().trim()));
				holiday.setHolidayTodt(DateConvertor.convertToYMD(arrOfStr[1].toString().trim()));
				holiday.setHolidayId(holidayId);
				holiday.setHolidayRemark(holidayRemark);
				holiday.setIsActive(1);
				holiday.setLocId(locId);
				holiday.setMakerEnterDatetime(dateTime);
				holiday.setMakerUserId(1);

				Holiday res = Constants.getRestTemplate().postForObject(Constants.url + "/saveHoliday", holiday,
						Holiday.class);

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

		return "redirect:/holidayAdd";
	}

	@RequestMapping(value = "/editHoliday", method = RequestMethod.GET)
	public ModelAndView editHoliday(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/holiday_edit");

		try {

			Company[] companyArray = Constants.getRestTemplate().getForObject(Constants.url + "/getCompanyList",
					Company[].class);

			List<Company> compList = new ArrayList<>(Arrays.asList(companyArray));

			model.addObject("compList", compList);

			System.out.println(compList); // System.out.println("asdfsdf");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);

			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			for (int i = 0; i < locationList.size(); i++) {

				locationList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(locationList.get(i).getLocId())));
			}

			model.addObject("locationList", locationList);

			// getCalculateYearList

			CalenderYear[] calculateYear = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getCalculateYearListIsCurrent", CalenderYear[].class);

			List<CalenderYear> yearList = new ArrayList<>(Arrays.asList(calculateYear));

			model.addObject("yearList", yearList);
			String base64encodedString = request.getParameter("holidayId");
			String holidayId = FormValidation.DecodeKey(base64encodedString);
			System.out.println("holidayId" + holidayId);

			map = new LinkedMultiValueMap<>();
			map.add("holidayId", holidayId);
			editHoliday = Constants.getRestTemplate().postForObject(Constants.url + "/getHolidayById", map,
					Holiday.class);
			model.addObject("editHoliday", editHoliday);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/showHolidayList", method = RequestMethod.GET)
	public ModelAndView showHolidayList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/holiday_list");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			map.add("locIdList", 1);

			GetHoliday[] holListArray = Constants.getRestTemplate().postForObject(Constants.url + "/getHolidayList",
					map, GetHoliday[].class);

			List<GetHoliday> holList = new ArrayList<>(Arrays.asList(holListArray));

			for (int i = 0; i < holList.size(); i++) {

				holList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(holList.get(i).getHolidayId())));
			}

			model.addObject("holList", holList);
			System.out.println("HolidayList" + holList.toString());

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
			System.out.println("111" + arrOfStr[0].toString().trim());
			System.out.println("222" + arrOfStr[1].toString().trim());

			String holidayRemark = request.getParameter("holidayRemark");

			int calYrId = Integer.parseInt(request.getParameter("calYrId"));
			int companyId = Integer.parseInt(request.getParameter("companyId"));

			int locId = Integer.parseInt(request.getParameter("locId"));

			int holidayId = 0;
			try {
				holidayId = Integer.parseInt(request.getParameter("holidayId"));
			} catch (Exception e) {
				holidayId = 0;
			}
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			String curDate = dateFormat.format(new Date());
			String dateTime = dateFormat.format(now);

			Boolean ret = false;

			if (FormValidation.Validaton(dateRange, "") == true) {

				ret = true;
				System.out.println("holidayFromdt" + ret);
			}

			if (FormValidation.Validaton(holidayRemark, "") == true) {

				ret = true;
				System.out.println("holidayRemark" + ret);
			}

			if (ret == false) {

				editHoliday.setCalYrId(calYrId);
				editHoliday.setCompanyId(companyId);
				editHoliday.setHolidayFromdt(DateConvertor.convertToYMD(arrOfStr[0].toString().trim()));
				editHoliday.setHolidayTodt(DateConvertor.convertToYMD(arrOfStr[1].toString().trim()));
				editHoliday.setHolidayRemark(holidayRemark);
				editHoliday.setLocId(locId);

				Holiday res = Constants.getRestTemplate().postForObject(Constants.url + "/saveHoliday", editHoliday,
						Holiday.class);

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
