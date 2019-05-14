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

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.krakpi.model.FinancialYear;
import com.ats.hradmin.krakpi.model.GetEmpKpiReview;
import com.ats.hradmin.krakpi.model.GetKraReviewList;
import com.ats.hradmin.krakpi.model.Kpi;
import com.ats.hradmin.krakpi.model.KpiReview;
import com.ats.hradmin.krakpi.model.Kra;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LoginResponse;

@Controller
@Scope("session")
public class KpiController {
	Kpi editKpi=new Kpi();
	
	KpiReview editKpiReview=new KpiReview();
	
	@RequestMapping(value = "/showAddKpi", method = RequestMethod.GET)
	public ModelAndView showAddKpi(HttpServletRequest request, HttpServletResponse response) {
		
		List<Kpi> kpiInfoList=new ArrayList<Kpi>();
		ModelAndView model = new ModelAndView("krakpi/addKpi");
		try {
			String base64encodedString = request.getParameter("kraId");
			String kraId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("kraId", kraId);
			Kra editKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraByKraId", map,
					Kra.class);
			model.addObject("editKra", editKra);
			//
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
 			  map.add("kraId",kraId);
			    
			  Kpi[] kpiList = Constants.getRestTemplate().postForObject(Constants.url + "/getKpiByKraId",map,
					  Kpi[].class);
			   
			  kpiInfoList = new ArrayList<Kpi>(Arrays.asList(kpiList));
			  for (int i = 0; i < kpiInfoList.size(); i++) {

				  kpiInfoList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(kpiInfoList.get(i).getKpiId())));
				}
			  
			  model.addObject("kpiList", kpiInfoList);
			  System.err.println("kpiList List is:"+kpiInfoList.toString());
			 
		      
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}

	
	@RequestMapping(value = "/submitInsertKpi", method = RequestMethod.POST)
	public String submitInsertKpi(HttpServletRequest request, HttpServletResponse response) {
		String empId = request.getParameter("empId");
		String kraId = request.getParameter("kraId");
		 
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

			 
			String kpi_title = request.getParameter("kpi_title");
			 
			System.err.println("in submitInsertKra");
 			String remark = null;
	 
 			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(kpi_title, "") == true) {

				ret = true;
				System.out.println("kpi_title" + ret);
			}
			 
			if (ret == false) {
 
	 
				Kpi leaveSummary = new Kpi();
				
				 
				leaveSummary.setEmpId(Integer.parseInt(empId));
				leaveSummary.setKraId(Integer.parseInt(kraId));
				
				leaveSummary.setKpiTitle(kpi_title);
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

				Kpi res = Constants.getRestTemplate().postForObject(Constants.url + "/saveKpi",
						leaveSummary, Kpi.class);

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

		return "redirect:/showAddKpi?kraId="+FormValidation.Encrypt(kraId);
	 
	}

	
	@RequestMapping(value = "/showEditKpi", method = RequestMethod.GET)
	public ModelAndView showEditKpi(HttpServletRequest request, HttpServletResponse response) {
		
		List<Kpi> kpiInfoList=new ArrayList<Kpi>();
		ModelAndView model = new ModelAndView("krakpi/editKpi");
		try {
			String base64encodedString = request.getParameter("kpiId");
			String kpiId = FormValidation.DecodeKey(base64encodedString);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("kpiId", kpiId);
		 editKpi = Constants.getRestTemplate().postForObject(Constants.url + "/getKpiByKpiId", map,
					Kpi.class);
			model.addObject("editKpi", editKpi);
			

		
			map.add("kraId", editKpi.getKraId());
			Kra editKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraByKraId", map,
					Kra.class);
			model.addObject("editKra", editKra);
			//
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
 			  map.add("kraId",editKpi.getKraId());
			    
			  Kpi[] kpiList = Constants.getRestTemplate().postForObject(Constants.url + "/getKpiByKraId",map,
					  Kpi[].class);
			   
			  kpiInfoList = new ArrayList<Kpi>(Arrays.asList(kpiList));
			  for (int i = 0; i < kpiInfoList.size(); i++) {

				  kpiInfoList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(kpiInfoList.get(i).getKpiId())));
				}
			  
			  model.addObject("kpiList", kpiInfoList);
			  System.err.println("kpiList List is:"+kpiInfoList.toString());
			 
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}
	
	@RequestMapping(value = "/submitEditKpi", method = RequestMethod.POST)
	public String submitEditKpi(HttpServletRequest request, HttpServletResponse response) {
		String empId = request.getParameter("empId");
		String kraId = request.getParameter("kraId");
		 
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
			 
			String kpi_title = request.getParameter("kpi_title");
			 
			System.err.println("in submitInsertKra");
 			String remark = null;
	 
 			try {
				remark = request.getParameter("remark");
			} catch (Exception e) {
				remark = "NA";
			}

			Boolean ret = false;

			if (FormValidation.Validaton(kpi_title, "") == true) {

				ret = true;
				System.out.println("kpi_title" + ret);
			}
			 
			if (ret == false) {
  
				editKpi.setEmpId(Integer.parseInt(empId));
				editKpi.setKraId(Integer.parseInt(kraId));
				
				editKpi.setKpiTitle(kpi_title);
				editKpi.setRemark(remark); 
				editKpi.setExInt1(1);
				editKpi.setExInt2(1);
				editKpi.setExInt3(1);
				editKpi.setExVar1("NA");
				editKpi.setExVar2("NA");
				editKpi.setExVar3("NA");
				editKpi.setIsActive(1);
				editKpi.setDelStatus(1);
				editKpi.setMakerUserId(userObj.getUserId());
				editKpi.setMakerEnterDatetime(sf.format(date));

				Kpi res = Constants.getRestTemplate().postForObject(Constants.url + "/saveKpi",
						editKpi, Kpi.class);

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

		return "redirect:/showAddKpi?kraId="+FormValidation.Encrypt(kraId);
	 
	}
	

	@RequestMapping(value = "/deleteKpi", method = RequestMethod.GET)
	public String deleteKpi(HttpServletRequest request, HttpServletResponse response) {
		String empId=null; 
		int kraId=0;
		try {

			String base64encodedString = request.getParameter("kpiId");
			String kpiId = FormValidation.DecodeKey(base64encodedString);
			
			MultiValueMap<String, Object>  map = new LinkedMultiValueMap<>();
			map.add("kpiId", kpiId);
		Kpi delKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKpiByKpiId", map,
				Kpi.class);
		
		 empId=String.valueOf(delKra.getEmpId());
		  kraId=delKra.getKraId();

		map = new LinkedMultiValueMap<>();
		map.add("kpiId", kpiId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteKpi", map, Info.class);
			System.err.println("info is "+info.toString());
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showAddKpi?kraId="+FormValidation.Encrypt(String.valueOf(kraId));
	}
	
	@RequestMapping(value = "/showAddKpiReview", method = RequestMethod.GET)
	public ModelAndView showAddKpiReview(HttpServletRequest request, HttpServletResponse response) {
 		 
		ModelAndView model = new ModelAndView("krakpi/addKpiReview");
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
		try {
			List<GetEmpKpiReview> employeeInfoList=new ArrayList<GetEmpKpiReview>();
			String base64encodedString = request.getParameter("kpiId");
			String kpiId = FormValidation.DecodeKey(base64encodedString);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("kpiId", kpiId);
		 editKpi = Constants.getRestTemplate().postForObject(Constants.url + "/getKpiByKpiId", map,
					Kpi.class);
			model.addObject("editKpi", editKpi);
			
			map.add("kraId", editKpi.getKraId());
			Kra editKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraByKraId", map,
					Kra.class);
			model.addObject("editKra", editKra);
			//
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
 			   
			  map = new LinkedMultiValueMap<>();
			  map.add("kpiId",kpiId);
			  
			  GetEmpKpiReview[] kraReviewList = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKpiReview",map,
					  GetEmpKpiReview[].class);
			   
			  employeeInfoList = new ArrayList<GetEmpKpiReview>(Arrays.asList(kraReviewList));
			  for (int i = 0; i < employeeInfoList.size(); i++) {

				  employeeInfoList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getKpiReviewId())));
				}
			  
			  model.addObject("kpiReviewList", employeeInfoList);
			  System.err.println("kpiReviewList List is edit:"+employeeInfoList.toString());
			
			 model.addObject("empIdOrig",userObj.getEmpId()) ;
			 
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}
	
	@RequestMapping(value = "/submitKpiReview", method = RequestMethod.POST)
	public String submitKpiReview(HttpServletRequest request, HttpServletResponse response) {
	 
		String kpiId = request.getParameter("kpiId");
		 
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
			 
			String kpi_review = request.getParameter("kpi_review");
			 
			System.err.println("in submitInsertKra");
 			String remark = null;
	 
 			
			Boolean ret = false;

			if (FormValidation.Validaton(kpi_review, "") == true) {

				ret = true;
				System.out.println("kpi_review" + ret);
			}
			 
			if (ret == false) {
  
				 KpiReview rev=new KpiReview();
				 rev.setKpiId(Integer.parseInt(kpiId));
				
				rev.setReview(kpi_review);
 				rev.setExInt1(1);
				rev.setExInt2(1);
				rev.setExInt3(1);
				rev.setExVar1("NA");
				rev.setExVar2("NA");
				rev.setExVar3("NA");
				rev.setIsActive(1);
				rev.setDelStatus(1);
				rev.setMakerUserId(userObj.getUserId());
				rev.setMakerEnterDatetime(sf.format(date));

				KpiReview res = Constants.getRestTemplate().postForObject(Constants.url + "/saveKpiReview",
						rev, KpiReview.class);

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

		return "redirect:/showAddKpiReview?kpiId="+FormValidation.Encrypt(kpiId);
	 
	}
	
	@RequestMapping(value = "/showEditKpiReview", method = RequestMethod.GET)
	public ModelAndView showEditKpiReview(HttpServletRequest request, HttpServletResponse response) {
 		 
		ModelAndView model = new ModelAndView("krakpi/editKpiReview");
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
		try {
			List<KpiReview> employeeInfoList=new ArrayList<KpiReview>();
			String base64encodedString = request.getParameter("kpiReviewId");
			String kpiReviewId = FormValidation.DecodeKey(base64encodedString);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  System.err.println("kpiReviewId  is edit:"+kpiReviewId);
				 
			
			map.add("kpiReviewId", kpiReviewId);
			editKpiReview = Constants.getRestTemplate().postForObject(Constants.url + "/getKpiReviewByKpiReviewId", map,
						KpiReview.class);
				model.addObject("editKpiReview", editKpiReview);
				  System.err.println("kpiReview  is edit:"+editKpiReview.toString());
				 
			map.add("kpiId", editKpiReview.getKpiId());
		 editKpi = Constants.getRestTemplate().postForObject(Constants.url + "/getKpiByKpiId", map,
					Kpi.class);
			model.addObject("editKpi", editKpi);
			System.err.println("kpi  is edit:"+editKpi.toString());
			
			map.add("kraId", editKpi.getKraId());
			Kra editKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKraByKraId", map,
					Kra.class);
			model.addObject("editKra", editKra);
			System.err.println("Kra  is edit:"+editKpi.toString());
			//
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
 			   
			  map = new LinkedMultiValueMap<>();
			  map.add("kpiId",editKpiReview.getKpiId());
			  
			  KpiReview[] kraReviewList = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpKpiReview",map,
					  KpiReview[].class);
			   
			  employeeInfoList = new ArrayList<KpiReview>(Arrays.asList(kraReviewList));
			  for (int i = 0; i < employeeInfoList.size(); i++) {

				  employeeInfoList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(employeeInfoList.get(i).getKpiReviewId())));
				}
			  
			  model.addObject("kpiReviewList", employeeInfoList);
			  System.err.println("kpiReviewList List is edit:"+employeeInfoList.toString());
			
			 model.addObject("empIdOrig",userObj.getEmpId()) ;
			 
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return model;
		
	}

	
	@RequestMapping(value = "/submitEditKpiReview", method = RequestMethod.POST)
	public String submitEditKpiReview(HttpServletRequest request, HttpServletResponse response) {
	 
		String kpiId = request.getParameter("kpiId");
		 
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			 
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
			 
			String kpi_review = request.getParameter("kpi_review");
			 
			System.err.println("in submitInsertKra");
 			String remark = null;
	 
 			
			Boolean ret = false;

			if (FormValidation.Validaton(kpi_review, "") == true) {

				ret = true;
				System.out.println("kpi_review" + ret);
			}
			 
			if (ret == false) {
  
				
				editKpiReview.setKpiId(Integer.parseInt(kpiId));
				
				editKpiReview.setReview(kpi_review);
				editKpiReview.setExInt1(1);
				editKpiReview.setExInt2(1);
				editKpiReview.setExInt3(1);
				editKpiReview.setExVar1("NA");
				editKpiReview.setExVar2("NA");
				editKpiReview.setExVar3("NA");
				editKpiReview.setIsActive(1);
				editKpiReview.setDelStatus(1);
				editKpiReview.setMakerUserId(userObj.getUserId());
				editKpiReview.setMakerEnterDatetime(sf.format(date));

				KpiReview res = Constants.getRestTemplate().postForObject(Constants.url + "/saveKpiReview",
						editKpiReview, KpiReview.class);

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

		return "redirect:/showAddKpiReview?kpiId="+FormValidation.Encrypt(kpiId);
	 
	}
	@RequestMapping(value = "/deleteKpiReview", method = RequestMethod.GET)
	public String deleteKpiReview(HttpServletRequest request, HttpServletResponse response) {
		 
		System.out.println("in delete kpi review");
		int kpiId=0;
		try {

			String base64encodedString = request.getParameter("kpiReviewId");
			String kpiReviewId = FormValidation.DecodeKey(base64encodedString);
			
			MultiValueMap<String, Object>  map = new LinkedMultiValueMap<>();
			map.add("kpiReviewId", kpiReviewId);
		KpiReview delKra = Constants.getRestTemplate().postForObject(Constants.url + "/getKpiReviewByKpiReviewId", map,
				KpiReview.class);
		
		kpiId=delKra.getKpiId();

		map = new LinkedMultiValueMap<>();
		map.add("kpiReviewId", kpiReviewId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteKpiReview", map, Info.class);
			System.err.println("info is "+info.toString());
			 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showAddKpiReview?kpiId="+FormValidation.Encrypt(String.valueOf(kpiId));
	}
	
}
