package info.san.books.app.command.auteur;

import java.util.UUID;

/**
 * Command used for an auteur creation.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class AuteurCreateCommand {

	private String id;

	private String nom;

	private String prenom;

	public AuteurCreateCommand(String nom, String prenom) {
		this.id = UUID.randomUUID().toString();
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
