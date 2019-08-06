package com.edu.realestate.model;

public class SearchCriteria {
	
	private int cityId;
	private String query;
	private RealEstateType realEstateType;
	private TransactionType transactionType;
	private int priceMin;
	private int priceMax;
	private int areaMin;
	private int areaMax;
	private int landMin;
	private int landMax;
	private int roomsMin;
	private int roomsMax;
	private char energyLevel;
	private char gasLevel;
	private boolean elevator;
	private boolean intercom;
	private boolean balcony;
	private boolean terrace;
	private boolean garage;
	private boolean parking;
	private boolean alarm;
	private boolean digicode;
	private boolean cellar;
	private boolean swimmingPool;
	private double longitude;
	private double latitude;
	private int distance;
	private String sort;
	private int limit;
	private int offset;
	private int exclude;
	
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public RealEstateType getRealEstateType() {
		return realEstateType;
	}
	public void setRealEstateType(RealEstateType realEstateType) {
		this.realEstateType = realEstateType;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public int getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}
	public int getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}
	public int getAreaMin() {
		return areaMin;
	}
	public void setAreaMin(int areaMin) {
		this.areaMin = areaMin;
	}
	public int getAreaMax() {
		return areaMax;
	}
	public void setAreaMax(int areaMax) {
		this.areaMax = areaMax;
	}
	public int getLandMin() {
		return landMin;
	}
	public void setLandMin(int landMin) {
		this.landMin = landMin;
	}
	public int getLandMax() {
		return landMax;
	}
	public void setLandMax(int landMax) {
		this.landMax = landMax;
	}
	public int getRoomsMin() {
		return roomsMin;
	}
	public void setRoomsMin(int roomsMin) {
		this.roomsMin = roomsMin;
	}
	public int getRoomsMax() {
		return roomsMax;
	}
	public void setRoomsMax(int roomsMax) {
		this.roomsMax = roomsMax;
	}
	public double getLongitude() {
		return longitude;
	}
	public char getEnergyLevel() {
		return energyLevel;
	}
	public void setEnergyLevel(char energyLevel) {
		this.energyLevel = energyLevel;
	}
	public char getGasLevel() {
		return gasLevel;
	}
	public void setGasLevel(char gasLevel) {
		this.gasLevel = gasLevel;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public boolean hasElevator() {
		return elevator;
	}
	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}
	public boolean hasIntercom() {
		return intercom;
	}
	public void setIntercom(boolean intercom) {
		this.intercom = intercom;
	}
	public boolean hasBalcony() {
		return balcony;
	}
	public void setBalcony(boolean balcony) {
		this.balcony = balcony;
	}
	public boolean hasTerrace() {
		return terrace;
	}
	public void setTerrace(boolean terrace) {
		this.terrace = terrace;
	}
	public boolean hasGarage() {
		return garage;
	}
	public void setGarage(boolean garage) {
		this.garage = garage;
	}
	public boolean hasParking() {
		return parking;
	}
	public void setParking(boolean parking) {
		this.parking = parking;
	}
	public boolean hasAlarm() {
		return alarm;
	}
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
	public boolean hasDigicode() {
		return digicode;
	}
	public void setDigicode(boolean digicode) {
		this.digicode = digicode;
	}
	public boolean hasCellar() {
		return cellar;
	}
	public void setCellar(boolean cellar) {
		this.cellar = cellar;
	}
	public boolean hasSwimmingPool() {
		return swimmingPool;
	}
	public void setSwimmingPool(boolean swimmingPool) {
		this.swimmingPool = swimmingPool;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getExclude() {
		return exclude;
	}
	public void setExclude(int exclude) {
		this.exclude = exclude;
	}
	

	@Override
	public String toString() {
		return "SearchCriteria [cityId=" + cityId + ", query=" + query + ", realEstateType=" + realEstateType
				+ ", transactionType=" + transactionType + ", priceMin=" + priceMin + ", priceMax=" + priceMax
				+ ", areaMin=" + areaMin + ", areaMax=" + areaMax + ", landMin=" + landMin + ", landMax=" + landMax
				+ ", roomsMin=" + roomsMin + ", roomsMax=" + roomsMax + ", energyLevel=" + energyLevel + ", gasLevel="
				+ gasLevel + ", elevator=" + elevator + ", intercom=" + intercom + ", balcony=" + balcony + ", terrace="
				+ terrace + ", garage=" + garage + ", parking=" + parking + ", alarm=" + alarm + ", digicode="
				+ digicode + ", cellar=" + cellar + ", swimmingPool=" + swimmingPool + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", distance=" + distance + ", sort=" + sort + ", limit=" + limit
				+ ", offset=" + offset + ", exclude=" + exclude + "]";
	}
	
}
