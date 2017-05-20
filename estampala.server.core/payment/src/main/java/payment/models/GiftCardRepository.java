package payment.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GiftCardRepository extends JpaRepository<GiftCard, UUID>, JpaSpecificationExecutor<GiftCard>{

	@Query("Select gc from GiftCard gc where receiver =:id and balance > 0")
	public List<GiftCard> findByReceiver(@Param("id")UUID id);
}
