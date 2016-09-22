package info.san.books.app.event.auteur;

import java.io.Serializable;

/**
 * Event when an auteur is updated.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 */
public class AuteurUpdatedEvent implements Serializable {

	private static final long serialVersionUID = 3308423193034075998L;

	private String id;

	private String nom;

	private String prenom;

	public AuteurUpdatedEvent(String id, String nom, String prenom) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getId() {
		return this.id;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

}
