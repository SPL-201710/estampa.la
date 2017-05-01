package users.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import users.exceptions.InvalidTokenException;
import users.exceptions.UserNotActiveException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import users.models.User;
import users.models.UserSession;
import users.models.UserSessionRepository;
import users.services.UserService;
import users.exceptions.UserNotFoundException;

/**
 * Servicio de autenticacion
 * @author usuario
 *
 */
@Service
public class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "Estampa.la";
	static final String TOKEN_PREFIX = "Token";
	static final String HEADER_STRING = "Authorization";

	@Autowired
	private UserService userService;

	@Autowired
	private UserSessionRepository sessionRepository;

	/**
	 * Returns authentication token for user
	 * @param username
	 * @return
	 */
	public String generateToken(String username) {
	    String JWT = Jwts.builder()
	        .setSubject(username)
	        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
	        .signWith(SignatureAlgorithm.HS512, SECRET)
	        .compact();
	    return JWT;
	}

	public void removeToken(String jwt) throws UserNotFoundException, InvalidTokenException, UserNotActiveException {
			String username = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();

			User user = userService.findUserByUsername(username);
			if(user == null) {
					throw new UserNotFoundException(username);
			}

			UserSession userSession = sessionRepository.findAllByUser(user.getId());
			if(userSession == null) {
					throw new InvalidTokenException();
			}
			sessionRepository.delete(userSession.getId());
	}

	public UserSession validateToken(String jwt) throws InvalidTokenException, UserNotFoundException, UserNotActiveException {
		String username = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();

		User user = userService.findUserByUsername(username);
		if(user == null) {
			throw new UserNotFoundException(username);
		}
		
		if(!user.getActive()) {
			throw new UserNotActiveException(user.getUsername());
		}

		UserSession userSession = sessionRepository.findAllByUser(user.getId());
		if(userSession == null || !userSession.getJWT().equals(jwt)) {
			throw new InvalidTokenException();
		}

		Date date = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getExpiration();

		if(date.compareTo(new Date()) < 0){
			throw new InvalidTokenException();
		}
		return userSession;
	}
}
