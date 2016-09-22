package info.san.books.app.ddd.livre;

import info.san.books.app.command.livre.LivreCreateCommand;
import info.san.books.app.command.livre.LivreUpdateCommand;
import info.san.books.app.event.livre.LivreAssignedAuteurEvent;
import info.san.books.app.event.livre.LivreCreatedEvent;
import info.san.books.app.event.livre.LivreDeletedEvent;
import info.san.books.app.event.livre.LivreUnassignedAuteurEvent;
import info.san.books.app.event.livre.LivreUpdatedEvent;
import info.san.books.app.exception.AuteurIsAlreadyAssignedToLivreException;
import info.san.books.app.exception.AuteurIsNotAssignedToLivreException;

import java.util.HashSet;
import java.util.Set;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class Livre extends AbstractAnnotatedAggregateRoot<String> {

	private static final long serialVersionUID = 2179233922855550609L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Livre.class);

	@AggregateIdentifier
	private String id;

	private Set<String> auteurIds;

	public Livre() {
		this.auteurIds = new HashSet<String>();
	}

	public Livre(LivreCreateCommand cmd) {
		this();
		this.apply(new LivreCreatedEvent(cmd.getIsbn(), cmd.getTitre(), cmd.getTitreOriginal(),
				cmd.getLangue(), cmd.getResume(), cmd.getNbPage(), cmd.getFormat(), cmd.getEditeur(),
				cmd.getImagePath(), cmd.isLu(), cmd.isPossede(), cmd.getSagaId()));
	}

	public void delete() {
		this.apply(new LivreDeletedEvent(this.id));
	}

	public void update(LivreUpdateCommand cmd) {
		this.apply(new LivreUpdatedEvent(cmd.getIsbn(), cmd.getTitre(), cmd.getTitreOriginal(),
				cmd.getLangue(), cmd.getResume(), cmd.getNbPage(), cmd.getFormat(), cmd.getEditeur(),
				cmd.getImagePath(), cmd.isLu(), cmd.isPossede(), cmd.getSagaId()));
	}

	public void assignAuteur(String auteurId) {
		if (!this.auteurIds.contains(auteurId)) {
			this.apply(new LivreAssignedAuteurEvent(this.id, auteurId));
		} else {
			Livre.LOGGER.error("The auteur [id={}] is already assigned to the book [isbn={}].", auteurId, this.id);
			throw new AuteurIsAlreadyAssignedToLivreException("The auteur [id=" + auteurId + "] is already assigned to the book [isbn=" + this.id + "].");
		}
	}

	public void unassignAuteur(String auteurId) {
		if (this.auteurIds.contains(auteurId)) {
			this.apply(new LivreUnassignedAuteurEvent(this.id, auteurId));
		} else {
			Livre.LOGGER.error("The auteur [id={}] is not assigned to the book [isbn={}].", auteurId, this.id);
			throw new AuteurIsNotAssignedToLivreException("The auteur [id=" + auteurId + "] is not assigned to the book [isbn=" + this.id + "].");
		}
	}

	@EventHandler
	public void handle(LivreCreatedEvent e) {
		this.id = e.getIsbn();
	}

	@EventHandler
	public void handle(LivreDeletedEvent e) {
		this.markDeleted();
	}

	@EventHandler
	public void handle(LivreAssignedAuteurEvent e) {
		this.auteurIds.add(e.getAuteurId());
	}

	@EventHandler
	public void handle(LivreUnassignedAuteurEvent e) {
		this.auteurIds.remove(e.getAuteurId());
	}

}
