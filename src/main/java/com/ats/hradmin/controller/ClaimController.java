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

import com.ats.hradmin.claim.ClaimAuthority;
import com.ats.hradmin.claim.ClaimType;
import com.ats.hradmin.claim.GetClaimAuthority;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.GetLeaveAuthority;
import com.ats.hradmin.leave.model.Holiday;
import com.ats.hradmin.leave.model.LeaveAuthority;
import com.ats.hradmin.model.Customer;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveSummary;
import com.ats.hradmin.model.LeaveType;
import com.ats.hradmin.model.Location;
import com.ats.hradmin.model.LoginResponse;

@Controller
@Scope("session")
public class ClaimController {

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);
	ClaimType editClaimType = new ClaimType();
	Customer editCust = new Customer();

	// ************************************Customer***************************
	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	public ModelAndView addCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/cust_add");

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertCustomer", method = RequestMethod.POST)
	public String submitInsertCustomer(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			String custName = request.getParameter("custName");

			String remark = null;
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(custName, "") == true) {

				ret = true;

			}

			if (ret == false) {

				Customer save = new Customer();
				save.setCustName(custName);
				save.setCustRemarks(remark);
				save.setCustType("1");
				save.setCompanyId(userObj.getCompanyId());

				save.setDelStatus(1);
				save.setIsActive(1);
				save.setMakerUserId(userObj.getUserId());
				save.setMakerEnterDatetime(dateTime);

				Customer res = Constants.getRestTemplate().postForObject(Constants.url + "/saveCustomer", save,
						Customer.class);

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
			session.setAttribute("errorMsg", "Failed to Insert Record");
		}

		return "redirect:/showCustomerList";

	}

	@RequestMapping(value = "/showCustomerList", method = RequestMethod.GET)
	public ModelAndView showCustomerList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/cust_list");

		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			Customer[] custListArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getCustListByCompanyId", map, Customer[].class);

			List<Customer> custlist = new ArrayList<Customer>(Arrays.asList(custListArray));

			for (int i = 0; i < custlist.size(); i++) {

				custlist.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(custlist.get(i).getCustId())));
			}

			model.addObject("custlist", custlist);

			System.out.println("" + custlist.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	// cust_edit

	@RequestMapping(value = "/editCustomer", method = RequestMethod.GET)
	public ModelAndView editCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/cust_edit");

		try {

			String base64encodedString = request.getParameter("custId");
			String custId = FormValidation.DecodeKey(base64encodedString);
			// System.out.println("claimTypeId" + claimTypeId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("custId", custId);
			editCust = Constants.getRestTemplate().postForObject(Constants.url + "/getCustByCustId", map,
					Customer.class);
			model.addObject("editCust", editCust);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditCustomer", method = RequestMethod.POST)
	public String submitEditCustomer(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			String custName = request.getParameter("custName");

			String remark = null;
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(custName, "") == true) {

				ret = true;

			}

			if (ret == false) {
				editCust.setCustName(custName);
				editCust.setCustRemarks(remark);

				Customer res = Constants.getRestTemplate().postForObject(Constants.url + "/saveCustomer", editCust,
						Customer.class);

				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Update Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Update Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Update Record");
		}

		return "redirect:/showCustomerList";
	}

	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.GET)
	public String deleteCustomer(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("custId");
			String custId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("custId", custId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteCustomer", map, Info.class);

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

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			String calimTypeTitle = request.getParameter("calimTypeTitle");
			String claimShortTypeTitle = request.getParameter("claimShortTypeTitle");

			String claimTypeColor = request.getParameter("claimColor");
			String remark = null;
			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(calimTypeTitle, "") == true) {

				ret = true;

			}
			if (FormValidation.Validaton(claimShortTypeTitle, "") == true) {

				ret = true;

			}

			if (FormValidation.Validaton(request.getParameter("claimColor"), "") == true) {

				ret = true;

			}

			if (ret == false) {

				ClaimType save = new ClaimType();
				save.setClaimTypeColor(claimTypeColor);
				save.setClaimTypeRemarks(remark);
				save.setClaimTypeTitle(calimTypeTitle);
				save.setClaimTypeTitleShort(claimShortTypeTitle);
				save.setCompanyId(userObj.getCompanyId());

				save.setDelStatus(1);
				save.setIsActive(1);
				save.setMakerUserId(userObj.getUserId());
				save.setMakerEnterDatetime(dateTime);

				ClaimType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimType", save,
						ClaimType.class);

				System.out.println(res.toString());

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

		return "redirect:/showClaimTypeList";

	}

	@RequestMapping(value = "/showClaimTypeList", method = RequestMethod.GET)
	public ModelAndView showClaimTypeList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_type_list");

		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			ClaimType[] claimTypeListArray = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getClaimListByCompanyId", map, ClaimType[].class);

			List<ClaimType> claimTypelist = new ArrayList<ClaimType>(Arrays.asList(claimTypeListArray));

			for (int i = 0; i < claimTypelist.size(); i++) {

				claimTypelist.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(claimTypelist.get(i).getClaimTypeId())));
			}

			model.addObject("claimTypelist", claimTypelist);

			// System.out.println("" + claimTypelist.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/editClaimType", method = RequestMethod.GET)
	public ModelAndView editClaimType(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_type_edit");

		try {

			String base64encodedString = request.getParameter("claimTypeId");
			String claimTypeId = FormValidation.DecodeKey(base64encodedString);
			// System.out.println("claimTypeId" + claimTypeId);

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

			// System.out.println("color " + claimTypeColor);

			Boolean ret = false;

			if (FormValidation.Validaton(calimTypeTitle, "") == true) {

				ret = true;
				// System.out.println("calimTypeTitle" + ret);
			}
			if (FormValidation.Validaton(claimShortTypeTitle, "") == true) {

				ret = true;
				// System.out.println("add" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("claimColor"), "") == true) {

				ret = true;
				// System.out.println("add" + ret);
			}

			if (ret == false) {

				editClaimType.setClaimTypeColor(claimTypeColor);
				editClaimType.setClaimTypeRemarks(remark);
				editClaimType.setClaimTypeTitle(calimTypeTitle);
				editClaimType.setClaimTypeTitleShort(claimShortTypeTitle);

				ClaimType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimType",
						editClaimType, ClaimType.class);
				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Update Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Update Record");
				}

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

	@RequestMapping(value = "/addClaimAuthority", method = RequestMethod.GET)
	public ModelAndView addClaimAuthority(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_authority_add");

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			map.add("locIdList", userObj.getLocId());

			GetEmployeeInfo[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoList", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeDepartmentlist = new ArrayList<GetEmployeeInfo>(
					Arrays.asList(employeeDepartment));

			model.addObject("empList", employeeDepartmentlist);

			map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			GetEmployeeInfo[] empInfoError = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoListForClaimAuth", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeInfo = new ArrayList<>(Arrays.asList(empInfoError));
			model.addObject("empListAuth", employeeInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitClaimAuthorityList", method = RequestMethod.POST)
	public String submitClaimAuthorityList(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			int iniAuthEmpId = Integer.parseInt(request.getParameter("iniAuthEmpId"));

			int finAuthEmpId = Integer.parseInt(request.getParameter("finAuthEmpId"));

			String[] empIds = request.getParameterValues("empIds");
			String[] repToEmpIds = request.getParameterValues("repToEmpIds");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < empIds.length; i++) {
				sb = sb.append(empIds[i] + ",");

			}
			String empIdList = sb.toString();
			empIdList = empIdList.substring(0, empIdList.length() - 1);

			sb = new StringBuilder();

			for (int i = 0; i < repToEmpIds.length; i++) {
				sb = sb.append(repToEmpIds[i] + ",");

			}
			String repToEmpIdsList = sb.toString();
			repToEmpIdsList = repToEmpIdsList.substring(0, repToEmpIdsList.length() - 1);

			String[] arrOfStr = empIdList.split(",");
			ClaimAuthority claim = new ClaimAuthority();

			for (int j = 0; j < arrOfStr.length; j++) {

				claim.setDelStatus(1);
				claim.setEmpId(Integer.parseInt(arrOfStr[j]));

				claim.setExVar1("NA");
				claim.setExVar2("NA");
				claim.setExVar3("NA");
				claim.setIsActive(1);
				claim.setMakerUserId(userObj.getUserId());
				claim.setMakerEnterDatetime(dateTime);
				claim.setCaIniAuthEmpId(iniAuthEmpId);
				claim.setCaFinAuthEmpId(finAuthEmpId);
				claim.setCompanyId(userObj.getCompanyId());
				claim.setCaRepToEmpIds(repToEmpIdsList);

				ClaimAuthority res = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimAuthority",
						claim, ClaimAuthority.class);

				if (res != null) {
					session.setAttribute("successMsg", "Record Insert Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/claimAuthorityList";
	}

	@RequestMapping(value = "/claimAuthorityList", method = RequestMethod.GET)
	public ModelAndView claimAuthorityList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_authority_list");

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());

			GetClaimAuthority[] empInfoError = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getClaimAuthorityList", map, GetClaimAuthority[].class);

			List<GetClaimAuthority> empLeaveAuth = new ArrayList<>(Arrays.asList(empInfoError));

			for (int i = 0; i < empLeaveAuth.size(); i++) {

				empLeaveAuth.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(empLeaveAuth.get(i).getEmpId())));
			}

			model.addObject("empLeaveAuth", empLeaveAuth);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	ClaimAuthority claimAuthority = new ClaimAuthority();

	@RequestMapping(value = "/editClaimAuthority", method = RequestMethod.GET)
	public ModelAndView editClaimAuthority(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_authority_edit");

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			String base64encodedString = request.getParameter("empId");
			String empId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			map.add("locIdList", userObj.getLocationIds());

			GetEmployeeInfo[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoList", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeDepartmentlist = new ArrayList<GetEmployeeInfo>(
					Arrays.asList(employeeDepartment));

			model.addObject("empList", employeeDepartmentlist);

			map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			map.add("empIdList", empId);
			GetEmployeeInfo[] empInfoError = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoListByEmpIdList", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeInfo = new ArrayList<>(Arrays.asList(empInfoError));
			model.addObject("empListAuth", employeeInfo);

			model.addObject("empIdForEdit", empId);

			map = new LinkedMultiValueMap<>();
			map.add("empId", empId);
			claimAuthority = Constants.getRestTemplate().postForObject(Constants.url + "/getClaimAuthorityListByEmpId",
					map, ClaimAuthority.class);
			model.addObject("claimAuthority", claimAuthority);

			List<Integer> reportingIdList = Stream.of(claimAuthority.getCaRepToEmpIds().split(","))
					.map(Integer::parseInt).collect(Collectors.toList());

			model.addObject("reportingIdList", reportingIdList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/editSubmitClaimAuthorityList", method = RequestMethod.POST)
	public String editSubmitClaimAuthorityList(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			int iniAuthEmpId = Integer.parseInt(request.getParameter("iniAuthEmpId"));

			int finAuthEmpId = Integer.parseInt(request.getParameter("finAuthEmpId"));

			String[] repToEmpIds = request.getParameterValues("repToEmpIds");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < repToEmpIds.length; i++) {
				sb = sb.append(repToEmpIds[i] + ",");

			}
			String repToEmpIdsList = sb.toString();
			repToEmpIdsList = repToEmpIdsList.substring(0, repToEmpIdsList.length() - 1);

			claimAuthority.setCaRepToEmpIds(repToEmpIdsList);
			claimAuthority.setCaFinAuthEmpId(finAuthEmpId);
			claimAuthority.setCaIniAuthEmpId(iniAuthEmpId);

			ClaimAuthority res = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimAuthority",
					claimAuthority, ClaimAuthority.class);

			if (res != null) {
				session.setAttribute("successMsg", "Record Update Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Update Record");
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return "redirect:/claimAuthorityList";
	}

}
