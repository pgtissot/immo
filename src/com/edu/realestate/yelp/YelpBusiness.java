package com.edu.realestate.yelp;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

public class YelpBusiness extends YelpElement {

	private String image_url;
	private List<String> categories;
	private double rating;
	private double distance;
	private double longitude;
	private double latitude;

	
	public YelpBusiness(String name, String url, String image_url, List<String> categories, double rating,
			double distance, double longitude, double latitude, String address) {
		super(name, url, address);
		this.image_url = image_url;
		this.categories = categories;
		this.rating = rating;
		this.distance = distance;
		this.longitude = longitude;
		this.latitude = latitude;
	}


	public String getImage_url() {
		return image_url;
	}


	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	public List<String> getCategories() {
		return categories;
	}


	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance) {
		this.distance = distance;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	@Override
	public String toString() {
		return "YelpBusiness [" + super.toString() + ", image_url=" + image_url + ", categories=" + categories + ", rating=" + rating
				+ ", distance=" + distance + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
	
	public JsonObject toJson () {

		JsonBuilderFactory jbf = Json.createBuilderFactory(null);

		JsonArrayBuilder jab = jbf.createArrayBuilder();
		for (String cat : categories)
			jab.add(cat);
		
		JsonArray jCatArray = jab.build();
		
		JsonObject json = jbf.createObjectBuilder()
				.add("name", name)
				.add("url", url)
				.add("image_url", image_url)
				.add("categories", jCatArray)
				.add("rating", rating)
				.add("distance", distance)
				.add("longitude", longitude)
				.add("latitude", latitude)
				.add("address", address).build();

		return json;
	}
	
}
