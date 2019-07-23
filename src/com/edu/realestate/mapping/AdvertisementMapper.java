package com.edu.realestate.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.Picture;
import com.edu.realestate.model.RealEstate;
import com.edu.realestate.model.TransactionType;

public class AdvertisementMapper {

	public static Advertisement resultToAdvertisement(ResultSet rs, Advertiser ad, RealEstate re, List<Picture> pictures) throws SQLException {
		Advertisement advertisement = new Advertisement();
		advertisement.setId(rs.getInt("id"));
		advertisement.setTitle(rs.getString("title"));
		advertisement.setStatus(AdStatus.valueOf(rs.getString("status")));
		advertisement.setTransactionType(TransactionType.valueOf(rs.getString("transaction_type"))); 
		advertisement.setDescription(rs.getString("description"));
		advertisement.setReleaseDate(rs.getDate("release_date").toLocalDate());
		advertisement.setAdNumber(rs.getString("ad_number"));
		advertisement.setAdvertiser(ad);
		advertisement.setRealEstate(re);
		advertisement.setPictures(pictures);
		advertisement.setRefusedComment(rs.getString("refused_comment"));
		
		return advertisement;

	}
	
	public static String advertisementToResult(Advertisement ad) throws Exception {
		throw new Exception();
	}
	
}
