package eivindw;

import eivindw.health.EfergyHealthCheck;
import eivindw.health.WeatherHealthCheck;
import eivindw.resources.ApiResource;
import eivindw.resources.WeatherResource;
import eivindw.services.EfergyService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.client.HttpClient;

public class MonitorService extends Application<MonitorConfiguration> {

   public static void main(String[] args) throws Exception {
      new MonitorService().run(args);
   }

   @Override
   public void initialize(Bootstrap<MonitorConfiguration> bootstrap) {
      bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
   }

   @Override
   public void run(MonitorConfiguration config, Environment env) throws Exception {
      final HttpClient httpClient =
         new HttpClientBuilder(env.metrics()).using(config.getHttpClient()).build("default-http-client");
      final MonitorConfiguration.UserPass efergy = config.getClients().getEfergy();
      final EfergyService efergyService = new EfergyService(httpClient, efergy.getUser(), efergy.getPassword());

      env.healthChecks().register("Efergy", new EfergyHealthCheck(efergyService));
      env.healthChecks().register("Klimadata", new WeatherHealthCheck());

      env.jersey().register(new ApiResource());
      env.jersey().register(new WeatherResource());
   }
}
