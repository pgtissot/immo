package com.edu.realestate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.bson.internal.Base64;

@Entity
public class Picture {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="content")
	private byte[] data;

	public Picture() {
	}

	public Picture(int id, byte[] data) {
		this.id = id;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return "data:image/jpeg;base64," + Base64.encode(data);
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Picture [id=" + id + ", data=" + getData() + "]";
	}

	
}
