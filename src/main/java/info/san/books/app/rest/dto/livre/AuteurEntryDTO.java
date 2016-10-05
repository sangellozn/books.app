package info.san.books.app.rest.dto.livre;

import info.san.books.app.rest.RestPathConstants;
import info.san.books.app.rest.dto.AbstractBasicDTO;
import info.san.books.app.rest.dto.Rel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
public class AuteurEntryDTO extends AbstractBasicDTO {

    private static final long serialVersionUID = 2084308328021382071L;

    private String id;

    private String nom;

    private String prenom;

    private Date createdAt;

    private Date updatedAt;

    @Override
    public Object getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    @Override
    protected Collection<Rel> defineRels() {
        List<Rel> rels = new ArrayList<Rel>();

        rels.add(new Rel(RestPathConstants.AUTEURS_BASE_PATH, this.id, "auteur", RelScope._self));
        //rels.add(new Rel(RestPathConstants.AUTEURS_BASE_PATH, this.id + "/" + RestPathConstants.LIVRES_BASE_PATH, "livreCollection", RelScope._rel));

        return rels;
    }

}
