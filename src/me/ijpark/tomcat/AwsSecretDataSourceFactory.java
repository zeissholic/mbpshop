package me.ijpark.tomcat;


import java.nio.ByteBuffer;
import java.util.Properties;
import javax.naming.Context;
import javax.sql.DataSource;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.*;
import com.amazonaws.services.secretsmanager.model.*;
import com.google.gson.*;
import java.util.Base64; 
import java.util.Base64.Decoder; 
import java.util.Base64.Encoder;


public class AwsSecretDataSourceFactory extends DataSourceFactory {

	  public static String getSecret() {

	      String secretName = "mbp/shop/rds2";
	      String endpoint = "secretsmanager.ap-northeast-2.amazonaws.com";
	      String region = "ap-northeast-2";

	      AwsClientBuilder.EndpointConfiguration config = new AwsClientBuilder.EndpointConfiguration(endpoint, region);
	      AWSSecretsManagerClientBuilder clientBuilder = AWSSecretsManagerClientBuilder.standard();
	      clientBuilder.setEndpointConfiguration(config);
	      AWSSecretsManager client = clientBuilder.build();

	      String secret, decodedBinarySecret;
	      ByteBuffer binarySecretData;
	      GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
	              .withSecretId(secretName).withVersionStage("AWSCURRENT");
	      GetSecretValueResult getSecretValueResult = null;
	      try {
	          getSecretValueResult = client.getSecretValue(getSecretValueRequest);

	      } catch(ResourceNotFoundException e) {
	          System.out.println("The requested secret " + secretName + " was not found");
	      } catch (InvalidRequestException e) {
	          System.out.println("The request was invalid due to: " + e.getMessage());
	      } catch (InvalidParameterException e) {
	          System.out.println("The request had invalid params: " + e.getMessage());
	      }

	      if(getSecretValueResult == null) {
	          return null;
	      }

	      // Depending on whether the secret was a string or binary, one of these fields will be populated
	      if(getSecretValueResult.getSecretString() != null) {
	          secret = getSecretValueResult.getSecretString();
	          System.out.println("str:"+secret);
	          return secret;
	      }
	      else {
	          //binarySecretData = getSecretValueResult.getSecretBinary();
	          //System.out.println("bin:"+binarySecretData.toString());
	    	  //return null;
	    	  decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
	    	  System.out.println("bin:"+decodedBinarySecret);
	    	  return decodedBinarySecret;
	      }
	      
	      
	      
	  }

    @Override
    public DataSource createDataSource(Properties properties, Context context, boolean XA) throws Exception {
    	//properties.setProperty(PROP_USERNAME, "pocuser");
    	//properties.setProperty(PROP_PASSWORD, "bQ9wBijSoC");
    	//if (properties.getProperty("name").toString().contentEquals("jdbc/shopdb")) {
    		replacePassword(properties);
    		//System.out.println(properties.getProperty("password").toString());
    	//}

		return super.createDataSource(properties, context, XA);
	}

	private void replacePassword(Properties properties) throws Exception {
		String secret = getSecret();
		System.out.println("secret:"+secret);
		JsonObject convertedObject = new Gson().fromJson(secret, JsonObject.class);

        String password = convertedObject.getAsJsonObject().get("password").toString();
        String username = convertedObject.getAsJsonObject().get("username").toString();
        
        System.out.println("id:"+username+",pw"+password);
        
        properties.setProperty(PROP_USERNAME, username);
		properties.setProperty(PROP_PASSWORD, password);
		
	}

}