package eivindw.health;

import com.yammer.metrics.core.HealthCheck;
import eivindw.services.EfergyService;

public class EfergyHealthCheck extends HealthCheck {

   private final EfergyService service;

   public EfergyHealthCheck(String name, EfergyService service) {
      super(name);
      this.service = service;
   }

   @Override
   protected Result check() throws Exception {
      try {
         service.getInstant();
         return Result.healthy();
      } catch (Exception e) {
         return Result.unhealthy(e);
      }
   }
}
