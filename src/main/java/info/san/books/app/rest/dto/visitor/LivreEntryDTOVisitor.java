package info.san.books.app.rest.dto.visitor;

import info.san.books.app.model.entry.AuteurEntry;
import info.san.books.app.model.entry.LivreEntry;
import info.san.books.app.model.entry.SagaEntry;
import info.san.books.app.rest.dto.livre.AuteurEntryDTO;
import info.san.books.app.rest.dto.livre.LivreEntryDTO;
import info.san.books.app.rest.dto.livre.SagaEntryDTO;

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
public class LivreEntryDTOVisitor {

    private LivreEntryDTO dto = new LivreEntryDTO();

    public void visit(LivreEntry entry) {
        this.dto.setEditeur(entry.getEditeur());
        this.dto.setFormat(entry.getFormat());
        this.dto.setImagePath(entry.getImagePath());
        this.dto.setImageBase64(entry.getImageAsBase64());
        this.dto.setIsbn(entry.getIsbn());
        this.dto.setLangue(entry.getLangue());
        this.dto.setNbPage(entry.getNbPage());
        this.dto.setResume(entry.getResume());
        this.dto.setTitre(entry.getTitre());
        this.dto.setTitreOriginal(entry.getTitreOriginal());
        this.dto.setLu(entry.isLu());
        this.dto.setPossede(entry.isPossede());
        this.dto.setCreatedAt(entry.getCreatedAt());
        this.dto.setUpdatedAt(entry.getUpdatedAt());
    }

    public void visit(SagaEntry sagaEntry) {
        this.dto.setSaga(new SagaEntryDTO());
        this.dto.getSaga().setId(sagaEntry.getId());
        this.dto.getSaga().setNom(sagaEntry.getNom());
        this.dto.getSaga().setCreatedAt(sagaEntry.getCreatedAt());
        this.dto.getSaga().setUpdatedAt(sagaEntry.getUpdatedAt());
    }

    public void visit(AuteurEntry auteurEntry) {
        AuteurEntryDTO auteurDto = new AuteurEntryDTO();
        auteurDto.setId(auteurEntry.getId());
        auteurDto.setNom(auteurEntry.getNom());
        auteurDto.setPrenom(auteurEntry.getPrenom());
        auteurDto.setCreatedAt(auteurEntry.getCreatedAt());
        auteurDto.setUpdatedAt(auteurEntry.getUpdatedAt());
        this.dto.getAuteurs().add(auteurDto);
    }

    public LivreEntryDTO getDto() {
        return this.dto;
    }

}
