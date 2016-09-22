package info.san.books.app.rest.dto.livre;

import info.san.books.app.rest.RestPathConstants;
import info.san.books.app.rest.dto.AbstractBasicDTO;
import info.san.books.app.rest.dto.Rel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
