package info.san.books.app.command.auteur;

/**
 * Command used for an auteur deletion.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class AuteurDeleteCommand {

	private String id;

	public AuteurDeleteCommand(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

}
