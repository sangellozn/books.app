package info.san.books.app.rest.dto.visitor;

import info.san.books.app.model.entry.AuteurEntry;
import info.san.books.app.model.entry.LivreEntry;
import info.san.books.app.model.entry.SagaEntry;
import info.san.books.app.rest.dto.livre.AuteurEntryDTO;
import info.san.books.app.rest.dto.livre.LivreEntryDTO;
import info.san.books.app.rest.dto.livre.SagaEntryDTO;

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