package info.san.books.app.event.saga;

import java.io.Serializable;

public class SagaCreatedEvent implements Serializable {

	private static final long serialVersionUID = -4060716491891209830L;

	private String id;

	private String nom;

	public SagaCreatedEvent(String id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public String getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

}
