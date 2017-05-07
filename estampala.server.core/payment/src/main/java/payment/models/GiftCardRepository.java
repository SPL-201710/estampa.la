package payment.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GiftCardRepository extends JpaRepository<GiftCard, UUID>, JpaSpecificationExecutor<GiftCard>{

}
