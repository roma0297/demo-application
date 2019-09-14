package ru.raiffeisen.demoapplication.controllers;

import java.security.Principal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthenticationController {
	@RequestMapping("/user")
	public Principal getUser(Principal user) {
		return user;
	}
}
