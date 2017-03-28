package catalog.models.print;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrintRepository extends JpaRepository<Print, UUID>, JpaSpecificationExecutor<Print> {

}
