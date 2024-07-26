package com.garbuz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.garbuz.web.model.LoginPage;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/login")
	public ModelAndView login(final HttpServletRequest request) {
		LOG.debug("Logging request received");

		 
		LoginPage loginPage = new LoginPage();
		loginPage.setTitle("Log In");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loginPage");
		mv.addObject("loginPage", loginPage);
		return mv;
	}
}
