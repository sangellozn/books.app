package info.san.books.app.event.livre;

import java.io.Serializable;

public class LivreAssignedAuteurEvent implements Serializable {

	private static final long serialVersionUID = -7711940377723249899L;

	private String isbn;

	private String auteurId;

	public LivreAssignedAuteurEvent(String isbn, String auteurId) {
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
