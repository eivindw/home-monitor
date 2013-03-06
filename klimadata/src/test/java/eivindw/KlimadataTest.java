package eivindw;

import metdata.met.no.IMetDataService_xsd.No_met_metdata_Metdata;
import metdata.met.no.IMetDataService_xsd.No_met_metdata_TimeStamp;
import metdata.met.no.IMetDataService_xsd.No_met_metdata_WeatherElement;
import no.met.metdata.MetDataService_wsdl.MetDataServiceLocator;
import no.met.metdata.MetDataService_wsdl.MetDataServicePortType;
import org.junit.Test;

public class KlimadataTest {

   @Test
   public void shouldLookupWebService() throws Exception {
      MetDataServicePortType service = (MetDataServicePortType) new MetDataServiceLocator().getPort(MetDataServicePortType.class);

      No_met_metdata_Metdata metData = service.getMetData("0", "", "2013-02-01", "2013-03-01", "18700", "TAN,TAX", "", "", "");

      for (No_met_metdata_TimeStamp timeStamp : metData.getTimeStamp()) {
         final No_met_metdata_WeatherElement[] weatherElements = timeStamp.getLocation()[0].getWeatherElement();

         final String tax = weatherElements[0].getValue();
         final String tan = weatherElements[1].getValue();

         System.out.printf("Date: %s Max: %s Min: %s%n", timeStamp.getFrom().getTime(), tax, tan);
      }
   }
}
