package com.garbuz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.garbuz.web.model.HomePage;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@GetMapping("/home")
	public ModelAndView home(final HttpServletRequest request) {
		final ModelAndView mv = new ModelAndView();
		final HomePage homePage = new HomePage();
		homePage.setTitle("Website Builder");
		
		mv.setViewName("homePage");
		mv.getModel().put("contentTemplate", "homepageBodyFragment");
		
		mv.addObject("homePage", homePage);
		
		
		LOG.debug("Returning {} page", homePage);
		return mv;
	}
	
	
}