package eivindw.health;

import com.yammer.metrics.core.HealthCheck;
import metdata.met.no.IMetDataService_xsd.No_met_metdata_Metdata;
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

         No_met_metdata_Metdata metData = service.getMetData("0", "", "2013-02-01", "2013-03-01", "18700", "TAN", "", "", "");

         return Result.healthy("Weather data created: " + metData.getCreated().getTime());
      } catch (Exception e) {
         return Result.unhealthy(e);
      }
   }
}
