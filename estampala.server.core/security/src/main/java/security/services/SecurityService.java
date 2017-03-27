package security.services;

import javax.security.auth.login.CredentialException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD:estampala.server.core/security/src/main/java/security/services/SecurityService.java
import security.exceptions.InvalidTokenException;
import security.model.UserSession;
import security.model.UserSessionRepository;
=======
import exceptions.InvalidTokenException;
import users.exceptions.UserNotFoundException;
import model.UserSession;
import model.UserSessionRepository;
import users.exceptions.UserNotFoundException;
>>>>>>> master:estampala.server.core/security/src/main/java/services/SecurityService.java
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

	public String validateToken(String jwt) throws InvalidTokenException, UserNotFoundException {
		return tokenService.validateToken(jwt);
	}
}
