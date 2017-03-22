package model;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 
 * @author usuario
 *
 */
public interface ClientRepository extends JpaRepository<Client, UUID>{

	public Client findByUsername(String username);
}
