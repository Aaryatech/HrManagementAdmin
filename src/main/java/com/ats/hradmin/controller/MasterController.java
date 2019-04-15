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
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.common.VpsImageUpload;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.EmpType;
import com.ats.hradmin.model.EmployeDoc;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.Location;

@Controller
@Scope("session")
public class MasterController {

	Company editCompany = new Company();
	Location editLocation = new Location();
	EmpType editEmpType = new EmpType();
	
	@RequestMapping(value = "/companyAdd", method = RequestMethod.GET)
	public ModelAndView companyAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/companyAdd");

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

	@RequestMapping(value = "/submitInsertCompany", method = RequestMethod.POST)
	public String submitInsertCompany(@RequestParam("compImg") List<MultipartFile> compImg, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			VpsImageUpload upload = new VpsImageUpload();

			String compName = request.getParameter("compName");
			String remark = request.getParameter("remark");

			Boolean ret = false;

			if (FormValidation.Validaton(compName, "") == true) {

				ret = true;
			}
			if (FormValidation.Validaton(compImg.get(0).getOriginalFilename(), "") == true) {
				ret = true;
			}

			if (ret == false) {

				Company company = new Company();

				company.setCompanyName(compName);
				company.setCompanyRemarks(remark);
				company.setIsActive(1);
				company.setDelStatus(1);
				company.setMakerUserId(1);
				company.setMakerEnterDatetime(sf.format(date));

				String imageName = new String();
				imageName = dateTimeInGMT.format(date) + "_" + compImg.get(0).getOriginalFilename();

				try {
					upload.saveUploadedImge(compImg.get(0), Constants.imageSaveUrl, imageName, Constants.values, 0, 0,
							0, 0, 0);
					company.setCompanyLogo(imageName);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				Company res = Constants.getRestTemplate().postForObject(Constants.url + "/saveCompany", company,
						Company.class);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showCompanyList";
	}

	@RequestMapping(value = "/showCompanyList", method = RequestMethod.GET)
	public ModelAndView showCompanyList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/companyList");

		try {

			Company[] company = Constants.getRestTemplate().getForObject(Constants.url + "/getCompanyList",
					Company[].class);

			List<Company> compList = new ArrayList<Company>(Arrays.asList(company));

			for (int i = 0; i < compList.size(); i++) {

				compList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(compList.get(i).getCompanyId())));
			}

			model.addObject("compList", compList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/deleteCompany", method = RequestMethod.GET)
	public String deleteCompany(HttpServletRequest request, HttpServletResponse response) {

		try {

			String base64encodedString = request.getParameter("compId");
			String compId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", compId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteCompany", map, Info.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showCompanyList";
	}

	@RequestMapping(value = "/editCompany", method = RequestMethod.GET)
	public ModelAndView editCompany(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/companyEdit");

		try {
			String base64encodedString = request.getParameter("compId");
			String compId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", compId);
			editCompany = Constants.getRestTemplate().postForObject(Constants.url + "/getCompanyById", map,
					Company.class);
			model.addObject("editCompany", editCompany);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditCompany", method = RequestMethod.POST)
	public String submitEditCompany(@RequestParam("compImg") List<MultipartFile> compImg, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			VpsImageUpload upload = new VpsImageUpload();

			String compName = request.getParameter("compName");
			String remark = request.getParameter("remark");
			String imageName = request.getParameter("imageName");

			Boolean ret = false;

			if (FormValidation.Validaton(compName, "") == true) {

				ret = true;
			}
			if (FormValidation.Validaton(imageName, "") == true) {
				ret = true;
			}

			if (ret == false) {

				editCompany.setCompanyName(compName);
				editCompany.setCompanyRemarks(remark);
				editCompany.setMakerUserId(1);
				editCompany.setMakerEnterDatetime(sf.format(date));

				if (!compImg.get(0).getOriginalFilename().equals(null)
						&& !compImg.get(0).getOriginalFilename().equals("")) {
					imageName = new String();
					imageName = dateTimeInGMT.format(date) + "_" + compImg.get(0).getOriginalFilename();

					try {
						upload.saveUploadedImge(compImg.get(0), Constants.imageSaveUrl, imageName, Constants.values, 0,
								0, 0, 0, 0);
						editCompany.setCompanyLogo(imageName);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}

				Company res = Constants.getRestTemplate().postForObject(Constants.url + "/saveCompany", editCompany,
						Company.class);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showCompanyList";
	}

	@RequestMapping(value = "/locationAdd", method = RequestMethod.GET)
	public ModelAndView locationAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/locationAdd");

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertLocation", method = RequestMethod.POST)
	public String submitInsertLocation(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String locName = request.getParameter("locName");
			String locShortName = request.getParameter("locShortName");
			String add = request.getParameter("add");
			String prsnName = request.getParameter("prsnName");
			String contactNo = request.getParameter("contactNo");
			String email = request.getParameter("email");
			String remark = request.getParameter("remark");

			Boolean ret = false;

			if (FormValidation.Validaton(locName, "") == true) {

				ret = true;
				System.out.println("locName" + ret);
			}
			if (FormValidation.Validaton(locShortName, "") == true) {

				ret = true;
				System.out.println("locShortName" + ret);
			}
			if (FormValidation.Validaton(add, "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(prsnName, "") == true) {

				ret = true;
				System.out.println("prsnName" + ret);
			}
			if (FormValidation.Validaton(contactNo, "mobile") == true) {

				ret = true;
				System.out.println("contactNo" + ret);
			}
			if (FormValidation.Validaton(email, "email") == true) {

				ret = true;
				System.out.println("email" + ret);
			}

			if (ret == false) {

				Location location = new Location();

				location.setLocName(locName);
				location.setLocNameShort(locShortName);
				location.setLocShortAddress(add);
				location.setLocHrContactPerson(prsnName);
				location.setLocHrContactNumber(contactNo);
				location.setLocHrContactEmail(email);
				location.setLocRemarks(remark);
				location.setIsActive(1);
				location.setDelStatus(1);
				location.setMakerUserId(1);
				location.setCompId(1);
				location.setMakerEnterDatetime(sf.format(date));

				Location res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLocation", location,
						Location.class);

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
			session.setAttribute("errorMsg", "Failed to Insert Record");
		}

		return "redirect:/showLocationList";
	}

	@RequestMapping(value = "/showLocationList", method = RequestMethod.GET)
	public ModelAndView showLocationList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/locationList");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);

			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			for (int i = 0; i < locationList.size(); i++) {

				locationList.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(locationList.get(i).getLocId())));
			}

			model.addObject("locationList", locationList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
	public String deleteLocation(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("locId");
			String locId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("locId", locId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteLocation", map, Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showLocationList";
	}

	@RequestMapping(value = "/editLocation", method = RequestMethod.GET)
	public ModelAndView editLocation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/locationEdit");

		try {
			String base64encodedString = request.getParameter("locId");
			String locId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("locId", locId);
			editLocation = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationById", map,
					Location.class);
			model.addObject("editLocation", editLocation);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditLocation", method = RequestMethod.POST)
	public String submitEditLocation(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String locName = request.getParameter("locName");
			String locShortName = request.getParameter("locShortName");
			String add = request.getParameter("add");
			String prsnName = request.getParameter("prsnName");
			String contactNo = request.getParameter("contactNo");
			String email = request.getParameter("email");
			String remark = request.getParameter("remark");

			Boolean ret = false;

			if (FormValidation.Validaton(locName, "") == true) {

				ret = true;
				System.out.println("locName" + ret);
			}
			if (FormValidation.Validaton(locShortName, "") == true) {

				ret = true;
				System.out.println("locShortName" + ret);
			}
			if (FormValidation.Validaton(add, "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(prsnName, "") == true) {

				ret = true;
				System.out.println("prsnName" + ret);
			}
			if (FormValidation.Validaton(contactNo, "mobile") == true) {

				ret = true;
				System.out.println("contactNo" + ret);
			}
			if (FormValidation.Validaton(email, "email") == true) {

				ret = true;
				System.out.println("email" + ret);
			}

			if (ret == false) {

				editLocation.setLocName(locName);
				editLocation.setLocNameShort(locShortName);
				editLocation.setLocShortAddress(add);
				editLocation.setLocHrContactPerson(prsnName);
				editLocation.setLocHrContactNumber(contactNo);
				editLocation.setLocHrContactEmail(email);
				editLocation.setLocRemarks(remark);
				editLocation.setMakerUserId(1);
				editLocation.setMakerEnterDatetime(sf.format(date));

				Location res = Constants.getRestTemplate().postForObject(Constants.url + "/saveLocation", editLocation,
						Location.class);

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

		return "redirect:/showLocationList";
	}

	@RequestMapping(value = "/empTypeAdd", method = RequestMethod.GET)
	public ModelAndView empTypeAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empTypeAdd");

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertEmpType", method = RequestMethod.POST)
	public String submitInsertEmpType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String empTypeName = request.getParameter("empTypeName");
			String empShortName = request.getParameter("empShortName");
			int comoffallowed = Integer.parseInt(request.getParameter("comoffallowed"));
			String remark = request.getParameter("remark");
			
			Boolean ret = false;

			if (FormValidation.Validaton(empTypeName, "") == true) {

				ret = true;
				System.out.println("locName" + ret);
			}
			if (FormValidation.Validaton(empShortName, "") == true) {

				ret = true;
				System.out.println("locShortName" + ret);
			}
			 

			if (ret == false) {

				EmpType empType = new EmpType();

				empType.setEmpTypeName(empTypeName);
				empType.setEmpTypeShortName(empShortName);
				empType.setCompOffRequestAllowed(comoffallowed); 
				empType.setIsActive(1);
				empType.setDelStatus(1);
				empType.setMakerUserId(1);
				empType.setCompanyId(1);
				empType.setEmpTypeRemarks(remark);
				empType.setEmpTypeAccess("");
				empType.setMakerEnterDatetime(sf.format(date));

				EmpType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpType", empType,
						EmpType.class);

				if (res.isError()==false) {
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

		return "redirect:/showEmpTypeList";
	}

	@RequestMapping(value = "/showEmpTypeList", method = RequestMethod.GET)
	public ModelAndView showEmpTypeList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empTypeList");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", 1);
			EmpType[] EmpType = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpTypeList", map,
					EmpType[].class);

			List<EmpType> empTypelist = new ArrayList<EmpType>(Arrays.asList(EmpType));

			for (int i = 0; i < empTypelist.size(); i++) {

				empTypelist.get(i).setExVar1(FormValidation.Encrypt(String.valueOf(empTypelist.get(i).getEmpTypeId())));
			}

			model.addObject("empTypelist", empTypelist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteEmpType", method = RequestMethod.GET)
	public String deleteEmpType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("empTypeId");
			String empTypeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empTypeId", empTypeId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteEmpType", map, Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showEmpTypeList";
	}
	
	@RequestMapping(value = "/editEmpType", method = RequestMethod.GET)
	public ModelAndView editEmpType(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empTypeEdit");

		try {
			String base64encodedString = request.getParameter("empTypeId");
			String empTypeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empTypeId", empTypeId);
			editEmpType = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpTypeById", map,
					EmpType.class);
			model.addObject("editEmpType", editEmpType);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	@RequestMapping(value = "/submitEditEmpType", method = RequestMethod.POST)
	public String submitEditEmpType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String empTypeName = request.getParameter("empTypeName");
			String empShortName = request.getParameter("empShortName");
			int comoffallowed = Integer.parseInt(request.getParameter("comoffallowed"));
			String remark = request.getParameter("remark");
			
			Boolean ret = false;

			if (FormValidation.Validaton(empTypeName, "") == true) {

				ret = true;
				 
			}
			if (FormValidation.Validaton(empShortName, "") == true) {

				ret = true;
				 
			}
			 

			if (ret == false) {

				 

				editEmpType.setEmpTypeName(empTypeName);
				editEmpType.setEmpTypeShortName(empShortName);
				editEmpType.setCompOffRequestAllowed(comoffallowed);  
				editEmpType.setCompanyId(1);
				editEmpType.setEmpTypeRemarks(remark);
				editEmpType.setEmpTypeAccess("");
				editEmpType.setMakerEnterDatetime(sf.format(date));

				EmpType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpType", editEmpType,
						EmpType.class);

				if (res.isError()==false) {
					session.setAttribute("successMsg", "Record Updated Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Updated Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Updated Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Updated Record");
		}

		return "redirect:/showEmpTypeList";
	}

}
