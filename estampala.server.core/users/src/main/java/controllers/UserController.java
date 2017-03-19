package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import commons.controllers.EstampalaController;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import services.UserService;
import users.models.User;

@RestController
@RequestMapping("/users")
public class UserController extends EstampalaController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) throws UserAlreadyExistsException {
		
		if(userService.userExists(user.getUserName())) {
			throw new UserAlreadyExistsException(user.getUserName());
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) throws UserNotFoundException {
		
		if(!userService.userExists(user.getUserName())) {
			throw new UserNotFoundException(user.getUserName());
		}
		
		user = userService.saveUser(user);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
