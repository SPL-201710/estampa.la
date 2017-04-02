package users.models;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User>  {	

	public User findByUsername(String username);
	
	@Query("Select u From estampalaUser u join u.roles r where r.name = :roleName")
	public Page<User> findAllRole(@Param("roleName")String role, Pageable pageable);
}
