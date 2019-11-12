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

import com.ats.hradmin.claim.ClaimStructureDetail;
import com.ats.hradmin.claim.ClaimStructureHeader;
import com.ats.hradmin.claim.ClaimType;
import com.ats.hradmin.common.AcessController;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.LeaveStructureDetails;
import com.ats.hradmin.leave.model.LeaveStructureHeader;
import com.ats.hradmin.model.AccessRightModule;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveType;
import com.ats.hradmin.model.LoginResponse;

@Controller
@Scope("session")
public class ClaimStructureAndAllotController {

	List<ClaimStructureDetail> tempDetailList = new ArrayList<ClaimStructureDetail>();

	List<ClaimType> claimTypeList = new ArrayList<ClaimType>();

	@RequestMapping(value = "/addClaimStructure", method = RequestMethod.GET)
	public ModelAndView addLeaveStructure(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
		ModelAndView model = null;

		try {

			model = new ModelAndView("claim/add_claim_structure");
			tempDetailList = new ArrayList<ClaimStructureDetail>();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			ClaimType[] employeeDoc = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getClaimListByCompanyId", map, ClaimType[].class);

			claimTypeList = new ArrayList<ClaimType>(Arrays.asList(employeeDoc));
			System.out.println("claimTypeList list " + claimTypeList.toString());
			model.addObject("claimTypeList", claimTypeList);

			model.addObject("title", "Add Claim Structure");

		} catch (Exception e) {

			System.err.println("exception In addClaimStructure at claim structure Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertClaimStructure", method = RequestMethod.POST)
	public String insertClaimStructure(HttpServletRequest request, HttpServletResponse response) {
		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			String curDate = dateFormat.format(new Date());
			String dateTime = dateFormat.format(now);
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			String clmsName = request.getParameter("clmsName");

			Boolean ret = false;

			if (FormValidation.Validaton(clmsName, "") == true) {

				ret = true;

			}

			if (ret == false) {

				ClaimStructureHeader head = new ClaimStructureHeader();

				head.setCompanyId(userObj.getCompanyId());
				head.setDelStatus(1);
				head.setIsActive(1);
				head.setClaimStructName(clmsName);
				head.setMakerDatetime(dateTime);
				head.setMakerUserId(userObj.getUserId());
				 
				List<ClaimStructureDetail> detailList = new ArrayList<>();
				for (int i = 0; i < claimTypeList.size(); i++) {

					float amt = 0;
					try {
						amt = (Integer.parseInt(request.getParameter("amt" + claimTypeList.get(i).getClaimTypeId())));
					} catch (Exception e) {
						amt = 0;
					}

					/* if (noOfLeaves > 0) { */
					ClaimStructureDetail detail = new ClaimStructureDetail();
					detail.setDelStatus(1);
					detail.setExInt1(0);
					detail.setExInt2(0);
					detail.setExVar1("NA");
					detail.setExVar2("NA");
					detail.setIsActive(1);

					detail.setClmAmt(
							Integer.parseInt(request.getParameter("amt" + claimTypeList.get(i).getClaimTypeId())));

					detail.setClmTypeId(claimTypeList.get(i).getClaimTypeId());
					detail.setMakerUserId(userObj.getUserId());
					detail.setMakerDatetime(dateTime);
					detailList.add(detail);

				}

				head.setDetailList(detailList);

				ClaimStructureHeader res = Constants.getRestTemplate()
						.postForObject(Constants.url + "saveClaimStruture", head, ClaimStructureHeader.class);

				if (res != null) {
					session.setAttribute("successMsg", "Record Inserted Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}

			}
		} catch (Exception e) {

			System.err.println("Exce In insertClaimStructure method  " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showClaimStructureList";

	}

	@RequestMapping(value = "/showClaimStructureList", method = RequestMethod.GET)
	public ModelAndView showClaimStructureList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			model = new ModelAndView("claim/claim_structure_list");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			ClaimStructureHeader[] summary = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getClaimStructHeadList", map, ClaimStructureHeader[].class);

			List<ClaimStructureHeader> clmSummarylist = new ArrayList<>(Arrays.asList(summary));

			for (int i = 0; i < clmSummarylist.size(); i++) {

				clmSummarylist.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(clmSummarylist.get(i).getClmStructHeadId())));
			}

			model.addObject("clmSummarylist", clmSummarylist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	ClaimStructureHeader editStructure = new ClaimStructureHeader();

	@RequestMapping(value = "/editClaimStructure", method = RequestMethod.GET)
	public ModelAndView editClaimStructure(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

		ModelAndView model = null;
		try {
			model = new ModelAndView("claim/edit_claim_structure");
			String base64encodedString = request.getParameter("clmHeadId");
			String headId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("headId", headId);
			editStructure = Constants.getRestTemplate().postForObject(Constants.url + "/getClaimStructureById", map,
					ClaimStructureHeader.class);
			model.addObject("editStructure", editStructure);

			model.addObject("editStructureDetail", editStructure.getDetailList());

			map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			ClaimType[] employeeDoc = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getClaimListByCompanyId", map, ClaimType[].class);

			ArrayList<ClaimType> claimTypeList1 = new ArrayList<ClaimType>(Arrays.asList(employeeDoc));

			model.addObject("claimTypeList", claimTypeList1);

			//System.out.println("editStructure" + editStructure.toString());
			//System.out.println("editStructureDetail" + editStructure.getDetailList().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/insertEditedClaimSummary", method = RequestMethod.POST)
	public String insertEditedClaimSummary(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("hii");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String curDate = dateFormat.format(new Date());
		String dateTime = dateFormat.format(now);
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

		String clmsName = request.getParameter("clmsName");

		Boolean ret = false;

		if (FormValidation.Validaton(clmsName, "") == true) {

			ret = true;

		}

		int flag = 0;
		if (ret == false) {
			//System.err.println("hii  "+editStructure.getDetailList().size());
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			ClaimType[] employeeDoc = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getClaimListByCompanyId", map, ClaimType[].class);

			ArrayList<ClaimType> claimTypeList1 = new ArrayList<ClaimType>(Arrays.asList(employeeDoc));

			//System.err.println("hii  "+claimTypeList1.size());
			editStructure.setCompanyId(userObj.getCompanyId());
			editStructure.setClaimStructName(clmsName);
			editStructure.setMakerDatetime(dateTime);
			editStructure.setMakerUserId(userObj.getUserId());

			List<ClaimStructureDetail> detailList = new ArrayList<>();
			for (int i = 0; i < claimTypeList1.size(); i++) {

				float amt = 0;
				try {
					for (int j = 0; j < editStructure.getDetailList().size(); j++) {

						if (editStructure.getDetailList().get(j).getClmTypeId() == claimTypeList1.get(i)
								.getClaimTypeId()) {

							flag = 1;
							System.err.println("flag set");

							editStructure.getDetailList().get(j).setClmAmt(Float
									.parseFloat(request.getParameter("amt" + claimTypeList1.get(i).getClaimTypeId())));

							editStructure.getDetailList().get(j).setMakerUserId(userObj.getUserId());
							editStructure.getDetailList().get(j).setMakerDatetime(dateTime);
						}
					}
				} catch (Exception e) {
					amt = 0;
				}
				if (flag == 0) {
					System.err.println("flag not set");
					ClaimStructureDetail detail = new ClaimStructureDetail();

					detail.setClmAmt(
							Integer.parseInt(request.getParameter("amt" + claimTypeList1.get(i).getClaimTypeId())));

					detail.setClmTypeId(claimTypeList1.get(i).getClaimTypeId());
					detail.setMakerUserId(userObj.getUserId());
					detail.setMakerDatetime(dateTime);
					editStructure.getDetailList().add(detail);
				}

			}
		}

		ClaimStructureHeader res = Constants.getRestTemplate().postForObject(Constants.url + "saveClaimStruture",
				editStructure, ClaimStructureHeader.class);

		if (res != null) {
			session.setAttribute("successMsg", "Record Inserted Successfully");
		} else {
			session.setAttribute("errorMsg", "Failed to Insert Record");
		}

		/*
		 * } catch (Exception e) {
		 * 
		 * System.err.println("Exce In insertClaimStructure method  " + e.getMessage());
		 * e.printStackTrace();
		 * 
		 * }
		 */
		return "redirect:/showClaimStructureList";

	}

	@RequestMapping(value = "/deleteClaimStructure", method = RequestMethod.GET)
	public String deleteClaimStructure(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			a = "redirect:/showClaimStructureList";
			String base64encodedString = request.getParameter("clmHeadId");
			String clmsId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("clmsId", clmsId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteClaimStructure", map,
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
		return a;
	}

}
