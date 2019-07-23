package com.edu.realestate.dao;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClientProvider {

	private RestHighLevelClient client;
	
	// Will disappear later
	ESClientProvider() { init(); }
	
	@PostConstruct
	public void init() {
		client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
	}
	
	@PreDestroy
	public void destroy() throws IOException {
		if (client != null) client.close();
	}
	
	public RestHighLevelClient getClient() {
		return client;
	}
}

