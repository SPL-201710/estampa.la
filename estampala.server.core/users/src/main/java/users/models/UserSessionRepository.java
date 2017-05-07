package users.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserSessionRepository extends JpaRepository<UserSession, UUID>{

	@Query("select us from UserSession us where us.user.id = ?1")
	public List<UserSession> findAllByUser(UUID userID);
	
	@Query("select us from UserSession us where us.token = ?1")
	public UserSession findByToken(String token);
}
