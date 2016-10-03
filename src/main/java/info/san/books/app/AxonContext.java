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
 * MIT License
 *
 * Copyright (c) 2016 sangellozn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

    /**
     * Get the {@link AxonContext} singleton instance.
     *
     * @return the {@link AxonContext} instance.
     */
    public static final AxonContext getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Returns the event store associated to the AxonContext.
     *
     * @return the application event store.
     *
     * @see EventStore
     */
    public EventStore getEventStore() {
        return this.eventStore;
    }

    /**
     * Returns the command bus associated to the AxonContext.
     *
     * @return the application command bus.
     *
     * @see CommandBus
     */
    public CommandBus getCommandBus() {
        return this.commandBus;
    }

    /**
     * Returns the event bus associated to the AxonContext.
     *
     * @return the application event bus.
     *
     * @see EventBus
     */
    public EventBus getEventBus() {
        return this.eventBus;
    }

    /**
     * Returns the command gateway associated to the AxonContext.
     *
     * @return the application command gateway.
     *
     * @see CommandGateway
     */
    public CommandGateway getCommandGateway() {
        return this.commandGateway;
    }

}
