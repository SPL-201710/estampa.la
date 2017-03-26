package services;

import javax.security.auth.login.CredentialException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.InvalidTokenException;
import exceptions.UserNotFoundException;
import model.UserSession;
import model.UserSessionRepository;
import users.models.User;

@Service
public class SecurityService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenAuthenticationService tokenService;
	
	@Autowired
	private UserSessionRepository sessionRepository;
	
	public SecurityService() {
		
	}
	
	/**
	 * authenticates an user
	 * @param username 
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 * @throws CredentialException 
	 */
	public String login(String username, String password) throws UserNotFoundException, CredentialException {
		String token = "";
		
		String hashPwd = DigestUtils.sha256Hex(password);
		User user = userService.findUserByUsername(username);
		
		if(user == null) {
			throw new UserNotFoundException(username);
		}
		
		if(user.getPassword().equals(hashPwd)){ 
			
			token = tokenService.generateToken(username);
			
			UserSession session = new UserSession();
			session.setUser(user);
			session.setJWT(token);
			
			sessionRepository.save(session);
			
		} else {
			throw new CredentialException(username);
		}
		
		return token;
	}
	
	public String validateToken(String jwt) throws InvalidTokenException {
		
		return tokenService.validateToken(jwt);
	}
}
