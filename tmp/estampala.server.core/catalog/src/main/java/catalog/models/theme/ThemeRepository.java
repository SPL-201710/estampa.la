package catalog.models.theme;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, UUID> {
	
}
