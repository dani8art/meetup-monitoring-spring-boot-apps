package com.darteaga.demo.monitoring.config;

import com.darteaga.demo.monitoring.dao.PurchaseJpaRepository;
import com.darteaga.demo.monitoring.model.Purchase;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CloudStreamConfig {

  private static final List<Purchase> INCOMING_PURCHASES = new ArrayList<>();

  @Bean
  public List<Purchase> purchasesBuffer() {
    return INCOMING_PURCHASES;
  }

  @Bean
  public Supplier<Purchase> purchaseSupplier(final List<Purchase> purchasesBuffer) {
    return () ->  {
      if (purchasesBuffer.size() > 0) {
        return purchasesBuffer.remove(purchasesBuffer.size() - 1);
      }

      return null;
    };
  }

  @Bean
  public Function<Purchase, Purchase> purchaseProcessor() {
    return purchase -> purchase;
  }

  @Bean
  public Consumer<Purchase> purchaseSink(final PurchaseJpaRepository dao) {
    return dao::save;
  }

}
