package eivindw;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;

public class MonitorService extends Service<Configuration> {

   public static void main(String[] args) throws Exception {
      new MonitorService().run(args);
   }

   @Override
   public void initialize(Bootstrap<Configuration> bootstrap) {
      bootstrap.setName("Home Monitor");
   }

   @Override
   public void run(Configuration configuration, Environment environment) throws Exception {
   }
}
