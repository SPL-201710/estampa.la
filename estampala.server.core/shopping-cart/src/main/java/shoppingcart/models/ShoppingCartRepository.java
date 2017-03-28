package shoppingcart.models;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {

}
