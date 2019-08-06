package com.edu.realestate.exceptions;

public class RealEstateException extends Exception {

	private static final long serialVersionUID = 1161749579016406571L;

	public RealEstateException() {
		super("RealEstate");
	}

	public RealEstateException(String message) {
		super("RealEstate Error : " + message);
	}
}
