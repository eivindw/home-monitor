package eivindw.health;

import com.yammer.metrics.core.HealthCheck;
import eivindw.services.EfergyService;

import java.util.Map;

public class EfergyHealthCheck extends HealthCheck {

   private final EfergyService service;

   public EfergyHealthCheck(String name, EfergyService service) {
      super(name);
      this.service = service;
   }

   @Override
   protected Result check() throws Exception {
      try {
         final Map instant = service.getInstant();
         if (instant.containsKey("reading")) {
            return Result.healthy();
         } else {
            return Result.unhealthy("Missing reading from map: {}", instant);
         }
      } catch (Exception e) {
         return Result.unhealthy(e);
      }
   }
}
