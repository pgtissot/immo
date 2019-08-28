package com.edu.spring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.edu.config.SpringConfigLvl2;
import com.edu.config.SpringConfigLvl3;
import com.edu.realestate.config.SpringConfig;
import com.edu.realestate.config.TestConfig;
import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.SearchDao;
import com.edu.realestate.dao.SearchDaoHib;
import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Moderator;
import com.edu.realestate.services.AdvertisementService;
import com.edu.realestate.services.UserService;

public class SpringTest {
	
	public static void main(String[] args) throws RealEstateException {
		
		

//		Spring Config Level 1 : via un fichier XML		
//		ApplicationContext springCtx = new FileSystemXmlApplicationContext("/resources/monappli.xml");
//		CityDao cdao = springCtx.getBean("citydao");

//		Spring Config Level 2 : via annotations dans une classe Java vide et les classes cibles
		ApplicationContext springCtx = new AnnotationConfigApplicationContext(TestConfig.class);
//		CityDao cdao = springCtx.getBean(CityDao.class);
//		System.out.println(cdao);
	
//		Spring Config Level 3 : via seulement la classe Java
//		ApplicationContext springCtx = new AnnotationConfigApplicationContext(SpringConfigLvl3.class);
//		CityDao cdao = springCtx.getBean("citydao");

//		System.out.println(cdao);
		
		AdvertisementService as = springCtx.getBean(AdvertisementService.class);
		System.out.println(as.findAdvertisementById(1792));
//		List<Advertisement> lads = as.findLatestAds(5); 
//		for (Advertisement a : lads)
//			System.out.println(a.getId());
		
//		UserService us = springCtx.getBean(UserService.class);
//		us.register(new Moderator("blah", "blah", "blah"));

		((ConfigurableApplicationContext)springCtx).close(); 
	}

}
