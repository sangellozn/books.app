package info.san.books.app.rest;

import info.san.books.app.AxonContext;
import info.san.books.app.model.entry.Entry;
import info.san.books.app.query.QueryRepository;

import javax.ws.rs.core.Response;

import org.axonframework.commandhandling.gateway.CommandGateway;

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
