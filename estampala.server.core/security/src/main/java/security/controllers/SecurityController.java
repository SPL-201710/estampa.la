package security.controllers;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import security.exceptions.InvalidTokenException;
import security.model.UserAuth;
import security.services.SecurityService;
import users.exceptions.UserNotFoundException;

@RestController
@RequestMapping("/auth")
public class SecurityController {

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/login",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody UserAuth auth) throws CredentialException, UserNotFoundException {

		return securityService.login(auth.getUsername(), auth.getPassword());
	}

	@RequestMapping(value = "/token",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String validateToken(WebRequest parameters) throws CredentialException, UserNotFoundException, InvalidTokenException {

		return securityService.validateToken(parameters.getParameter("token"));
	}

}
