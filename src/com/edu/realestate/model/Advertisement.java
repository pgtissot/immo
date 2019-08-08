package com.edu.realestate.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Advertisement {

	private int id;
	private String title;
	private AdStatus status;
	private TransactionType transactionType;
	private String description;
	private LocalDate releaseDate;
	private String adNumber;
	private RealEstate realEstate;
	private Advertiser advertiser;
	private List<Picture> pictures;
	private String refusedComment;

	public Advertisement() {
	}

	public Advertisement(int id, String title, AdStatus status, TransactionType transactionType,
			String description, LocalDate releaseDate, String adNumber, RealEstate realEstate, Advertiser advertiser,
			List<Picture> pictures, String refusedComment) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.transactionType = transactionType;
		this.description = description;
		this.releaseDate = releaseDate;
		this.adNumber = adNumber;
		this.realEstate = realEstate;
		this.advertiser = advertiser;
		this.pictures = pictures;
		this.refusedComment = refusedComment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AdStatus getStatus() {
		return status;
	}

	public void setStatus(AdStatus status) {
		this.status = status;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getAdNumber() {
		return adNumber;
	}

	public void setAdNumber(String adNumber) {
		this.adNumber = adNumber;
	}

	public RealEstate getRealEstate() {
		switch (realEstate.getType()) {
		case "Apartment" :
			return (Apartment) realEstate;
		case "House" :
			return (House) realEstate;
		case "Commercial" :
			return (CommercialProperty) realEstate;
		case "Land" :
			return (Land) realEstate;
		case "Parking" :
			return (Parking) realEstate;
		case "Other" :
			return (OtherProperty) realEstate;
		default :
			return realEstate;
		}
	}

	public void setRealEstate(RealEstate realEstate) {
		this.realEstate = realEstate;
	}

	public Advertiser getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Advertiser advertiser) {
		this.advertiser = advertiser;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getRefusedComment() {
		return refusedComment;
	}

	public void setRefusedComment(String refusedComment) {
		this.refusedComment = refusedComment;
	}
	
	public String transactionTypeToFrench() {
		switch(transactionType) {
		case Sale : return "Vente";
		case Rent : return "Location";
		default : return "";
		}
	}
	
	public String getFrenchReleaseDate() {
		return releaseDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
	}

	@Override
	public String toString() {
		return "Advertisement [id=" + id + ", title=" + title + ", status=" + status + ", transactionType="
				+ transactionType + ", releaseDate=" + releaseDate + ", adNumber="
				+ adNumber + ", realEstate=" + realEstate + ", advertiser=" + advertiser + ", pictures=" + pictures
				+ ", refusedComment=" + refusedComment + "]";
	}
	
	

}