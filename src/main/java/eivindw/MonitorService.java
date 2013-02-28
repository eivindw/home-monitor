package eivindw;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.client.HttpClientBuilder;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import eivindw.health.EfergyHealthCheck;
import eivindw.resources.ApiResource;
import eivindw.services.EfergyService;
import org.apache.http.client.HttpClient;

public class MonitorService extends Service<MonitorConfiguration> {

   public static void main(String[] args) throws Exception {
      new MonitorService().run(args);
   }

   @Override
   public void initialize(Bootstrap<MonitorConfiguration> bootstrap) {
      bootstrap.setName("Home Monitor");

      bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
   }

   @Override
   public void run(MonitorConfiguration config, Environment environment) throws Exception {
      environment.addHealthCheck(new EfergyHealthCheck("Efergy"));

      environment.addResource(new ApiResource());

      final HttpClient httpClient = new HttpClientBuilder().using(config.getHttpClient()).build();
      final MonitorConfiguration.UserPass efergy = config.getClients().getEfergy();

      new EfergyService(httpClient, efergy.getUser(), efergy.getPassword());
   }
}
