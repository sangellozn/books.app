package info.san.books.app.rest.dto.livre;

import info.san.books.app.rest.RestPathConstants;
import info.san.books.app.rest.dto.AbstractBasicDTO;
import info.san.books.app.rest.dto.Rel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
