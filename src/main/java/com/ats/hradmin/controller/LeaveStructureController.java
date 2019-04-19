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
import com.ats.hradmin.leave.model.GetStructureAllotment;
import com.ats.hradmin.leave.model.Holiday;
import com.ats.hradmin.leave.model.LeaveStructureDetails;
import com.ats.hradmin.leave.model.LeaveStructureHeader;
import com.ats.hradmin.leave.model.LeavesAllotment;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveSummary;
import com.ats.hradmin.model.LeaveType;

@Controller
@Scope("session")
public class LeaveStructureController {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);
	List<LeaveStructureDetails> tempDetailList = new ArrayList<LeaveStructureDetails>();

	@RequestMapping(value = "/addLeaveStructureHeader", method = RequestMethod.GET)
	public ModelAndView addLeaveStructureHeader(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			tempDetailList = new ArrayList<LeaveStructureDetails>();

			model = new ModelAndView("leave/leave_structure");

			LeaveType[] leaveArray = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getLeaveTypeListIsStructure", LeaveType[].class);

			List<LeaveType> leaveTypeList = new ArrayList<>(Arrays.asList(leaveArray));

			model.addObject("leaveTypeList", leaveTypeList);

			model.addObject("title", "Add Leave Structure");

		} catch (Exception e) {

			System.err.println("exception In addLeaveStructureHeader at leave structure Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addStrDetail", method = RequestMethod.GET)
	public @ResponseBody List<LeaveStructureDetails> addStrDetail(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			int isDelete = Integer.parseInt(request.getParameter("isDelete"));

			int isEdit = Integer.parseInt(request.getParameter("isEdit"));

			if (isDelete == 1) {
				System.out.println("IsDelete" + isDelete);
				int key = Integer.parseInt(request.getParameter("key"));

				tempDetailList.remove(key);

			} else if (isEdit == 1) {
				System.out.println("hii is edit");
				int index = Integer.parseInt(request.getParameter("index"));

				int noOfLeaves = Integer.parseInt(request.getParameter("noOfLeaves"));
				int lvTypeId = Integer.parseInt(request.getParameter("leaveTypeId"));

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("lvTypeId", lvTypeId);
				LeaveType editLeaveType = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveTypeById",
						map, LeaveType.class);

				tempDetailList.get(index).setLvTypeId(lvTypeId);
				tempDetailList.get(index).setLvsAllotedLeaves(noOfLeaves);
				tempDetailList.get(index).setExVar1(editLeaveType.getLvTitle());

			}

			else {

				int noOfLeaves = Integer.parseInt(request.getParameter("noOfLeaves"));
				int lvTypeId = Integer.parseInt(request.getParameter("leaveTypeId"));

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("lvTypeId", lvTypeId);
				LeaveType editLeaveType = Constants.getRestTemplate().postForObject(Constants.url + "/getLeaveTypeById",
						map, LeaveType.class);

				LeaveStructureDetails tempDetail = new LeaveStructureDetails();
				tempDetail.setLvTypeId(lvTypeId);
				tempDetail.setLvsAllotedLeaves(noOfLeaves);
				tempDetail.setLvsDetailsId(0);
				tempDetail.setExVar1(editLeaveType.getLvTitle());
				tempDetail.setDelStatus(1);
				tempDetail.setExInt1(1);
				tempDetail.setExInt2(1);
				tempDetail.setExVar2("NA");
				tempDetail.setIsActive(1);
				tempDetail.setMakerDatetime(dateTime);
				tempDetail.setMakerUserId(1);

				tempDetailList.add(tempDetail);
			}

		} catch (Exception e) {
			System.err.println("Exce In atempDocList  temp List " + e.getMessage());
			e.printStackTrace();
		}
		System.err.println(" enq Item List " + tempDetailList.toString());

		return tempDetailList;

	}

	@RequestMapping(value = "/getLeaveStructureForEdit", method = RequestMethod.GET)
	public @ResponseBody LeaveStructureDetails getLeaveStructureForEdit(HttpServletRequest request,
			HttpServletResponse response) {

		int index = Integer.parseInt(request.getParameter("index"));

		return tempDetailList.get(index);

	}

	// submitInsertLeaveStructure
	@RequestMapping(value = "/submitInsertLeaveStructure", method = RequestMethod.POST)
	public String submitInsertLeaveStructure(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.err.println("Inside insert submitInsertLeaveStructure method");

			String lvsName = request.getParameter("lvsName");

			LeaveStructureHeader head = new LeaveStructureHeader();

			head.setCompanyId(1);
			head.setDelStatus(1);
			head.setIsActive(1);
			head.setLvsName(lvsName);
			head.setMakerDatetime(dateTime);
			head.setMakerUserId(1);

			head.setDetailList(tempDetailList);

			LeaveStructureHeader docInsertRes = Constants.getRestTemplate()
					.postForObject(Constants.url + "saveLeaveStruture", head, LeaveStructureHeader.class);
			System.out.println("docInsertRes" + docInsertRes.toString());

		} catch (Exception e) {

			System.err.println("Exce In submitInsertLeaveStructure method  " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/addLeaveStructureHeader";

	}

	List<LeaveType> leaveTypeList;

	@RequestMapping(value = "/addLeaveStructure", method = RequestMethod.GET)
	public ModelAndView addLeaveStructure(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			tempDetailList = new ArrayList<LeaveStructureDetails>();

			model = new ModelAndView("leave/add_leave_structure");

			LeaveType[] leaveArray = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getLeaveTypeListIsStructure", LeaveType[].class);

			leaveTypeList = new ArrayList<>(Arrays.asList(leaveArray));

			model.addObject("leaveTypeList", leaveTypeList);

			model.addObject("title", "Add Leave Structure");

		} catch (Exception e) {

			System.err.println("exception In addLeaveStructureHeader at leave structure Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertLeaveStructure", method = RequestMethod.POST)
	public String insertLeaveStructure(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.err.println("Inside insert submitInsertLeaveStructure method");
			HttpSession session = request.getSession();
			String lvsName = request.getParameter("lvsName");

			Boolean ret = false;

			if (FormValidation.Validaton(lvsName, "") == true) {

				ret = true;
				System.out.println("lvsName" + ret);
			}

			if (ret == false) {

				LeaveStructureHeader head = new LeaveStructureHeader();

				head.setCompanyId(1);
				head.setDelStatus(1);
				head.setIsActive(1);
				head.setLvsName(lvsName);
				head.setMakerDatetime(dateTime);
				head.setMakerUserId(1);

				List<LeaveStructureDetails> detailList = new ArrayList<>();
				for (int i = 0; i < leaveTypeList.size(); i++) {

					LeaveStructureDetails detail = new LeaveStructureDetails();
					detail.setDelStatus(1);
					detail.setExInt1(1);
					detail.setExInt2(1);
					detail.setExVar1("NA");
					detail.setExVar2("NA");
					detail.setIsActive(1);
					int noOfLeaves = 0;
					try {
						detail.setLvsAllotedLeaves(Integer
								.parseInt(request.getParameter("noOfLeaves" + leaveTypeList.get(i).getLvTypeId())));

					} catch (Exception e) {
						detail.setLvsAllotedLeaves(noOfLeaves);
					}

					detail.setLvTypeId(leaveTypeList.get(i).getLvTypeId());
					detail.setMakerUserId(1);
					detail.setMakerDatetime(dateTime);
					detailList.add(detail);
				}

				head.setDetailList(detailList);

				LeaveStructureHeader res = Constants.getRestTemplate()
						.postForObject(Constants.url + "saveLeaveStruture", head, LeaveStructureHeader.class);
				System.out.println("docInsertRes" + res.toString());

				if (res != null) {
					session.setAttribute("successMsg", "Record Insert Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}
		} catch (Exception e) {

			System.err.println("Exce In submitInsertLeaveStructure method  " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showLeaveStructureList";

	}

	@RequestMapping(value = "/showLeaveStructureList", method = RequestMethod.GET)
	public ModelAndView showLeaveStructureList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leave_structure_list");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			LeaveStructureHeader[] summary = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getStructureList", map, LeaveStructureHeader[].class);

			List<LeaveStructureHeader> leaveSummarylist = new ArrayList<>(Arrays.asList(summary));

			for (int i = 0; i < leaveSummarylist.size(); i++) {

				leaveSummarylist.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(leaveSummarylist.get(i).getLvsId())));
			}

			model.addObject("lvStructureList", leaveSummarylist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	LeaveStructureHeader editStructure = new LeaveStructureHeader();

	@RequestMapping(value = "/editLeaveStructure", method = RequestMethod.GET)
	public ModelAndView editLeaveStructure(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/edit_leave_structure");

		try {
			String base64encodedString = request.getParameter("lvsId");
			String lvsId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("lvsId", lvsId);
			editStructure = Constants.getRestTemplate().postForObject(Constants.url + "/getStructureById", map,
					LeaveStructureHeader.class);
			model.addObject("editStructure", editStructure);

			model.addObject("editStructureDetail", editStructure.getDetailList());

			LeaveType[] leaveArray = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getLeaveTypeListIsStructure", LeaveType[].class);

			leaveTypeList = new ArrayList<>(Arrays.asList(leaveArray));

			model.addObject("leaveTypeList", leaveTypeList);

			System.out.println("editStructure" + editStructure.toString());
			System.out.println("editStructureDetail" + editStructure.getDetailList().toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/deleteLeaveStructure", method = RequestMethod.GET)
	public String deleteLeaveStructure(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("lvsId");
			String lvsId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("lvsId", lvsId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteLeaveStructure", map,
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
		return "redirect:/showLeaveStructureList";
	}

	@RequestMapping(value = "/editInsertLeaveStructure", method = RequestMethod.POST)
	public String editInsertLeaveStructure(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.err.println("Inside insert editInsertLeaveStructure method");
			HttpSession session = request.getSession();
			String lvsName = request.getParameter("lvsName");

			Boolean ret = false;

			if (FormValidation.Validaton(lvsName, "") == true) {

				ret = true;
				System.out.println("lvsName" + ret);
			}

			if (ret == false) {

				editStructure.setLvsName(lvsName);

				List<LeaveStructureDetails> detailList = new ArrayList<>();

				for (int i = 0; i < editStructure.getDetailList().size(); i++) {

					int noOfLeaves = 0;
					try {
						editStructure.getDetailList().get(i).setLvsAllotedLeaves(Integer.parseInt(request
								.getParameter("noOfLeaves" + editStructure.getDetailList().get(i).getLvTypeId())));

					} catch (Exception e) {
						editStructure.getDetailList().get(i).setLvsAllotedLeaves(noOfLeaves);
					}

					detailList.add(editStructure.getDetailList().get(i));
				}

				editStructure.setDetailList(detailList);

				LeaveStructureHeader res = Constants.getRestTemplate()
						.postForObject(Constants.url + "saveLeaveStruture", editStructure, LeaveStructureHeader.class);
				System.out.println("docInsertRes" + res.toString());

				if (res != null) {
					session.setAttribute("successMsg", "Record Insert Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}
		} catch (Exception e) {

			System.err.println("Exce In editInsertLeaveStructure method  " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/showLeaveStructureList";

	}

	@RequestMapping(value = "/leaveStructureAllotment", method = RequestMethod.GET)
	public ModelAndView leaveStructureAllotment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("leave/leave_structure_allot_list");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			LeaveStructureHeader[] lvStrSummery = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getStructureList", map, LeaveStructureHeader[].class);

			List<LeaveStructureHeader> lSummarylist = new ArrayList<>(Arrays.asList(lvStrSummery));
			model.addObject("lStrList", lSummarylist);
			map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			map.add("locIdList", 1);
			GetStructureAllotment[] summary = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getStructureAllotmentList", map, GetStructureAllotment[].class);

			List<GetStructureAllotment> leaveSummarylist = new ArrayList<>(Arrays.asList(summary));

			model.addObject("lvStructureList", leaveSummarylist);
			System.out.println("leaveSummarylist" + leaveSummarylist.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitStructureList", method = RequestMethod.POST)
	public String submitStructureList(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			int lvsId = Integer.parseInt(request.getParameter("lvsId"));

			String[] empIds = request.getParameterValues("empIds");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < empIds.length; i++) {
				sb = sb.append(empIds[i] + ",");

			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);

			String[] arrOfStr = items.split(",");
			LeavesAllotment leavesAllotment = new LeavesAllotment();
			for (int i = 0; i < items.length(); i++) {
				for (int j = 0; j < arrOfStr.length; j++) {

					leavesAllotment.setCalYrId(1);

					leavesAllotment.setDelStatus(1);
					leavesAllotment.setEmpId(Integer.parseInt(arrOfStr[j]));
					leavesAllotment.setExInt1(1);
					leavesAllotment.setExInt2(1);
					leavesAllotment.setExInt3(1);
					leavesAllotment.setExVar1("NA");
					leavesAllotment.setExVar2("NA");
					leavesAllotment.setExVar3("NA");
					leavesAllotment.setIsActive(1);
					leavesAllotment.setMakerUserId(1);
					leavesAllotment.setMakerEnterDatetime(dateTime);
					leavesAllotment.setLvsId(lvsId);

				}

				LeavesAllotment res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLeaveAllotment",
						leavesAllotment, LeavesAllotment.class);

				if (res != null) {
					session.setAttribute("successMsg", "Record Insert Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/leaveStructureAllotment";
	}

}
