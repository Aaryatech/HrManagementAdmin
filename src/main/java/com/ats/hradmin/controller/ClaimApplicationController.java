package com.ats.hradmin.controller;

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

import com.ats.hradmin.claim.ClaimApply;
import com.ats.hradmin.claim.ClaimTrail;
import com.ats.hradmin.claim.ClaimType;
import com.ats.hradmin.claim.GetClaimApplyAuthwise;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.leave.model.GetAuthorityIds;
import com.ats.hradmin.leave.model.GetLeaveApplyAuthwise;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveTrail;
import com.ats.hradmin.model.LoginResponse;
import com.ats.hradmin.model.ProjectHeader;

@Controller
@Scope("session")
public class ClaimApplicationController {
	
	
	
	@RequestMapping(value = "/showApplyForClaim", method = RequestMethod.GET)
	public ModelAndView showEmpList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/applyForClaim");

		try {
			
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			
			
			map.add("companyId", userObj.getCompanyId());
			map.add("empId",userObj.getEmpId());
			
			GetEmployeeInfo[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoClaimAuthWise", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeDepartmentlist = new ArrayList<GetEmployeeInfo>(
					Arrays.asList(employeeDepartment));
		
			
			int flag=1;
			
		 map = new LinkedMultiValueMap<>();
			map.add("empId", userObj.getEmpId());
			
			GetEmployeeInfo  editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/GetEmployeeInfo", map,
					GetEmployeeInfo.class);
			model.addObject("editEmp", editEmp);
			for (int i = 0; i < employeeDepartmentlist.size(); i++) {
				if(employeeDepartmentlist.get(i).getEmpId()==userObj.getEmpId()) {
					flag=0;
					System.err.println(" matched");
					break;
				}
				
			}
			if(flag == 1) {
				System.err.println("not matched");
				GetEmployeeInfo temp =new GetEmployeeInfo();
				temp.setCompanyId(editEmp.getCompanyId());
				temp.setCompanyName(editEmp.getCompanyName());
				temp.setEmpCategory(editEmp.getEmpCategory());
				temp.setEmpCatId(editEmp.getEmpCatId());
				temp.setEmpCode(editEmp.getEmpCode());
				temp.setEmpDept(editEmp.getEmpDept());
				temp.setEmpDeptId(editEmp.getEmpDeptId());
				temp.setEmpEmail(editEmp.getEmpEmail());
				temp.setEmpFname(editEmp.getEmpFname());
				temp.setEmpId(editEmp.getEmpId());
				temp.setEmpMname(editEmp.getEmpMname());
				temp.setEmpMobile1(editEmp.getEmpMobile1());
				temp.setEmpPrevExpYrs(editEmp.getEmpPrevExpYrs());
				temp.setEmpSname(editEmp.getEmpSname());
				temp.setEmpType(editEmp.getEmpType());
				temp.setEmpTypeId(editEmp.getEmpTypeId());
				employeeDepartmentlist.add(temp);
			}
			
			

			for (int i = 0; i < employeeDepartmentlist.size(); i++) {
						//System.out.println("employeeDepartmentlist.get(i).getEmpId()"+employeeDepartmentlist.get(i).getEmpId());
				employeeDepartmentlist.get(i).setExVar1(
						FormValidation.Encrypt(String.valueOf(employeeDepartmentlist.get(i).getEmpId())));
			}

			model.addObject("empList", employeeDepartmentlist);
			System.err.println("emp list is  "+employeeDepartment.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	@RequestMapping(value = "/showClaimApply", method = RequestMethod.GET)
	public ModelAndView showClaimApply(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claimApply");

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", userObj.getCompanyId());
			
			
			ClaimType[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url +
					 "/getClaimListByCompanyId",map, ClaimType[].class);
					
			 List<ClaimType> claimTypeList = new ArrayList<ClaimType>(Arrays.asList(employeeDoc));
			 System.out.println("claimTypeList list "+claimTypeList.toString()); 
			 model.addObject("claimTypeList",claimTypeList);
			 
			 String base64encodedString = request.getParameter("empId");
				String empId = FormValidation.DecodeKey(base64encodedString);
				 map = new LinkedMultiValueMap<>();
				map.add("empId", empId);
				
				EmployeeInfo  editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpInfoById", map,
						EmployeeInfo.class);
				model.addObject("editEmp", editEmp);
				 System.out.println("empInfo list "+editEmp.toString()); 
				
				
				map = new LinkedMultiValueMap<>();
				map.add("companyId", userObj.getCompanyId());
				ProjectHeader[] employeeDoc1 = Constants.getRestTemplate().postForObject(Constants.url +
						 "/getProjectsListByCompanyId",map, ProjectHeader[].class);
				 List<ProjectHeader> proTypeList = new ArrayList<ProjectHeader>(Arrays.asList(employeeDoc1));
				 System.out.println("proTypeList list "+proTypeList.toString()); 
				 model.addObject("projectTypeList",proTypeList);
				
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "/insertSubmitClaim", method = RequestMethod.POST)
	public String submitInsertLeave(HttpServletRequest request,	HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
         
			//String compName = request.getParameter("1");
			String claimDate = request.getParameter("claimDate");
			int projectTypeId =Integer.parseInt( request.getParameter("projectTypeId"));
			int claimTypeId =Integer.parseInt( request.getParameter("claimTypeId"));
			int claimAmt = Integer.parseInt( request.getParameter("claimAmt"));
			int empId = Integer.parseInt( request.getParameter("empId"));
			
			//get Authority ids 
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);
			map.add("companyId", userObj.getCompanyId());
			
			GetAuthorityIds  editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getClaimAuthIds", map,
					GetAuthorityIds.class);
			
			
			int stat=0;
			if(editEmp.getFinAuthEmpId()==userObj.getEmpId()) {
				stat=3;
			}
			else if(editEmp.getIniAuthEmpId()==userObj.getEmpId()) {
				stat=2;
			}
			else {
				stat=1;
			}
			System.out.println("stat is "+stat);
			String remark=null;
			
			
			//System.out.println("dayType" + dayTypeName);
			
			
			try {
			 remark =  request.getParameter("claimRemark");
			}
			catch (Exception e) {
				 remark =  "NA";
			}

			Boolean ret = false;
			
			if (FormValidation.Validaton(claimDate, "") == true) {

				ret = true;
				System.out.println("claimDate" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("projectTypeId"), "") == true) {

				ret = true;
				System.out.println("projectTypeId" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("claimTypeId"), "") == true) {

				ret = true;
				System.out.println("claimTypeId" + ret);
			}
			
			if (FormValidation.Validaton(request.getParameter("claimAmt"), "") == true) {

				ret = true;
				System.out.println("claimAmt" + ret);
			}
			
			
			if(ret == false)
			{
				
		ClaimApply leaveSummary = new ClaimApply();

			leaveSummary.setEmpId(empId);
			leaveSummary.setClaimAmount(claimAmt);
			leaveSummary.setClaimDate(DateConvertor.convertToYMD(claimDate));
			leaveSummary.setClaimFinalStatus(stat);
			leaveSummary.setClaimRemarks(remark);
			leaveSummary.setClaimTypeId(claimTypeId);
			leaveSummary.setProjectId(projectTypeId);
			leaveSummary.setCirculatedTo("1");
			
			leaveSummary.setExInt1(stat);
			leaveSummary.setExInt2(1);
			leaveSummary.setExInt3(1);
			leaveSummary.setExVar1("NA");
			leaveSummary.setExVar2("NA");
			leaveSummary.setExVar3("NA");
			leaveSummary.setIsActive(1);
			leaveSummary.setDelStatus(1);
			leaveSummary.setMakerUserId(userObj.getUserId());
			leaveSummary.setMakerEnterDatetime(sf.format(date));

			

			ClaimApply res = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimApply", leaveSummary,
					ClaimApply.class);
			
			
			if(res!=null) {
				ClaimTrail lt = new ClaimTrail();
				
				lt.setEmpRemarks(remark);;
				lt.setClaimId(res.getClaimId());
				lt.setClaimStatus(stat);
				lt.setEmpId(empId);
				lt.setExInt1(1);
				lt.setExInt2(1);
				lt.setExInt3(1);
				lt.setExVar1("NA");
				lt.setExVar2("NA");
				lt.setExVar3("NA");
				
				lt.setMakerUserId(userObj.getUserId());
				lt.setMakerEnterDatetime(sf.format(date));
				

				ClaimTrail res1 = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimTrail", lt,
						ClaimTrail.class);
				if(res1!=null) {
					
				 map = new LinkedMultiValueMap<>();
				map.add("claimId", res.getClaimId());
				map.add("trailId", res1.getClaimTrailPkey());
				Info info = Constants.getRestTemplate().postForObject(Constants.url + "/updateClaimTrailId", map, Info.class);


				}
			}
			
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showApplyForClaim";
	

}
	
	////////////////////////////////**********************Claim approvals******************************//////////////////
	
	
	
	@RequestMapping(value = "/showClaimApprovalByAuthority", method = RequestMethod.GET)
	public ModelAndView showClaimApprovalByAuthority(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claimApprovalByAuthorities");
		
		//for pending 
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId",userObj.getEmpId());
			
			
			GetClaimApplyAuthwise[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url +
					 "/getClaimApplyListForPending",map, GetClaimApplyAuthwise[].class);
					
			 List<GetClaimApplyAuthwise> claimList = new ArrayList<GetClaimApplyAuthwise>(Arrays.asList(employeeDoc));
			 System.out.println("lv claimList list pending "+claimList.toString()); 
			
				for (int i = 0; i < claimList.size(); i++) {

					claimList.get(i).setCirculatedTo(FormValidation.Encrypt(String.valueOf(claimList.get(i).getClaimId())));
					claimList.get(i).setClaimRemarks(FormValidation.Encrypt(String.valueOf(claimList.get(i).getEmpId())));

				}
				 model.addObject("claimListForApproval",claimList);
				 model.addObject("list1Count",claimList.size());
				
	//for Info	
				 
				 
				 
		 model.addObject("empIdOrig",userObj.getEmpId());
				 
		 map = new LinkedMultiValueMap<>();
			map.add("empId",userObj.getEmpId());
		
			GetClaimApplyAuthwise[] employeeDoc1 = Constants.getRestTemplate().postForObject(Constants.url +
						 "/getClaimApplyListForInformation",map, GetClaimApplyAuthwise[].class);
						
			List<GetClaimApplyAuthwise> claimList1 = new ArrayList<GetClaimApplyAuthwise>(Arrays.asList(employeeDoc1));
		System.out.println("lv leaveList list1 info "+claimList1.toString()); 
			for (int i = 0; i < claimList1.size(); i++) {

				claimList1.get(i).setCirculatedTo(FormValidation.Encrypt(String.valueOf(claimList1.get(i).getClaimId())));
				claimList1.get(i).setClaimRemarks(FormValidation.Encrypt(String.valueOf(claimList1.get(i).getEmpId())));

			}
			
			model.addObject("list2Count",claimList1.size());
			model.addObject("claimListForApproval1",claimList1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	

	@RequestMapping(value = "/approveClaimByAuth", method = RequestMethod.GET)
	public String insertLeave(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			
			int  empId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("empId")));
			int claimId=Integer.parseInt(FormValidation.DecodeKey(request.getParameter("claimId")));
			String stat=request.getParameter("stat");
			
	       System.err.println("link data :::"+empId+claimId+stat);
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("claimId", claimId);
			map.add("status",stat);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/updateClaimStatus", map, Info.class);

			if(info.isError()==false) {
				ClaimTrail lt = new ClaimTrail();
				
				
				lt.setEmpRemarks("null");
			
				lt.setClaimId(claimId);;
				
				lt.setClaimStatus(Integer.parseInt(stat));
				lt.setEmpId(empId);
				lt.setExInt1(1);
				lt.setExInt2(1);
				lt.setExInt3(1);
				lt.setExVar1("NA");
				lt.setExVar2("NA");
				lt.setExVar3("NA");
				
				lt.setMakerUserId(userObj.getUserId());
				lt.setMakerEnterDatetime(sf.format(date));
				

				ClaimTrail res1 = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimTrail", lt,
						ClaimTrail.class);
				if(res1!=null) {
					
				 map = new LinkedMultiValueMap<>();
				map.add("claimId",claimId);
				map.add("trailId", res1.getClaimTrailPkey());
				Info info1 = Constants.getRestTemplate().postForObject(Constants.url + "/updateClaimTrailId", map, Info.class);


				}
			}
			
			 else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showClaimApprovalByAuthority";
	

}
	
	
	
	
	
	
	
	
	
	
	

}
