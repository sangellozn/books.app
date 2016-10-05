package info.san.books.app.rest.dto.visitor;

import info.san.books.app.model.entry.SagaEntry;
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
public class SagaEntryDTOVisitor {

    private SagaEntryDTO dto = new SagaEntryDTO();

    public void visit(SagaEntry entry) {
        this.dto.setId(entry.getId());
        this.dto.setNom(entry.getNom());
        this.dto.setCreatedAt(entry.getCreatedAt());
        this.dto.setUpdatedAt(entry.getUpdatedAt());
    }

    public SagaEntryDTO getDto() {
        return this.dto;
    }

}
