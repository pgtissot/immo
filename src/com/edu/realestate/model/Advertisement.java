package com.edu.realestate.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class Advertisement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String title;

	@Enumerated(EnumType.STRING)
	private AdStatus status;

	@Enumerated(EnumType.STRING)
	@Column(name="transaction_type")
	private TransactionType transactionType;

	private String description;
	
	@Column(name="release_date")
	private LocalDate releaseDate;
	
	@Column(name="ad_number")
	private String adNumber;

	@OneToOne
	@JoinColumn(name="real_estate_id")
	private RealEstate realEstate;

	@ManyToOne
	@JoinColumn(name="advertiser_id")
	private Advertiser advertiser;

	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="advertisement_id")
	@OrderBy("id")
	private List<Picture> pictures;

	@Column(name="refused_comment")
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