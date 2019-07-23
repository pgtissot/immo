package com.edu.realestate.dao;

import java.util.List;

import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.SearchCriteria;

public interface SearchDao {

	List<Advertisement> search(SearchCriteria sc);
	
}
