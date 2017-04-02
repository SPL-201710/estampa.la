package payment.models;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodPSERepository extends JpaRepository<PaymentMethodPSE, UUID> {
    
}
