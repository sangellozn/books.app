package info.san.books.app.ddd.livre;

import info.san.books.app.event.saga.SagaCreatedEvent;
import info.san.books.app.event.saga.SagaDeletedEvent;
import info.san.books.app.event.saga.SagaUpdatedEvent;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

public class Saga extends AbstractAnnotatedAggregateRoot<String> {

	private static final long serialVersionUID = -3426154283544678759L;

	@AggregateIdentifier
	private String id;

	public Saga() {
		// Nothing.
	}

	public Saga(String id, String nom) {
		this.apply(new SagaCreatedEvent(id, nom));
	}

	public void update(String nom) {
		this.apply(new SagaUpdatedEvent(this.id, nom));
	}

	public void delete() {
		this.apply(new SagaDeletedEvent(this.id));
	}

	@EventHandler
	public void handle(SagaCreatedEvent e) {
		this.id = e.getId();
	}

	@EventHandler
	public void handle(SagaDeletedEvent e) {
		this.markDeleted();
	}
}
