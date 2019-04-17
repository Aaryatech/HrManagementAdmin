package com.ats.hradmin.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.common.VpsImageUpload;
import com.ats.hradmin.leave.model.Holiday;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.Location;

@Controller
@Scope("session")
public class LeaveHolidayController {

	@RequestMapping(value = "/holidayAdd", method = RequestMethod.GET)
	public ModelAndView holidayAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/holiday");

		try {

			/*
			 * Company[] company = Constants.getRestTemplate().getForObject(Constants.url +
			 * "/getCompanyList", Company[].class);
			 * 
			 * List<Company> compList = new ArrayList<Company>(Arrays.asList(company));
			 * 
			 * System.out.println(compList); //System.out.println("asdfsdf");
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

			String holidayFromdt = request.getParameter("holidayFromdt");
			String holidayTodt = request.getParameter("holidayTodt");
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

			if (FormValidation.Validaton(holidayFromdt, "") == true) {

				ret = true;
				System.out.println("holidayFromdt" + ret);
			}
			if (FormValidation.Validaton(holidayTodt, "") == true) {

				ret = true;
				System.out.println("holidayTodt" + ret);
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
				holiday.setHolidayFromdt(DateConvertor.convertToYMD(holidayFromdt));
				holiday.setHolidayTodt(DateConvertor.convertToYMD(holidayTodt));
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

		return "redirect:/showCompanyList";
	}

}
