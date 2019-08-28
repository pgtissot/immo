package com.edu.realestate.model;

import java.util.Arrays;

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

	private int codage;
	
	@Column(name="content")
	private byte[] data;

	public Picture() {
	}

	public Picture(int id, int codage, byte[] data) {
		this.id = id;
		this.codage = codage;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodage() {
		return codage;
	}

	public void setCodage(int codage) {
		this.codage = codage;
	}

	public String getData() {
		return "data:image/jpeg;base64," + Base64.encode(data);
	}

	public byte[] getDataBytes() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Picture [id=" + id + ", codage=" + codage + ", data=" + Arrays.toString(data) + "]";
	}

	
}
