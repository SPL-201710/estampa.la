package model;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * wrapper for client
 * @author jorge
 */
public class CurrentClient extends org.springframework.security.core.userdetails.User{
	
	private Client client;
	
	public CurrentClient(Client client) {
		super(client.getUsername(), client.getPassword(), AuthorityUtils.createAuthorityList(client.getRole().toString()));
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
