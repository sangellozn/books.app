/**
 *
 */
package info.san.books.app.model.entry;

import info.san.books.app.rest.dto.visitor.AuteurEntryDTOVisitor;
import info.san.books.app.rest.dto.visitor.LivreEntryDTOVisitor;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
@NamedQueries(value = {
		@NamedQuery(name = "AuteurEntry.countByNomAndPrenom", query = "Select Count(auteur) From AuteurEntry auteur Where auteur.nom = :nom And auteur.prenom = :prenom"),
		@NamedQuery(name = "AuteurEntry.countById", query = "Select Count(auteur) From AuteurEntry auteur Where auteur.id = :id"),
		@NamedQuery(name = "AuteurEntry.count", query = "Select Count(auteur) From AuteurEntry auteur")
})
@Entity
@Table(name = "AUTEUR", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "nom", "prenom" })
})
public class AuteurEntry implements Entry {

	private static final long serialVersionUID = 9014971070682344334L;

	@Id
	@Column(length = 36, nullable = false, name = "ID")
	private String id;

	@Column(length = 100, nullable = false, name = "NOM")
	private String nom;

	@Column(length = 100, nullable = false, name = "PRENOM")
	private String prenom;

	@ManyToMany(mappedBy = "auteurs")
	private List<LivreEntry> livres;

	@Column(nullable = false, name = "CREATED_AT")
	private Date createdAt;

	@Column(nullable = false, name = "UPDATED_AT")
	private Date updatedAt;

	@Override
	public String getId() {
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

	public List<LivreEntry> getLivres() {
		return this.livres;
	}

	public void accept(AuteurEntryDTOVisitor visitor) {
		visitor.visit(this);
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	@PrePersist
	public void prePersist() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = new Date();
	}

	public void accept(LivreEntryDTOVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		AuteurEntry other = (AuteurEntry) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
