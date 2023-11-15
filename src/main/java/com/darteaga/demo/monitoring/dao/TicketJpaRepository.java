package com.darteaga.demo.monitoring.dao;

import com.darteaga.demo.monitoring.model.Ticket;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<Ticket, UUID> {

}
