package info.san.books.app.query;

import java.util.HashMap;
import java.util.Map;

public class Filter {

	private Map<String, String> filters;

	public Filter(String rawFilters) {
		this.filters = new HashMap<String, String>();

		if (rawFilters != null && !rawFilters.trim().isEmpty()) {
			for (String filter : rawFilters.split("[,]")) {
				if (!filter.trim().isEmpty() && filter.contains(":")) {
					String[] filterParts = filter.split("[:]");
					if (filterParts.length == 2) {
						this.filters.put(filterParts[0], filterParts[1]);
					}
				}
			}
		}
	}

	public Map<String, String> getFilters() {
		return this.filters;
	}

}
