package eivindw.health;

import com.codahale.metrics.health.HealthCheck;
import no.met.metdata.MetDataService_wsdl.MetDataServiceLocator;
import no.met.metdata.MetDataService_wsdl.MetDataServicePortType;

public class WeatherHealthCheck extends HealthCheck {

   @Override
   protected Result check() throws Exception {
      try {
         final MetDataServicePortType service =
            (MetDataServicePortType) new MetDataServiceLocator().getPort(MetDataServicePortType.class);

         service.getDateFormat();

         return Result.healthy();
      } catch (Exception e) {
         return Result.unhealthy(e);
      }
   }
}
