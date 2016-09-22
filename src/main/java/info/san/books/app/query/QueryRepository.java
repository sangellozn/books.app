package info.san.books.app.query;

import java.util.List;

public interface QueryRepository<E> {

	public List<E> findAll(Ordering ordering, Page page, Filter filters);

	public E find(Object id);

	public int count();

}