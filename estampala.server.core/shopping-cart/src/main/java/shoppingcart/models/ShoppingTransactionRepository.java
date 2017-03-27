package shoppingcart.models;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShoppingTransactionRepository extends JpaRepository<ShoppingTransaction, UUID>{

	@Query("Select t from ShoppingTransaction t where t.transactionUser = ?1")
	public List<ShoppingTransaction> findAllByUserId(UUID userId);
}
