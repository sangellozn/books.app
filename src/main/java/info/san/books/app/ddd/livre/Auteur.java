package info.san.books.app.ddd.livre;

import info.san.books.app.event.auteur.AuteurCreatedEvent;
import info.san.books.app.event.auteur.AuteurDeletedEvent;
import info.san.books.app.event.auteur.AuteurUpdatedEvent;

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
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class Auteur extends AbstractAnnotatedAggregateRoot<String> {

    private static final long serialVersionUID = 7822862035780552538L;

    @AggregateIdentifier
    private String id;

    public Auteur() {
        // Nothing.
    }

    public Auteur(String id, String nom, String prenom) {
        this.apply(new AuteurCreatedEvent(id, nom, prenom));
    }

    public void update(String nom, String prenom) {
        this.apply(new AuteurUpdatedEvent(this.id, nom, prenom));
    }

    public void delete() {
        this.apply(new AuteurDeletedEvent(this.id));
    }

    @EventHandler
    public void handle(AuteurCreatedEvent e) {
        this.id = e.getId();
    }

    @EventHandler
    public void handle(AuteurDeletedEvent e) {
        this.markDeleted();
    }

}
