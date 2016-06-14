package com.gopomelo.endpoints;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

@Api(name="testendpoints",version="v1", description="Test Endpoints")
public class Endpoints {


	@ApiMethod(name="test", path="test", httpMethod=HttpMethod.POST)
	public Data test(@Named("number") int number, @Named("id_token") String id_token) {
	    
		Data data = new Data();
		
		if(OAuthVertifier.isValid(id_token)) {
			data.setCount(number);
		}
		return data;
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
