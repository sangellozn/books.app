/**
 *
 */
package info.san.books.app.model.listener;

import info.san.books.app.Persistence;
import info.san.books.app.event.saga.SagaCreatedEvent;
import info.san.books.app.event.saga.SagaDeletedEvent;
import info.san.books.app.event.saga.SagaUpdatedEvent;
import info.san.books.app.model.entry.LivreEntry;
import info.san.books.app.model.entry.SagaEntry;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class SagaListener extends AbstractListener {

	@EventHandler
	public void handle(SagaCreatedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		SagaEntry entry = new SagaEntry();
		entry.setId(e.getId());
		entry.setNom(e.getNom());

		em.persist(entry);

		t.commit();
	}

	@EventHandler
	public void handle(SagaUpdatedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		SagaEntry entry = em.find(SagaEntry.class, e.getId());
		entry.setNom(e.getNom());

		t.commit();
	}

	@EventHandler
	public void handle(SagaDeletedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();

		SagaEntry entry = em.find(SagaEntry.class, e.getId());
		for (LivreEntry livre : entry.getLivres()) {
			livre.setSaga(null);
			em.merge(livre);
		}

		em.remove(entry);

		t.commit();
	}

}
