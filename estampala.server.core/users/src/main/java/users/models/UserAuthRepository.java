package users.models;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAuthRepository extends JpaRepository<UserAuth, UUID>{
	
	@Query("select u from UserAuth u where u.user = ?1")
	public UserAuth findByUser(UUID userId);	
}
