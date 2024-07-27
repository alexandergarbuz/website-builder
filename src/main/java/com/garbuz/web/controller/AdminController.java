package com.garbuz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.garbuz.web.model.page.ControlPanelPage;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private Logger LOG = LoggerFactory.getLogger(AdminController.class);

	@GetMapping("/home")
	public ModelAndView home(final HttpServletRequest request) {
		final ModelAndView mv = new ModelAndView();
		final ControlPanelPage controlPanelPage = new ControlPanelPage();
		controlPanelPage.setTitle("Control Panel");
		
		mv.setViewName("adminPage");
		mv.getModel().put("contentTemplate", "controlPanelBodyFragment");
		
		mv.addObject("controlPanelPage", controlPanelPage);
		
		
		LOG.debug("Returning {} page", controlPanelPage);
		return mv;
	}
	
	
}
