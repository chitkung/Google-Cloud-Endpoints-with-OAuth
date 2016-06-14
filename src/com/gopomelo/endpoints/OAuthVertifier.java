package com.gopomelo.endpoints;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

public class OAuthVertifier {

	public static boolean isValid(String id_token) {
		
		boolean test;
		
		//Client Id can be found in Developers Console
		String[] clientIds = new String[]{CLIENT_IDS};
		
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
}
