package eivindw.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class EfergyService {

   private static final Logger logger = LoggerFactory.getLogger(EfergyService.class);

   private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

   private final HttpClient httpClient;
   private final String user;
   private final String password;

   public EfergyService(HttpClient httpClient, String user, String password) {
      this.httpClient = httpClient;
      this.user = user;
      this.password = password;
   }

   public Map getInstant() {
      return postToJson("http://engage.efergy.com/mobile_proxy/getInstant", nvParam("token", getLoginToken()));
   }

   private String getLoginToken() {
      Map<String, Object> jsonMap = postToJson(
         "http://engage.efergy.com/mobile/get_token",
         nvParam("username", user),
         nvParam("password", password),
         nvParam("device", "IPHONE")
      );

      if("ok".equals(jsonMap.get("status"))) {
         return (String) jsonMap.get("token");
      } else {
         throw new RuntimeException("Not authenticated! Username: " + user);
      }
   }

   @SuppressWarnings("unchecked")
   private Map<String, Object> postToJson(String url, BasicNameValuePair... params) {
      try {
         HttpPost loginPost = new HttpPost(url);

         loginPost.setEntity(new UrlEncodedFormEntity(Lists.<NameValuePair>newArrayList(params)));

         HttpResponse loginResponse = httpClient.execute(loginPost);

         return OBJECT_MAPPER.readValue(loginResponse.getEntity().getContent(), Map.class);
      } catch (Exception e) {
         logger.error("Problem connecting to Efergy", e);
         throw new RuntimeException("Problem connecting to Efergy", e);
      }
   }

   private BasicNameValuePair nvParam(String name, String value) {
      return new BasicNameValuePair(name, value);
   }
}
