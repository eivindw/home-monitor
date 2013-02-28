package eivindw.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EfergyService {

   private final static Logger logger = LoggerFactory.getLogger(EfergyService.class);

   private final HttpClient httpClient;
   private final String user;
   private final String password;

   public EfergyService(HttpClient httpClient, String user, String password) {
      this.httpClient = httpClient;
      this.user = user;
      this.password = password;
   }

   public Map getInstant() {
      try {
         // post to login
         HttpPost httpPost = new HttpPost("http://engage.efergy.com/login/dashboard");
         List<NameValuePair> nvps = new ArrayList<>();
         nvps.add(new BasicNameValuePair("email", user));
         nvps.add(new BasicNameValuePair("password", password));
         httpPost.setEntity(new UrlEncodedFormEntity(nvps));
         httpClient.execute(httpPost);

         // get request
         HttpGet httpGet = new HttpGet("http://engage.efergy.com/api/proxy/getInstant");
         HttpResponse response = httpClient.execute(httpGet);

         return new ObjectMapper().readValue(response.getEntity().getContent(), Map.class);
      } catch (Exception e) {
         logger.error("Problem connecting to Efergy", e);
         return null;
      }
   }
}
