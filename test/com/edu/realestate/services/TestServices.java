package com.edu.realestate.services;

import java.util.List;

import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.City;
import com.edu.realestate.model.Moderator;
import com.edu.realestate.model.RealEstate;
import com.edu.realestate.model.RealEstateType;
import com.edu.realestate.model.User;
import com.edu.realestate.services.AdvertisementService;
import com.edu.realestate.services.ReferenceService;
import com.edu.realestate.services.UserService;
import com.edu.realestate.services.mock.AdvertisementServiceMock;
import com.edu.realestate.services.mock.ReferenceServiceMock;
import com.edu.realestate.services.mock.UserServiceMock;

public class TestServices {

	public static void main(String[] args) throws AuthenticationException {

		// UserService Test
		UserService userService = new UserServiceMock();

		Moderator mod = new Moderator("Tartempion", "123456", "Yay", "Oops", "Hey", "What", "Destructor");
		userService.register(mod);

		Advertiser adv = new Advertiser("Globubill", "1", "Seller", "Zorglub", "Biglodur", "0123456789");
		userService.register(adv);

		User u = null;
		
		try {
			u = userService.authenticate("Tartempion", "123456");
			System.out.println(u);
			u = userService.authenticate("Globubill", "654321");
			System.out.println("-------------------------");
		} catch (AuthenticationException ae) {
			System.err.println("Cannot connect : " + ae.getLocalizedMessage());
		}
		System.out.println(u);

		// ReferenceService Test
		ReferenceService rs = new ReferenceServiceMock();
		List<City> list = rs.listCities();
		for (City city : list)
			System.out.println(city);
		System.out.println("-------------------------");

		// AdvertisementService Test
		AdvertisementService advertisementService = new AdvertisementServiceMock();

		// RealEstateFactory Test
		RealEstateFactory ref = new RealEstateFactory();

		RealEstate rea = ref.getRealEstate(RealEstateType.Apartment);
		System.out.println(rea);
		
	}
}
