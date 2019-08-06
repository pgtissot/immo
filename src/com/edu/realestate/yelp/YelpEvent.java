package com.edu.realestate.yelp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;

public class YelpEvent extends YelpElement {

	private String description;
	private boolean free;
	private LocalDateTime timeStart;
	private LocalDateTime timeEnd;

	public YelpEvent(String name, String url, String description, boolean free, LocalDateTime timeStart,
			LocalDateTime timeEnd, String address) {
		super(name, url, address);
		this.description = description;
		this.free = free;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public LocalDateTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalDateTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalDateTime timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String formatDateTime(LocalDateTime ldt) {
		return ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}
	
	
	@Override
	public String toString() {
		return "YelpEvent [" + super.toString() + ", description=" + description + ", free=" + free
				+ ", timeStart=" + timeStart + ", timeEnd=" + timeEnd + "]";
	}
	
	
	public JsonObject toJson () {

		JsonBuilderFactory jbf = Json.createBuilderFactory(null);

		JsonObject json = jbf.createObjectBuilder()
				.add("name", name)
				.add("url", url)
				.add("description", description)
				.add("free", free)
				.add("timeStart", formatDateTime(timeStart))
				.add("timeEnd", formatDateTime(timeEnd))
				.add("address", address).build();
		
		return json;
	}
	
}
