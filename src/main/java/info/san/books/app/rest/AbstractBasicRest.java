package info.san.books.app.rest;

import info.san.books.app.AxonContext;
import info.san.books.app.model.entry.Entry;
import info.san.books.app.query.QueryRepository;

import javax.ws.rs.core.Response;

import org.axonframework.commandhandling.gateway.CommandGateway;

/**
 * Basic REST services implementation.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 * @param <DTO> the DTO class the REST services manipulate.
 * @param <E> the Entry class the REST services manipulate.
 */
public abstract class AbstractBasicRest<DTO extends Entry, E extends Entry> {

	protected CommandGateway commandGateway = AxonContext.getInstance().getCommandGateway();

	public static final class CountWrapper {
		private int count;

		public CountWrapper() {
			// Nothing.
		}

		public CountWrapper(int count) {
			this.count = count;
		}

		public int getCount() {
			return this.count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}

	public abstract Response findAll(String orderBy, int page, int limit, String filters, boolean count);

	public abstract Response find(String id);

	public abstract Response create(DTO entry);

	public abstract Response update(String id, DTO entry);

	public abstract Response delete(String id);

	public abstract QueryRepository<E> getQueryRepository();

	public Response count() {
		return Response.ok(new CountWrapper(this.getQueryRepository().count())).build();
	}

}
