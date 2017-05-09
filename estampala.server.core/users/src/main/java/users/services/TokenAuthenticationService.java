package users.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import users.exceptions.InvalidTokenException;
import users.exceptions.UserNotActiveException;
import users.exceptions.UserNotFoundException;
import users.models.User;
import users.models.UserSession;
import users.models.UserSessionRepository;

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

	public void removeToken(String token) throws UserNotFoundException, InvalidTokenException, UserNotActiveException {
			UserSession userSession = sessionRepository.findByToken(token);									
			if(userSession == null) {
					throw new InvalidTokenException();
			}

			sessionRepository.delete(userSession.getId());
	}

	public Date getExpiration(String jwt){
		Date date = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getExpiration();
		
		return date;
	}
	
	public boolean validateToken(String token) throws InvalidTokenException, UserNotFoundException, UserNotActiveException {		
		UserSession userSession = sessionRepository.findByToken(token);		
		
		if(userSession == null) {
			return false;
		}	

		if(userSession.getExpiration().compareTo(new Date()) < 0){
			return false;
		}
		
		return true;
	}
	
	public User getUserByToken(String token) throws InvalidTokenException, UserNotFoundException, UserNotActiveException {		
		UserSession userSession = sessionRepository.findByToken(token);		
		
		if(userSession == null) {
			return null;
		}
		
		return userSession.getUser();
	}
}
