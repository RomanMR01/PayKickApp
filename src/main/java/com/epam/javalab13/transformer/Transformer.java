package com.epam.javalab13.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public interface Transformer<E> {
	E getOne(ResultSet rs) throws SQLException;

	List<E> getAll(ResultSet rs) throws SQLException;
}
