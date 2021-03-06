package info.san.books.app.model.entry;

import info.san.books.app.rest.dto.visitor.LivreEntryDTOVisitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

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
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
@NamedQueries(value = {
        @NamedQuery(name = "LivreEntry.countByISBN", query = "Select count(livre) From LivreEntry livre Where livre.isbn = :isbn"),
        @NamedQuery(name = "LivreEntry.count", query = "Select Count(livre) From LivreEntry livre")
})
@Entity
@Table(name = "LIVRE")
public class LivreEntry implements Entry, Visitable<LivreEntryDTOVisitor> {

    private static final long serialVersionUID = -8073125155029296505L;

    @Id
    @Column(length = 13, nullable = false, name = "ISBN")
    private String isbn;

    @Column(length = 50, nullable = false, name = "TITRE")
    private String titre;

    @Column(length = 50, nullable = false, name = "TITRE_ORIGINAL")
    private String titreOriginal;

    @Column(length = 20, nullable = false, name = "LANGUE")
    @Enumerated(EnumType.STRING)
    private Langue langue;

    @Column(name = "RESUME")
    @Lob
    private String resume;

    @Column(nullable = false, name = "NB_PAGE")
    private int nbPage;

    @Column(nullable = false, name = "FORMAT")
    @Enumerated(EnumType.STRING)
    private Format format;

    @Column(length = 50, nullable = false, name = "EDITEUR")
    private String editeur;

    @Column(nullable = true, name = "IMAGE_PATH")
    @Lob
    private String imagePath;

    @Column(nullable = true, name = "IMAGE_BASE_64")
    @Lob
    private String imageAsBase64;

    @Column(nullable = false, name = "CREATED_AT")
    private Date createdAt;

    @Column(nullable = false, name = "UPDATED_AT")
    private Date updatedAt;

    @Column(nullable = false, name = "LU")
    private boolean lu;

    @Column(nullable = false, name = "POSSEDE")
    private boolean possede;

    @ManyToMany
    @JoinTable(name = "LIVRES_HAS_AUTEURS",
            joinColumns = @JoinColumn(name = "ISBN", referencedColumnName = "ISBN"),
            inverseJoinColumns = @JoinColumn(name = "AUTEUR_ID", referencedColumnName = "ID"))
    private List<AuteurEntry> auteurs;

    @ManyToOne(optional = true)
    private SagaEntry saga;

    public LivreEntry() {
        this.auteurs = new ArrayList<AuteurEntry>();
    }

    @Override
    public Object getId() {
        return this.isbn;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getTitreOriginal() {
        return this.titreOriginal;
    }

    public void setTitreOriginal(String titreOriginal) {
        this.titreOriginal = titreOriginal;
    }

    public Langue getLangue() {
        return this.langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public String getResume() {
        return this.resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public int getNbPage() {
        return this.nbPage;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }

    public Format getFormat() {
        return this.format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public String getEditeur() {
        return this.editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<AuteurEntry> getAuteurs() {
        return this.auteurs;
    }

    @Override
    public void accept(LivreEntryDTOVisitor visitor) {
        visitor.visit(this);
        for (AuteurEntry auteur : this.getAuteurs()) {
            auteur.accept(visitor);
        }

        if (this.getSaga() != null) {
            this.getSaga().accept(visitor);
        }
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public boolean isLu() {
        return this.lu;
    }

    public boolean isPossede() {
        return this.possede;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public void setPossede(boolean possede) {
        this.possede = possede;
    }

    public SagaEntry getSaga() {
        return this.saga;
    }

    public void setSaga(SagaEntry saga) {
        this.saga = saga;
    }

    public String getImageAsBase64() {
        return this.imageAsBase64;
    }

    public void setImageAsBase64(String imageAsBase64) {
        this.imageAsBase64 = imageAsBase64;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.isbn == null) ? 0 : this.isbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        LivreEntry other = (LivreEntry) obj;
        if (this.isbn == null) {
            if (other.isbn != null) {
                return false;
            }
        } else if (!this.isbn.equals(other.isbn)) {
            return false;
        }
        return true;
    }

}
