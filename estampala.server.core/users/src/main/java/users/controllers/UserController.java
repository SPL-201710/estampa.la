package users.controllers;

import java.util.UUID;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commons.controllers.EstampalaController;
import commons.responses.SuccessResponse;

import users.exceptions.UserAlreadyExistsException;
import users.exceptions.UserNotFoundException;
import users.exceptions.InvalidTokenException;
import users.models.User;
import users.models.UserAuth;
import users.models.UserSession;
import users.services.UserService;
import users.services.SecurityService;
import users.services.SecurityService;

import javax.security.auth.login.CredentialException;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

/**
 *
 * @author Jorge
 */
@RestController
@RequestMapping("/users")
public class UserController extends EstampalaController{

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<User>> getAll(@RequestParam(value="page", defaultValue = "1", required = true) int page,
			@RequestParam(value="page_size", defaultValue = "1", required = true) int pageSize) {

		return new ResponseEntity<Page<User>>(userService.findAll(page, pageSize), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> get(@PathVariable UUID id) throws UserNotFoundException {
		if(!userService.exists(id)) {
			throw new UserNotFoundException(id);
		}

		return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/filter/",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> get(@RequestParam(value="username", required = true) String username) throws UserNotFoundException {
		if(!userService.exists(username)) {
			throw new UserNotFoundException(username);
		}

		return new ResponseEntity<User>(userService.findUserByUsername(username), HttpStatus.OK);
	}

	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) throws UserAlreadyExistsException {
		if(userService.exists(user.getUsername())) {
			throw new UserAlreadyExistsException(user.getUsername());
		}

		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) throws UserNotFoundException {
		if(!userService.exists(id)) {
			throw new UserNotFoundException(id);
		}
		user.setId(id);
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws UserNotFoundException {

		if(!userService.exists(id)) {
			throw new UserNotFoundException(id);
		}

		userService.delete(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The user was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}

	@RequestMapping(value = "/login",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserSession login(@RequestBody UserAuth auth) throws CredentialException, UserNotFoundException {

		return securityService.login(auth.getUsername(), auth.getPassword());
	}

	@RequestMapping(value = "/logout/",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> logout(@RequestParam(value="token", required = true) String token) throws InvalidTokenException, UserNotFoundException {

		securityService.logout(token);
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The token was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}

	@RequestMapping(value = "/auth/",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserSession validateToken(@RequestParam(value="token", required = true) String token) throws CredentialException, UserNotFoundException, InvalidTokenException {

		return securityService.validateToken(token);
	}
}
