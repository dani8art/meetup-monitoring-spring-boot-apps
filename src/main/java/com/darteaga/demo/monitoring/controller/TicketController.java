package com.darteaga.demo.monitoring.controller;

import com.darteaga.demo.monitoring.dao.TicketJpaRepository;
import com.darteaga.demo.monitoring.model.Ticket;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/v1/tickets")
@RequiredArgsConstructor
@Slf4j
public class TicketController {

  private final TicketJpaRepository dao;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<Ticket>> getAllTickets() {
    return ResponseEntity.ok(dao.findAll());
  }

  @PostConstruct
  public void initializeDatabase() {
    final List<Ticket> tickets = dao.findAll();

    if (tickets.size() == 0) {
      dao.saveAll(List.of(
          new Ticket(null, "29.95", "F00A000", Collections.emptyList()),
          new Ticket(null, "29.95", "F00A001", Collections.emptyList()),
          new Ticket(null, "49.95", "F01A000", Collections.emptyList()),
          new Ticket(null, "49.95", "F01A001", Collections.emptyList()),
          new Ticket(null, "59.95", "F02A000", Collections.emptyList()),
          new Ticket(null, "59.95", "F02A001", Collections.emptyList())
      ));
    }
  }

}