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
 * REST application.
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
