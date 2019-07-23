package com.edu.realestate.services;

import java.util.List;

import com.edu.realestate.model.*;
import com.edu.realestate.services.ReferenceService;

public class TestReferenceServiceImpl {

	public static void main(String[] args) throws Exception {

		SearchCriteria sc = new SearchCriteria();
		sc.setCityId(2003);
//		sc.setQuery("copropriété");
		

		ReferenceService rs = new ReferenceServiceImpl();
		List<Advertisement> lads = rs.findAdsByCriteria(sc);
		
		for (Advertisement a : lads)
			System.out.println(a);

	}	
}
