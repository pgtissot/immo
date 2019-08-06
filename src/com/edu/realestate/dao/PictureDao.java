package com.edu.realestate.dao;

import java.util.List;

import com.edu.realestate.model.Picture;

public interface PictureDao extends AbstractDao<Picture> {

	List<Picture> getAllPicturesByAd(Integer id);
}
