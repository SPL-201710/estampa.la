package users.services;

import java.util.UUID;

import javax.security.auth.login.CredentialException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import users.exceptions.InvalidTokenException;
import users.exceptions.RequiredParameterException;
import users.exceptions.UserNotActiveException;
import users.exceptions.UserNotFoundException;
import users.models.User;
import users.models.UserAuth;
import users.models.UserAuthRepository;
import users.models.UserRepository;
import users.models.UserSession;
import users.models.UserSessionRepository;
import users.pojos.UserAuthData;

@Service
public class SecurityService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserAuthRepository userAuthRepository;

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
	 * @throws UserNotActiveException 
	 */
	public UserSession login(String username, String password) throws UserNotFoundException, CredentialException, UserNotActiveException {
		String hashPwd = DigestUtils.sha256Hex(password);
		User user = userRepository.findByUsername(username);

		if(user == null) {
			throw new UserNotFoundException(username);
		}
		
		if(!user.getUserActive()) {
			throw new UserNotActiveException(username);
		}

		UserAuth userAuth = userAuthRepository.findByUser(user.getId());
		if(userAuth.getPassword().equals(hashPwd)) {
			UserSession userSession = sessionRepository.findAllByUser(userAuth.getId());
			if(userSession == null) {
				String token = tokenService.generateToken(username);
				UserSession session = new UserSession();
				session.setJWT(token);
				session.setUser(user);
				return sessionRepository.save(session);
			} else {
				return userSession;
			}
		} else {
			throw new CredentialException(username);
		}
	}

	public void logout(String jwt) throws InvalidTokenException, UserNotFoundException, UserNotActiveException {
		tokenService.removeToken(jwt);
	}

	public UserSession validateToken(String jwt) throws InvalidTokenException, UserNotFoundException, UserNotActiveException {
		return tokenService.validateToken(jwt);
	}
	
	public void changeUserPassword(UUID id, UserAuthData userData) throws RequiredParameterException, UserNotFoundException, CredentialException, UserNotActiveException {
		
		if(id == null) {
			throw new RequiredParameterException("user ID");
		}
		
		if(userData.getOriginalPwd() == null) {
			throw new RequiredParameterException("original Password");
		}
		
		if(userData.getNewPwd() == null) {
			throw new RequiredParameterException("new Password");
		}
		
		if(!userRepository.exists(id)) {
			throw new UserNotFoundException(id);
		}
		
		UserAuth userAuth = userAuthRepository.findByUser(id);
		
		User user = userRepository.findOne(id);
		
		if(!user.getUserActive()) {
			throw new UserNotActiveException(user.getUsername());
		}
		
		String hashPwd = DigestUtils.sha256Hex(userData.getOriginalPwd());
		
		if(userAuth.getPassword().equals(hashPwd)) {
			
			String hashNewPwd = DigestUtils.sha256Hex(userData.getNewPwd());
			userAuth.setPassword(hashNewPwd);
			
			userAuthRepository.save(userAuth);
		} else {
			throw new CredentialException("" + id);
		}
	}
}
