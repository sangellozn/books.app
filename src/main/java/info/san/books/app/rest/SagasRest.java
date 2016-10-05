package info.san.books.app.rest;

import info.san.books.app.command.saga.SagaCreateCommand;
import info.san.books.app.command.saga.SagaDeleteCommand;
import info.san.books.app.command.saga.SagaUpdateCommand;
import info.san.books.app.exception.ValidationException;
import info.san.books.app.model.entry.LivreEntry;
import info.san.books.app.model.entry.SagaEntry;
import info.san.books.app.query.Filter;
import info.san.books.app.query.Ordering;
import info.san.books.app.query.Page;
import info.san.books.app.query.QueryRepository;
import info.san.books.app.query.SagaQueryRepository;
import info.san.books.app.rest.dto.livre.LivreEntryDTO;
import info.san.books.app.rest.dto.livre.SagaEntryDTO;
import info.san.books.app.rest.dto.visitor.LivreEntryDTOVisitor;
import info.san.books.app.rest.dto.visitor.SagaEntryDTOVisitor;
import info.san.books.app.rest.exceptionmapper.BasicExceptionMessage;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
@Path(RestPathConstants.SAGAS_BASE_PATH)
public class SagasRest extends AbstractBasicRest<SagaEntryDTO, SagaEntry> {

    private SagaQueryRepository sagaQueryRepository = new SagaQueryRepository();

    @GET
    @Override
    public Response findAll(@QueryParam("order") @DefaultValue("") String orderBy,
            @QueryParam("p") @DefaultValue("0") int page,
            @QueryParam("l") @DefaultValue("0") int limit,
            @QueryParam("f") @DefaultValue("") String filter,
            @QueryParam("count") @DefaultValue("false") boolean count) {

        if (count) {
            return super.count();
        }

        Collection<SagaEntryDTO> res = new ArrayList<SagaEntryDTO>();
        Collection<SagaEntry> entries = this.sagaQueryRepository.findAll(new Ordering(orderBy),
                new Page(page, limit), new Filter(filter));

        for (SagaEntry entry : entries) {
            SagaEntryDTOVisitor visitor = new SagaEntryDTOVisitor();
            entry.accept(visitor);
            res.add(visitor.getDto());
        }

        return Response.ok(res).build();
    }

    @GET
    @Path("{id}")
    @Override
    public Response find(@PathParam("id") String id) {
        SagaEntry entry = this.sagaQueryRepository.find(id);

        if (entry == null) {
            return Response.status(Status.NOT_FOUND).entity(new BasicExceptionMessage("NotFound", "Saga with [id=" + id + "] does not exist.")).build();
        }

        SagaEntryDTOVisitor visitor = new SagaEntryDTOVisitor();
        entry.accept(visitor);

        return Response.ok(visitor.getDto()).build();
    }

    @POST
    @Override
    public Response create(SagaEntryDTO entry) {
        try {
            this.validateSagaEntry(entry);
        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(new BasicExceptionMessage("BadRequest", e.getMessage())).build();
        }

        SagaCreateCommand cmd = new SagaCreateCommand(entry.getNom());
        this.commandGateway.sendAndWait(cmd);

        return Response.ok().location(URI.create(RestPathConstants.SAGAS_BASE_PATH + "/" + cmd.getId())).build();

    }

    @PUT
    @Path("{id}")
    @Override
    public Response update(@PathParam("id") String id, SagaEntryDTO entry) {
        entry.setId(id);

        try {
            this.validateSagaEntry(entry);
        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(new BasicExceptionMessage("BadRequest", e.getMessage())).build();
        }


        SagaUpdateCommand cmd = new SagaUpdateCommand(entry.getId(), entry.getNom());
        this.commandGateway.sendAndWait(cmd);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response delete(@PathParam("id") String id) {
        SagaDeleteCommand cmd = new SagaDeleteCommand(id);

        this.commandGateway.sendAndWait(cmd);

        return Response.noContent().build();
    }

    @GET
    @Path("{id}/livres")
    public Response getLivres(@PathParam("id") String id) {
        SagaEntry entry = this.sagaQueryRepository.find(id);

        if (entry == null) {
            return Response.status(Status.NOT_FOUND).entity(new BasicExceptionMessage("NotFound", "Saga with [id=" + id + "] does not exist.")).build();
        }

        Collection<LivreEntryDTO> res = new ArrayList<LivreEntryDTO>();

        for (LivreEntry livre : entry.getLivres()) {
            LivreEntryDTOVisitor visitor = new LivreEntryDTOVisitor();
            livre.accept(visitor);
            res.add(visitor.getDto());
        }

        return Response.ok(res).build();
    }

    private void validateSagaEntry(SagaEntryDTO entry) throws ValidationException {
        if (entry.getNom() == null || entry.getNom().trim().isEmpty()) {
            throw new ValidationException("The Saga 'nom' cannot be null or empty.");
        }
    }

    @Override
    public QueryRepository<SagaEntry> getQueryRepository() {
        return this.sagaQueryRepository;
    }

}
