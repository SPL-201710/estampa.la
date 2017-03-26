package controllers;

import java.util.Collections;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import exceptions.UserNotFoundException;
import model.UserAuth;
import services.SecurityService;
import services.TokenAuthenticationService;

@RestController
@RequestMapping("/auth")
public class SecurityController {
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value = "/token",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody UserAuth auth) throws CredentialException, UserNotFoundException {
		
		return securityService.login(auth.getUsername(), auth.getPassword());
	}
	
	@RequestMapping(value = "/token",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String validateToken(WebRequest parameters) throws CredentialException, UserNotFoundException {
		
		return securityService.login(parameters.getParameter("token"));
	}

}
