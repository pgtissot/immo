package com.edu.realestate.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.edu.realestate.converter.BooleanToStringConverter;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class House extends RealEstate {

	private Integer rooms;

	@Column(name="land_area")
	private Integer landArea;

	@Column(name="energy_level")
	private String energyLevel;

	@Column(name="gas_level")
	private String gasLevel;

	@Convert(converter=BooleanToStringConverter.class)
	private boolean cellar;

	@Convert(converter=BooleanToStringConverter.class)
	private boolean alarm;

	@Column(name="swimming_pool")
	@Convert(converter=BooleanToStringConverter.class)
	private boolean swimmingPool;

	public House() {}
	
	public House(int id, int rooms, Integer landArea, String energyLevel, String gasLevel, boolean cellar, boolean alarm,
			boolean swimmingPool) {
		this.id = id;
		this.rooms = rooms;
		this.landArea = landArea;
		this.energyLevel = energyLevel;
		this.gasLevel = gasLevel;
		this.cellar = cellar;
		this.alarm = alarm;
		this.swimmingPool = swimmingPool;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}

	public Integer getLandArea() {
		return landArea;
	}

	public void setLandArea(int landArea) {
		this.landArea = landArea;
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
	public String toFrench() {
		return "Maison";
	}

	@Override
	public String toString() {
		return "House [" + super.toString() + ", rooms=" + rooms + ", landArea=" + landArea +
				", energyLevel=" + energyLevel + ", gasLevel=" + gasLevel + ", cellar=" + cellar +
				", alarm=" + alarm + ", swimmingPool=" + swimmingPool + "]";
	}
	


}
