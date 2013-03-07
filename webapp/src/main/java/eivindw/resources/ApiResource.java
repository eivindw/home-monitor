package eivindw.resources;

import com.google.common.collect.ImmutableMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ApiResource {

   @GET
   public Map listServices() {
      return ImmutableMap.of(
         "weather", "/api/weather"
      );
   }
}
