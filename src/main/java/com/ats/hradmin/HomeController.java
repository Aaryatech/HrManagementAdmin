package com.ats.hradmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.common.FormValidation;
import com.ats.hradmin.common.VpsImageUpload;
import com.ats.hradmin.leave.model.CalenderYear;
import com.ats.hradmin.leave.model.GetAuthorityIds;
import com.ats.hradmin.model.AccessRightModule;
import com.ats.hradmin.model.AuthorityInformation;
import com.ats.hradmin.model.Company;
import com.ats.hradmin.model.EmployeeInfo;
import com.ats.hradmin.model.Info;
import com.ats.hradmin.model.LoginResponse;
import com.ats.hradmin.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate );
	 * 
	 * return "home"; }
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {

		ModelAndView mav = new ModelAndView("login");

		return mav;
	}

	/*
	 * @RequestMapping(value = "/home", method = RequestMethod.GET) public
	 * ModelAndView home(Locale locale, Model model) {
	 * 
	 * 
	 * ModelAndView mav = new ModelAndView("home");
	 * 
	 * return mav; }
	 */

	@RequestMapping(value = "/formSample", method = RequestMethod.GET)
	public ModelAndView formSample(Locale locale, Model model) {

		ModelAndView mav = new ModelAndView("formSample");

		return mav;
	}
	
	@RequestMapping(value = "/changePass", method = RequestMethod.GET)
	public ModelAndView changePass(HttpServletRequest request, HttpServletResponse res) {
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
		ModelAndView mav = new ModelAndView("changePassword");
		mav.addObject("empId",userObj.getUserId());

		return mav;
	}
	
	@RequestMapping(value = "/checkPass", method = RequestMethod.GET)
	public @ResponseBody User updateLeaveLimit(HttpServletRequest request, HttpServletResponse response) {

		User user1=new User();		  
		try {
			System.err.println("in  checkPass is ");
			String empId=(request.getParameter("empId"));
			String password=(request.getParameter("password"));
			
			  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("empId",empId);
			  map.add("password",password);
			  
				    user1 = Constants.getRestTemplate().postForObject(Constants.url + "/getUserInfoByEmpIdPass", map, User.class);
				System.err.println("info is "+ user1);
				
				   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user1;
	}
	@RequestMapping(value = "/submitUpdatePass", method = RequestMethod.POST)
	public String submitInsertCompany(  HttpServletRequest request,
			HttpServletResponse response) {

		try {
			/*
			 * HttpSession session = request.getSession(); LoginResponse userObj =
			 * (LoginResponse) session.getAttribute("UserDetail"); Date date = new Date();
			 * SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 * SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			 * VpsImageUpload upload = new VpsImageUpload();
			 */
			String empId = request.getParameter("empId");
			String password = request.getParameter("password");

			Boolean ret = false;

			 

			if (ret == false) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("empId", empId);
				map.add("password", password);
				Info info = Constants.getRestTemplate().postForObject(Constants.url + "/updateUserPass", map, Info.class);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/changePass";
	}


	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public ModelAndView fileUpload(Locale locale, Model model) {

		ModelAndView mav = new ModelAndView("fileUpload");

		return mav;
	}

	@RequestMapping(value = "/listSample", method = RequestMethod.GET)
	public ModelAndView listSample(Locale locale, Model model) {

		ModelAndView mav = new ModelAndView("listSample");

		return mav;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse res) {

		ModelAndView mav = new ModelAndView("welcome");
		HttpSession session = request.getSession();
		session.setAttribute("sessionModuleId", 0);
		session.setAttribute("sessionSubModuleId", 0);

		try {
			LoginResponse userObj = (LoginResponse) session.getAttribute("UserDetail");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
			map.add("empId", userObj.getEmpId());

			AuthorityInformation authorityInformation = Constants.getRestTemplate()
					.postForObject(Constants.url + "/getAuthorityInfoByEmpId", map, AuthorityInformation.class);
			mav.addObject("authorityInformation", authorityInformation);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping("/loginProcess")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse res) throws IOException {
		ModelAndView mav = new ModelAndView();
		try {
			String name = request.getParameter("username");
			String password = request.getParameter("password");

			mav = new ModelAndView("login");
			res.setContentType("text/html");
			PrintWriter pw = res.getWriter();
			HttpSession session = request.getSession();

			System.out.println("Login Process " + name + password);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("login");
			} else {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				System.out.println("Login Process  2" + name + password);
				map.add("username", name);
				map.add("userPass", password);

				LoginResponse userObj = Constants.getRestTemplate().postForObject(Constants.url + "login", map,
						LoginResponse.class);
				System.out.println("JSON Response Objet " + userObj.toString());
				String loginResponseMessage = "";

				if (userObj.isError() == false) {

					mav = new ModelAndView("welcome");
					session.setAttribute("UserDetail", userObj);
					CalenderYear currYr = Constants.getRestTemplate()
							.getForObject(Constants.url + "getCalculateYearListIsCurrent", CalenderYear.class);
					System.out.println("currYr.getCalYrId():" + currYr.getCalYrId());
					session.setAttribute("currYearId", currYr.getCalYrId());
					session.setAttribute("logoUrl", Constants.getImageSaveUrl);

					List<AccessRightModule> moduleJsonList = new ArrayList<AccessRightModule>();

					try {

						AccessRightModule[] moduleJson = null;
						ObjectMapper mapper = new ObjectMapper();
						moduleJson = mapper.readValue(userObj.getEmpTypeAccess(), AccessRightModule[].class);
						moduleJsonList = new ArrayList<AccessRightModule>(Arrays.asList(moduleJson));
						session.setAttribute("sessionModuleId", 0);
						session.setAttribute("sessionSubModuleId", 0);

					} catch (Exception e) {

					}

					session.setAttribute("moduleJsonList", moduleJsonList);

					loginResponseMessage = "Login Successful";
					mav.addObject("loginResponseMessage", loginResponseMessage);

					map = new LinkedMultiValueMap<>();
					map.add("empId", userObj.getEmpId());

					AuthorityInformation authorityInformation = Constants.getRestTemplate()
							.postForObject(Constants.url + "/getAuthorityInfoByEmpId", map, AuthorityInformation.class);
					mav.addObject("authorityInformation", authorityInformation);

					return mav;

				} else {
					session.setAttribute("errorMsg", "Login Failed");
					mav = new ModelAndView("login");
					System.out.println("Invalid login credentials");

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mav;

	}

	@RequestMapping(value = "/setSubModId", method = RequestMethod.GET)
	public @ResponseBody void setSubModId(HttpServletRequest request, HttpServletResponse response) {
		int subModId = Integer.parseInt(request.getParameter("subModId"));
		int modId = Integer.parseInt(request.getParameter("modId"));
		/*
		 * System.out.println("subModId " + subModId); System.out.println("modId " +
		 * modId);
		 */
		HttpSession session = request.getSession();
		session.setAttribute("sessionModuleId", modId);
		session.setAttribute("sessionSubModuleId", subModId);
		session.removeAttribute("exportExcelList");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/sessionTimeOut", method = RequestMethod.GET)
	public String sessionTimeOut(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/uploadOtherMediaProccess", method = RequestMethod.POST)
	public void uploadOtherMediaProccess(@RequestParam("file") List<MultipartFile> file, HttpServletRequest request,
			HttpServletResponse response) {

		try {

			Date date = new Date();
			SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			String imageName = new String();

			System.out.println("sdfsdfsdf" + file.get(0).getOriginalFilename());
			/*
			 * imageName = dateTimeInGMT.format(date)+"_"+file.get(0).getOriginalFilename();
			 * VpsImageUpload upload = new VpsImageUpload();
			 * 
			 * 
			 * try { upload.saveUploadedImge(file.get(0),
			 * Constant.otherDocURL,imageName,Constant.values,0,0,0,0,0);
			 * upload.saveUploadedImge(file.get(0),
			 * Constant.uploadDocURL,imageName,Constant.DocValues,0,0,0,0,0);
			 * 
			 * }catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return "redirect:/fileUpload";
		// return "{}";

	}

	// *************************Forgot
	// Pass***********************************************

	@RequestMapping(value = "/showForgotPassForm", method = RequestMethod.GET)
	public ModelAndView showForgotPassForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("forgetPassword");

		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/sendForgotPass", method = RequestMethod.POST)
	public ModelAndView checkUniqueField(HttpServletRequest request, HttpServletResponse response) {
		String c = null;
		System.err.println("Hiii  checkValue  ");
		Info info = new Info();
		ModelAndView model = null;

		try {
			// model = new ModelAndView("forgotPassword");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("username");
			System.err.println("Info inputValue  " + inputValue);

			map.add("inputValue", inputValue);

			info = Constants.getRestTemplate().postForObject(Constants.url + "checkUserName", map, Info.class);
			System.err.println("Info Response  " + info.toString());

			if (info.isError() == true) {
				model = new ModelAndView("forgotPassword");
				// c="redirect:/showforgotPassForm";
				model.addObject("msg", "Invalid User Name");

			} else {
				model = new ModelAndView("verifyOTP");
				// c= "redirect:/showVerifyOTP";
				model.addObject("username", info.getMsg());
			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

}
