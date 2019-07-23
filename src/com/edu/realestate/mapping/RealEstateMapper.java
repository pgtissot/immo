package com.edu.realestate.mapping;

import java.sql.ResultSet;
import com.edu.realestate.dao.ApartmentDao;
import com.edu.realestate.dao.ApartmentDaoJDBC;
import com.edu.realestate.dao.CityDao;
import com.edu.realestate.dao.CityDaoJDBC;
import com.edu.realestate.dao.CommercialPropertyDao;
import com.edu.realestate.dao.CommercialPropertyDaoJDBC;
import com.edu.realestate.dao.HouseDao;
import com.edu.realestate.dao.HouseDaoJDBC;
import com.edu.realestate.dao.LandDao;
import com.edu.realestate.dao.LandDaoJDBC;
import com.edu.realestate.dao.OtherPropertyDao;
import com.edu.realestate.dao.OtherPropertyDaoJDBC;
import com.edu.realestate.dao.ParkingDao;
import com.edu.realestate.dao.ParkingDaoJDBC;
import com.edu.realestate.model.City;
import com.edu.realestate.model.RealEstate;

public class RealEstateMapper {

	public static RealEstate resultToRealEstate(ResultSet rs) throws Exception {
		RealEstate re = null;

		switch (rs.getString("realtype")) {
		case "Apartment":
			ApartmentDao adao = new ApartmentDaoJDBC();
			re = adao.read(rs.getInt("id"));
			break;
		case "Commercial":
			CommercialPropertyDao cdao = new CommercialPropertyDaoJDBC();
			re = cdao.read(rs.getInt("id"));
			break;
		case "House":
			HouseDao hdao = new HouseDaoJDBC();
			re = hdao.read(rs.getInt("id"));
			break;
		case "Land":
			LandDao ldao = new LandDaoJDBC();
			re = ldao.read(rs.getInt("id"));
			break;
		case "Other":
			OtherPropertyDao odao = new OtherPropertyDaoJDBC();
			re = odao.read(rs.getInt("id"));
			break;
		case "Parking":
			ParkingDao kdao = new ParkingDaoJDBC();
			re = kdao.read(rs.getInt("id"));
			break;
		default:
			throw new IllegalArgumentException();
		}

		CityDao cdao = new CityDaoJDBC();
		City city = cdao.read(rs.getInt("city_id"));
		re.setCity(city);

		return re;

	}

	public static String realEstatetoResult(RealEstate re) throws Exception {
		throw new Exception();
	}

}
