package info.san.books.app.command.livre;

import info.san.books.app.model.entry.Format;
import info.san.books.app.model.entry.Langue;

public class LivreCreateCommand {

	private String isbn;

	private String titre;

	private String titreOriginal;

	private Langue langue;

	private String resume;

	private int nbPage;

	private Format format;

	private String editeur;

	private String imagePath;

	private boolean lu;

	private boolean possede;

	private String sagaId;

	public LivreCreateCommand(String isbn, String titre, String titreOriginal, Langue langue,
			String resume, int nbPage, Format format, String editeur,
			String imagePath, boolean lu, boolean possede, String sagaId) {
		super();
		this.isbn = isbn;
		this.titre = titre;
		this.titreOriginal = titreOriginal;
		this.langue = langue;
		this.resume = resume;
		this.nbPage = nbPage;
		this.format = format;
		this.editeur = editeur;
		this.imagePath = imagePath;
		this.lu = lu;
		this.possede = possede;
		this.sagaId = sagaId;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public String getTitre() {
		return this.titre;
	}

	public String getTitreOriginal() {
		return this.titreOriginal;
	}

	public Langue getLangue() {
		return this.langue;
	}

	public String getResume() {
		return this.resume;
	}

	public int getNbPage() {
		return this.nbPage;
	}

	public Format getFormat() {
		return this.format;
	}

	public String getEditeur() {
		return this.editeur;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public boolean isLu() {
		return this.lu;
	}

	public boolean isPossede() {
		return this.possede;
	}

	public String getSagaId() {
		return this.sagaId;
	}

}
