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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ats.hradmin.claim.ClaimApply;
import com.ats.hradmin.claim.ClaimDetail;
import com.ats.hradmin.claim.ClaimProof;
import com.ats.hradmin.claim.ClaimTemp;
import com.ats.hradmin.claim.ClaimTrail;
import com.ats.hradmin.claim.ClaimType;
import com.ats.hradmin.claim.GetClaimApplyAuthwise;
import com.ats.hradmin.claim.GetClaimTrailStatus;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.common.VpsImageUpload;
import com.ats.hradmin.leave.model.GetAuthorityIds;
import com.ats.hradmin.leave.model.GetLeaveApplyAuthwise;
import com.ats.hradmin.leave.model.GetLeaveStatus;
import com.ats.hradmin.model.AuthorityInformation;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LoginResponse;
import com.ats.hradmin.model.ProjectHeader;

@Controller
@Scope("session")
public class ClaimApplicationController {
	
	ClaimTemp ct=new ClaimTemp();
	ArrayList<String> a =new ArrayList<String>();
	List<MultipartFile> imgList=new ArrayList<MultipartFile>();
	List<ClaimProof> proofList=new ArrayList<ClaimProof>();
	
	@RequestMapping(value = "/showApplyForClaim", method = RequestMethod.GET)
	public ModelAndView showEmpList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/applyForClaim");

		try {
			GetEmployeeInfo temp =new GetEmployeeInfo();
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
					employeeDepartmentlist.remove(i);
					break;
				}
				
			}
		/*	if(flag == 1) {*/
				System.err.println("not matched");
			
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
				temp.setEmpRatePerhr(editEmp.getEmpRatePerhr());
				temp.setEmpSname(editEmp.getEmpSname());
				temp.setEmpType(editEmp.getEmpType());
				temp.setEmpTypeId(editEmp.getEmpTypeId());
				temp.setEmpDeptShortName(editEmp.getEmpDeptShortName());
				temp.setEmpCatShortName(editEmp.getEmpCatShortName());
				temp.setEmpTypeShortName(editEmp.getEmpTypeShortName());
				//employeeDepartmentlist.add(temp);
			/* } */
			
				temp.setExVar1(FormValidation.Encrypt(String.valueOf(editEmp.getEmpId())));
				System.err.println("temp list is claim  " + temp.toString());
			for (int i = 0; i < employeeDepartmentlist.size(); i++) {
						//System.out.println("employeeDepartmentlist.get(i).getEmpId()"+employeeDepartmentlist.get(i).getEmpId());
				employeeDepartmentlist.get(i).setExVar1(
						FormValidation.Encrypt(String.valueOf(employeeDepartmentlist.get(i).getEmpId())));
			}

			model.addObject("empList", employeeDepartmentlist);
			System.err.println("emp list is  "+employeeDepartment.toString());
			model.addObject("tempList", temp);
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
				 map = new LinkedMultiValueMap<String, Object>();
					map.add("empId", empId);

					AuthorityInformation authorityInformation = Constants.getRestTemplate()
							.postForObject(Constants.url + "/getAuthorityInfoByEmpId", map, AuthorityInformation.class);
					model.addObject("authorityInformation", authorityInformation);

					System.err.println("authorityInformation is  " + authorityInformation.toString());
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	@RequestMapping(value = "/insertSubmitClaim", method = RequestMethod.POST)
	public String submitInsertLeave(HttpServletRequest request,	HttpServletResponse response) {
		String empId1=request.getParameter("empId");
		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

		//return "redirect:/showApplyForClaim";
		return "redirect:/showClaimList?empId="+FormValidation.Encrypt(empId1);
	

}
	
	////////////////////////////////**********************Claim Approvals******************************//////////////////
	
	
	
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
			
				for (int i = 0; i < claimList.size(); i++) {

					claimList.get(i).setCirculatedTo(FormValidation.Encrypt(String.valueOf(claimList.get(i).getClaimId())));
					claimList.get(i).setClaimRemarks(FormValidation.Encrypt(String.valueOf(claimList.get(i).getEmpId())));
					//claimList.get(i).setClaimDate(DateConvertor.convertToDMY(claimList.get(i).getClaimDate()));
					
				}
				 model.addObject("claimListForApproval",claimList);
				 model.addObject("list1Count",claimList.size());
				 System.out.println("lv claimList list pending "+claimList.toString()); 

	//for Info	
				 
		 model.addObject("empIdOrig",userObj.getEmpId());
				 
		 map = new LinkedMultiValueMap<>();
			map.add("empId",userObj.getEmpId());
		
			GetClaimApplyAuthwise[] employeeDoc1 = Constants.getRestTemplate().postForObject(Constants.url +
						 "/getClaimApplyListForInformation",map, GetClaimApplyAuthwise[].class);
						
			List<GetClaimApplyAuthwise> claimList1 = new ArrayList<GetClaimApplyAuthwise>(Arrays.asList(employeeDoc1));
		
			for (int i = 0; i < claimList1.size(); i++) {

				claimList1.get(i).setCirculatedTo(FormValidation.Encrypt(String.valueOf(claimList1.get(i).getClaimId())));
				claimList1.get(i).setClaimRemarks(FormValidation.Encrypt(String.valueOf(claimList1.get(i).getEmpId())));
				//claimList1.get(i).setClaimDate(DateConvertor.convertToDMY(claimList.get(i).getClaimDate()));
			}
			
			model.addObject("list2Count",claimList1.size());
			model.addObject("claimListForApproval1",claimList1);
			System.out.println("lv leaveList list1 info "+claimList1.toString()); 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/approveClaimByAuth", method = RequestMethod.GET)
	public ModelAndView approveLeaveByInitialAuth(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("claim/claimApprovalRemark");
		try {

			
			int empId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("empId")));
			int claimId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("claimId")));
			String stat = request.getParameter("stat");
			
			model.addObject("empId",empId);
			model.addObject("claimId", claimId);
			model.addObject("stat", stat);
			System.out.println("alim id is "+claimId);
			
			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("claimId",claimId);
			  GetClaimTrailStatus[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpClaimInfoListByTrailEmpId", map,GetClaimTrailStatus[].class);
			  
			  List<GetClaimTrailStatus> employeeList = new
			  ArrayList<GetClaimTrailStatus>(Arrays.asList(employeeDoc));
		      model.addObject("employeeList",employeeList);
		      
		      
		      MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
		      map1.add("claimId",claimId);

			 GetClaimApplyAuthwise lvEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getClaimApplyDetailsByClaimId", map1,
					 GetClaimApplyAuthwise.class);
			// lvEmp.setClaimDate(DateConvertor.convertToDMY(lvEmp.getClaimDate()));
				model.addObject("lvEmp", lvEmp);
				System.out.println("emp leave details"+lvEmp.toString());
		      
			
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}

	@RequestMapping(value = "/approveClaimByAuth1", method = RequestMethod.POST)
	public String insertLeave(HttpServletRequest request,HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			  System.err.println("emp data :::"+request.getParameter("empId"));
			int  empId = Integer.parseInt((request.getParameter("empId")));
			int claimId=Integer.parseInt((request.getParameter("claimId")));
			String stat=request.getParameter("stat");
			int stat1=Integer.parseInt(stat);
			
	           String msg=null;
			if(stat1==2 ||  stat1==3) {
	            msg="Approved";
                }else if(stat1==8 ||  stat1==9) {
            	 msg="Rejected";
               }else if(stat1==7){
            	   msg="Cancelled";
                   }
			
			String remark = null;
			try {
				 remark =  request.getParameter("remark");
				}
				catch (Exception e) {
					 remark =  "NA";
				}
	       System.err.println("link data :::"+empId+claimId+stat);
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("claimId", claimId);
			map.add("status",stat);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/updateClaimStatus", map, Info.class);

			if(info.isError()==false) {
				ClaimTrail lt = new ClaimTrail();
				
				
				lt.setEmpRemarks(remark);
			
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
				
				if (info1.isError() == false) {
					session.setAttribute("successMsg", "Record "+msg+" Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to "+msg+" Record");
				}

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
	
	
	////////////////////////////////**********************Claim Proof******************************//////////////////

	
	
	@RequestMapping(value = "/showClaimList", method = RequestMethod.GET)
	public ModelAndView showClaimList(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("claim/claimHistory");
		try {

			
			int empId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("empId")));
			System.err.println("emp idis "+empId);
			MultiValueMap<String, Object>  map = new LinkedMultiValueMap<>();
				map.add("empId",empId);
			
			ClaimDetail[] employeeDoc1 = Constants.getRestTemplate().postForObject(Constants.url +
							 "/getClaimListByEmpId",map, ClaimDetail[].class);
							
			List<ClaimDetail> claimList1 = new ArrayList<ClaimDetail>(Arrays.asList(employeeDoc1));			
			System.err.println("claim list"+claimList1.toString());
			
			for (int i = 0; i < claimList1.size(); i++) {

				claimList1.get(i).setCirculatedTo(FormValidation.Encrypt(String.valueOf(claimList1.get(i).getClaimId())));
				claimList1.get(i).setClaimDate(DateConvertor.convertToDMY(claimList1.get(i).getClaimDate()));
				

			}
			
			
			
			 model.addObject("claimList1",claimList1);
			
			
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		  
	}
	//************************************claim new process************************************
	
	
	@RequestMapping(value = "/showClaimProof", method = RequestMethod.POST)
	public ModelAndView addCustomer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claimProof");
		
		try {
			proofList=new ArrayList<ClaimProof>();
			String claimDate = request.getParameter("claimDate");
			int projectTypeId =Integer.parseInt( request.getParameter("projectTypeId"));
			int claimTypeId =Integer.parseInt( request.getParameter("claimTypeId"));
			int claimAmt = Integer.parseInt( request.getParameter("claimAmt"));
			int empId = Integer.parseInt( request.getParameter("empId"));
			String remark=null;
			try {
			  remark=request.getParameter("claimRemark");
			}
			  catch (Exception e){
				  remark=null;
			}
			ct.setEmpId(empId);
			ct.setClaimAmt(claimAmt);
			ct.setClaimDate(claimDate);
			ct.setClaimProjectId(projectTypeId);
 			ct.setClaimTypeId(claimTypeId);
			ct.setClaimRemark(remark);
			System.err.println("temp claim set:::"+ct.toString());
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/uploadOtherMediaProccessForClaim", method = RequestMethod.POST)
	public void uploadOtherMediaProccessForClaim(@RequestParam("file") List<MultipartFile> file, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			 
			String imageName = new String();

			System.out.println("sdfsdfsdf" + file.get(0).getOriginalFilename());
			 VpsImageUpload upload = new VpsImageUpload();
			 
			  imageName = dateTimeInGMT.format(date)+"_"+file.get(0).getOriginalFilename();
			 
			 
				 System.out.println("sdfsdfsdf" + file.get(0).getOriginalFilename());
				imageName = dateTimeInGMT.format(date) + "_" + file.get(0).getOriginalFilename();

				
				ClaimProof company = new ClaimProof();
				 
				company.setIsActive(1);
				company.setDelStatus(1);
				company.setMakerUserId(userObj.getUserId());
				company.setMakerEnterDatetime(sf.format(date));
				
				try {
					upload.saveUploadedImge(file.get(0), Constants.imageSaveUrl, imageName, Constants.values, 0, 0,
							0, 0, 0);
					company.setCpDocPath(imageName);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				proofList.add(company);
			  
				System.err.println("temp claim imaglist:::"+imgList.toString());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return "redirect:/fileUpload";
		// return "{}";

	}

	@RequestMapping(value = "/uploadClaimProof", method = RequestMethod.POST)
	public String uploadClaimProof( HttpServletRequest request, HttpServletResponse response) {

		 
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
		   Date date = new Date();
		   SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		   SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   VpsImageUpload upload = new VpsImageUpload();
		  
			
		  // int claimId = Integer.parseInt(request.getParameter("claimId"));
		
		   
			String remark = request.getParameter("remark");
			
			
			int empId=ct.getEmpId();
			
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
			
			
			ClaimApply leaveSummary = new ClaimApply();

			leaveSummary.setEmpId(ct.getEmpId());
			leaveSummary.setClaimAmount(ct.getClaimAmt());
			leaveSummary.setClaimDate(DateConvertor.convertToYMD(ct.getClaimDate()));
			leaveSummary.setClaimFinalStatus(stat);
			leaveSummary.setClaimRemarks( ct.getClaimRemark());
			leaveSummary.setClaimTypeId(ct.getClaimTypeId());
			leaveSummary.setProjectId(ct.getClaimProjectId());
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
				System.out.println("claim saved success");
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
				System.err.println("trail detail::"+res1.toString());
				
				if(res1.isError()==false) {
					System.out.println("claim trail saved success");	
				 map = new LinkedMultiValueMap<>();
				map.add("claimId", res.getClaimId());
				map.add("trailId", res1.getClaimTrailPkey());
				Info info = Constants.getRestTemplate().postForObject(Constants.url + "/updateClaimTrailId", map, Info.class);


				}
			}
			
			
			/////////////////proof image
           System.err.println("img list final ::"+imgList.toString());
			for(int i=0;i<proofList.size();i++) {
				
				proofList.get(i).setClaimId(res.getClaimId());
 
			}
			List<ClaimProof> res1 = Constants.getRestTemplate().postForObject(Constants.url + "/saveClaimProof", proofList,
					List.class);
			
			System.err.println("res1 claim is"+res1.toString());
		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/showClaimList?empId="+FormValidation.Encrypt(String.valueOf(ct.getEmpId()));
	}
	
	
	
	@RequestMapping(value = "/showClaimProofAgain", method = RequestMethod.GET)
	public ModelAndView showClaimProofAgain(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claimProofAgain");
		int claimId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("claimId")));
		model.addObject("claimId",claimId);

		try {

			MultiValueMap<String, Object>  map = new LinkedMultiValueMap<>();
			map.add("claimId",claimId);
		
			ClaimProof[] employeeDoc1 = Constants.getRestTemplate().postForObject(Constants.url +
						 "/getClaimProofByClaimId",map, ClaimProof[].class);
						
		List<ClaimProof> claimProofList1 = new ArrayList<ClaimProof>(Arrays.asList(employeeDoc1));			
		System.err.println("claimProofList1 list"+claimProofList1.toString());
		for (int i = 0; i < claimProofList1.size(); i++) {

			claimProofList1.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(claimProofList1.get(i).getCpId())));
			claimProofList1.get(i).setExVar2(FormValidation.Encrypt(String.valueOf(claimProofList1.get(i).getClaimId())));

		}
		
		
		 model.addObject("claimProofList1",claimProofList1);
		 model.addObject("fileUrl",Constants.getImageSaveUrl);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/uploadClaimProofAgain", method = RequestMethod.POST)
	public void uploadClaimProof(@RequestParam("file") List<MultipartFile> file, HttpServletRequest request, HttpServletResponse response) {

		 
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
		   Date date = new Date();
		   SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		   SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   VpsImageUpload upload = new VpsImageUpload();
		   System.out.println("sdfsdfsdf" + file.get(0).getOriginalFilename());
			
		   int claimId = Integer.parseInt(request.getParameter("claimId"));
		//	String remark = request.getParameter("remark");

			Boolean ret = false;

			
			if (FormValidation.Validaton(file.get(0).getOriginalFilename(), "") == true) {
				ret = true;
			}

			if (ret == false) {

				ClaimProof company = new ClaimProof();
				
				company.setCpDocRemark(null);
				company.setClaimId(claimId);				
				company.setIsActive(1);
				company.setDelStatus(1);
				company.setMakerUserId(userObj.getUserId());
				company.setMakerEnterDatetime(sf.format(date));
				

				String imageName = new String();
				imageName = dateTimeInGMT.format(date) + "_" + file.get(0).getOriginalFilename();

				try {
					upload.saveUploadedImge(file.get(0), Constants.imageSaveUrl, imageName, Constants.values, 0, 0,
							0, 0, 0);
					company.setCpDocPath(imageName);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				ClaimProof res = Constants.getRestTemplate().postForObject(Constants.url + "/saveSingleClaimProof", company,
						ClaimProof.class);

			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	
      //*************end*****************************
	
	@RequestMapping(value = "/deleteClaimProof", method = RequestMethod.GET)
	public String deletdeleteClaimProofeEmployee(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String claimId1=new String();
		try {
      
			String base64encodedString = request.getParameter("claimProofId");
			String claimProofId = FormValidation.DecodeKey(base64encodedString);
			String base64encodedString1 = request.getParameter("claimId");
			claimId1 = FormValidation.DecodeKey(base64encodedString1);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("cpId", claimProofId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteClaimProof", map, Info.class);
			
			if (info.isError() == false) {
				session.setAttribute("successMsg", "Record Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
			System.out.println("claimProofId " + claimProofId + " claimId1 " + claimId1);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showClaimProof?claimId="+FormValidation.Encrypt(claimId1);
	}
	
	///*******************************History******************************************
	@RequestMapping(value = "/claimDetailHistory", method = RequestMethod.GET)
	public ModelAndView claimDetailHistory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claim_trail_history");

		try {
		
			String base64encodedString = request.getParameter("claimId");			
			String claimId = FormValidation.DecodeKey(base64encodedString);		
			
			System.out.println("ID: "+claimId);
			  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("claimId",claimId);
			  GetClaimTrailStatus[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url + "/getClaimTrailList", map,GetClaimTrailStatus[].class);
			  
			  List<GetClaimTrailStatus> employeeList = new
			  ArrayList<GetClaimTrailStatus>(Arrays.asList(employeeDoc));
			  System.out.println(employeeList);
			  model.addObject("employeeList",employeeList);
			  
			  MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
		      map1.add("claimId",claimId);

			 GetClaimApplyAuthwise lvEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getClaimApplyDetailsByClaimId", map1,
					 GetClaimApplyAuthwise.class);
			//lvEmp.setClaimDate(DateConvertor.convertToDMY(lvEmp.getClaimDate()));

				model.addObject("lvEmp", lvEmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	//1st list
	@RequestMapping(value = "/showClaimHistDetailList", method = RequestMethod.GET)
	public ModelAndView showClaimHistDetailList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("claim/claimDetailHistoryList");
		int claimId = Integer.parseInt(FormValidation.DecodeKey(request.getParameter("claimId")));
		model.addObject("claimId",claimId);

		try {

			 MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("claimId",claimId);
			  GetClaimTrailStatus[] employeeDoc = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpClaimInfoListByTrailEmpId", map,GetClaimTrailStatus[].class);
			  
			  List<GetClaimTrailStatus> employeeList = new
			  ArrayList<GetClaimTrailStatus>(Arrays.asList(employeeDoc));
			  
			 
		      model.addObject("employeeList",employeeList);
		      
		      
		      MultiValueMap<String, Object> map1 = new LinkedMultiValueMap<>();
		      map1.add("claimId",claimId);

			 GetClaimApplyAuthwise lvEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getClaimApplyDetailsByClaimId", map1,
					 GetClaimApplyAuthwise.class);
			//lvEmp.setClaimDate(DateConvertor.convertToDMY(lvEmp.getClaimDate()));
				model.addObject("lvEmp", lvEmp);
				System.out.println("emp leave details"+lvEmp.toString());
				
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	
	
	

}
