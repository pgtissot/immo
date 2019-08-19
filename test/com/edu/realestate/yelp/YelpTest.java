package com.edu.realestate.yelp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.CityDaoJDBC;
import com.edu.realestate.model.City;

public class YelpTest {

	public static void main(String[] args) throws Exception {

		CityDao cdao = new CityDaoJDBC();
		City city = cdao.read(1370);
//		City city = cdao.read(12694);
			
		List<YelpBusiness> ybusList = YelpSearch.getBusinesses(city);
		List<YelpEvent> yevList = YelpSearch.getEvents(city);

		BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\test.txt"));

		writer.write(ybusList.toString());
		writer.write("\n");
		writer.write(yevList.toString());
		writer.close();

	}

}
