/**
 *
 */
package info.san.books.app.query;

import info.san.books.app.model.entry.AuteurEntry;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class AuteurQueryRepository extends AbstractBasicQueryRepository<AuteurEntry> {

	public AuteurQueryRepository() {
		super(AuteurEntry.class);
	}

	public boolean isAuteurExists(String nom, String prenom) {
		return ((Number) this.em.createNamedQuery("AuteurEntry.countByNomAndPrenom").setParameter("nom", nom).setParameter("prenom", prenom).getSingleResult()).intValue() > 0;
	}

	public boolean isAuteurExists(String id) {
		return ((Number) this.em.createNamedQuery("AuteurEntry.countById").setParameter("id", id).getSingleResult()).intValue() > 0;
	}

}
