package eivindw;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class MonitorConfiguration extends Configuration {

   @JsonProperty
   private Clients clients;

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
