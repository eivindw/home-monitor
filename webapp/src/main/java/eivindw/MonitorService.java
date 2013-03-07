package eivindw;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.client.HttpClientBuilder;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import eivindw.health.EfergyHealthCheck;
import eivindw.health.WeatherHealthCheck;
import eivindw.resources.ApiResource;
import eivindw.resources.WeatherResource;
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
      final HttpClient httpClient = new HttpClientBuilder().using(config.getHttpClient()).build();
      final MonitorConfiguration.UserPass efergy = config.getClients().getEfergy();
      final EfergyService efergyService = new EfergyService(httpClient, efergy.getUser(), efergy.getPassword());

      environment.addHealthCheck(new EfergyHealthCheck("Efergy", efergyService));
      environment.addHealthCheck(new WeatherHealthCheck("Klimadata"));

      environment.addResource(new ApiResource());
      environment.addResource(new WeatherResource());
   }
}
