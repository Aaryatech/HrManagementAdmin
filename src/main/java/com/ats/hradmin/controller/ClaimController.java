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

import com.ats.hradmin.claim.ClaimType;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.Holiday;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveSummary;
import com.ats.hradmin.model.LeaveType;
import com.ats.hradmin.model.Location;

@Controller
@Scope("session")
public class ClaimController {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);
	ClaimType editClaimType = new ClaimType();

	// ************************************Claim Type***************************

	@RequestMapping(value = "/claimTypeAdd", method = RequestMethod.GET)
	public ModelAndView claimTypeAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_type_add");

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertClaimType", method = RequestMethod.POST)
	public String submitInsertClaimType(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			String calimTypeTitle = request.getParameter("calimTypeTitle");
			String claimShortTypeTitle = request.getParameter("claimShortTypeTitle");

			String claimTypeColor = request.getParameter("claimColor");
			String remark = null;
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			System.out.println("color    " + claimTypeColor);

			Boolean ret = false;

			if (FormValidation.Validaton(calimTypeTitle, "") == true) {

				ret = true;
				System.out.println("calimTypeTitle" + ret);
			}
			if (FormValidation.Validaton(claimShortTypeTitle, "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("claimColor"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (ret == false) {

				ClaimType save = new ClaimType();
				save.setClaimTypeColor(claimTypeColor);
				save.setClaimTypeRemarks(remark);
				save.setClaimTypeTitle(calimTypeTitle);
				save.setClaimTypeTitleShort(claimShortTypeTitle);
				save.setCompanyId(1);

				save.setDelStatus(1);
				save.setIsActive(1);
				save.setMakerUserId(1);
				save.setMakerEnterDatetime(dateTime);

				ClaimType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimType", save,
						ClaimType.class);
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/claimTypeAdd";

	}

	@RequestMapping(value = "/showClaimTypeList", method = RequestMethod.GET)
	public ModelAndView showClaimTypeList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_type_list");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);

			ClaimType[] claimTypeListArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getClaimListByCompanyId", map, ClaimType[].class);

			List<ClaimType> claimTypelist = new ArrayList<ClaimType>(Arrays.asList(claimTypeListArray));

			for (int i = 0; i < claimTypelist.size(); i++) {

				claimTypelist.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(claimTypelist.get(i).getClaimTypeId())));
			}

			model.addObject("claimTypelist", claimTypelist);

			System.out.println("" + claimTypelist.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/editClaimType", method = RequestMethod.GET)
	public ModelAndView editClaimType(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_edit");

		try {

			String base64encodedString = request.getParameter("claimTypeId");
			String claimTypeId = FormValidation.DecodeKey(base64encodedString);
			System.out.println("claimTypeId" + claimTypeId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("claimTypeId", claimTypeId);
			editClaimType = Constants.getRestTemplate().postForObject(Constants.url + "/getClaimById", map,
					ClaimType.class);
			model.addObject("editClaimType", editClaimType);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditClaimType", method = RequestMethod.POST)
	public String submitEditClaimType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			String calimTypeTitle = request.getParameter("calimTypeTitle");
			String claimShortTypeTitle = request.getParameter("claimShortTypeTitle");

			String claimTypeColor = request.getParameter("claimColor");
			String remark = null;
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			System.out.println("color    " + claimTypeColor);

			Boolean ret = false;

			if (FormValidation.Validaton(calimTypeTitle, "") == true) {

				ret = true;
				System.out.println("calimTypeTitle" + ret);
			}
			if (FormValidation.Validaton(claimShortTypeTitle, "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("claimColor"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (ret == false) {

				editClaimType.setClaimTypeColor(claimTypeColor);
				editClaimType.setClaimTypeRemarks(remark);
				editClaimType.setClaimTypeTitle(calimTypeTitle);
				editClaimType.setClaimTypeTitleShort(claimShortTypeTitle);

				ClaimType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimType",
						editClaimType, ClaimType.class);
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Update Record");
		}

		return "redirect:/showClaimTypeList";
	}

	@RequestMapping(value = "/deleteClaimType", method = RequestMethod.GET)
	public String deleteClaimType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("claimTypeId");
			String claimTypeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("claimTypeId", claimTypeId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteClaimType", map, Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showClaimTypeList";
	}

}
