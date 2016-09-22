package info.san.books.app.rest.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

	@Override
	public Response toResponse(InvalidFormatException exception) {
		return Response.status(Status.BAD_REQUEST).entity(new BasicExceptionMessage("BadRequest", exception.getMessage())).build();
	}

}
