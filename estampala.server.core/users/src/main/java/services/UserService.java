package services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
	public Boolean exists(String username) {
		return userRepository.findByUsername(username) != null ? true : false;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean exists(UUID id){		
		return userRepository.exists(id);						
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
	
	/**
	 * returns the user with specified id
	 * @param id id of user
	 * @return user
	 */
	public User findUserById(UUID id) {
		return userRepository.findById(id);
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	/**
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page<User> findAll(int page, int pageSize) {
		PageRequest pageRequest = new PageRequest(page - 1, pageSize, Sort.Direction.DESC, "userName");
		return userRepository.findAll(pageRequest);
	}
	
	/**
	 * 
	 * @param id
	 */
	public void delete(UUID id) {
		userRepository.delete(id);
	}
}
