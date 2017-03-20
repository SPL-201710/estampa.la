package services;

import model.Client;

public interface ClientService {

	public Client findByUsername(String username);
}
