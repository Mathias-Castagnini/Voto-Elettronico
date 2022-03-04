package dao;

import java.util.List;

public interface GenericDAO<T> {
	public T get(String id) throws Exception;
	List<T> getAll();
	void delete(T t);
	void save (T t);
}
