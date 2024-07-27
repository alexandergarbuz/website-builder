package com.garbuz.web.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/csrf")
public class CsrfController {

	@GetMapping("/token")
	public CsrfToken getToken(final HttpServletRequest request, final HttpServletResponse response) {
        // The CsrfToken is automatically available in the HttpServletRequest
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        // Add the CSRF token to the response header
        response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken());

        return csrfToken;
	}
}
