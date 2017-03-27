package users.services;

import javax.security.auth.login.CredentialException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import users.exceptions.InvalidTokenException;
import users.models.UserSession;
import users.models.UserSessionRepository;
import users.models.User;
import users.services.UserService;
import users.exceptions.UserNotFoundException;

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
	public UserSession login(String username, String password) throws UserNotFoundException, CredentialException {
		String hashPwd = DigestUtils.sha256Hex(password);
		User user = userService.findUserByUsername(username);

		if(user == null) {
			throw new UserNotFoundException(username);
		}

		if(user.getPassword().equals(hashPwd)) {
			UserSession userSession = sessionRepository.findAllByUser(user.getId());
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

	public void logout(String jwt) throws InvalidTokenException, UserNotFoundException {
		tokenService.removeToken(jwt);
	}

	public UserSession validateToken(String jwt) throws InvalidTokenException, UserNotFoundException {
		return tokenService.validateToken(jwt);
	}
}
