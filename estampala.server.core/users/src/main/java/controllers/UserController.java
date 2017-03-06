package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import services.UserService;
import users.models.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		if(userService.userExists(user.getUserName())) {
			return new ResponseEntity("The user already exists " + user.getUserName(), HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		
		if(userService.userExists(user.getUserName())) {
			return new ResponseEntity("The user doesn't exist " + user.getUserName(), HttpStatus.NOT_FOUND);
		}
		
		user = userService.saveUser(user);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
