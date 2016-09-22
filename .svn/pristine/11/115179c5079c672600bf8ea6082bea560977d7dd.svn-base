package info.san.books.app.rest.dto;

import info.san.books.app.model.entry.Entry;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class AbstractBasicDTO implements Entry, Reliable {

	private static final long serialVersionUID = -6231053888994657370L;

	public enum RelScope {
		_self,
		_rel,
	}

	public AbstractBasicDTO() {
		// Nothing.
	}

	protected abstract Collection<Rel> defineRels();

	@Override
	@JsonProperty(value = "links")
	public Collection<Rel> getRel() {
		return this.defineRels();
	}

}
