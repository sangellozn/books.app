package info.san.books.app.rest.dto.livre;

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
public class SagaEntryDTO extends AbstractBasicDTO {

    private static final long serialVersionUID = 5263205886045865061L;

    private String id;

    private String nom;

    private Date createdAt;

    private Date updatedAt;

    public SagaEntryDTO() {
        // Nothing.
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        Collection<Rel> res = new ArrayList<Rel>();

        res.add(new Rel(RestPathConstants.SAGAS_BASE_PATH, this.id, "saga", RelScope._self));

        return res;
    }

}
