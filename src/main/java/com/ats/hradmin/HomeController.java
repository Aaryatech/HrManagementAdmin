package com.ats.hradmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.hradmin.common.Constants;
import com.ats.hradmin.model.LoginResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}*/
	
	
	
	
	
	

	
	
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
	
	@RequestMapping(value = "/listSample", method = RequestMethod.GET)
	public ModelAndView listSample(Locale locale, Model model) {
		 
		
		ModelAndView mav = new ModelAndView("listSample");
		
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

		
			System.out.println("Login Process " + name+password);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("login");
			} 
			else {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				System.out.println("Login Process  2" + name+password);
				map.add("username", name);
				map.add("userPass", password);

				LoginResponse userObj =  Constants.getRestTemplate().postForObject(Constants.url+"login",map,LoginResponse.class);
				System.out.println("JSON Response Objet " + userObj.toString());
				String loginResponseMessage = "";

				if (userObj.getErrMsg().equals("false") ) {
					
					mav = new ModelAndView("formSample");
					session.setAttribute("UserDetail", userObj);

					session.setAttribute("empDetail", userObj.getGetData());
					
					session.setAttribute("empTypeDetail", userObj.getEmpType());

					
					session.setAttribute("userName", name);

					loginResponseMessage = "Login Successful";
					mav.addObject("loginResponseMessage", loginResponseMessage);

					map = new LinkedMultiValueMap<String, Object>();
					//int userId = userObj.getUser().getUserId();
					//map.add("userId", userId);
					///System.out.println("user data" + userResponse.toString());

					
					return mav;

				} else {
					session.setAttribute("errorMsg", "Login Failed");
					mav = new ModelAndView("login");
					System.out.println("Invalid login credentials");

				}

			}
}
catch(Exception e) {
	e.printStackTrace();
}

		return mav;

	}
	
}
