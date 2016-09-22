package info.san.books.app;

import info.san.books.app.command.handler.AuteurCommandHandler;
import info.san.books.app.command.handler.LivreCommandHandler;
import info.san.books.app.command.handler.SagaCommandHandler;
import info.san.books.app.model.listener.AuteurListener;
import info.san.books.app.model.listener.LivreListener;
import info.san.books.app.model.listener.SagaListener;

import java.io.File;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerAdapter;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;

/**
 * This is the singleton Axon Context. It contains all infrastructure objects necessary for the EventSourcing/CQRS/DDD
 * skeleton.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class AxonContext {

	private EventStore eventStore;

	private CommandBus commandBus;

	private CommandGateway commandGateway;

	private EventBus eventBus;

	private static final class Holder {
		private static final AxonContext INSTANCE = new AxonContext();
	}

	private AxonContext() {
		this.eventStore     = new FileSystemEventStore(new SimpleEventFileResolver(new File(AppProperties.getInstance().getEventStorePath())));
		this.commandBus     = new SimpleCommandBus();
		this.commandGateway = new DefaultCommandGateway(this.commandBus);
		this.eventBus       = new SimpleEventBus();

		// Registering command handler.
		AnnotationCommandHandlerAdapter.subscribe(new AuteurCommandHandler(), this.commandBus);
		AnnotationCommandHandlerAdapter.subscribe(new LivreCommandHandler(), this.commandBus);
		AnnotationCommandHandlerAdapter.subscribe(new SagaCommandHandler(), this.commandBus);

		// Registering event listener.
		AnnotationEventListenerAdapter.subscribe(new AuteurListener(), this.eventBus);
		AnnotationEventListenerAdapter.subscribe(new LivreListener(), this.eventBus);
		AnnotationEventListenerAdapter.subscribe(new SagaListener(), this.eventBus);
	}

	public static final AxonContext getInstance() {
		return Holder.INSTANCE;
	}

	public EventStore getEventStore() {
		return this.eventStore;
	}

	public CommandBus getCommandBus() {
		return this.commandBus;
	}

	public EventBus getEventBus() {
		return this.eventBus;
	}

	public CommandGateway getCommandGateway() {
		return this.commandGateway;
	}

}
