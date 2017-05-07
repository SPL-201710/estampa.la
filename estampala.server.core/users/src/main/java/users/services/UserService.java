package users.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import commons.exceptions.EstampalaException;
import users.exceptions.UserNotActiveException;
import users.models.User;
import users.pojos.UserCreator;

public interface UserService {
	
	User saveUser(UserCreator item) throws EstampalaException;
	User updateUser(UserCreator item) throws EstampalaException;
	boolean exists(UUID id);
	Boolean exists(String username);
	User findUserById(UUID id);
	User findUserByUsername(String username) throws UserNotActiveException;
	Page<User> findAll(int page, int pageSize, String order, Specification<User> spec);
	Page<User> findAllRole(int page, int pageSize, String role);
}
