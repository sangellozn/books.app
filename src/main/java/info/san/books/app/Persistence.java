package info.san.books.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    /**
     * Get the {@link Persistence} singleton instance.
     *
     * @return the {@link Persistence} instance.
     */
    public static Persistence getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Create an entity manager.
     *
     * @return a newly created entity manager.
     *
     * @see EntityManager
     */
    public EntityManager createEntityManager() {
        return this.emf.createEntityManager();
    }

}
