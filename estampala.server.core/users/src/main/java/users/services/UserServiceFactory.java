package users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceFactory {

	@Autowired
	private UserServiceSystem userServiceSystem;
	
	@Autowired
	private UserServiceFacebook userServiceFacebook;
	
	public UserService getInstance(String authenticationMethod){
		return getInstance(AuthenticationMethods.parse(authenticationMethod));
	}
	
	public UserService getInstance(AuthenticationMethods authenticationMethod){
		
		switch(authenticationMethod){
			case SYSTEM: return userServiceSystem;						
			case FACEBOOK: return userServiceFacebook;				
			default: return userServiceSystem;
		}
	}
	
}
