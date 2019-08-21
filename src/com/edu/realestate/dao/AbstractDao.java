package com.edu.realestate.dao;

public interface AbstractDao<T> {

	void create(T element);

	T read(Integer id);

	void update(T element);

	void delete(T element);

}
