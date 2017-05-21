package payment.models;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentMethodRepository <T extends PaymentMethod> extends JpaRepository<T, UUID> {
	
}
