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
				+ URLEncoder.encode(city.getPostcode(), "UTF-8") + "&sort_by=distance&limit=10";

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

				YelpBusiness ybus = new YelpBusiness(
						!jobj.isNull("name") ? jobj.getString("name") : "",
						!jobj.isNull("url") ? jobj.getString("url") : "",
						!jobj.isNull("image_url") ? jobj.getString("image_url") : "",
						String.join(", ", categories),
						!jobj.isNull("rating") ? jobj.getJsonNumber("rating").doubleValue() : 0,
						!jobj.isNull("distance") ? jobj.getJsonNumber("distance").doubleValue() : 0,
						String.join(", ", daList));
				yBusList.add(ybus);
			}

			connection.cacheResults(uri, "YelpBusiness", yBusList);

		}

		return yBusList;

	}
	
	
	public static List<YelpEvent> getEvents(City city) throws Exception {

		String uri = "https://api.yelp.com/v3/events?location=" + URLEncoder.encode(city.getName(), "UTF-8") + "+"
				+ URLEncoder.encode(city.getPostcode(), "UTF-8") + "&limit=10";
		
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
				
				YelpEvent yevt = new YelpEvent(
						!jobj.isNull("name") ? jobj.getString("name") : "",
						!jobj.isNull("event_site_url") ? jobj.getString("event_site_url") : "",
						!jobj.isNull("description") ? jobj.getString("description") : "",
						!jobj.isNull("is_free") ? jobj.getBoolean("is_free") : false,
						!jobj.isNull("time_start") ? LocalDateTime.parse(jobj.getString("time_start"), DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null,
						!jobj.isNull("time_end") ? LocalDateTime.parse(jobj.getString("time_end"), DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null,
						String.join(", ", daList));
				yEvtList.add(yevt);
			}

			connection.cacheResults(uri, "YelpEvent", yEvtList);

		}
		
		return yEvtList;

	}

}
