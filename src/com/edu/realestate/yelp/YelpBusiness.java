package com.edu.realestate.yelp;

public class YelpBusiness extends YelpElement {

	private String image_url;
	private String categories;
	private double rating;
	private double distance;

	
	public YelpBusiness(String name, String url, String image_url, String categories, double rating,
			double distance, String address) {
		super(name, url, address);
		this.image_url = image_url;
		this.categories = categories;
		this.rating = rating;
		this.distance = distance;
	}


	public String getImage_url() {
		return image_url;
	}


	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}


	public String getCategories() {
		return categories;
	}


	public void setCategories(String categories) {
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


	@Override
	public String toString() {
		return "YelpBusiness [" + super.toString() + ", image_url=" + image_url + ", categories=" + categories + ", rating=" + rating
				+ ", distance=" + distance + "]";
	}
	
}
