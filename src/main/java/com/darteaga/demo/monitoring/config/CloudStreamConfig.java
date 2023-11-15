package com.darteaga.demo.monitoring.config;

import com.darteaga.demo.monitoring.dao.PurchaseJpaRepository;
import com.darteaga.demo.monitoring.model.Purchase;
import io.micrometer.core.annotation.Timed;
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
    return new PurchaseProcessor();
  }

  @Bean
  public Consumer<Purchase> purchaseSink(final PurchaseJpaRepository dao) {
    return dao::save;
  }


  public static class PurchaseProcessor implements Function<Purchase, Purchase> {

    private int milli = 0;

    @Timed(value = "purchase_processor")
    public Purchase apply(Purchase purchase) {
      milli = (milli + 100) % 1000;

      try {
        Thread.sleep(milli);
      } catch (InterruptedException e) {
        // no-op
      }

      return purchase;
    }
  }
}
