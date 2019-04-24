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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.DateConvertor;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.common.VpsImageUpload;
import com.ats.hradmin.model.AccessRightModule;
import com.ats.hradmin.model.AccessRightSubModule;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.EmpType;
import com.ats.hradmin.model.EmployeeCategory;
import com.ats.hradmin.model.EmployeeDepartment;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.GetEmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LeaveType;
import com.ats.hradmin.model.Location;
import com.ats.hradmin.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@Scope("session")
public class MasterController {
	EmployeeInfo editEmp= new EmployeeInfo();
	Company editCompany = new Company();
	Location editLocation = new Location();
	EmpType editEmpType = new EmpType();
	EmployeeCategory editEmpCategory = new EmployeeCategory();
	EmployeeDepartment editEmployeeDepartment = new EmployeeDepartment();
	List<AccessRightModule> moduleList = new ArrayList<>();

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

			AccessRightModule[] accessRightModule = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getModuleAndSubModuleList", AccessRightModule[].class);

			moduleList = new ArrayList<AccessRightModule>(Arrays.asList(accessRightModule));

			model.addObject("moduleList", moduleList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/getSubmoduleList", method = RequestMethod.GET)
	public @ResponseBody List<Integer> getSubmoduleList(HttpServletRequest request, HttpServletResponse response) {

		List<Integer> list = new ArrayList<>();
		try {

			int moduleId = Integer.parseInt(request.getParameter("moduleId"));
			// System.out.println(moduleId);
			for (int i = 0; i < moduleList.size(); i++) {

				if (moduleList.get(i).getModuleId() == moduleId) {

					for (int j = 0; j < moduleList.get(i).getAccessRightSubModuleList().size(); j++) {

						list.add(moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId());
					}
					break;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;

	}

	@RequestMapping(value = "/submitInsertEmpType", method = RequestMethod.POST)
	public String submitInsertEmpType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			List<AccessRightModule> moduleJsonList = new ArrayList<>();

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

				for (int i = 0; i < moduleList.size(); i++) {

					AccessRightModule moduleJson = new AccessRightModule();
					int isPresent = 0;

					List<AccessRightSubModule> subModuleJsonList = new ArrayList<>();

					for (int j = 0; j < moduleList.get(i).getAccessRightSubModuleList().size(); j++) {

						AccessRightSubModule subModuleJson = new AccessRightSubModule();

						String view = request
								.getParameter(moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId()
										+ "view" + moduleList.get(i).getModuleId());

						try {
							if (view.equals("1")) {
								isPresent = 1;

								subModuleJson.setView(Integer.parseInt(view));
								subModuleJson.setSubModuleId(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId());
								subModuleJson.setModuleId(moduleList.get(i).getModuleId());
								subModuleJson.setSubModulName(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModulName());
								subModuleJson.setSubModuleDesc(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleDesc());
								subModuleJson.setSubModuleMapping(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleMapping());
								subModuleJson.setOrderBy(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getOrderBy());

								try {
									int add = Integer.parseInt(request.getParameter(
											moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId()
													+ "add" + moduleList.get(i).getModuleId()));
									subModuleJson.setAddApproveConfig(add);
								} catch (Exception e) {
									subModuleJson.setAddApproveConfig(0);
								}

								try {
									int edit = Integer.parseInt(request.getParameter(
											moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId()
													+ "edit" + moduleList.get(i).getModuleId()));
									subModuleJson.setEditReject(edit);
								} catch (Exception e) {
									subModuleJson.setEditReject(0);
								}

								try {
									int delete = Integer.parseInt(request.getParameter(
											moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId()
													+ "delete" + moduleList.get(i).getModuleId()));
									subModuleJson.setDeleteRejectApprove(delete);
								} catch (Exception e) {
									subModuleJson.setDeleteRejectApprove(0);
								}

								subModuleJsonList.add(subModuleJson);

							}
						} catch (Exception e) {

						}

					}

					if (isPresent == 1) {
						moduleJson.setModuleId(moduleList.get(i).getModuleId());
						moduleJson.setModuleName(moduleList.get(i).getModuleName());
						moduleJson.setModuleDesc(moduleList.get(i).getModuleDesc());
						moduleJson.setOrderBy(moduleList.get(i).getOrderBy());
						moduleJson.setAccessRightSubModuleList(subModuleJsonList);
						moduleJsonList.add(moduleJson);
					}

				}

				if (moduleJsonList != null && !moduleJsonList.isEmpty()) {

					ObjectMapper mapper = new ObjectMapper();
					try {

						String newsLetterJSON = mapper.writeValueAsString(moduleJsonList);

						System.out.println("JSON  " + newsLetterJSON);
						empType.setEmpTypeAccess(newsLetterJSON);

					} catch (JsonProcessingException e) {

						e.printStackTrace();
					}

					EmpType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpType", empType,
							EmpType.class);

					if (res.isError() == false) {
						session.setAttribute("successMsg", "Record Insert Successfully");
					} else {
						session.setAttribute("errorMsg", "Failed to Insert Record");
					}

				} else {
					session.setAttribute("errorMsg", "Select Minimum One View Access.");
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

			List<AccessRightModule> moduleJsonList = new ArrayList<AccessRightModule>();

			try {

				AccessRightModule[] moduleJson = null;
				ObjectMapper mapper = new ObjectMapper();
				moduleJson = mapper.readValue(editEmpType.getEmpTypeAccess(), AccessRightModule[].class);
				moduleJsonList = new ArrayList<AccessRightModule>(Arrays.asList(moduleJson));

			} catch (Exception e) {

			}

			AccessRightModule[] accessRightModule = Constants.getRestTemplate()
					.getForObject(Constants.url + "/getModuleAndSubModuleList", AccessRightModule[].class);

			moduleList = new ArrayList<AccessRightModule>(Arrays.asList(accessRightModule));

			for (int i = 0; i < moduleList.size(); i++) {

				for (int j = 0; j < moduleJsonList.size(); j++) {

					if (moduleList.get(i).getModuleId() == moduleJsonList.get(j).getModuleId()) {

						System.out.println("match Module " + moduleList.get(i).getModuleName());

						for (int k = 0; k < moduleList.get(i).getAccessRightSubModuleList().size(); k++) {

							for (int m = 0; m < moduleJsonList.get(j).getAccessRightSubModuleList().size(); m++) {

								if (moduleList.get(i).getAccessRightSubModuleList().get(k)
										.getSubModuleId() == moduleJsonList.get(j).getAccessRightSubModuleList().get(m)
												.getSubModuleId()) {

									moduleList.get(i).getAccessRightSubModuleList().get(k)
											.setAddApproveConfig(moduleJsonList.get(j).getAccessRightSubModuleList()
													.get(m).getAddApproveConfig());
									moduleList.get(i).getAccessRightSubModuleList().get(k).setView(
											moduleJsonList.get(j).getAccessRightSubModuleList().get(m).getView());
									moduleList.get(i).getAccessRightSubModuleList().get(k).setEditReject(
											moduleJsonList.get(j).getAccessRightSubModuleList().get(m).getEditReject());
									moduleList.get(i).getAccessRightSubModuleList().get(k)
											.setDeleteRejectApprove(moduleJsonList.get(j).getAccessRightSubModuleList()
													.get(m).getDeleteRejectApprove());
									break;
								}

							}

						}

						break;
					}

				}

			}
			model.addObject("allModuleList", moduleList);
			model.addObject("moduleJsonList", moduleJsonList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditEmpType", method = RequestMethod.POST)
	public String submitEditEmpType(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			List<AccessRightModule> moduleJsonList = new ArrayList<>();

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

				for (int i = 0; i < moduleList.size(); i++) {

					AccessRightModule moduleJson = new AccessRightModule();
					int isPresent = 0;

					List<AccessRightSubModule> subModuleJsonList = new ArrayList<>();

					for (int j = 0; j < moduleList.get(i).getAccessRightSubModuleList().size(); j++) {

						AccessRightSubModule subModuleJson = new AccessRightSubModule();

						String view = request
								.getParameter(moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId()
										+ "view" + moduleList.get(i).getModuleId());

						try {
							if (view.equals("1")) {
								isPresent = 1;

								subModuleJson.setView(Integer.parseInt(view));
								subModuleJson.setSubModuleId(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId());
								subModuleJson.setModuleId(moduleList.get(i).getModuleId());
								subModuleJson.setSubModulName(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModulName());
								subModuleJson.setSubModuleDesc(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleDesc());
								subModuleJson.setSubModuleMapping(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleMapping());
								subModuleJson.setOrderBy(
										moduleList.get(i).getAccessRightSubModuleList().get(j).getOrderBy());

								try {
									int add = Integer.parseInt(request.getParameter(
											moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId()
													+ "add" + moduleList.get(i).getModuleId()));
									subModuleJson.setAddApproveConfig(add);
								} catch (Exception e) {
									subModuleJson.setAddApproveConfig(0);
								}

								try {
									int edit = Integer.parseInt(request.getParameter(
											moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId()
													+ "edit" + moduleList.get(i).getModuleId()));
									subModuleJson.setEditReject(edit);
								} catch (Exception e) {
									subModuleJson.setEditReject(0);
								}

								try {
									int delete = Integer.parseInt(request.getParameter(
											moduleList.get(i).getAccessRightSubModuleList().get(j).getSubModuleId()
													+ "delete" + moduleList.get(i).getModuleId()));
									subModuleJson.setDeleteRejectApprove(delete);
								} catch (Exception e) {
									subModuleJson.setDeleteRejectApprove(0);
								}

								subModuleJsonList.add(subModuleJson);

							}
						} catch (Exception e) {

						}

					}

					if (isPresent == 1) {
						moduleJson.setModuleId(moduleList.get(i).getModuleId());
						moduleJson.setModuleName(moduleList.get(i).getModuleName());
						moduleJson.setModuleDesc(moduleList.get(i).getModuleDesc());
						moduleJson.setOrderBy(moduleList.get(i).getOrderBy());
						moduleJson.setIconDiv(moduleList.get(i).getIconDiv());
						moduleJson.setAccessRightSubModuleList(subModuleJsonList);
						moduleJsonList.add(moduleJson);
					}

				}

				if (moduleJsonList != null && !moduleJsonList.isEmpty()) {

					ObjectMapper mapper = new ObjectMapper();
					try {

						String newsLetterJSON = mapper.writeValueAsString(moduleJsonList);

						System.out.println("JSON  " + newsLetterJSON);
						editEmpType.setEmpTypeAccess(newsLetterJSON);

					} catch (JsonProcessingException e) {

						e.printStackTrace();
					}

					EmpType res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpType", editEmpType,
							EmpType.class);

					if (res.isError() == false) {
						session.setAttribute("successMsg", "Record Updated Successfully");
					} else {
						session.setAttribute("errorMsg", "Failed to Updated Record");
					}

				} else {
					session.setAttribute("errorMsg", "Select Minimum One View Access.");
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

	@RequestMapping(value = "/employeeCatAdd", method = RequestMethod.GET)
	public ModelAndView employeeCatAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/employeeCatAdd");

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertEmpCat", method = RequestMethod.POST)
	public String submitInsertEmpCat(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String catName = request.getParameter("catName");
			String catShortName = request.getParameter("catShortName");
			String remark = request.getParameter("remark");

			Boolean ret = false;

			if (FormValidation.Validaton(catName, "") == true) {

				ret = true;
			}
			if (FormValidation.Validaton(catShortName, "") == true) {

				ret = true;
			}

			if (ret == false) {

				EmployeeCategory employeeCategory = new EmployeeCategory();

				employeeCategory.setEmpCatName(catName);
				employeeCategory.setEmpCatShortName(catShortName);
				employeeCategory.setEmpCatRemarks(remark);
				employeeCategory.setIsActive(1);
				employeeCategory.setDelStatus(1);
				employeeCategory.setMakerUserId(1);
				employeeCategory.setCompanyId(1);
				employeeCategory.setMakerEnterDatetime(sf.format(date));

				EmployeeCategory res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpCategory",
						employeeCategory, EmployeeCategory.class);

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

		return "redirect:/showEmpCatList";
	}

	@RequestMapping(value = "/showEmpCatList", method = RequestMethod.GET)
	public ModelAndView showEmpCatList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empCatList");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", 1);
			EmployeeCategory[] employeeCategory = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpCategoryList", map, EmployeeCategory[].class);

			List<EmployeeCategory> employeeCategorylist = new ArrayList<EmployeeCategory>(
					Arrays.asList(employeeCategory));

			for (int i = 0; i < employeeCategorylist.size(); i++) {

				employeeCategorylist.get(i)
						.setExVar1(FormValidation.Encrypt(String.valueOf(employeeCategorylist.get(i).getEmpCatId())));
			}

			model.addObject("empCatList", employeeCategorylist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/deleteEmpCategory", method = RequestMethod.GET)
	public String deleteEmpCategory(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("catId");
			String empCatId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empCatId", empCatId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteEmpCategory", map,
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
		return "redirect:/showEmpCatList";
	}

	@RequestMapping(value = "/editEmpCategory", method = RequestMethod.GET)
	public ModelAndView editEmpCategory(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empCatEdit");

		try {
			String base64encodedString = request.getParameter("catId");
			String catId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empCatId", catId);
			editEmpCategory = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpCategoryById", map,
					EmployeeCategory.class);
			model.addObject("editEmpCategory", editEmpCategory);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditEmpCat", method = RequestMethod.POST)
	public String submitEditEmpCat(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String catName = request.getParameter("catName");
			String catShortName = request.getParameter("catShortName");
			String remark = request.getParameter("remark");

			Boolean ret = false;

			if (FormValidation.Validaton(catName, "") == true) {

				ret = true;
			}
			if (FormValidation.Validaton(catShortName, "") == true) {

				ret = true;
			}

			if (ret == false) {

				editEmpCategory.setEmpCatName(catName);
				editEmpCategory.setEmpCatShortName(catShortName);
				editEmpCategory.setEmpCatRemarks(remark);
				editEmpCategory.setMakerUserId(1);
				editEmpCategory.setCompanyId(1);
				editEmpCategory.setMakerEnterDatetime(sf.format(date));

				EmployeeCategory res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpCategory",
						editEmpCategory, EmployeeCategory.class);

				if (res.isError() == false) {
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

		return "redirect:/showEmpCatList";
	}

	@RequestMapping(value = "/empDeptAdd", method = RequestMethod.GET)
	public ModelAndView empDeptAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empDeptAdd");

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitInsertEmpDept", method = RequestMethod.POST)
	public String submitInsertEmpDept(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String deptName = request.getParameter("deptName");
			String deptShortName = request.getParameter("deptShortName");
			String remark = request.getParameter("remark");

			Boolean ret = false;

			if (FormValidation.Validaton(deptName, "") == true) {

				ret = true;
			}
			if (FormValidation.Validaton(deptShortName, "") == true) {

				ret = true;
			}

			if (ret == false) {

				EmployeeDepartment employeeDepartment = new EmployeeDepartment();

				employeeDepartment.setEmpDeptName(deptName);
				employeeDepartment.setEmpDeptShortName(deptShortName);
				employeeDepartment.setEmpDeptRemarks(remark);
				employeeDepartment.setIsActive(1);
				employeeDepartment.setDelStatus(1);
				employeeDepartment.setMakerUserId(1);
				employeeDepartment.setCompanyId(1);
				employeeDepartment.setMakerEnterDatetime(sf.format(date));

				EmployeeDepartment res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpDept",
						employeeDepartment, EmployeeDepartment.class);

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

		return "redirect:/showEmpDeptList";
	}

	@RequestMapping(value = "/showEmpDeptList", method = RequestMethod.GET)
	public ModelAndView showEmpDeptList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empDeptList");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("compId", 1);
			EmployeeDepartment[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpDeptList", map, EmployeeDepartment[].class);

			List<EmployeeDepartment> employeeDepartmentlist = new ArrayList<EmployeeDepartment>(
					Arrays.asList(employeeDepartment));

			for (int i = 0; i < employeeDepartmentlist.size(); i++) {

				employeeDepartmentlist.get(i).setExVar1(
						FormValidation.Encrypt(String.valueOf(employeeDepartmentlist.get(i).getEmpDeptId())));
			}

			model.addObject("deptList", employeeDepartmentlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/deleteEmpDept", method = RequestMethod.GET)
	public String deleteEmpDept(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			String base64encodedString = request.getParameter("deptId");
			String deptId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empDeptId", deptId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteEmpDept", map, Info.class);

			if (info.isError() == false) {
				session.setAttribute("successMsg", "Deleted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Delete");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Delete");
		}
		return "redirect:/showEmpDeptList";
	}

	@RequestMapping(value = "/editEmpDept", method = RequestMethod.GET)
	public ModelAndView editEmpDept(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empDeptEdit");

		try {
			String base64encodedString = request.getParameter("deptId");
			String deptId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empDeptId", deptId);
			editEmployeeDepartment = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpDeptById", map,
					EmployeeDepartment.class);
			model.addObject("editEmpDept", editEmployeeDepartment);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/submitEditEmpDept", method = RequestMethod.POST)
	public String submitEditEmpDept(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {

			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			String deptName = request.getParameter("deptName");
			String deptShortName = request.getParameter("deptShortName");
			String remark = request.getParameter("remark");

			Boolean ret = false;

			if (FormValidation.Validaton(deptName, "") == true) {

				ret = true;
			}
			if (FormValidation.Validaton(deptShortName, "") == true) {

				ret = true;
			}

			if (ret == false) {

				editEmployeeDepartment.setEmpDeptName(deptName);
				editEmployeeDepartment.setEmpDeptShortName(deptShortName);
				editEmployeeDepartment.setEmpDeptRemarks(remark);
				editEmployeeDepartment.setMakerUserId(1);
				editEmployeeDepartment.setCompanyId(1);
				editEmployeeDepartment.setMakerEnterDatetime(sf.format(date));

				EmployeeDepartment res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpDept",
						editEmployeeDepartment, EmployeeDepartment.class);

				if (res.isError() == false) {
					session.setAttribute("successMsg", "Record Updeted Successfully");
				} else {
					session.setAttribute("errorMsg", "Failed to Updeted Record");
				}

			} else {
				session.setAttribute("errorMsg", "Failed to Updeted Record");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMsg", "Failed to Updeted Record");
		}

		return "redirect:/showEmpDeptList";
	}

	@RequestMapping(value = "/employeeAdd", method = RequestMethod.GET)
	public ModelAndView employeeAdd(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/employeeAdd");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);
			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			map = new LinkedMultiValueMap<>();
			map.add("compId", 1);

			EmpType[] EmpType = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpTypeList", map,
					EmpType[].class);
			List<EmpType> empTypelist = new ArrayList<EmpType>(Arrays.asList(EmpType));

			EmployeeDepartment[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpDeptList", map, EmployeeDepartment[].class);
			List<EmployeeDepartment> employeeDepartmentlist = new ArrayList<EmployeeDepartment>(
					Arrays.asList(employeeDepartment));

			EmployeeCategory[] employeeCategory = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpCategoryList", map, EmployeeCategory[].class);
			List<EmployeeCategory> employeeCategorylist = new ArrayList<EmployeeCategory>(
					Arrays.asList(employeeCategory));

			model.addObject("empTypelist", empTypelist);
			model.addObject("locationList", locationList);
			model.addObject("deptList", employeeDepartmentlist);
			model.addObject("catList", employeeCategorylist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	@RequestMapping(value = "/editEmp", method = RequestMethod.GET)
	public ModelAndView empEdit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empEdit");
		model.addObject("weighImageUrl", Constants.imageSaveUrl);
		try {
			String base64encodedString = request.getParameter("typeId");
			String compId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", compId);
			editEmp = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpInfoById", map,
					EmployeeInfo.class);
			model.addObject("editEmp", editEmp);
			
			map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			Location[] location = Constants.getRestTemplate().postForObject(Constants.url + "/getLocationList", map,
					Location[].class);
			List<Location> locationList = new ArrayList<Location>(Arrays.asList(location));

			map = new LinkedMultiValueMap<>();
			map.add("compId", 1);

			EmpType[] EmpType = Constants.getRestTemplate().postForObject(Constants.url + "/getEmpTypeList", map,
					EmpType[].class);
			List<EmpType> empTypelist = new ArrayList<EmpType>(Arrays.asList(EmpType));

			EmployeeDepartment[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpDeptList", map, EmployeeDepartment[].class);
			List<EmployeeDepartment> employeeDepartmentlist = new ArrayList<EmployeeDepartment>(
					Arrays.asList(employeeDepartment));

			EmployeeCategory[] employeeCategory = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpCategoryList", map, EmployeeCategory[].class);
			List<EmployeeCategory> employeeCategorylist = new ArrayList<EmployeeCategory>(
					Arrays.asList(employeeCategory));

			model.addObject("empTypelist", empTypelist);
			model.addObject("locationList", locationList);
			model.addObject("deptList", employeeDepartmentlist);
			model.addObject("catList", employeeCategorylist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	

	@RequestMapping(value = "/SubmitEditEmp", method = RequestMethod.POST)
	public String editEmp(@RequestParam("profilePic") List<MultipartFile> profilePic,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			VpsImageUpload upload = new VpsImageUpload();

			
			String empCode = request.getParameter("empCode");
			String fname = request.getParameter("fname");
			System.out.println("name is "+fname);
			String mname = request.getParameter("mname");
			String sname = request.getParameter("sname");
			int locId =Integer.parseInt( request.getParameter("locId"));
			int catId =Integer.parseInt( request.getParameter("catId"));
			int typeId =Integer.parseInt( request.getParameter("typeId"));
			int deptId =Integer.parseInt( request.getParameter("deptId"));
			String tempAdd = request.getParameter("tempAdd");
			String permntAdd = request.getParameter("permntAdd");
			String bloodGrp = request.getParameter("bloodGrp");
			String mobile1 = request.getParameter("mobile1");
			String mobile2 = request.getParameter("mobile2");
			String email = request.getParameter("email");
			String emgContPrsn1 = request.getParameter("emgContPrsn1");
			String emgContNo1 = request.getParameter("emgContNo1");
			String emgContPrsn2 = request.getParameter("emgContPrsn2");
			String emgContNo2 = request.getParameter("emgContNo2");
			int  ratePerHr =Integer.parseInt( request.getParameter("ratePerHr"));
			String joiningDate = request.getParameter("joiningDate");
			int prevsExpYr =Integer.parseInt( request.getParameter("prevsExpYr"));
			int prevsExpMn =Integer.parseInt( request.getParameter("prevsExpMn"));
			String leavingDate = request.getParameter("leavingDate");
			String lvngReson = request.getParameter("lvngReson");
			 Boolean ret = false;
			
			if(ret == false)
			{
				
				if(profilePic.get(0).getOriginalFilename()!="") {
					String imageName = new String(); imageName = dateTimeInGMT.format(date) + "_" + profilePic.get(0).getOriginalFilename();
					 
					  try {
							  upload.saveUploadedImge(profilePic.get(0), Constants.imageSaveUrl, imageName,
							  Constants.values, 0, 0, 0, 0, 0); 
							  editEmp.setEmpPhoto(imageName);
							  }
							  catch (Exception e) { 
								 System.out.println(e.getMessage());  
								   }
				}
	   
			//leaveSummary.setEmpPhoto("siri");;
			editEmp.setCompanyId(1);
			editEmp.setEmpCode(empCode);
			editEmp.setEmpCatId(catId);
			editEmp.setEmpDeptId(deptId);
			editEmp.setEmpTypeId(typeId);
			editEmp.setLocId(locId);
			editEmp.setEmpFname(fname);
			editEmp.setEmpMname(mname);
			editEmp.setEmpSname(sname);
			editEmp.setEmpMobile1(mobile1);
			editEmp.setEmpMobile2(mobile2);
			editEmp.setEmpEmail(email);
			editEmp.setEmpAddressTemp(tempAdd);
			editEmp.setEmpAddressPerm(permntAdd);
			editEmp.setEmpBloodgrp(bloodGrp);
			editEmp.setEmpEmergencyPerson1(emgContPrsn1);
			editEmp.setEmpEmergencyPerson2(emgContPrsn2);
			editEmp.setEmpEmergencyNo2(emgContNo2);
			editEmp.setEmpEmergencyNo1(emgContNo1);
			editEmp.setEmpRatePerhr(ratePerHr);
			
			editEmp.setEmpJoiningDate(DateConvertor.convertToYMD(joiningDate));
			editEmp.setEmpLeavingDate(DateConvertor.convertToYMD(leavingDate));
			
			editEmp.setEmpPrevExpYrs(prevsExpYr);
			editEmp.setEmpPrevExpMonths(prevsExpMn);
			editEmp.setEmpLeavingReason(lvngReson);
			
			
			editEmp.setMakerUserId(1);
			editEmp.setMakerEnterDatetime(sf.format(date));

			

			EmployeeInfo res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpInfo", editEmp,
					EmployeeInfo.class);
			System.err.println("edited success");
			
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}
			
			
			

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showEmpList";
		
	

}
	
	
	@RequestMapping(value = "/submitInsertEmployeeUserInfo", method = RequestMethod.POST)
	public String submitInsertLeaveType(@RequestParam("profilePic") List<MultipartFile> profilePic,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			VpsImageUpload upload = new VpsImageUpload();

			
			
			String empCode = request.getParameter("empCode");
			String fname = request.getParameter("fname");
			String mname = request.getParameter("mname");
			String sname = request.getParameter("sname");
			int locId =Integer.parseInt( request.getParameter("locId"));
			int catId =Integer.parseInt( request.getParameter("catId"));
			int typeId =Integer.parseInt( request.getParameter("typeId"));
			int deptId =Integer.parseInt( request.getParameter("deptId"));
			String tempAdd = request.getParameter("tempAdd");
			String permntAdd = request.getParameter("permntAdd");
			String bloodGrp = request.getParameter("bloodGrp");
			String mobile1 = request.getParameter("mobile1");
			String mobile2 = request.getParameter("mobile2");
			String email = request.getParameter("email");
			String emgContPrsn1 = request.getParameter("emgContPrsn1");
			String emgContNo1 = request.getParameter("emgContNo1");
			String emgContPrsn2 = request.getParameter("emgContPrsn2");
			String emgContNo2 = request.getParameter("emgContNo2");
			int  ratePerHr =Integer.parseInt( request.getParameter("ratePerHr"));
			String joiningDate = request.getParameter("joiningDate");
			int prevsExpYr =Integer.parseInt( request.getParameter("prevsExpYr"));
			int prevsExpMn =Integer.parseInt( request.getParameter("prevsExpMn"));
			String leavingDate = request.getParameter("leavingDate");
			String lvngReson = request.getParameter("lvngReson");
			String uname = request.getParameter("uname");
			String upass = request.getParameter("upass");
			///int locId2 =Integer.parseInt( request.getParameter("locId2"));
			String[] locId2 = request.getParameterValues("locId2");

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < locId2.length; i++) {
				sb = sb.append(locId2[i] + ",");

			}
			String items = sb.toString();
			items = items.substring(0, items.length() - 1);
			System.err.println("loc are:::"+items);
			/*
			 * int summId =Integer.parseInt( request.getParameter("summId"));
			 * 
			 * String remark=null;
			 * 
			 * System.out.println("color    "+leaveColor); int isStructured =
			 * Integer.parseInt( request.getParameter("isStructured")); try { remark =
			 * request.getParameter("remark"); } catch (Exception e) { remark = "NA"; }
			 */

			Boolean ret = false;
			if (FormValidation.Validaton(profilePic.get(0).getOriginalFilename(), "") == true) {
				ret = true;
			}

			if (FormValidation.Validaton(empCode, "") == true) {

				ret = true;
				System.out.println("empCode" + ret);
			}
			if (FormValidation.Validaton(fname, "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}

			if (FormValidation.Validaton(request.getParameter("mname"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			if (FormValidation.Validaton(request.getParameter("sname"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("locId"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			if (FormValidation.Validaton(request.getParameter("catId"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			 if (FormValidation.Validaton(request.getParameter("typeId"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if(FormValidation.Validaton(request.getParameter("deptId"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			if (FormValidation.Validaton(request.getParameter("tempAdd"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("permntAdd"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("bloodGrp"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("mobile1"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("email"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("emgContPrsn1"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("emgContNo1"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("emgContPrsn2"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("emgContNo2"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			
			
			if (FormValidation.Validaton(request.getParameter("ratePerHr"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("joiningDate"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("prevsExpYr"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("prevsExpMn"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("uname"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("upass"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			if (FormValidation.Validaton(request.getParameter("locId2"), "") == true) {

				ret = true;
				System.out.println("add" + ret);
			}
			
			EmployeeInfo leaveSummary = new EmployeeInfo();
			if(ret == false)
			{
				
				
	  String imageName = new String(); imageName = dateTimeInGMT.format(date) + "_" + profilePic.get(0).getOriginalFilename();
				 
		  try {
				  upload.saveUploadedImge(profilePic.get(0), Constants.imageSaveUrl, imageName,
				  Constants.values, 0, 0, 0, 0, 0); leaveSummary.setEmpPhoto(imageName);
				  }
				  catch (Exception e) { 
					 System.out.println(e.getMessage());  
					   }
				 
				 

			//leaveSummary.setEmpPhoto("siri");;
			leaveSummary.setCompanyId(1);
			leaveSummary.setEmpCode(empCode);
			leaveSummary.setEmpCatId(catId);
			leaveSummary.setEmpDeptId(deptId);
			leaveSummary.setEmpTypeId(typeId);
			leaveSummary.setLocId(locId);
			leaveSummary.setEmpFname(fname);
			leaveSummary.setEmpMname(mname);
			leaveSummary.setEmpSname(sname);
			leaveSummary.setEmpMobile1(mobile1);
			leaveSummary.setEmpMobile2(mobile2);
			leaveSummary.setEmpEmail(email);
			leaveSummary.setEmpAddressTemp(tempAdd);
			leaveSummary.setEmpAddressPerm(permntAdd);
			leaveSummary.setEmpBloodgrp(bloodGrp);
			leaveSummary.setEmpEmergencyPerson1(emgContPrsn1);
			leaveSummary.setEmpEmergencyPerson2(emgContPrsn2);
			leaveSummary.setEmpEmergencyNo2(emgContNo2);
			leaveSummary.setEmpEmergencyNo1(emgContNo1);
			leaveSummary.setEmpRatePerhr(ratePerHr);
			
			leaveSummary.setEmpJoiningDate(DateConvertor.convertToYMD(joiningDate));
			leaveSummary.setEmpLeavingDate(DateConvertor.convertToYMD(leavingDate));
			
			leaveSummary.setEmpPrevExpYrs(prevsExpYr);
			leaveSummary.setEmpPrevExpMonths(prevsExpMn);
			leaveSummary.setEmpLeavingReason(lvngReson);
			
			leaveSummary.setExInt1(1);
			leaveSummary.setExInt2(1);
			leaveSummary.setExInt3(1);
			leaveSummary.setExVar1("NA");
			leaveSummary.setExVar2("NA");
			leaveSummary.setExVar3("NA");
			leaveSummary.setIsActive(1);
			leaveSummary.setDelStatus(1);
			leaveSummary.setMakerUserId(1);
			leaveSummary.setMakerEnterDatetime(sf.format(date));

			

			EmployeeInfo res = Constants.getRestTemplate().postForObject(Constants.url + "/saveEmpInfo", leaveSummary,
					EmployeeInfo.class);
			if(res!=null) {
			
			User uinfo = new User();
			uinfo.setEmpId(res.getEmpId());
			uinfo.setEmpTypeId(typeId);
			uinfo.setUserName(uname);
			uinfo.setUserPwd(upass);
			uinfo.setLocId(items);
			uinfo.setExInt1(1);
			uinfo.setExInt2(1);
			uinfo.setExInt3(1);
			uinfo.setExVar1("NA");
			uinfo.setExVar2("NA");
			uinfo.setExVar3("NA");
			uinfo.setIsActive(1);
			uinfo.setDelStatus(1);
			uinfo.setMakerUserId(1);
			uinfo.setMakerEnterDatetime(sf.format(date));

			User res1 = Constants.getRestTemplate().postForObject(Constants.url + "/saveUserInfo", uinfo,
					User.class);
			
			}
			
			
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showEmpList";
	

}
	
	@RequestMapping(value = "/showEmpList", method = RequestMethod.GET)
	public ModelAndView showEmpList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/empList");

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("companyId", 1);
			map.add("locIdList", 1);
			
			GetEmployeeInfo[] employeeDepartment = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getEmpInfoList", map, GetEmployeeInfo[].class);

			List<GetEmployeeInfo> employeeDepartmentlist = new ArrayList<GetEmployeeInfo>(
					Arrays.asList(employeeDepartment));

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
	
	
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public String deleteEmployee(HttpServletRequest request, HttpServletResponse response) {

		try {
//System.err.println("request.getParameter(\"empId\")"+request.getParameter("typeId"));
			String base64encodedString = request.getParameter("typeId");
			String typeId = FormValidation.DecodeKey(base64encodedString);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("empId", typeId);
			Info info = Constants.getRestTemplate().postForObject(Constants.url + "/deleteEmpInfo", map, Info.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showEmpList";
	}


}
