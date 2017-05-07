package users.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
	private UserServiceSystem userService;

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
		
			String username = "";
			try {
			username = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			} catch (Exception e) {
				throw new InvalidTokenException();
			}

			User user = userService.findUserByUsername(username);
			if(user == null) {
					throw new UserNotFoundException(username);
			}

			List<UserSession> userSessions = sessionRepository.findAllByUser(user.getId());
			
			userSessions = userSessions.stream()
					.filter(x -> x.getJWT().equals(jwt))
					.collect(Collectors.toList());
			
			if(userSessions == null || userSessions.isEmpty()) {
					throw new InvalidTokenException();
			}

			sessionRepository.delete(userSessions.get(0).getId());
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
}
