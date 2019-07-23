package com.edu.realestate.model;

public class House extends RealEstate {

	private int rooms;
	private int landArea;
	private char energyLevel;
	private char gasLevel;
	private boolean cellar;
	private boolean alarm;
	private boolean swimmingPool;

	public House() {}
	
	public House(int id) {
		super(id);
	}

	public House(int id, int rooms, int landArea, char energyLevel, char gasLevel, boolean cellar, boolean alarm,
			boolean swimmingPool) {
		super(id);
		this.rooms = rooms;
		this.landArea = landArea;
		this.energyLevel = energyLevel;
		this.gasLevel = gasLevel;
		this.cellar = cellar;
		this.alarm = alarm;
		this.swimmingPool = swimmingPool;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public int getLandArea() {
		return landArea;
	}

	public void setLandArea(int landArea) {
		this.landArea = landArea;
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

	@Override
	public String toString() {
		return "House [" + super.toString() + ", rooms=" + rooms + ", landArea=" + landArea +
				", energyLevel=" + energyLevel + ", gasLevel=" + gasLevel + ", cellar=" + cellar +
				", alarm=" + alarm + ", swimmingPool=" + swimmingPool + "]";
	}
	


}
