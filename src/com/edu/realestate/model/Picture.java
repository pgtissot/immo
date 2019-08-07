package com.edu.realestate.model;

public class Picture {

	private int id;
	private String codage;
	private String data;

	public Picture() {
	}

	public Picture(int id, String codage, String data) {
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

	public String getCodage() {
		return codage;
	}

	public void setCodage(String codage) {
		this.codage = codage;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", codage=" + codage + ", data=" + data + "]";
	}

	
}
