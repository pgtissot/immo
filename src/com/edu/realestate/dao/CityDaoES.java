package com.edu.realestate.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.edu.realestate.model.City;

public class CityDaoES implements CityDao {

	ESClientProvider clientProvider;

	public CityDaoES() {
		clientProvider = new ESClientProvider();
	}

	@Override
	public void create(City element) {
		// TODO Auto-generated method stub

	}

	@Override
	public City read(Integer id) {
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("id", id));
		searchRequest.source(searchSourceBuilder);
		
		City city = new City();
		
		try {
			SearchResponse searchResponse = clientProvider.getClient().search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits = searchResponse.getHits();
			for (SearchHit hit : hits) {
				Map<String, Object> hitMap = hit.getSourceAsMap();

				city.setId((Integer)(hitMap.get("id")));
				city.setName((String)(hitMap.get("name")));
				city.setPostcode((String)(hitMap.get("postcode")));
				city.setLongitude((Double)(hitMap.get("longitude")));
				city.setLatitude((Double)(hitMap.get("latitude")));

			}
		} catch (IOException ioe) {
			System.out.println("CityDaoES read error " + ioe.getLocalizedMessage());
		}

		// TODO Auto-generated method stub
		return city;
	}

	@Override
	public void update(City element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(City element) {
		// TODO Auto-generated method stub

	}

	public List<City> listMatching(String comparator, boolean exact) {
		SearchRequest searchRequest = new SearchRequest();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchQuery("name", comparator));
		searchRequest.source(searchSourceBuilder);
		
		List<City> list = new ArrayList<>();
		
		try {
			SearchResponse searchResponse = clientProvider.getClient().search(searchRequest, RequestOptions.DEFAULT);
			SearchHits hits = searchResponse.getHits();
			for (SearchHit hit : hits) {
				Map<String, Object> hitMap = hit.getSourceAsMap();

				City city = new City();
				city.setId((Integer)(hitMap.get("id")));
				city.setName((String)(hitMap.get("name")));
				city.setPostcode((String)(hitMap.get("postcode")));
				city.setLongitude((Double)(hitMap.get("longitude")));
				city.setLatitude((Double)(hitMap.get("latitude")));
				list.add(city);
			}
		} catch (IOException ioe) {
			System.out.println("CityDaoES listMatching error " + ioe.getLocalizedMessage());
		}
		
		return list;
	}
	
	@Override
	public List<City> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
