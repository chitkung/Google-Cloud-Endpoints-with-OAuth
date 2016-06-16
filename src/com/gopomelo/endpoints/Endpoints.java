package com.gopomelo.endpoints;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.Json;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

import com.google.api.services.plus.PlusScopes;

@Api(name="testendpoints",version="v1", description="Test Endpoints")
public class Endpoints {

	@ApiMethod(name="test", path="test", httpMethod=HttpMethod.POST)
	public Data test(@Named("number") int number, @Named("accessToken") String accessToken) {
	    
		Data data = new Data();
		
		if(OAuthVertifier.validateByAccessToken(accessToken)) {
			data.setCount(number);
		}
		
		return data;
	}
	
	@ApiMethod(name="getAccessToken", path="getAccessToken", httpMethod=HttpMethod.POST)
	public OAuth getAccessToken() {

		try {
			String serviceAccountId = "SERVICE_ACCOUNT_ID";
			File p12File = new File("WEB-INF/filename.p12");
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			
			GoogleCredential credential = new GoogleCredential.Builder()
				    .setTransport(httpTransport)
				    .setJsonFactory(new JacksonFactory())
				    .setServiceAccountId(serviceAccountId)
				    .setServiceAccountPrivateKeyFromP12File(p12File)
				    .setServiceAccountScopes(Arrays.asList(new String[]{PlusScopes.USERINFO_EMAIL}))
				    .build();
			
			//"https://www.googleapis.com/auth/userinfo.email"
			
			credential.refreshToken();
			
			System.out.println("Access Token " + credential.getAccessToken());
			System.out.println("Refresh Token " + credential.getRefreshToken());
			System.out.println("Is Request Token " + credential.refreshToken());
			
			OAuth oAuth = new OAuth();
			oAuth.setAccessToken(credential.getAccessToken());
			
			return oAuth;
			
		} catch (IOException e) {
			System.out.println(e);
		} catch (GeneralSecurityException e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	
	public class Data {
		
		int count;
		
		public void setCount(int count) {
			this.count = count;
		}
		
		public int getCount() {
			return this.count;
		}
	}
}
