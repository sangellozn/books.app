package info.san.books.app.query;

import java.util.ArrayList;
import java.util.List;

public class Select {

	private List<String> selects;

	public Select(String rawSelects) {
		this.selects = new ArrayList<String>();

		if (rawSelects != null && !rawSelects.trim().isEmpty()) {
			String[] selectParts = rawSelects.split(",");
			for (String selectPart : selectParts) {
				if (!selectPart.trim().isEmpty()) {
					this.selects.add(selectPart);
				}
			}
		}
	}

	public List<String> getSelects() {
		return this.selects;
	}

	public boolean isEmtpy() {
		return this.selects.isEmpty();
	}

}
