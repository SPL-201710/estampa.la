package users.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import users.models.User;

@Entity
public class UserSession {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type="pg-uuid")
	private UUID id;

	@OneToOne
	private User user;

	@Column(nullable = false)
	private String JWT;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private Date expiration;

	public UserSession() {

	}

	public UserSession(UUID id, String jwt, User user, String token, Date expiration) {
		super();
		this.id = id;
		this.JWT = jwt;
		this.user = user;
		this.token = token;
		this.expiration = expiration;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJWT() {
		return JWT;
	}

	public void setJWT(String jWT) {
		JWT = jWT;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}	
}
