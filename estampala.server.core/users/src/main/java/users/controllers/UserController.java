package users.controllers;

import java.util.UUID;

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
import commons.exceptions.ResourceNotFoundException;
import commons.responses.SuccessResponse;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import users.exceptions.CredentialsException;
import users.exceptions.InvalidTokenException;
import users.exceptions.RequiredParameterException;
import users.exceptions.UserAlreadyExistsException;
import users.exceptions.UserNotActiveException;
import users.exceptions.UserNotFoundException;
import users.models.User;
import users.models.UserSession;
import users.pojos.UserAuthData;
import users.pojos.UserCreator;
import users.pojos.UserLogin;
import users.pojos.UserSocialLogin;
import users.services.AuthenticationMethods;
import users.services.SecurityService;
import users.services.UserServiceFactory;

/**
 *
 * @author Jorge
 */
@RestController
@RequestMapping("/users")
public class UserController extends EstampalaController{

	@Autowired
	private UserServiceFactory userServiceFactory;

	@Autowired
	private SecurityService securityService;

	@CrossOrigin
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getAll(@RequestParam(value="page", defaultValue="1", required = false) int page,
											@RequestParam(value="page_size", defaultValue="10", required = false) int pageSize,
											@RequestParam(value="order", defaultValue="asc", required = false) String popularity,
											@And({	@Spec(path = "username", params={"username"}, spec = Equal.class),
													@Spec(path = "firstName", params={"firstName"}, spec = Like.class),
													@Spec(path = "lastName", params={"lastName"}, spec = Equal.class),
													@Spec(path = "active", params={"active"}, spec = Equal.class),
													@Spec(path = "email", params={"email"}, spec = Equal.class)}) Specification<User> spec) throws ResourceNotFoundException {


		return new ResponseEntity<Page<User>>(userServiceFactory.getInstance(AuthenticationMethods.SYSTEM).findAll(page, pageSize, popularity, spec), HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> getAllRole(@RequestParam(value="page", defaultValue="1", required = false) int page,
											@RequestParam(value="page_size", defaultValue="10", required = false) int pageSize,
											@RequestParam(value="role", defaultValue="USER", required = true) String role) throws ResourceNotFoundException {


		return new ResponseEntity<Page<User>>(userServiceFactory.getInstance(AuthenticationMethods.SYSTEM).findAllRole(page, pageSize, role), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> get(@PathVariable UUID id) throws UserNotFoundException, ResourceNotFoundException {
		if(!userServiceFactory.getInstance(AuthenticationMethods.SYSTEM).exists(id)) {
			throw new UserNotFoundException(id);
		}

		return new ResponseEntity<User>(userServiceFactory.getInstance(AuthenticationMethods.SYSTEM).findUserById(id), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody UserCreator user) throws EstampalaException {
		if(userServiceFactory.getInstance(user.getMethod()).exists(user.getUsername())) {
			throw new UserAlreadyExistsException(user.getUsername());
		}

		return new ResponseEntity<User>(userServiceFactory.getInstance(user.getMethod()).saveUser(user), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH}, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody UserCreator user) throws EstampalaException {
		if(!userServiceFactory.getInstance(user.getMethod()).exists(id)) {
			throw new UserNotFoundException(id);
		}
		user.setUser(id);
		return new ResponseEntity<User>(userServiceFactory.getInstance(user.getMethod()).updateUser(user), HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/login",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserSession login(@RequestBody UserLogin auth) throws CredentialsException, UserNotFoundException, UserNotActiveException {

		return securityService.login(auth.getUsername(), auth.getPassword());
	}
	
	@CrossOrigin
	@RequestMapping(value = "/sociallogin",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public UserSession socialLogin(@RequestBody UserSocialLogin auth) throws EstampalaException {

		return securityService.socialLogin(auth.getToken(), auth.getUsername(), AuthenticationMethods.parse(auth.getMethod()));
	}

	@CrossOrigin
	@RequestMapping(value = "/logout",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> logout(@RequestParam(value="token", required = true) String token) throws InvalidTokenException, UserNotFoundException, UserNotActiveException {

		securityService.logout(token);
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The token was successfully deleted");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}

	@CrossOrigin
	@RequestMapping(value = "/validatetoken",method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> validateToken(@RequestParam(value="token", required = true) String token) throws CredentialsException, UserNotFoundException, InvalidTokenException, UserNotActiveException {

		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		if(securityService.validateToken(token)){
			response.setSuccess(true);
			response.setMessage(securityService.getUserByToken(token).getId().toString());
		}
		else{
			response.setSuccess(false);
			response.setMessage("The token is invalid");
		}		
		
		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
	
	@CrossOrigin
	@RequestMapping(value = "/auth/{id}",method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> changePassword(@PathVariable UUID id, @RequestBody UserAuthData authData) 
			throws UserNotFoundException, InvalidTokenException, RequiredParameterException, UserNotActiveException, CredentialsException {

		securityService.changeUserPassword(id, authData);
		
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(true);
		response.setMessage("The password was successfully updated");
		
		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}
	
	@CrossOrigin
	@RequestMapping(value = "/exist/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse> exist(@PathVariable UUID id) throws EstampalaException {
		SuccessResponse response = new SuccessResponse();
		response.setHttpStatus(HttpStatus.OK);
		response.setSuccess(userServiceFactory.getInstance(AuthenticationMethods.SYSTEM).exists(id));
		response.setMessage("Look success attribute");

		return new ResponseEntity<SuccessResponse>(response, response.getHttpStatus());
	}	
}
