package info.san.books.app.rest.dto.visitor;

import info.san.books.app.model.entry.AuteurEntry;
import info.san.books.app.rest.dto.livre.AuteurEntryDTO;

public class AuteurEntryDTOVisitor {

	private AuteurEntryDTO dto = new AuteurEntryDTO();

	public void visit(AuteurEntry auteurEntry) {
		this.dto.setId(auteurEntry.getId());
		this.dto.setNom(auteurEntry.getNom());
		this.dto.setPrenom(auteurEntry.getPrenom());
		this.dto.setCreatedAt(auteurEntry.getCreatedAt());
		this.dto.setUpdatedAt(auteurEntry.getUpdatedAt());
	}

	public AuteurEntryDTO getDto() {
		return this.dto;
	}

}
