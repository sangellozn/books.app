package info.san.books.app.rest.dto;

import info.san.books.app.rest.dto.AbstractBasicDTO.RelScope;

public class Rel {

	private RelScope scope;

	private String url;

	private String base;

	private String type;

	public Rel() {
		// Nothing.
	}

	public Rel(String base, String url, String type, RelScope scope) {
		this.base  = base;
		this.url   = url;
		this.type  = type;
		this.scope = scope;
	}

	public RelScope getScope() {
		return this.scope;
	}

	public String getUrl() {
		return this.base + "/" + this.url;
	}

	public String getType() {
		return this.type;
	}

}
