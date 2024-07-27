package com.garbuz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.garbuz.web.model.ContactUsPage;

@Controller
public class ContactUsController {

	private static final Logger LOG = LoggerFactory.getLogger(ContactUsController.class);
	
	@GetMapping("/contact")
	public ModelAndView showPage() {
		LOG.debug("Request for Contact Us page is received");
		ContactUsPage contactUsPage = new ContactUsPage();
		contactUsPage.setTitle("Contact Us");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("homePage");
		mv.addObject("pageObject", contactUsPage);
		mv.getModel().put("contentTemplate", "contactUsFragment");
		return mv;
	}
}
