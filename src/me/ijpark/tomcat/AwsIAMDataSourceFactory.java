package me.ijpark.tomcat;


import java.util.Properties;
import javax.naming.Context;
import javax.sql.DataSource;
import org.apache.tomcat.jdbc.pool.DataSourceFactory;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.rds.auth.GetIamAuthTokenRequest;
import com.amazonaws.services.rds.auth.RdsIamAuthTokenGenerator;


public class AwsIAMDataSourceFactory extends DataSourceFactory {

    @Override
    public DataSource createDataSource(Properties properties, Context context, boolean XA) throws Exception {
    	

	    String region = "ap-northeast-2";
	    String hostname = "mbp.cluster-cuk380xfkb1v.ap-northeast-2.rds.amazonaws.com";
	    String port = "3306";
	    String username = "IAMTESTUSER";
	    String password;
	    String url;
	    
	    //password = "Sk2p*6HGlzVc2WM";
	    password = generateAuthToken(region, hostname, port, username);
	    properties.setProperty(PROP_PASSWORD, password);
	    properties.setProperty(PROP_USERNAME, username);
	    
	    url = "jdbc:mariadb:aurora//"+hostname+":"+ port+"/webuser?autoReconnect=true&verifyServerCertificate=true&useSSL=true&requireSSL=true";
	    properties.setProperty(PROP_URL, url);
	    
		return super.createDataSource(properties, context, XA);
	}

    static String generateAuthToken(String region, String hostName, String port, String username) {

	    RdsIamAuthTokenGenerator generator = RdsIamAuthTokenGenerator.builder()
		    .credentials(new DefaultAWSCredentialsProviderChain())
		    .region(region)
		    .build();

	    String authToken = generator.getAuthToken(
		    GetIamAuthTokenRequest.builder()
		    .hostname(hostName)
		    .port(Integer.parseInt(port))
		    .userName(username)
		    .build());
	    
	    return authToken;
    }

}