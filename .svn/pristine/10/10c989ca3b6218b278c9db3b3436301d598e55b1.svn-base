/**
 *
 */
package info.san.books.app.rest.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.core.JsonParseException;

/**
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class JsonParseExceptionMapper implements ExceptionMapper<JsonParseException> {

	public JsonParseExceptionMapper() {
		// Nothing.
	}

	@Override
	public Response toResponse(JsonParseException exception) {
		return Response.status(Status.BAD_REQUEST).entity(new BasicExceptionMessage("JsonParseException", exception.getMessage())).build();
	}

}
