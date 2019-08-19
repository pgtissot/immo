package com.edu.realestate.yelp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.internal.Base64;

import com.google.gson.Gson;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;

public class YelpConnections {

	private final String API_KEY = "mw-qRw9jKZL7uTa6YdLlVgyyVOIc4Krc-WVUt8n51FpqS_2gg2l1A0cjYgumohhL3Z5LC1upznwXePjTyF8sHuN-LmSsJNR2FurAsUtJ5r0Jlvlo8am90pvWHeRDXXYx";
	private MongoCollection<Document> cache = MongoClients.create("mongodb://localhost:27017").getDatabase("yelp").getCollection("yelpCache");
	
	public String sendGet(String request) throws Exception {

		URL obj = new URL(request);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// GET METHOD
		con.setRequestMethod("GET");

		// Request header
		con.setRequestProperty("Authorization", "Bearer " + API_KEY);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	
	public void cacheResults(String uri, String type, List<? extends YelpElement> yelpResults) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		String md5 = Base64.encode(md.digest(uri.getBytes()));

		Gson gson = new Gson();
		List<String> jsonL = new ArrayList<>();

		for (YelpElement yel : yelpResults)
			jsonL.add(gson.toJson(yel));
		
		Document doc = new Document("id", md5);
		doc.append("type", type);
		doc.append("results", jsonL.toString());
		cache.insertOne(doc);

	}

	
	@SuppressWarnings("unchecked")
	public <T extends YelpElement> List<T> searchCache(String uri, String type) throws Exception {

		MessageDigest md = MessageDigest.getInstance("MD5");
		String md5 = Base64.encode(md.digest(uri.getBytes()));
		
		Gson gson = new Gson();

		Document doc = cache.find(and(eq("id", md5),eq("type", type))).first();

		if (doc != null) {
			switch(type) {
			case "YelpBusiness" :
				YelpBusiness[] ybArray = gson.fromJson((String)doc.get("results"), YelpBusiness[].class);
				return (List<T>) Arrays.asList(ybArray);
			case "YelpEvent" :
				YelpEvent[] yeArray = gson.fromJson((String)doc.get("results"), YelpEvent[].class);
				return (List<T>) Arrays.asList(yeArray);
			}
		}

		return new ArrayList<>(); 

	}

}