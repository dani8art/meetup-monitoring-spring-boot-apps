package com.darteaga.demo.monitoring.controller;

import com.darteaga.demo.monitoring.dao.PurchaseJpaRepository;
import com.darteaga.demo.monitoring.model.Purchase;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/v1/purchases")
@RequiredArgsConstructor
@Slf4j
public class PurchaseController {

  private final List<Purchase> purchasesBuffer;
  private final PurchaseJpaRepository dao;

  @RequestMapping(method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<List<Purchase>> getAllPurchases() {
    return ResponseEntity.ok(dao.findAll());
  }

  @RequestMapping(method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<Void> createPurchases(@RequestBody final Purchase purchase) {
    purchase.setId(UUID.randomUUID());

    purchasesBuffer.add(purchase);

    return ResponseEntity.created(URI.create("/v1/purchases/%s".formatted(purchase.getId()))).build();
  }

}
