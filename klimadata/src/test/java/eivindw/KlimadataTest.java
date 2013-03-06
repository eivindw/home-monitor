package eivindw;

import metdata.met.no.IMetDataService_xsd.No_met_metdata_Metdata;
import metdata.met.no.IMetDataService_xsd.No_met_metdata_TimeStamp;
import no.met.metdata.MetDataService_wsdl.MetDataServiceLocator;
import no.met.metdata.MetDataService_wsdl.MetDataServicePortType;
import org.junit.Test;

public class KlimadataTest {

    @Test
    public void shouldLookupWebService() throws Exception {
        MetDataServicePortType service = (MetDataServicePortType) new MetDataServiceLocator().getPort(MetDataServicePortType.class);

        No_met_metdata_Metdata metData = service.getMetData("0", "", "2013-02-01", "2013-03-01", "18700", "TAN", "", "", "");

        for (No_met_metdata_TimeStamp timeStamp : metData.getTimeStamp()) {
            String value = timeStamp.getLocation()[0].getWeatherElement()[0].getValue();

            System.out.println("Date: " + timeStamp.getFrom().getTime() + " Value: " + value);
        }
    }
}
