package com.gopomelo.endpoints;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Tokeninfo;

public class OAuthVertifier {

	static final String CLIENT_ID = "CLIENT_ID";

	public static boolean validateByIDToken(String id_token) {
		
		boolean test;
		
		//Client Id can be found in Developers Console
		String[] clientIds = new String[]{CLIENT_ID};
		
		//If coming from Google Play Services 8.3 API or newer, 
		//set issuer the issuer to "https://accounts.google.com"
		//Otherwise, set the issuer to "accounts.google.com"
		final String issuer = "accounts.google.com"; 
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory())
				.setAudience(Arrays.asList(clientIds))
			    .setIssuer(issuer)
			    .build();

		try {
			
			GoogleIdToken idToken = verifier.verify(id_token);
			
			if(idToken != null) {
				test = true;
			} else {
				test = false;
			}
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
			test = false;
		}
		
		return test;
	}
	
	public static boolean validateByAccessToken(String accessToken) {
		

		if (accessToken != null) {
	          // Check that the Access Token is valid.
	          try {
	        	  
	            GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
	            Oauth2 oauth2 = new Oauth2.Builder(
	                new NetHttpTransport(), new JacksonFactory(), credential).build();
	            
	            Tokeninfo tokenInfo = oauth2.tokeninfo().setAccessToken(accessToken).execute();
	            
	            if (!tokenInfo.getIssuedTo().equals(CLIENT_ID)) {
	            		System.out.println("Access Token not meant for this app.");
	            		return false;
	            } else {
	            		System.out.println("Access Token is valid.");
	            		return true;
	            }
	            
	          } catch (IOException e) {
	        	  	System.out.println("Invalid Access Token.");
	        	  	return false;
	          }
	        } else {
	        		System.out.println("Access Token not provided");
	        		return false;
	        }
	}
}
