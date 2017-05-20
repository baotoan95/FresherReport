package com.fresher.report.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestLoginController {

	@RequestMapping(value = "/authenticated", method = RequestMethod.GET)
	public Boolean user(Authentication auth) {
		if(auth == null) {
			return false;
		}
		return auth.isAuthenticated();
	}

}
