/**
 *
 */
package info.san.books.app.model.listener;

import info.san.books.app.Persistence;
import info.san.books.app.event.livre.LivreAssignedAuteurEvent;
import info.san.books.app.event.livre.LivreCreatedEvent;
import info.san.books.app.event.livre.LivreDeletedEvent;
import info.san.books.app.event.livre.LivreUnassignedAuteurEvent;
import info.san.books.app.event.livre.LivreUpdatedEvent;
import info.san.books.app.model.entry.AuteurEntry;
import info.san.books.app.model.entry.LivreEntry;
import info.san.books.app.model.entry.SagaEntry;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.IOUtils;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class LivreListener extends AbstractListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(LivreListener.class);

	@EventHandler
	public void handle(LivreCreatedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		LivreEntry entry = new LivreEntry();
		entry.setEditeur(e.getEditeur());
		entry.setFormat(e.getFormat());
		entry.setImagePath(e.getImagePath());
		entry.setIsbn(e.getIsbn());
		entry.setLangue(e.getLangue());
		entry.setNbPage(e.getNbPage());
		entry.setResume(e.getResume());
		entry.setTitre(e.getTitre());
		entry.setTitreOriginal(e.getTitreOriginal());
		entry.setLu(e.isLu());
		entry.setPossede(e.isPossede());
		try {
			entry.setImageAsBase64(this.getImageAsBase64(e.getImagePath()));
		} catch (IOException ioe) {
			LivreListener.LOGGER.warn("Cannot save the thumbnail in database: ", ioe);
			entry.setImageAsBase64(null);
		}

		if (e.getSagaId() != null && !e.getSagaId().trim().isEmpty()) {
			SagaEntry saga = em.getReference(SagaEntry.class, e.getSagaId());
			entry.setSaga(saga);
		}

		em.persist(entry);

		t.commit();
	}

	@EventHandler
	public void handle(LivreUpdatedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		LivreEntry entry = em.find(LivreEntry.class, e.getIsbn());
		entry.setEditeur(e.getEditeur());
		entry.setFormat(e.getFormat());
		entry.setImagePath(e.getImagePath());
		entry.setIsbn(e.getIsbn());
		entry.setLangue(e.getLangue());
		entry.setNbPage(e.getNbPage());
		entry.setResume(e.getResume());
		entry.setTitre(e.getTitre());
		entry.setTitreOriginal(e.getTitreOriginal());
		entry.setLu(e.isLu());
		entry.setPossede(e.isPossede());
		try {
			entry.setImageAsBase64(this.getImageAsBase64(e.getImagePath()));
		} catch (IOException ioe) {
			LivreListener.LOGGER.warn("Cannot save the thumbnail in database: ", ioe);
			entry.setImageAsBase64(null);
		}

		if (e.getImagePath() == null || e.getImagePath().isEmpty()) {
			entry.setImageAsBase64(null);
		}

		if (e.getSagaId() != null && !e.getSagaId().trim().isEmpty()) {
			SagaEntry saga = em.getReference(SagaEntry.class, e.getSagaId());
			entry.setSaga(saga);
		} else {
			entry.setSaga(null);
		}

		t.commit();
	}

	@EventHandler
	public void handle(LivreDeletedEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		LivreEntry entry = em.getReference(LivreEntry.class, e.getIsbn());
		em.remove(entry);

		t.commit();
	}

	@EventHandler
	public void handle(LivreAssignedAuteurEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		LivreEntry livre = em.find(LivreEntry.class, e.getIsbn());
		AuteurEntry auteur = em.find(AuteurEntry.class, e.getAuteurId());

		livre.getAuteurs().add(auteur);
		auteur.getLivres().add(livre);

		t.commit();
	}

	@EventHandler
	public void handle(LivreUnassignedAuteurEvent e) {
		EntityManager em = Persistence.getInstance().createEntityManager();

		EntityTransaction t = em.getTransaction();

		t.begin();

		LivreEntry livre = em.find(LivreEntry.class, e.getIsbn());
		AuteurEntry auteur = em.find(AuteurEntry.class, e.getAuteurId());

		livre.getAuteurs().remove(auteur);
		auteur.getLivres().remove(livre);

		t.commit();
	}

	private String getImageAsBase64(String imagePath) throws IOException {
		if (imagePath != null && !imagePath.isEmpty()) {
			InputStream is = null;
			try {
				URL imgUrl = new URL(imagePath);
				is = imgUrl.openStream();
				byte[] imgAsByte = IOUtils.toByteArray(is);

				return DatatypeConverter.printBase64Binary(imgAsByte);
			} catch (MalformedURLException mue) {
				LivreListener.LOGGER.error("MalformedURLException", mue);
				throw mue;
			} catch (IOException ioe) {
				LivreListener.LOGGER.error("IOException", ioe);
				throw ioe;
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException ioe) {
						LivreListener.LOGGER.warn("IOException", ioe);
					}
				}
			}
		}

		return null;
	}

}
