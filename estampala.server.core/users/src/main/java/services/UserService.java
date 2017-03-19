package services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import exceptions.UserNotFoundException;
import users.models.User;
import users.models.UserRepository;

/**
 * user services fachade
 * @author jorge perea
 *
 */
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserService() {
		
	}
	
	/**
	 * persists or merges the entity
	 * @param user
	 * @return user
	 */
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * finds if a user exists
	 * @param user
	 * @return boolean
	 */
	public Boolean userExists(String username) {
		return userRepository.findByUsername(username) != null ? true : false;
	}
	
	/**
	 * deletes an user
	 * @param user
	 */
	public void deleteUser(UUID id) throws UserNotFoundException {
		
		if(!userRepository.exists(id)) {
			throw new UserNotFoundException(id);
		}
		
		userRepository.delete(id);
	}
}
