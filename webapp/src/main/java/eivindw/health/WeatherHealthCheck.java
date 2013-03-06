package eivindw.health;

import com.yammer.metrics.core.HealthCheck;
import no.met.metdata.MetDataService_wsdl.MetDataServiceLocator;
import no.met.metdata.MetDataService_wsdl.MetDataServicePortType;

public class WeatherHealthCheck extends HealthCheck {

   public WeatherHealthCheck(String name) {
      super(name);
   }

   @Override
   protected Result check() throws Exception {
      try {
         MetDataServicePortType service = (MetDataServicePortType) new MetDataServiceLocator().getPort(MetDataServicePortType.class);

         String dateFormat = service.getDateFormat();

         return Result.healthy("Weather data date-format: " + dateFormat);
      } catch (Exception e) {
         return Result.unhealthy(e);
      }
   }
}
