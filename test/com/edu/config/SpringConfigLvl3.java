package com.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.CityDaoHib;

@Configuration
public class SpringConfigLvl3 {

	@Bean(name="citydao")
	public CityDao cityDao() {
		return new CityDaoHib();
	}
	
}
