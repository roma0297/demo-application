package ru.raiffeisen.demoapplication.controllers;

import java.security.Principal;
import java.util.Base64;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserAuthenticationController {
	
	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
