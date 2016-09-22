package info.san.books.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Singleton for persistence utility in the application.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class Persistence {

	private final EntityManagerFactory emf;

	private static final class Holder {
		private static final Persistence INSTANCE = new Persistence();
	}

	private Persistence() {
		this.emf = javax.persistence.Persistence.createEntityManagerFactory("books.app");
	}

	public static Persistence getInstance() {
		return Holder.INSTANCE;
	}

	public EntityManager createEntityManager() {
		return this.emf.createEntityManager();
	}

}
