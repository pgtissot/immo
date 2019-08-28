package com.edu.realestate.services;

import com.edu.realestate.model.*;

public class RealEstateFactory {

	private static int serialNumber = 10000;

	RealEstate getRealEstate(RealEstateType type) {
		return this.getRealEstate(type, null);
	}

	RealEstate getRealEstate(RealEstateType type, City city) {
		RealEstate re = null;

		switch (type) {
		case Apartment:
			Apartment a = new Apartment();
			a.setRooms(5);
			a.setAlarm(true);
			re = a;
			break;
		case CommercialProperty:
			CommercialProperty cp = new CommercialProperty();
			cp.setArea((short) 10000);
			cp.setAvailable(true);
			re = cp;
			break;
		case House:
			House h = new House();
			h.setRooms(10);
			re = h;
			break;
		case Land:
			Land l = new Land();
			l.setArea((short) 12);
			l.setAvailable(false);
			re = l;
			break;
		case OtherProperty:
			OtherProperty o = new OtherProperty();
			o.setPrice(12);
			re = o;
			break;
		case Parking:
			Parking p = new Parking();
			p.setPrice(5000);
			re = p;
			break;
		default:
			System.out.println("Mauvais RealEstateType");
		}

		if (re != null) {
			re.setId(serialNumber++);
			re.setCity(city);
		}

		return re;

	}
}
