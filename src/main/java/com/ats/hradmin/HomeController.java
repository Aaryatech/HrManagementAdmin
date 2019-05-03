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
import com.ats.hradmin.leave.model.CalenderYear;
import com.ats.hradmin.model.AccessRightModule;
import com.ats.hradmin.model.LoginResponse;
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

					map = new LinkedMultiValueMap<String, Object>();
					// int userId = userObj.getUser().getUserId();
					// map.add("userId", userId);
					/// System.out.println("user data" + userResponse.toString());

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
}
