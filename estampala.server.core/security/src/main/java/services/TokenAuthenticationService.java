package services;

import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import exceptions.InvalidTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import users.models.User;
import users.services.UserService;

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

	public String validateToken(String jwt) throws InvalidTokenException {

		String username = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();

		User user = userService.findUserByUsername(username);

		if(user == null) {
			throw new UsernameNotFoundException(username);
		}



		String token = Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(jwt.replace(TOKEN_PREFIX, ""))
				.getBody()
				.toString();

		if(!token.equals(jwt)) {
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

		return Collections.singletonMap("status", "valid").toString();
	}
}
