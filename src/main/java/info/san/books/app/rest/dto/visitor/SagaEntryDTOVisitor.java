package info.san.books.app.rest.dto.visitor;

import info.san.books.app.model.entry.SagaEntry;
import info.san.books.app.rest.dto.livre.SagaEntryDTO;

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
