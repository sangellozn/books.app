/**
 *
 */
package info.san.books.app.query;

import info.san.books.app.model.entry.SagaEntry;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class SagaQueryRepository extends AbstractBasicQueryRepository<SagaEntry> {

	public SagaQueryRepository() {
		super(SagaEntry.class);
	}

	public boolean isSagaExists(String sagaId) {
		return ((Number) this.em.createNamedQuery("SagaEntry.countById").setParameter("id", sagaId).getSingleResult()).intValue() > 0;
	}

}
