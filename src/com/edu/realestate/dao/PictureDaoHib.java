package com.edu.realestate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Picture;

@Repository
public class PictureDaoHib extends AbstractDaoHib implements PictureDao {

	@Override
	public void create(Picture p) {
		Session session = getSession();
		session.save(p);
	}

	@Override
	public Picture read(Integer id) {
		Session session = getSession();
		Picture picture = null;
		picture = session.get(Picture.class, id);
		return picture;

	}

	@Override
	public void update(Picture p) {
		Session session = getSession();
		session.save(p);
	}

	@Override
	public void delete(Picture p) {
		Session session = getSession();
		session.delete(p);
	}

	@Override
	public List<Picture> getAllPicturesByAd(Integer id) {
		Session session = getSession();
		List<Picture> list = new ArrayList<>();
		list = session.createQuery("FROM Advertisement WHERE id = " + id, Advertisement.class).getSingleResult().getPictures();
		return list;
	}
}
