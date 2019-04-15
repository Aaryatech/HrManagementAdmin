package com.ats.hradmin.controller;

import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
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
import com.ats.hradmin.model.Info;

@Controller
@Scope("session")
public class MasterController {

	Company editCompany = new Company();
	
 

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

			String base64encodedString =  request.getParameter("compId"); 
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
			String base64encodedString =  request.getParameter("compId"); 
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

}
