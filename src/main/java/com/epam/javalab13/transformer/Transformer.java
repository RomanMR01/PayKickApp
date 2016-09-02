package com.epam.javalab13.transformer;

import java.sql.ResultSet;
import java.util.List;

public interface Transformer<E> {
	E getOne(ResultSet rs);

	List<E> getAll(ResultSet rs);
}
