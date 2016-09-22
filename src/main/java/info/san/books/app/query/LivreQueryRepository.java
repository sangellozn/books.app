/**
 *
 */
package info.san.books.app.query;

import info.san.books.app.model.entry.LivreEntry;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class LivreQueryRepository extends AbstractBasicQueryRepository<LivreEntry> {

	public LivreQueryRepository() {
		super(LivreEntry.class);
	}

	public boolean isLivreExists(String isbn) {
		return ((Number) this.em.createNamedQuery("LivreEntry.countByISBN").setParameter("isbn", isbn).getSingleResult()).intValue() > 0;
	}
}
