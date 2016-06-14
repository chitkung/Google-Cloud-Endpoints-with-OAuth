package com.gopomelo.endpoints;

import java.io.IOException;

import javax.servlet.http.*;

//import com.appspot.gopomelo_cloud.testendpoints.Testendpoints;
//import com.appspot.gopomelo_cloud.testendpoints.model.A;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.apache.ApacheHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.gson.GsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;


@SuppressWarnings("serial")
public class Test_EndpointsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//		try {
//			
//			JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//			HttpTransport httpTransport;
//			
//			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//			
//			File p12File = new File("WEB-INF/testendpoints.p12");
//			GoogleCredential credential;
//			
//			credential = new GoogleCredential.Builder()
//				    .setTransport(httpTransport)
//				    .setJsonFactory(JSON_FACTORY)
//				    .setServiceAccountId("test-endpoints@gopomelo-cloud.iam.gserviceaccount.com")
//				    .setServiceAccountPrivateKeyFromP12File(p12File)
//				    .setServiceAccountScopes(Collections.singleton("https://www.googleapis.com/auth/userinfo.email"))
//				    .build();
//			
//			String rootUrl = "https://gopomelo-cloud.appspot.com/_ah/api/";
//			
//			Testendpoints.Builder builder = new Testendpoints.Builder(new ApacheHttpTransport(), new GsonFactory(), credential);
//			Testendpoints testendpoints = builder.setRootUrl(rootUrl).build();
//			
//			A a = testendpoints.test(4).execute();
//			System.out.println("OUTPUT " + a.getCount());
//			
//		} catch (GeneralSecurityException e1) {
//			e1.printStackTrace();
//		}

		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
