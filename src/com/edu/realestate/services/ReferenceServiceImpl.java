package com.edu.realestate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.SearchDao;
import com.edu.realestate.exceptions.RealEstateException;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.City;
import com.edu.realestate.model.SearchCriteria;

@Service
@Transactional(readOnly=true)
public class ReferenceServiceImpl implements ReferenceService {

	@Autowired
	CityDao cdao;

	@Autowired
	SearchDao sdao;
	
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
