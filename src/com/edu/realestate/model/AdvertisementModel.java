package com.edu.realestate.model;

import java.util.Arrays;

public class AdvertisementModel {
	
	String username;
	TransactionType transactionType;
	String title;
	RealEstateType realEstateType;
	int cityId;
	int price;
	short area;
	int landArea;
	int rooms;
	String floor;
	String description;
	byte[] picture;
	boolean cellar;
	boolean alarm;
	boolean swimmingPool;
	boolean elevator;
	boolean intercom;
	boolean balcony;
	boolean terrace;
	boolean garage;
	boolean parking;
	boolean digicode;
	String energyLevel;
	String gasLevel;
	
	public AdvertisementModel(String username, TransactionType transactionType, String title, RealEstateType realEstateType,
			int cityId, int price, short area, int landArea, int rooms, String floor, String description,
			byte[] picture, boolean cellar, boolean alarm, boolean swimmingPool, boolean elevator, boolean intercom,
			boolean balcony, boolean terrace, boolean garage, boolean parking, boolean digicode, String energyLevel,
			String gasLevel) {
		this.username = username;
		this.transactionType = transactionType;
		this.title = title;
		this.realEstateType = realEstateType;
		this.cityId = cityId;
		this.price = price;
		this.area = area;
		this.landArea = landArea;
		this.rooms = rooms;
		this.floor = floor;
		this.description = description;
		this.picture = picture;
		this.cellar = cellar;
		this.alarm = alarm;
		this.swimmingPool = swimmingPool;
		this.elevator = elevator;
		this.intercom = intercom;
		this.balcony = balcony;
		this.terrace = terrace;
		this.garage = garage;
		this.parking = parking;
		this.digicode = digicode;
		this.energyLevel = energyLevel;
		this.gasLevel = gasLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public RealEstateType getRealEstateType() {
		return realEstateType;
	}

	public void setRealEstateType(RealEstateType realEstate) {
		this.realEstateType = realEstate;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public short getArea() {
		return area;
	}

	public void setArea(short area) {
		this.area = area;
	}

	public int getLandArea() {
		return landArea;
	}

	public void setLandArea(int landArea) {
		this.landArea = landArea;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public boolean isCellar() {
		return cellar;
	}

	public void setCellar(boolean cellar) {
		this.cellar = cellar;
	}

	public boolean isAlarm() {
		return alarm;
	}

	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	public boolean isSwimmingPool() {
		return swimmingPool;
	}

	public void setSwimmingPool(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}

	public boolean isElevator() {
		return elevator;
	}

	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}

	public boolean isIntercom() {
		return intercom;
	}

	public void setIntercom(boolean intercom) {
		this.intercom = intercom;
	}

	public boolean isBalcony() {
		return balcony;
	}

	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}

	public boolean isTerrace() {
		return terrace;
	}

	public void setTerrace(boolean terrace) {
		this.terrace = terrace;
	}

	public boolean isGarage() {
		return garage;
	}

	public void setGarage(boolean garage) {
		this.garage = garage;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isDigicode() {
		return digicode;
	}

	public void setDigicode(boolean digicode) {
		this.digicode = digicode;
	}

	public String getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(String energyLevel) {
		this.energyLevel = energyLevel;
	}

	public String getGasLevel() {
		return gasLevel;
	}

	public void setGasLevel(String gasLevel) {
		this.gasLevel = gasLevel;
	}

	@Override
	public String toString() {
		return "AdvertisementModel [username=" + username + ", transactionType=" + transactionType + ", title=" + title
				+ ", realEstate=" + realEstateType + ", cityId=" + cityId + ", price=" + price + ", area=" + area
				+ ", landArea=" + landArea + ", rooms=" + rooms + ", floor=" + floor + ", description=" + description
				+ ", picture=" + Arrays.toString(picture) + ", cellar=" + cellar + ", alarm=" + alarm
				+ ", swimmingPool=" + swimmingPool + ", elevator=" + elevator + ", intercom=" + intercom + ", balcony="
				+ balcony + ", terrace=" + terrace + ", garage=" + garage + ", parking=" + parking + ", digicode="
				+ digicode + ", energyLevel=" + energyLevel + ", gasLevel=" + gasLevel + "]";
	}
	


}
