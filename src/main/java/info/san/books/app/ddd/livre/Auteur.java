/**
 *
 */
package info.san.books.app.ddd.livre;

import info.san.books.app.event.auteur.AuteurCreatedEvent;
import info.san.books.app.event.auteur.AuteurDeletedEvent;
import info.san.books.app.event.auteur.AuteurUpdatedEvent;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class Auteur extends AbstractAnnotatedAggregateRoot<String> {

	private static final long serialVersionUID = 7822862035780552538L;

	@AggregateIdentifier
	private String id;

	public Auteur() {
		// Nothing.
	}

	public Auteur(String id, String nom, String prenom) {
		this.apply(new AuteurCreatedEvent(id, nom, prenom));
	}

	public void update(String nom, String prenom) {
		this.apply(new AuteurUpdatedEvent(this.id, nom, prenom));
	}

	public void delete() {
		this.apply(new AuteurDeletedEvent(this.id));
	}

	@EventHandler
	public void handle(AuteurCreatedEvent e) {
		this.id = e.getId();
	}

	@EventHandler
	public void handle(AuteurDeletedEvent e) {
		this.markDeleted();
	}

}
