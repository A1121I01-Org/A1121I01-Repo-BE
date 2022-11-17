package com.example.demologin.repository;

import com.example.demologin.entity.cart.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartStatusRepository extends JpaRepository<CartStatus, Long> {

    @Query(value = "SELECT * FROM cart_status WHERE cart_status_id = 1", nativeQuery = true)
    CartStatus getCartStatus();
}
