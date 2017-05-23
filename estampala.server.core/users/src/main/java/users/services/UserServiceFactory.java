package users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commons.exceptions.ResourceNotFoundException;
import commons.util.FeaturesFlag;

@Service
public class UserServiceFactory {

	@Autowired
	private UserServiceSystem userServiceSystem;
	
	@Autowired
	private UserServiceFacebook userServiceFacebook;
	
	@Autowired
	private UserServiceTwitter userServiceTwitter;
	
	public UserService getInstance(String authenticationMethod) throws ResourceNotFoundException{
		return getInstance(AuthenticationMethods.parse(authenticationMethod));
	}
	
	public UserService getInstance(AuthenticationMethods authenticationMethod) throws ResourceNotFoundException{
		
		switch(authenticationMethod){
			case SYSTEM: return userServiceSystem;						
			case FACEBOOK: if(!FeaturesFlag.AUTH_FACEBOOK.isActive()) {
				throw new ResourceNotFoundException("auth_facebook");
				}	
				return userServiceFacebook;			
			case TWITTER: if(!FeaturesFlag.AUTH_TWITTER.isActive()) {
				throw new ResourceNotFoundException("auth_twitter");
				}	
				return userServiceTwitter;
			default: return userServiceSystem;
		}
	}
	
}
