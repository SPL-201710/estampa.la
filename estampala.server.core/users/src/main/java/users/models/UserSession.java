package users.models;

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

	public UserSession() {

	}

	public UserSession(UUID id, String jwt, User user) {
		super();
		this.id = id;
		this.JWT = jwt;
		this.user = user;
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
}
