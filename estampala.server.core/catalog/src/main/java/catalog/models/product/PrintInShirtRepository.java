package catalog.models.product;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrintInShirtRepository extends JpaRepository<PrintInShirt, UUID> {
    
}
