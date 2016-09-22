/**
 *
 */
package info.san.books.app.command.handler;

import info.san.books.app.AxonContext;

import org.axonframework.eventsourcing.EventSourcedAggregateRoot;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.repository.Repository;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public abstract class AbstractBasicCommandHandler<E extends EventSourcedAggregateRoot<I>, I> {

	private Repository<E> repository;

	private Class<E> clazz;

	/**
	 * Default constructor.
	 */
	public AbstractBasicCommandHandler(Class<E> clazz) {
		this.clazz = clazz;
	}

	public Repository<E> getRepository() {
		if (this.repository == null) {
			this.repository = new EventSourcingRepository<E>(this.clazz, AxonContext.getInstance().getEventStore());
			((EventSourcingRepository<E>) this.repository).setEventBus(AxonContext.getInstance().getEventBus());
		}
		return this.repository;
	}

}
