/**
 *
 */
package info.san.books.app.model.listener;

import info.san.books.app.Persistence;
import info.san.books.app.event.auteur.AuteurCreatedEvent;
import info.san.books.app.event.auteur.AuteurDeletedEvent;
import info.san.books.app.event.auteur.AuteurUpdatedEvent;
import info.san.books.app.model.entry.AuteurEntry;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class AuteurListener extends AbstractListener {

	@EventHandler
	public void handle(AuteurCreatedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		AuteurEntry entry = new AuteurEntry();
		entry.setId(e.getId());
		entry.setNom(e.getNom());
		entry.setPrenom(e.getPrenom());

		em.persist(entry);

		t.commit();
	}

	@EventHandler
	public void handle(AuteurUpdatedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		AuteurEntry entry = em.find(AuteurEntry.class, e.getId());
		entry.setNom(e.getNom());
		entry.setPrenom(e.getPrenom());

		em.merge(entry);

		t.commit();
	}

	@EventHandler
	public void handle(AuteurDeletedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		AuteurEntry entry = em.getReference(AuteurEntry.class, e.getId());
		em.remove(entry);

		t.commit();
	}

}
