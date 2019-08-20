package com.edu.realestate.yelp;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.edu.realestate.model.City;
import com.edu.realestate.yelp.YelpBusiness;
import com.edu.realestate.yelp.YelpConnections;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class YelpSearch {

	private static YelpConnections connection = new YelpConnections();

	public static List<YelpBusiness> getBusinesses(City city) throws Exception {

		String uri = "https://api.yelp.com/v3/businesses/search?location=" + URLEncoder.encode(city.getName(), "UTF-8") + "+"
				+ URLEncoder.encode(city.getPostcode(), "UTF-8") + "&sort_by=distance&limit=10";

		List<YelpBusiness> yBusList = new ArrayList<>();

		yBusList = connection.searchCache(uri, "YelpBusiness");

		if (yBusList.isEmpty()) {

			String response = connection.sendGet(uri);

			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(response);
			JsonObject jsonRoot = jsonTree.getAsJsonObject();
			JsonArray jArray = jsonRoot.getAsJsonArray("businesses");
			
			for (JsonElement j : jArray) {
				JsonObject jobj = j.getAsJsonObject();

				List<String> categories = new ArrayList<>();
				JsonArray jcatArray = jobj.getAsJsonArray("categories");
				for (JsonElement jvcat : jcatArray) {
					JsonObject o = jvcat.getAsJsonObject();
					JsonElement v = o.get("title");
					categories.add(v.getAsString());
				}

				JsonObject location = jobj.getAsJsonObject("location");
				JsonArray jdaArray = location.getAsJsonArray("display_address");
				List<String> daList = new ArrayList<>();
				for (int i = 0; i < jdaArray.size(); i++)
					daList.add(jdaArray.get(i).getAsString());

				jobj.get("name");
				
				YelpBusiness ybus = new YelpBusiness(
						!jobj.get("name").isJsonNull() ? jobj.get("name").getAsString() : "",
						!jobj.get("url").isJsonNull() ? jobj.get("url").getAsString() : "",
						!jobj.get("image_url").isJsonNull() ? jobj.get("image_url").getAsString() : "",
						String.join(", ", categories),
						!jobj.get("rating").isJsonNull() ? jobj.get("rating").getAsDouble() : 0,
						!jobj.get("distance").isJsonNull() ? jobj.get("distance").getAsDouble() : 0,
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

			JsonParser parser = new JsonParser();
			JsonElement jsonTree = parser.parse(response);
			JsonObject jsonRoot = jsonTree.getAsJsonObject();
			JsonArray jArray = jsonRoot.getAsJsonArray("events");

			for (JsonElement j : jArray) {
				JsonObject jobj = j.getAsJsonObject();

				JsonObject location = jobj.getAsJsonObject("location");
				JsonArray jdaArray = location.getAsJsonArray("display_address");
				List<String> daList = new ArrayList<>();
				for (int i = 0; i < jdaArray.size(); i++)
					daList.add(jdaArray.get(i).toString());
				
				YelpEvent yevt = new YelpEvent(
						!jobj.get("name").isJsonNull() ? jobj.get("name").getAsString() : "",
						!jobj.get("event_site_url").isJsonNull() ? jobj.get("event_site_url").getAsString() : "",
						!jobj.get("description").isJsonNull() ? jobj.get("description").getAsString() : "",
						!jobj.get("is_free").isJsonNull() ? jobj.get("is_free").getAsBoolean() : false,
						!jobj.get("time_start").isJsonNull() ? LocalDateTime.parse(jobj.get("time_start").getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null,
						!jobj.get("time_end").isJsonNull() ? LocalDateTime.parse(jobj.get("time_end").getAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME) : null,
						String.join(", ", daList));
				yEvtList.add(yevt);
			}

			connection.cacheResults(uri, "YelpEvent", yEvtList);

		}
		
		return yEvtList;

	}

}
