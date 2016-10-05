package info.san.books.app.model.listener;

import info.san.books.app.Persistence;
import info.san.books.app.event.auteur.AuteurCreatedEvent;
import info.san.books.app.event.auteur.AuteurDeletedEvent;
import info.san.books.app.event.auteur.AuteurUpdatedEvent;
import info.san.books.app.model.entry.AuteurEntry;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.axonframework.eventhandling.annotation.EventHandler;

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
public class AuteurListener extends AbstractListener {

    @EventHandler
    public void handle(AuteurCreatedEvent e) {
        EntityManager em = Persistence.getInstance().createEntityManager();
        EntityTransaction t = em.getTransaction();

        t.begin();

        AuteurEntry entry = new AuteurEntry();
        entry.setId(e.getId());
        entry.setNom(e.getNom());
        entry.setPrenom(e.getPrenom());

        em.persist(entry);

        t.commit();
    }

    @EventHandler
    public void handle(AuteurUpdatedEvent e) {
        EntityManager em = Persistence.getInstance().createEntityManager();

        EntityTransaction t = em.getTransaction();

        t.begin();

        AuteurEntry entry = em.find(AuteurEntry.class, e.getId());
        entry.setNom(e.getNom());
        entry.setPrenom(e.getPrenom());

        em.merge(entry);

        t.commit();
    }

    @EventHandler
    public void handle(AuteurDeletedEvent e) {
        EntityManager em = Persistence.getInstance().createEntityManager();

        EntityTransaction t = em.getTransaction();

        t.begin();

        AuteurEntry entry = em.getReference(AuteurEntry.class, e.getId());
        em.remove(entry);

        t.commit();
    }

}
