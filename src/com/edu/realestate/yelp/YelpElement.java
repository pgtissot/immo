package com.edu.realestate.yelp;

public abstract class YelpElement {

	protected String name;
	protected String url;
	protected String address;

	public YelpElement(String name, String url, String address) {
		super();
		this.name = name;
		this.url = url;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "YelpElement [name=" + name + ", url=" + url + ", address=" + address + "]";
	}

}
