package payment.models;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository <T extends PaymentMethod> extends JpaRepository<T, UUID> {
    
}
