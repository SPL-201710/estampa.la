package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Client;
import model.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository repository;

	@Override
	public Client findByUsername(String username) {
		return repository.findByUsername(username);
	}
}
