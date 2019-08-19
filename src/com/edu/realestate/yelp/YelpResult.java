package com.edu.realestate.yelp;

import java.util.List;

public class YelpResult {

	private List<YelpBusiness> businessList;
	private List<YelpEvent> eventList;

	public YelpResult(List<YelpBusiness> businessList, List<YelpEvent> eventList) {
		super();
		this.businessList = businessList;
		this.eventList = eventList;
	}

	public List<YelpBusiness> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<YelpBusiness> businessList) {
		this.businessList = businessList;
	}

	public List<YelpEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<YelpEvent> eventList) {
		this.eventList = eventList;
	}

	@Override
	public String toString() {
		return "YelpResult [businessList=" + businessList + ", eventList=" + eventList + "]";
	}

	
	
	
	
}
