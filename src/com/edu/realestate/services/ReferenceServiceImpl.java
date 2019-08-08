package com.edu.realestate.services;

import java.util.List;
import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.CityDaoJDBC;
import com.edu.realestate.dao.SearchDao;
import com.edu.realestate.dao.SearchDaoJDBC;
import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.SearchCriteria;

public class ReferenceServiceImpl implements ReferenceService {

	CityDao cdao;
	SearchDao sdao;
	
	public ReferenceServiceImpl() {
		cdao = new CityDaoJDBC();
		sdao = new SearchDaoJDBC();
	}
	
	@Override
	public List<City> findCitiesByName(String input, boolean exact) throws RealEstateException {
		return cdao.listMatching(input, exact);
	}

	@Override
	public List<Advertisement> findAdsByCriteria(SearchCriteria criteria) throws RealEstateException {
		return sdao.search(criteria);
	}

	@Override
	public List<City> listCities() {
		return cdao.listAll();
	}

}
