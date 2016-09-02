package com.epam.javalab13.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {

	E findById(int id);

	boolean create(E e);

	boolean update(E e);

	boolean delete(E e);

	boolean delete(int id);

	List<E> findAll();

	
}
