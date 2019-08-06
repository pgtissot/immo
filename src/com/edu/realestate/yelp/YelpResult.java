package com.edu.realestate.yelp;

import java.util.List;

public class YelpResult {

	private List<YelpBusiness> yBusList;
	private List<YelpEvent> yEvtList;

	public YelpResult(List<YelpBusiness> ybusList, List<YelpEvent> yevtList) {
		super();
		this.yBusList = ybusList;
		this.yEvtList = yevtList;
	}

	public List<YelpBusiness> getYbusList() {
		return yBusList;
	}

	public void setYbusList(List<YelpBusiness> ybusList) {
		this.yBusList = ybusList;
	}

	public List<YelpEvent> getYevtList() {
		return yEvtList;
	}

	public void setYevtList(List<YelpEvent> yevtList) {
		this.yEvtList = yevtList;
	}

	@Override
	public String toString() {
		return "YelpResult [ybusList=" + yBusList + ", yevtList=" + yEvtList + "]";
	}
	
	
	
	
}
