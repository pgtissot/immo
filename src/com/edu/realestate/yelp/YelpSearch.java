package com.edu.realestate.yelp;

import java.io.StringReader;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import com.edu.realestate.model.City;
import com.edu.realestate.yelp.YelpBusiness;
import com.edu.realestate.yelp.YelpConnections;


public class YelpSearch {

	private static YelpConnections connection = new YelpConnections();

	public static List<YelpBusiness> getBusinesses(City city) throws Exception {

		String uri = "https://api.yelp.com/v3/businesses/search?location=" + URLEncoder.encode(city.getName(), "UTF-8") + "+"
				+ URLEncoder.encode(city.getPostcode(), "UTF-8") + "&sort_by=distance";

		List<YelpBusiness> yBusList = new ArrayList<>();

		yBusList = connection.searchCache(uri, "YelpBusiness");
		
		if (yBusList.isEmpty()) {
		
			String response = connection.sendGet(uri);

			JsonReader reader = Json.createReader(new StringReader(response));
			JsonArray jarray = reader.readObject().getJsonArray("businesses");
			reader.close();

			for (JsonValue j : jarray) {
				JsonObject jobj = (JsonObject) j;

				List<String> categories = new ArrayList<>();
				JsonArray jcatArray = jobj.getJsonArray("categories");
				for (JsonValue jvcat : jcatArray) {
					categories.add(((JsonObject) jvcat).getString("title"));
				}

				JsonArray jdaArray = jobj.getJsonObject("location").getJsonArray("display_address");
				List<String> daList = new ArrayList<>();
				for (int i = 0; i < jdaArray.size(); i++)
					daList.add(jdaArray.getString(i));


				YelpBusiness ybus = new YelpBusiness(jobj.getString("name"), jobj.getString("url"),
						jobj.getString("image_url"), categories, jobj.getJsonNumber("rating").doubleValue(),
						jobj.getJsonNumber("distance").doubleValue(),
						jobj.getJsonObject("coordinates").getJsonNumber("longitude").doubleValue(),
						jobj.getJsonObject("coordinates").getJsonNumber("latitude").doubleValue(),
						String.join(", ", daList));
				yBusList.add(ybus);
			}

			connection.cacheResults(uri, yBusList);

		}

		return yBusList;

	}
	
	
	public static List<YelpEvent> getEvents(City city) throws Exception {

		String uri = "https://api.yelp.com/v3/events?location=" + URLEncoder.encode(city.getName(), "UTF-8") + "+"
				+ URLEncoder.encode(city.getPostcode(), "UTF-8");
		
		List<YelpEvent> yEvtList = new ArrayList<>();

		yEvtList = connection.searchCache(uri, "YelpEvent");

		if (yEvtList.isEmpty()) {
			
			String response = connection.sendGet(uri);

			JsonReader reader = Json.createReader(new StringReader(response));
			JsonArray jarray = reader.readObject().getJsonArray("events");
			reader.close();

			for (JsonValue j : jarray) {
				JsonObject jobj = (JsonObject) j;

				JsonArray jdaArray = jobj.getJsonObject("location").getJsonArray("display_address");
				List<String> daList = new ArrayList<>();
				for (int i = 0; i < jdaArray.size(); i++)
					daList.add(jdaArray.getString(i));

				YelpEvent yevt = new YelpEvent(jobj.getString("name"), jobj.getString("event_site_url"),
						jobj.getString("description"), jobj.getBoolean("is_free"),
						LocalDateTime.parse(jobj.getString("time_start"), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
						LocalDateTime.parse(jobj.getString("time_end"), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
						String.join(", ", daList));
				yEvtList.add(yevt);
			}

			connection.cacheResults(uri, yEvtList);

		}
		
		return yEvtList;

	}

}
