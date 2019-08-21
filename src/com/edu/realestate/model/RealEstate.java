package com.edu.realestate.model;

import java.text.DecimalFormat;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edu.realestate.converter.BooleanToStringConverter;

@Entity
@Table(name="real_estate")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class RealEstate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;

	protected int price;
	protected short area;
	
	@Convert(converter=BooleanToStringConverter.class)
	protected boolean available;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	protected City city;

	public RealEstate() {
	}

	public RealEstate(int id, int price, short area, boolean available, City city) {
		this.id = id;
		this.price = price;
		this.area = area;
		this.city = city;
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof RealEstate))
			return false;
		RealEstate re = (RealEstate) obj;
		return this.id == re.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + area;
		result = prime * result + (available ? 1231 : 1237);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + id;
		result = prime * result + price;
		return result;
	}

	public abstract String getType();

	public abstract String toFrench();
	
	public String priceFrench() {
		return new DecimalFormat("#,###").format(price);
	}

	@Override
	public String toString() {
		return " [id=" + id + ", price=" + price + ", area=" + area + ", available=" + available + ", city=" + city + "]";
	}

}
