package users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import commons.exceptions.EstampalaException;
import users.exceptions.RequiredParameterException;
import users.exceptions.RoleNotFoundException;
import users.exceptions.UserNotFoundException;
import users.models.Role;
import users.models.RoleRepository;
import users.models.User;
import users.models.UserAuth;
import users.models.UserSession;
import users.models.UserAuthRepository;
import users.models.UserRepository;
import users.models.UserSessionRepository;
import users.pojos.UserCreator;


/**
 * user services fachade
 * @author jorge perea
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserAuthRepository userAuthRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserSessionRepository userSessionRepository;

	public UserService() {

	}

	public User saveUser(UserCreator item) throws EstampalaException {
		if (item != null){

			if (item.getRoles() == null){
				throw new RequiredParameterException("role");
			}

			if (item.getUsername() == null){
				throw new RequiredParameterException("username");
			}

			if (item.getEmail() == null){
				throw new RequiredParameterException("email");
			}

			if (item.getPassword() == null){
				throw new RequiredParameterException("password");
			}

			if (item.getFirstName() == null){
				throw new RequiredParameterException("firts name");
			}

			String hashPwd = DigestUtils.sha256Hex(item.getPassword());
			item.setPassword(hashPwd);

			List<Role> roles = new ArrayList<>();
			for(UUID idRole : item.getRoles()){
				Role role = roleRepository.findOne(idRole);
				if (role == null){
					throw new RoleNotFoundException();
				}

				roles.add(role);
			}

			User user = new User(UUID.randomUUID(), item.getFirstName(), item.getLastName(), item.getEmail(), item.getUsername(), item.getPhoneNumber(), roles);
			user = userRepository.save(user);

			UserAuth userAuth = new UserAuth(UUID.randomUUID(), user, item.getPassword());
			userAuthRepository.save(userAuth);

			return user;
		}

		return null;
	}

	public User updateUser(UserCreator item) throws EstampalaException {
		if (item != null){

			User user = userRepository.findOne(item.getUser());

			if (item.getRoles() != null){
				List<Role> roles = new ArrayList<>();
				for(UUID idRole : item.getRoles()){
					Role role = roleRepository.findOne(idRole);
					if (role == null){
						throw new RoleNotFoundException();
					}

					roles.add(role);
				}

				user.setRoles(roles);
			}

			if (item.getEmail() != null){
				user.setEmail(item.getEmail());
			}

			if (item.getFirstName() != null){
				user.setFirstName(item.getFirstName());
			}

			if (item.getLastName() != null){
				user.setLastName(item.getLastName());
			}

			if (item.getPassword() != null){
				UserAuth userAuth = userAuthRepository.findByUser(user.getId());
				if (userAuth != null){
					String hashPwd = DigestUtils.sha256Hex(item.getPassword());
					userAuth.setPassword(hashPwd);

					userAuthRepository.save(userAuth);
				}
			}

			user = userRepository.save(user);
			return user;
		}

		return null;
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
	 * confirms if user exists
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

		UserAuth userAuth = userAuthRepository.findByUser(id);
		if(userAuth != null)
			userAuthRepository.delete(userAuth.getId());

		UserSession userSession =	userSessionRepository.findAllByUser(id);
		if(userSession != null)
			userSessionRepository.delete(userSession.getId());

		userRepository.delete(id);
	}

	/**
	 * returns the user with specified id
	 * @param id id of user
	 * @return user
	 */
	public User findUserById(UUID id) {
		return userRepository.findOne(id);
	}

	/**
	 * finds user by username
	 * @param username
	 * @return
	 */
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Page<User> findAll(int page, int pageSize, String order, Specification<User> spec) {
		Direction direction = Sort.Direction.DESC;
		if (order != null && "asc".equalsIgnoreCase(order)){
			direction = Sort.Direction.ASC;
		}

		PageRequest pageRequest = new PageRequest(page - 1, pageSize, direction, "firstName");
		return userRepository.findAll(spec, pageRequest);
	}

	/**
	 * delete the user
	 * @param id
	 */
	public void delete(UUID id) {
		userRepository.delete(id);
	}
}
