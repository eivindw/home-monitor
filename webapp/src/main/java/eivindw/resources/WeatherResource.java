package eivindw.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableMap;
import metdata.met.no.IMetDataService_xsd.No_met_metdata_Metdata;
import metdata.met.no.IMetDataService_xsd.No_met_metdata_TimeStamp;
import metdata.met.no.IMetDataService_xsd.No_met_metdata_WeatherElement;
import no.met.metdata.MetDataService_wsdl.MetDataServiceLocator;
import no.met.metdata.MetDataService_wsdl.MetDataServicePortType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {

   @GET @Timed
   public List getTemperaturesLastMonth() throws Exception {
      MetDataServicePortType service = (MetDataServicePortType) new MetDataServiceLocator().getPort(MetDataServicePortType.class);

      No_met_metdata_Metdata metData = service.getMetData("0", "", "2013-02-01", "2013-03-01", "18700", "TAN,TAX", "", "", "");

      List<Map> tempData = new ArrayList<>();

      for (No_met_metdata_TimeStamp timeStamp : metData.getTimeStamp()) {
         final No_met_metdata_WeatherElement[] weatherElements = timeStamp.getLocation()[0].getWeatherElement();

         final String tax = weatherElements[0].getValue();
         final String tan = weatherElements[1].getValue();

         tempData.add(ImmutableMap.of(
            "time", timeStamp.getFrom().getTimeInMillis(),
            "tax", tax,
            "tan", tan));
      }

      return tempData;
   }
}
