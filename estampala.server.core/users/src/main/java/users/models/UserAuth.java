package users.models;

/**
 * wrapper class for authenticating an user
 * @author Jorge
 */
public class UserAuth {
	
	private String username;
	
	private String password;
	
	public UserAuth() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
