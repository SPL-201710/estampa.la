package users.models;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserSessionRepository extends JpaRepository<UserSession, UUID>{

	@Query("select us from UserSession us where us.user.id = ?1")
	public UserSession findAllByUser(UUID userID);

}
