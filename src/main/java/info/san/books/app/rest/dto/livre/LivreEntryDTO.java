package info.san.books.app.rest.dto.livre;

import info.san.books.app.model.entry.Format;
import info.san.books.app.model.entry.Langue;
import info.san.books.app.rest.RestPathConstants;
import info.san.books.app.rest.dto.AbstractBasicDTO;
import info.san.books.app.rest.dto.Rel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
public class LivreEntryDTO extends AbstractBasicDTO {

    private static final long serialVersionUID = 4241823255634526042L;

    private String isbn;

    private String titre;

    private String titreOriginal;

    private Langue langue;

    private String resume;

    private int nbPage;

    private Format format;

    private String editeur;

    private String imagePath;

    private Date createdAt;

    private Date updatedAt;

    private boolean lu;

    private boolean possede;

    private String imageBase64;

    private Collection<AuteurEntryDTO> auteurs;

    private SagaEntryDTO saga;

    public LivreEntryDTO() {
        this.auteurs = new ArrayList<AuteurEntryDTO>();
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

    public SagaEntryDTO getSaga() {
        return this.saga;
    }

    public void setSaga(SagaEntryDTO saga) {
        this.saga = saga;
    }

    @Override
    public Object getId() {
        return this.getIsbn();
    }

    public void setId(String id) {
        this.setIsbn(id);
    }

    @Override
    protected Collection<Rel> defineRels() {
        Collection<Rel> res = new ArrayList<Rel>();

        res.add(new Rel(RestPathConstants.LIVRES_BASE_PATH, this.getIsbn(), "livre", RelScope._self));
        res.add(new Rel(RestPathConstants.LIVRES_BASE_PATH, this.getIsbn() + "/auteurs", "auteurCollection", RelScope._rel));

        if (this.saga != null) {
            res.add(new Rel(RestPathConstants.SAGAS_BASE_PATH, this.saga.getId(), "saga", RelScope._rel));
        }

        return res;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public Collection<AuteurEntryDTO> getAuteurs() {
        return this.auteurs;
    }

    public String getImageBase64() {
        return this.imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

}
