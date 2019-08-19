package com.edu.realestate.yelp;

import java.net.URLEncoder;
import java.security.MessageDigest;
import org.bson.Document;
import org.bson.internal.Base64;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class YelpWithCache {

	public static void main(String[] args) throws Exception {

		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mongodb = mongoClient.getDatabase("yelp");
		MongoCollection<Document> collection = mongodb.getCollection("yelpCache");

		String url = "https://api.yelp.com/v3/businesses/search?location=" + URLEncoder.encode("Paris", "UTF-8") + "+"
				+ URLEncoder.encode("75016", "UTF-8") + "&sort_by=distance";

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md5 = md.digest(url.getBytes());
			System.out.println(Base64.encode(md5));
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		mongoClient.close();

	}
}
