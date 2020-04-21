package videoAJC.dao;

import java.util.List;
import java.util.Optional;

public interface DaoGeneric <T, K>{
	void insert (T obj);
	
	T update (T obj);
	
	void delete (T obj);
	
	void deleteById (K key);
	
	Optional<T> findByKey(K key);
	
	List<T> findAll ();
	
}
