package com.edu.realestate.services;

public class TestFavoriteServiceImpl {

	public static void main(String[] args) {

		// UserService Test
		FavoriteService service = new FavoriteServiceImpl();

		service.addFavAds("pgthebest@blah.fr", 7, 1, "C'est plutôt cool ce truc");
		service.addFavAds("pgthebest@blah.fr", 2958, 5, null);

	}
}
