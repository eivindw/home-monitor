package eivindw;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MonitorConfiguration extends Configuration {

   @Valid
   @NotNull
   @JsonProperty
   private HttpClientConfiguration httpClient = new HttpClientConfiguration();

   @JsonProperty
   private Clients clients;

   public HttpClientConfiguration getHttpClient() {
      return httpClient;
   }

   public Clients getClients() {
      return clients;
   }

   public static class Clients {

      @JsonProperty
      private UserPass efergy;

      public UserPass getEfergy() {
         return efergy;
      }
   }

   public static class UserPass {

      @JsonProperty
      private String user;

      @JsonProperty
      private String password;

      public String getUser() {
         return user;
      }

      public String getPassword() {
         return password;
      }
   }
}
