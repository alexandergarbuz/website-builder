package com.garbuz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.garbuz.web.model.page.ContactUsPage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ContactUsController {

	private static final Logger LOG = LoggerFactory.getLogger(ContactUsController.class);
	
	@GetMapping("/contact")
	public ModelAndView showPage(final HttpServletRequest request, final HttpServletResponse response) {
		LOG.debug("Request for Contact Us page is received");
		
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		
		
		ContactUsPage contactUsPage = new ContactUsPage();
		contactUsPage.setTitle("Contact Us");
		contactUsPage.setCsrfHeader(csrfToken.getHeaderName());
		contactUsPage.setCsrfToken(csrfToken.getToken());
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("homePage");
		mv.addObject(NavigationUtils.PAGE_OBJECT_KEY, contactUsPage);
		mv.getModel().put(NavigationUtils.BODY_FRAGMENT_KEY, "contactUsFragment");
		LOG.debug("Returning {}", contactUsPage);
		return mv;
	}
}
