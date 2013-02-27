package eivindw.health;

import com.yammer.metrics.core.HealthCheck;

public class EfergyHealthCheck extends HealthCheck {

   public EfergyHealthCheck(String name) {
      super(name);
   }

   @Override
   protected Result check() throws Exception {
      return Result.healthy();
   }
}
