package info.san.books.app.command.livre;

public class LivreUnassignAuteurCommand {

	private String isbn;

	private String auteurId;

	public LivreUnassignAuteurCommand(String isbn, String auteurId) {
		this.isbn = isbn;
		this.auteurId = auteurId;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public String getAuteurId() {
		return this.auteurId;
	}

}