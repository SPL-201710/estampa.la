package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import model.Client;
import model.CurrentClient;

@Service
public class CurrentClientDetailsService implements UserDetailsService {

	@Autowired
	private ClientService clientService;
	
	public CurrentClientDetailsService() {
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client client = clientService.findByUsername(username);
		
		if(client == null) {
			throw new UsernameNotFoundException(String.format("Client with username was not found", username));
		}
		
		return new CurrentClient(client);
	}
}
