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
import com.edu.realestate.model.AdvertisementModel;
import com.edu.realestate.model.Apartment;
import com.edu.realestate.model.City;
import com.edu.realestate.model.CommercialProperty;
import com.edu.realestate.model.House;
import com.edu.realestate.model.Land;
import com.edu.realestate.model.OtherProperty;
import com.edu.realestate.model.Parking;
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

	
	public static RealEstate AdModelToRealEstate(AdvertisementModel am, City city) {

		switch (am.getRealEstateType().toString()) {
		case "Apartment":
			Apartment a = new Apartment();
			a.setAlarm(am.isAlarm());
			a.setArea(am.getArea());
			a.setAvailable(true);
			a.setBalcony(am.isBalcony());
			a.setCity(city);
			a.setDigicode(am.isDigicode());
			a.setElevator(am.isElevator());
			a.setEnergyLevel(am.getEnergyLevel());
			a.setFloor(am.getFloor());
			a.setGarage(am.isGarage());
			a.setGasLevel(am.getGasLevel());
			a.setIntercom(am.isIntercom());
			a.setParking(am.isParking());
			a.setPrice(am.getPrice());
			a.setRooms(am.getRooms());
			a.setTerrace(am.isTerrace());
			return a;
		case "Commercial":
			CommercialProperty c = new CommercialProperty();
			c.setArea(am.getArea());
			c.setAvailable(true);
			c.setCity(city);
			c.setPrice(am.getPrice());
			return c;
		case "House":
			House h = new House();
			h.setAlarm(am.isAlarm());
			h.setArea(am.getArea());
			h.setAvailable(true);
			h.setCellar(am.isCellar());
			h.setCity(city);
			h.setEnergyLevel(am.getEnergyLevel());
			h.setGasLevel(am.getGasLevel());
			h.setLandArea(am.getLandArea());
			h.setPrice(am.getPrice());
			h.setRooms(am.getRooms());
			h.setSwimmingPool(am.isSwimmingPool());
			return h;
		case "Land":
			Land l = new Land();
			l.setArea(am.getArea());
			l.setAvailable(true);
			l.setCity(city);
			l.setPrice(am.getPrice());
			return l;
		case "Other":
			OtherProperty o = new OtherProperty();
			o.setArea(am.getArea());
			o.setAvailable(true);
			o.setCity(city);
			o.setPrice(am.getPrice());
			return o;
		case "Parking":
			Parking k = new Parking();
			k.setArea(am.getArea());
			k.setAvailable(true);
			k.setCity(city);
			k.setPrice(am.getPrice());
			return k;
		default:
			throw new IllegalArgumentException();
		}

	}

	public static String realEstatetoResult(RealEstate re) throws Exception {
		throw new Exception();
	}

}
