package eivindw.services;

import com.yammer.dropwizard.client.HttpClientBuilder;
import com.yammer.dropwizard.client.HttpClientConfiguration;
import org.apache.http.client.HttpClient;
import org.junit.Test;

import java.util.Map;

public class EfergyServiceTest {

   @Test
   public void connectClientWithSession() throws Exception {
      final HttpClientConfiguration configuration = new HttpClientConfiguration();
      configuration.setCookiesEnabled(true);
      final HttpClient httpClient = new HttpClientBuilder().using(configuration).build();

      // Test runner must set these environment variables
      final String user = System.getenv("user");
      final String password = System.getenv("password");

      System.out.println("Calling with user: " + user + " password: " + password);

      EfergyService service = new EfergyService(httpClient, user, password);

      Map instant = service.getInstant();

      System.out.println("InstantMap: " + instant);
   }
}
