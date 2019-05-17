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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
  
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.krakpi.model.FinancialYear;
import com.ats.hradmin.krakpi.model.GetEmpKpiReview;
import com.ats.hradmin.krakpi.model.GetEmpKra;
import com.ats.hradmin.krakpi.model.GetEmpKraKpiCount;
import com.ats.hradmin.krakpi.model.GetKraReviewList;
import com.ats.hradmin.krakpi.model.Kra;
import com.ats.hradmin.krakpi.model.KraReview;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveSummary;
import com.ats.hradmin.model.LeaveType;
import com.ats.hradmin.model.LoginResponse;
  
@Controller
@Scope("session")
public class KraKpiController {
	Kra editKra=new Kra();
	
	KraReview editKraReview=new KraReview();
	
	@RequestMapping(value = "/showEmpKraKpiCountList", method = RequestMethod.GET)
	public ModelAndView showEmpKraKpiCountList(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("krakpi/krakpiCountList");
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
		try {
			
			  FinancialYear[] employeeDoc = Constants.getRestTemplate().getForObject(Constants.url + "/getFinYearList",FinancialYear[].class);
			  
			  List<FinancialYear> employeeList = new
			  ArrayList<FinancialYear>(Arrays.asList(employeeDoc));
		      model.addObject("finYrList",employeeList);
		       
		      //Show List
		       
		      List<GetEmpKraKpiCount> employeeInfoList=new ArrayList<GetEmpKraKpiCount>();
				try {
					
				
					int status=Integer.parseInt(request.getParameter("status"));
					int finYrId=Integer.parseInt(request.getParameter("finYrId"));
					
					  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					  map.add("status",status);
					  map.add("finYrId",finYrId);
					  map.add("locIdList", userObj.getLocationIds());
					  GetEmpKraKpiCount[] employeeInfo = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKraKpiCount",map,
							  GetEmpKraKpiCount[].class);
					   
					  employeeInfoList = new ArrayList<GetEmpKraKpiCount>(Arrays.asList(employeeInfo));
					  for (int i = 0; i < employeeInfoList.size(); i++) {

						  employeeInfoList.get(i).setEmpMname(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getEmpId())));
						  employeeInfoList.get(i).setEmpDeptName(FormValidation.Encrypt(String.valueOf(finYrId)));
						  

		  				}
					  System.err.println("emp List final  is:"+employeeInfoList.toString());
					   model.addObject("employeeInfoList",employeeInfoList);
					   model.addObject("fin",finYrId);
					   model.addObject("stat",status);
					  
				} catch (Exception e) {
					e.printStackTrace();
				}
		      
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}

	/*
	 * @RequestMapping(value = "/empInfoCountList", method = RequestMethod.GET)
	 * public @ResponseBody List<GetEmpKraKpiCount>
	 * empInfoCountList(HttpServletRequest request, HttpServletResponse response) {
	 * 
	 * List<GetEmpKraKpiCount> employeeInfoList=new ArrayList<GetEmpKraKpiCount>();
	 * try {
	 * 
	 * 
	 * int status=Integer.parseInt(request.getParameter("status")); int
	 * finYrId=Integer.parseInt(request.getParameter("finYrId"));
	 * 
	 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
	 * map.add("status",status); map.add("finYrId",finYrId);
	 * 
	 * GetEmpKraKpiCount[] employeeInfo =
	 * Constants.getRestTemplate().postForObject(Constants.url +
	 * "/getEmpKraKpiCount",map, GetEmpKraKpiCount[].class);
	 * 
	 * employeeInfoList = new
	 * ArrayList<GetEmpKraKpiCount>(Arrays.asList(employeeInfo)); for (int i = 0; i
	 * < employeeInfoList.size(); i++) {
	 * 
	 * employeeInfoList.get(i).setEmpMname(FormValidation.Encrypt(String.valueOf(
	 * employeeInfoList.get(i).getEmpId()))); }
	 * System.err.println("emp List is:"+employeeInfoList.toString());
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return employeeInfoList; }
	 */
	@RequestMapping(value = "/showAddKra", method = RequestMethod.GET)
	public ModelAndView showAddKra(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView model = new ModelAndView("krakpi/addKra");
		try {
			List<GetEmpKra> employeeInfoList=new ArrayList<GetEmpKra>();
 			String base64encodedString = request.getParameter("empId");
 			System.out.println("empId encypt:"+base64encodedString);
			String empId = FormValidation.DecodeKey(base64encodedString);	
			System.out.println("empId:"+empId);
			String base64encodedString1 = request.getParameter("finYrId");
			String finYrId = FormValidation.DecodeKey(base64encodedString1);	
 			System.out.println("finYrId"+finYrId);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", empId);

			GetEmployeeInfo empInfo = Constants.getRestTemplate().postForObject(Constants.url + "/GetEmployeeInfo", map,
					GetEmployeeInfo.class);
			model.addObject("empInfo", empInfo);
			
			map = new LinkedMultiValueMap<>();
			map.add("finYrId", finYrId);

			FinancialYear finYr = Constants.getRestTemplate().postForObject(Constants.url + "/getFinYearByFinYrId", map,
					FinancialYear.class);
			model.addObject("finYr", finYr);
			
			map = new LinkedMultiValueMap<>();
			  map.add("empId",empId);
			  map.add("finYrId",finYrId);
			   
			  GetEmpKra[] kraList = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKraList",map,
					  GetEmpKra[].class);
			   
			  employeeInfoList = new ArrayList<GetEmpKra>(Arrays.asList(kraList));
			  for (int i = 0; i < employeeInfoList.size(); i++) {

				  employeeInfoList.get(i).setExvar1(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getKraId())));
				}
			  
			  model.addObject("kraList", employeeInfoList);
			  System.err.println("kraList List is:"+employeeInfoList.toString());

		      
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}

	
	@RequestMapping(value = "/submitInsertKra", method = RequestMethod.POST)
	public String submitInsertKra(HttpServletRequest request, HttpServletResponse response) {
		String empId = request.getParameter("empId");
		String finYrId = request.getParameter("finYrId");
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			 
			String kra_title = request.getParameter("kra_title");
			 
			System.err.println("in submitInsertKra");
 			String remark = null;
	 
 			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(kra_title, "") == true) {

				ret = true;
				System.out.println("kra_title" + ret);
			}
			 
			if (ret == false) {
 
	 
				Kra leaveSummary = new Kra();
				leaveSummary.setAcceptedDate(sf.format(date));
				leaveSummary.setAcceptedFlag(0);
				leaveSummary.setApprovedBy(0);
				leaveSummary.setApprovedFlag(0);
				leaveSummary.setApprovedDate(sf.format(date));
				leaveSummary.setEmpId(Integer.parseInt(empId));
				leaveSummary.setYearId(Integer.parseInt(finYrId));
				leaveSummary.setKraTitle(kra_title);
				 
				leaveSummary.setRemark(remark); 
				leaveSummary.setExInt1(1);
				leaveSummary.setExInt2(1);
				leaveSummary.setExInt3(1);
				leaveSummary.setExVar1("NA");
				leaveSummary.setExVar2("NA");
				leaveSummary.setExVar3("NA");
				leaveSummary.setIsActive(1);
				leaveSummary.setDelStatus(1);
				leaveSummary.setMakerUserId(userObj.getUserId());
				leaveSummary.setMakerEnterDatetime(sf.format(date));

				Kra res = Constants.getRestTemplate().postForObject(Constants.url + "/saveKra",
						leaveSummary, Kra.class);

				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Inserted Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}
 
		}
		 	catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showAddKra?empId="+FormValidation.Encrypt(empId)+"&finYrId="+FormValidation.Encrypt(finYrId);
	 
	}
	
	@RequestMapping(value = "/submitEditKra", method = RequestMethod.POST)
	public String submitEditKra(HttpServletRequest request, HttpServletResponse response) {
		String empId = request.getParameter("empId");
		String finYrId = request.getParameter("finYrId");
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			 
			String kra_title = request.getParameter("kra_title");
			 
			System.err.println("in submitInsertKra");
 			String remark = null;
	 
 			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(kra_title, "") == true) {

				ret = true;
				System.out.println("kra_title" + ret);
			}
			 
			if (ret == false) {
				editKra.setAcceptedDate(sf.format(date));
				editKra.setAcceptedFlag(0);
				editKra.setApprovedBy(0);
				editKra.setApprovedFlag(0);
				editKra.setApprovedDate(sf.format(date));
				 
				editKra.setKraTitle(kra_title);
				 
				editKra.setRemark(remark); 
				editKra.setExInt1(1);
				editKra.setExInt2(1);
				editKra.setExInt3(1);
				editKra.setExVar1("NA");
				editKra.setExVar2("NA");
				editKra.setExVar3("NA");
				editKra.setIsActive(1);
				editKra.setDelStatus(1);
				editKra.setMakerUserId(userObj.getUserId());
				editKra.setMakerEnterDatetime(sf.format(date));

				Kra res = Constants.getRestTemplate().postForObject(Constants.url + "/saveKra",
						editKra, Kra.class);

				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Edited Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Edit Record");
				}


			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}
 
		}
		 	catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showAddKra?empId="+FormValidation.Encrypt(empId)+"&finYrId="+FormValidation.Encrypt(finYrId);
	 
	}
	
	@RequestMapping(value = "/editKraDetail", method = RequestMethod.GET)
	public ModelAndView editLeaveSummary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("krakpi/editKra");

		try {
			List<GetEmpKra> employeeInfoList=new ArrayList<GetEmpKra>();
			String base64encodedString = request.getParameter("kraId");
			String kraId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("kraId", kraId);
			editKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraByKraId", map,
					Kra.class);
			model.addObject("editKra", editKra);
			//
			  map = new LinkedMultiValueMap<>();
			map.add("empId", editKra.getEmpId());
			

			GetEmployeeInfo empInfo = Constants.getRestTemplate().postForObject(Constants.url + "/GetEmployeeInfo", map,
					GetEmployeeInfo.class);
			
			empInfo.setEmpEmail(FormValidation.Encrypt(String.valueOf(empInfo.getEmpId())));
			model.addObject("empInfo", empInfo);
			
			
			map = new LinkedMultiValueMap<>();
			map.add("finYrId", editKra.getYearId());

			FinancialYear finYr = Constants.getRestTemplate().postForObject(Constants.url + "/getFinYearByFinYrId", map,
					FinancialYear.class);
			model.addObject("finYr", finYr);
			
			map = new LinkedMultiValueMap<>();
			  map.add("empId",editKra.getEmpId());
			  map.add("finYrId",editKra.getYearId());
			   
			  GetEmpKra[] kraList = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKraList",map,
					  GetEmpKra[].class);
			   
			  employeeInfoList = new ArrayList<GetEmpKra>(Arrays.asList(kraList));
			  for (int i = 0; i < employeeInfoList.size(); i++) {

				  employeeInfoList.get(i).setExvar1(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getKraId())));
				}
			  
			  model.addObject("kraList", employeeInfoList);
			  System.err.println("kraList List is edit:"+employeeInfoList.toString());
			 // model.add("empId1",FormValidation.Encrypt(String.valueOf(empInfo.getEmpId())));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	
	
	@RequestMapping(value = "/deleteKra", method = RequestMethod.GET)
	public String deleteKra(HttpServletRequest request, HttpServletResponse response) {
		String empId=null;int yrd=0;
		try {

			String base64encodedString = request.getParameter("kraId");
			String kraId = FormValidation.DecodeKey(base64encodedString);
			
			MultiValueMap<String, Object>  map = new LinkedMultiValueMap<>();
			map.add("kraId", kraId);
		Kra delKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraByKraId", map,
				Kra.class);
		
		 empId=String.valueOf(delKra.getEmpId());
		  yrd=(delKra.getYearId());

		map = new LinkedMultiValueMap<>();
			map.add("kraId", kraId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteKra", map, Info.class);
			System.err.println("info is "+info.toString());
			if(info.isError()==false) {
			  map = new LinkedMultiValueMap<>();
			map.add("kraId", kraId);
			Info info1 = Constants.getRestTemplate().postForObject(Constants.url + "/deleteKpiOfKra", map, Info.class);
			 
			
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showAddKra?empId="+FormValidation.Encrypt(empId)+"&finYrId="+FormValidation.Encrypt(String.valueOf(yrd));
	}
	
	  
		@RequestMapping(value = "/showAddKraReview", method = RequestMethod.GET)
		public ModelAndView showAddKraReview(HttpServletRequest request, HttpServletResponse response) {
			String empId=null;int yrd=0;
			ModelAndView model = new ModelAndView("krakpi/addKraReview");
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			try {
				List<GetKraReviewList> employeeInfoList=new ArrayList<GetKraReviewList>();
				String base64encodedString = request.getParameter("kraId");
				String kraId = FormValidation.DecodeKey(base64encodedString);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("kraId", kraId);
				editKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraByKraId", map,
						Kra.class);
				editKra.setExVar3(FormValidation.Encrypt(String.valueOf(editKra.getEmpId())));
				editKra.setExVar2(FormValidation.Encrypt(String.valueOf(editKra.getYearId())));
 				
				model.addObject("editKra", editKra);
				
				//
				  map = new LinkedMultiValueMap<>();
				map.add("empId", editKra.getEmpId());

				GetEmployeeInfo empInfo = Constants.getRestTemplate().postForObject(Constants.url + "/GetEmployeeInfo", map,
						GetEmployeeInfo.class);
				empInfo.setEmpEmail(FormValidation.Encrypt(String.valueOf(empInfo.getEmpId())));
				model.addObject("empInfo", empInfo);
				
				map = new LinkedMultiValueMap<>();
				map.add("finYrId", editKra.getYearId());

				FinancialYear finYr = Constants.getRestTemplate().postForObject(Constants.url + "/getFinYearByFinYrId", map,
						FinancialYear.class);
				model.addObject("finYr", finYr);
				
				map = new LinkedMultiValueMap<>();
				  map.add("kraId",kraId);
				  
				  GetKraReviewList[] kraReviewList = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKraReview",map,
						  GetKraReviewList[].class);
				   
				  employeeInfoList = new ArrayList<GetKraReviewList>(Arrays.asList(kraReviewList));
				  for (int i = 0; i < employeeInfoList.size(); i++) {

					  employeeInfoList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getKraReviewId())));
					}
				  
				  model.addObject("kraReviewList", employeeInfoList);
				  System.err.println("kraReviewList is edit:"+employeeInfoList.toString());
				
				 model.addObject("empIdOrig",userObj.getEmpId()) ;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
			
		}


		@RequestMapping(value = "/submitInsertKraReview", method = RequestMethod.POST)
		public String submitInsertKraReview(HttpServletRequest request, HttpServletResponse response) {
			String kraId = request.getParameter("kraId");
		 
			try {
				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
				 
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

				 
				String review = request.getParameter("kra_review");
				 
				System.err.println("in submitInsertKra");
	 			String remark = null;
		 
	 		 
				Boolean ret = false;
 
				if (ret == false) {
	 
		 
					KraReview leaveSummary = new KraReview();
					 
					leaveSummary.setKraId(Integer.parseInt(kraId));
					leaveSummary.setReview(review);
					 
					leaveSummary.setExInt1(1);
					leaveSummary.setExInt2(1);
					leaveSummary.setExInt3(1);
					leaveSummary.setExVar1("NA");
					leaveSummary.setExVar2("NA");
					leaveSummary.setExVar3("NA");
					leaveSummary.setIsActive(1);
					leaveSummary.setDelStatus(1);
					leaveSummary.setMakerUserId(userObj.getUserId());
					leaveSummary.setMakerEnterDatetime(sf.format(date));

					KraReview res = Constants.getRestTemplate().postForObject(Constants.url + "/saveKraReview",
							leaveSummary, KraReview.class);

					if (res.isError() == false) {
						session.setAttribute("successMsg", "Record Inserted Successfully");
					} else {
						session.setAttribute("errorMsg", "Failed to Insert Record");
					}

				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}
	 
			}
			 	catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/showAddKraReview?kraId="+FormValidation.Encrypt(kraId);
		 
		}
		

		@RequestMapping(value = "/deleteKraReview", method = RequestMethod.GET)
		public String deleteKraReview(HttpServletRequest request, HttpServletResponse response) {
			String kraId=null;
			 
			try {

				String base64encodedString = request.getParameter("kraReviewId");
				String kraReviewId = FormValidation.DecodeKey(base64encodedString);
				
				MultiValueMap<String, Object>  map = new LinkedMultiValueMap<>();
				map.add("kraReviewId", kraReviewId);
			KraReview delKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraReviewByKraReviewId", map,
					KraReview.class);
			
			kraId=String.valueOf(delKra.getKraId());
			   

			map = new LinkedMultiValueMap<>();
				map.add("kraReviewId", kraReviewId);
				Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteKraReview", map, Info.class);
				System.err.println("info is "+info.toString());
				 

			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/showAddKraReview?kraId="+FormValidation.Encrypt(kraId);
		}
		
		
		@RequestMapping(value = "/editKraReviewDetail", method = RequestMethod.GET)
		public ModelAndView editKraReviewDetail(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = new ModelAndView("krakpi/editKraReview");
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			try {
				List<GetKraReviewList> employeeInfoList=new ArrayList<GetKraReviewList>();			
				String base64encodedString = request.getParameter("kraReviewId");
				String kraReviewId = FormValidation.DecodeKey(base64encodedString);
				
				

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("kraReviewId", kraReviewId);
				editKraReview = Constants.getRestTemplate().postForObject(Constants.url + "/getKraReviewByKraReviewId", map,
						KraReview.class);
				model.addObject("editKraReview", editKraReview);
				//
				
			  map = new LinkedMultiValueMap<>();
				map.add("kraId", editKraReview.getKraId());
				editKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraByKraId", map,
						Kra.class);
				model.addObject("editKra", editKra);
				  map = new LinkedMultiValueMap<>();
				map.add("empId", editKra.getEmpId());

				GetEmployeeInfo empInfo = Constants.getRestTemplate().postForObject(Constants.url + "/GetEmployeeInfo", map,
						GetEmployeeInfo.class);
				model.addObject("empInfo", empInfo);
				
				map = new LinkedMultiValueMap<>();
				map.add("finYrId", editKra.getYearId());

				FinancialYear finYr = Constants.getRestTemplate().postForObject(Constants.url + "/getFinYearByFinYrId", map,
						FinancialYear.class);
				model.addObject("finYr", finYr);
				
				map = new LinkedMultiValueMap<>();
				  map.add("kraId",editKraReview.getKraId());
				  
				  GetKraReviewList[] kraReviewList = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKraReview",map,
						  GetKraReviewList[].class);
				   
				  employeeInfoList = new ArrayList<GetKraReviewList>(Arrays.asList(kraReviewList));
				  for (int i = 0; i < employeeInfoList.size(); i++) {

					  employeeInfoList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getKraReviewId())));
					}
				  
				  model.addObject("kraReviewList", employeeInfoList);
				  System.err.println("kra Review List is edit:"+employeeInfoList.toString());
				
				  model.addObject("empIdOrig",userObj.getEmpId()) ;
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return model;
		}


		@RequestMapping(value = "/submitInsertEditKraReview", method = RequestMethod.POST)
		public String submitInsertEditKraReview(HttpServletRequest request, HttpServletResponse response) {
			String kraId = request.getParameter("kraId");
		 
			try {
				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
				 
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

				 
				String review = request.getParameter("kra_review");
				 
				System.err.println("in submitInsertKra");
	 			String remark = null;
		 
	 		 
				Boolean ret = false;
 
				if (ret == false) {
	 
		 
				 
					 
					editKraReview.setKraId(Integer.parseInt(kraId));
					editKraReview.setReview(review);
					 
					editKraReview.setExInt1(1);
					editKraReview.setExInt2(1);
					editKraReview.setExInt3(1);
					editKraReview.setExVar1("NA");
					editKraReview.setExVar2("NA");
					editKraReview.setExVar3("NA");
					editKraReview.setIsActive(1);
					editKraReview.setDelStatus(1);
					editKraReview.setMakerUserId(userObj.getUserId());
					editKraReview.setMakerEnterDatetime(sf.format(date));

					KraReview res = Constants.getRestTemplate().postForObject(Constants.url + "/saveKraReview",
							editKraReview, KraReview.class);

					if (res.isError() == false) {
						session.setAttribute("successMsg", "Record Inserted Successfully");
					} else {
						session.setAttribute("errorMsg", "Failed to Insert Record");
					}

				} else {
					session.setAttribute("errorMsg", "Failed to Insert Record");
				}
	 
			}
			 	catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/showAddKraReview?kraId="+FormValidation.Encrypt(kraId);
		 
		}
		
		/////**************************KPI**************************************************
		
}
