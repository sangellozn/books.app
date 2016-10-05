package info.san.books.app.ddd.livre;

import info.san.books.app.event.saga.SagaCreatedEvent;
import info.san.books.app.event.saga.SagaDeletedEvent;
import info.san.books.app.event.saga.SagaUpdatedEvent;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

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
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class Saga extends AbstractAnnotatedAggregateRoot<String> {

    private static final long serialVersionUID = -3426154283544678759L;

    @AggregateIdentifier
    private String id;

    public Saga() {
        // Nothing.
    }

    public Saga(String id, String nom) {
        this.apply(new SagaCreatedEvent(id, nom));
    }

    public void update(String nom) {
        this.apply(new SagaUpdatedEvent(this.id, nom));
    }

    public void delete() {
        this.apply(new SagaDeletedEvent(this.id));
    }

    @EventHandler
    public void handle(SagaCreatedEvent e) {
        this.id = e.getId();
    }

    @EventHandler
    public void handle(SagaDeletedEvent e) {
        this.markDeleted();
    }
}
