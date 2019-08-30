package com.edu.realestate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.realestate.config.TestConfig;
import com.edu.realestate.model.City;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TestConfig.class)
public class ReferenceServiceT {

	// SUT
	@Autowired
	private ReferenceService service; 
	
	
//	private static ClassPathXmlApplicationContext ctx;
	
	@BeforeClass
	public static void setupClass() throws Exception {
//		ctx = new ClassPathXmlApplicationContext("app-context-test.xml");
	}
	
	@AfterClass
	public static void tearDown() {
//		if (ctx!=null) ctx.close();
	}
	
	@Before
	public void setup() throws Exception {
		//ApplicationContext ctx= new ClassPathXmlApplicationContext("app-context-test.xml");
//		service = (UserService) ctx.getBean("userService");
//		service = ctx.getBean(ReferenceService.class);
	}
	
	@Test
	public void it_should_find_cities_starting_with_input() throws Exception{
		
		// arrange
		String input = "paris";
		
		// act
		List<City> cities = service.findCitiesByName(input, false);
		
		// assert
		assertThat(cities)
			.isNotNull()
			.hasSize(10)
			.doesNotContainNull();
		
		City city1 = cities.get(0);
		assertThat(city1)
			.extracting(City::getId, City::getName, City::getPostcode)
			.containsExactly(11333, "Parignargues", "30730");
		assertThat(city1.getLongitude()).isCloseTo(4.20000000, within(0.001));
		assertThat(city1.getLatitude()).isCloseTo(43.86670000, within(0.001));
		
		City city2 = cities.get(15);
		assertThat(city2)
		.extracting(City::getId, City::getName, City::getPostcode)
		.containsExactly(30473, "Paris 14", "75014");
		assertThat(city2.getLongitude()).isCloseTo(2.34445000, within(0.001));
		assertThat(city2.getLatitude()).isCloseTo(48.86000000, within(0.001));
		
		City city3 = cities.get(32);
		assertThat(city3)
		.extracting(City::getId, City::getName, City::getPostcode)
		.containsExactly(33572, "Parisot", "82160");
		assertThat(city3.getLongitude()).isCloseTo(1.85000000, within(0.001));
		assertThat(city3.getLatitude()).isCloseTo(44.26670000, within(0.001));
	}
	
}
