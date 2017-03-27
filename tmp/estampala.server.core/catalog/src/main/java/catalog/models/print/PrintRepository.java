package catalog.models.print;


import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintRepository extends JpaRepository<Print, UUID> {
    Collection<Print> findByName(String name);
}
