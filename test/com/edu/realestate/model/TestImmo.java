package com.edu.realestate.model;

public class TestImmo {

	public static void main(String[] args) {

		RealEstate re = new House();
		City c = new City(1, "Ici", "25000");
		House h = new House();
		Land l = new Land();
		Parking p = new Parking();
		CommercialProperty cp = new CommercialProperty();

		re.setId(1);
		re.setPrice(250000);
		re.setArea((short)10);
		h.setId(12);
		h.setPrice(150000);
		h.setArea((short)10);
		l.setId(3);
		l.setPrice(250000);
		l.setArea((short)10);
		p.setId(4);
		p.setPrice(250000);
		p.setArea((short)10);
		cp.setId(5);
		cp.setPrice(250000);
		cp.setArea((short)10);

		RealEstate[] estates = { re, h, l, p, cp };
		afficheRE(estates);

		System.out.println(c);
		System.out.println(l.equals(re));
		System.out.println(l.equals(l));
		
		AdStatus st = AdStatus.valueOf("Pending");
		System.out.println(st);

	}

	public static void afficheRE(RealEstate[] tab) {
		for (RealEstate re : tab)
			System.out.println(re);
	}

}
