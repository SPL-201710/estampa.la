package users.controllers;

import java.util.UUID;

import javax.security.auth.login.CredentialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import commons.controllers.EstampalaController;
import commons.exceptions.EstampalaException;
import commons.responses.SuccessResponse;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import users.exceptions.InvalidTokenException;
import users.exceptions.UserAlreadyExistsException;
import users.exceptions.UserNotFoundException;
import users.models.User;
import users.models.UserSession;
import users.pojos.UserCreator;
import users.pojos.UserLogin;
import users.services.SecurityService;
import users.services.UserService;

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

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page,
											@RequestParam(value="page_size", defaultValue="10", required = false) int pageSize,
											@RequestParam(value="order", defaultValue="asc", required = false) String popularity,
											@And({	@Spec(path = "roles.name", params={"role"}, spec = Equal.class),
													@Spec(path = "username", params={"username"}, spec = Equal.class),
													@Spec(path = "firstName", params={"firstName"}, spec = Like.class),
													@Spec(path = "lastName", params={"lastName"}, spec = Equal.class),
													@Spec(path = "email", params={"email"}, spec = Equal.class)}) Specification<User> spec) {


		return new ResponseEntity<Page<User>>(userService.findAll(page, pageSize, popularity, spec), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> get(@PathVariable UUID id) throws UserNotFoundException {
		if(!userService.exists(id)) {
			throw new UserNotFoundException(id);
		}

		return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody UserCreator user) throws EstampalaException {
		if(userService.exists(user.getUsername())) {
			throw new UserAlreadyExistsException(user.getUsername());
		}

		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserCreator user) throws EstampalaException {
		if(!userService.exists(id)) {
			throw new UserNotFoundException(id);
		}
		user.setUser(id);
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) throws UserNotFoundException {

		if(!userService.exists(id)) {
			throw new UserNotFoundException(id);
		}

		userService.deleteUser(id);

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The user was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}

	@CrossOrigin
	@RequestMapping(value = "/login",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserSession login(@RequestBody UserLogin auth) throws CredentialException, UserNotFoundException {

		return securityService.login(auth.getUsername(), auth.getPassword());
	}

	@CrossOrigin
	@RequestMapping(value = "/logout/",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> logout(@RequestParam(value="token", required = true) String token) throws InvalidTokenException, UserNotFoundException {

		securityService.logout(token);
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The token was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}

	@CrossOrigin
	@RequestMapping(value = "/auth/",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserSession validateToken(@RequestParam(value="token", required = true) String token) throws CredentialException, UserNotFoundException, InvalidTokenException {

		return securityService.validateToken(token);
	}
}
