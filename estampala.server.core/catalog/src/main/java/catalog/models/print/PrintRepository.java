package catalog.models.print;


import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface PrintRepository extends JpaRepository<Print, UUID>, JpaSpecificationExecutor<Print> {
    //Collection<Print> findByName(String name);
}
