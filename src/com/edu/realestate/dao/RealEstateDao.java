package com.edu.realestate.dao;

import com.edu.realestate.model.RealEstate;

public interface RealEstateDao extends AbstractDao<RealEstate> {

	long countRealEstates();

}
