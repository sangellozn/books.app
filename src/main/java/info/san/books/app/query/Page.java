package info.san.books.app.query;

/**
 * Class for paginate result set.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class Page {

	private int page;

	private int limit;

	public Page(int page, int limit) {
		this.page = page;
		this.limit  = limit;
	}

	public int getLimit() {
		return this.limit;
	}

	public int getPage() {
		return this.page;
	}

}
