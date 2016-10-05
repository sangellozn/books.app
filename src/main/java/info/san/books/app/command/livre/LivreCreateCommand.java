package info.san.books.app.command.livre;

import info.san.books.app.model.entry.Format;
import info.san.books.app.model.entry.Langue;

/**
 * MIT License
 *
 * Copyright (c) 2016 sangellozn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/**
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
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
