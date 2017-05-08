package users.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.security.auth.login.CredentialException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import commons.util.Endpoints;
import commons.util.EstampalaTools;
import users.exceptions.CredentialsException;
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
import users.pojos.FacebookResponse;
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
	public UserSession login(String username, String password) throws UserNotFoundException, CredentialsException, UserNotActiveException {
		String hashPwd = DigestUtils.sha256Hex(password);
		User user = userRepository.findByUsername(username);

		if(user == null) {
			throw new UserNotFoundException(username);
		}
		
		if(!user.getActive()) {
			throw new UserNotActiveException(username);
		}

		UserAuth userAuth = userAuthRepository.findByUser(user.getId());
		if(userAuth != null && userAuth.getPassword().equals(hashPwd)) {
			List<UserSession> userSessions = sessionRepository.findAllByUser(userAuth.getId());
			
			if(userSessions == null || userSessions.isEmpty()) {
				String jwToken = tokenService.generateToken(username);
				
				UserSession session = new UserSession();
				session.setJWT(jwToken);
				session.setUser(user);				
				session.setToken(jwToken);
				session.setExpiration(tokenService.getExpiration(jwToken));
				
				return sessionRepository.save(session);
			} else {
				return userSessions.get(0);
			}
		} else {
			throw new CredentialsException(username);
		}
	}
	
	public UserSession socialLogin(String token, String username, AuthenticationMethods socialNetwork) throws UserNotFoundException, CredentialsException, UserNotActiveException {
		
		if (socialNetwork == AuthenticationMethods.FACEBOOK){
			Map<String, String> parameters = new HashMap<>();
			parameters.put("access_token", token);
			
			FacebookResponse res = EstampalaTools.invokeGetRestServices(Endpoints.VALIDATE_TOKEN_FACEBOOK.getPath(), null, parameters, FacebookResponse.class);
			if (res != null && res.getError() == null){					
				User user = userRepository.findByUsername(username);

				if(user == null) {
					throw new UserNotFoundException(username);
				}
				
				if(!user.getActive()) {
					throw new UserNotActiveException(username);
				}

				
				String jwToken = tokenService.generateToken(username);						
				
				UserSession session = new UserSession();
				session.setJWT(jwToken);
				session.setUser(user);
				session.setToken(token);
				session.setExpiration(tokenService.getExpiration(jwToken));
				
				return sessionRepository.save(session);				
			}
		}
		
		throw new CredentialsException(username);		
	}

	public void logout(String jwt) throws InvalidTokenException, UserNotFoundException, UserNotActiveException {
		tokenService.removeToken(jwt);
	}

	public boolean validateToken(String token) throws CredentialsException, InvalidTokenException, UserNotFoundException, UserNotActiveException {
		return tokenService.validateToken(token);
	}
	
	public void changeUserPassword(UUID id, UserAuthData userData) throws RequiredParameterException, UserNotFoundException, UserNotActiveException, CredentialsException {
		
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
		
		if(!user.getActive()) {
			throw new UserNotActiveException(user.getUsername());
		}
		
		String hashPwd = DigestUtils.sha256Hex(userData.getOriginalPwd());
		
		if(userAuth.getPassword().equals(hashPwd)) {
			
			String hashNewPwd = DigestUtils.sha256Hex(userData.getNewPwd());
			userAuth.setPassword(hashNewPwd);
			
			userAuthRepository.save(userAuth);
		} else {
			throw new CredentialsException("" + id);
		}
	}
}
