package com.darteaga.demo.monitoring.dao;

import com.darteaga.demo.monitoring.model.Purchase;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseJpaRepository extends JpaRepository<Purchase, UUID> {

}
