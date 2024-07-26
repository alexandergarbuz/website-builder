package com.garbuz.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.garbuz.web.model.LogoutPage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {
	
	private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);

    @GetMapping("/logout")
    public ModelAndView logout(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) {
    	LOG.info("Loggout request received");
    	/*
    	 * Implement your pre-logout logic here. 
    	 * Close connections, save shoppoing cart, etc.
    	 */
    	LOG.debug("About to log out");
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        /*
         * Implement your post logout logic here. Notify exeternal systems, etc.
         */
        LOG.debug("Logged out");
        LogoutPage logoutPage = new LogoutPage();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/login?logout=true");
		mv.addObject("logoutPage", logoutPage);
        return mv;
    }
}
