package dao;

import java.util.List;

public interface GenericDAO<T> {
	public T get(String id);
	List<T> getAll();
	void update(T t, String[] dati);
	void delete(T t);
	void save (T t);
}
