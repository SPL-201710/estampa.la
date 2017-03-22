package catalog.models.print;


import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrintRepository extends JpaRepository<Print, UUID> {
    Collection<Print> findByName(String name);
}
