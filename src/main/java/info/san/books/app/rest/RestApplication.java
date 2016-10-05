package info.san.books.app.rest;

import info.san.books.app.rest.exceptionmapper.AggregateNotFoundExceptionMapper;
import info.san.books.app.rest.exceptionmapper.AuteurIsAlreadyAssignedToLivreExceptionMapper;
import info.san.books.app.rest.exceptionmapper.AuteurIsNotAssignedToLivreExceptionMapper;
import info.san.books.app.rest.exceptionmapper.InvalidFormatExceptionMapper;
import info.san.books.app.rest.exceptionmapper.JsonParseExceptionMapper;
import info.san.books.app.rest.exceptionmapper.NameAlreadyTakenExceptionMapper;
import info.san.books.app.rest.exceptionmapper.ObjectNotFoundExceptionMapper;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

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
 * REST application.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
@ApplicationPath("rest")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(AuteursRest.class);
        classes.add(LivresRest.class);
        classes.add(SagasRest.class);

        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> instances = new HashSet<>();

        instances.add(new JacksonJsonProvider());
        instances.add(new NameAlreadyTakenExceptionMapper());
        instances.add(new AggregateNotFoundExceptionMapper());
        instances.add(new InvalidFormatExceptionMapper());
        instances.add(new JsonParseExceptionMapper());
        instances.add(new AuteurIsAlreadyAssignedToLivreExceptionMapper());
        instances.add(new AuteurIsNotAssignedToLivreExceptionMapper());
        instances.add(new ObjectNotFoundExceptionMapper());

        return instances;
    }

}
