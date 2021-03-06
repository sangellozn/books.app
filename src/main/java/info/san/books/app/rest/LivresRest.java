package info.san.books.app.rest;

import info.san.books.app.command.livre.LivreAssignAuteurCommand;
import info.san.books.app.command.livre.LivreCreateCommand;
import info.san.books.app.command.livre.LivreDeleteCommand;
import info.san.books.app.command.livre.LivreUnassignAuteurCommand;
import info.san.books.app.command.livre.LivreUpdateCommand;
import info.san.books.app.exception.ValidationException;
import info.san.books.app.model.entry.AuteurEntry;
import info.san.books.app.model.entry.LivreEntry;
import info.san.books.app.query.Filter;
import info.san.books.app.query.LivreQueryRepository;
import info.san.books.app.query.Ordering;
import info.san.books.app.query.Page;
import info.san.books.app.query.QueryRepository;
import info.san.books.app.rest.dto.livre.AuteurEntryDTO;
import info.san.books.app.rest.dto.livre.LivreEntryDTO;
import info.san.books.app.rest.dto.visitor.AuteurEntryDTOVisitor;
import info.san.books.app.rest.dto.visitor.LivreEntryDTOVisitor;
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
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
@Consumes(value = { MediaType.APPLICATION_JSON })
@Produces(value = { MediaType.APPLICATION_JSON })
@Path(RestPathConstants.LIVRES_BASE_PATH)
public class LivresRest extends AbstractBasicRest<LivreEntryDTO, LivreEntry> {

    private LivreQueryRepository livreQueryRepository = new LivreQueryRepository();

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

        Collection<LivreEntryDTO> res = new ArrayList<LivreEntryDTO>();
        Collection<LivreEntry> entries = this.livreQueryRepository.findAll(new Ordering(orderBy),
                new Page(page, limit), new Filter(filter));

        for (LivreEntry entry : entries) {
            LivreEntryDTOVisitor visitor = new LivreEntryDTOVisitor();
            entry.accept(visitor);
            res.add(visitor.getDto());
        }

        return Response.ok(res).build();
    }

    @GET
    @Path("{id}")
    @Override
    public Response find(@PathParam("id") String id) {
        LivreEntry entry = this.livreQueryRepository.find(id);

        if (entry == null) {
            return Response.status(Status.NOT_FOUND).entity(new BasicExceptionMessage("NotFound", "Livre with [id= " + id + "] does not exists.")).build();
        }

        LivreEntryDTOVisitor visitor = new LivreEntryDTOVisitor();
        entry.accept(visitor);

        return Response.ok(visitor.getDto()).build();
    }

    @POST
    @Override
    public Response create(LivreEntryDTO entry) {
        try {
            this.validateAndPrepareEntry(entry);
        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(new BasicExceptionMessage("BadRequest", e.getMessage())).build();
        }

        String sagaId = entry.getSaga() != null ? entry.getSaga().getId() : null;
        LivreCreateCommand cmd = new LivreCreateCommand(entry.getIsbn(), entry.getTitre(), entry.getTitreOriginal(),
                entry.getLangue(), entry.getResume(), entry.getNbPage(), entry.getFormat(), entry.getEditeur(), entry.getImagePath(),
                entry.isLu(), entry.isPossede(), sagaId);
        this.commandGateway.sendAndWait(cmd);

        return Response.ok().location(URI.create(RestPathConstants.LIVRES_BASE_PATH + "/" + cmd.getIsbn())).build();
    }

    @PUT
    @Path("{id}")
    @Override
    public Response update(@PathParam("id") String id, LivreEntryDTO entry) {
        try {
            this.validateAndPrepareEntry(entry);
        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST).entity(new BasicExceptionMessage("BadRequest", e.getMessage())).build();
        }

        entry.setIsbn(id);

        String sagaId = entry.getSaga() != null ? entry.getSaga().getId() : null;
        LivreUpdateCommand cmd = new LivreUpdateCommand(id, entry.getTitre(), entry.getTitreOriginal(), entry.getLangue(),
                entry.getResume(), entry.getNbPage(), entry.getFormat(), entry.getEditeur(), entry.getImagePath(),
                entry.isLu(), entry.isPossede(), sagaId);
        this.commandGateway.sendAndWait(cmd);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @Override
    public Response delete(@PathParam("id") String id) {
        LivreDeleteCommand cmd = new LivreDeleteCommand(id);

        this.commandGateway.sendAndWait(cmd);

        return Response.noContent().build();
    }

    @GET
    @Path("{id}/" + RestPathConstants.AUTEURS_BASE_PATH)
    public Response getAuteurs(@PathParam("id") String id) {
        LivreEntry livre = this.livreQueryRepository.find(id);

        if (livre == null) {
            return Response.status(Status.NOT_FOUND).entity(new BasicExceptionMessage("NotFound", "Livre with [id=" + id + "] cannot be found.")).build();
        }

        Collection<AuteurEntryDTO> res = new ArrayList<AuteurEntryDTO>();

        for (AuteurEntry entry : livre.getAuteurs()) {
            AuteurEntryDTOVisitor visitor = new AuteurEntryDTOVisitor();
            entry.accept(visitor);
            res.add(visitor.getDto());
        }

        return Response.ok(res).build();
    }

    @POST
    @Path("{id}/" + RestPathConstants.AUTEURS_BASE_PATH)
    public Response addAuteur(@PathParam("id") String id, AuteurEntryDTO auteur) {
        if (auteur == null || auteur.getId() == null || auteur.getId().toString().trim().isEmpty()) {
            return Response.status(Status.BAD_REQUEST).entity(new BasicExceptionMessage("BadRequest", "The associated auteur 'id' cannot be null or empty.")).build();
        }

        LivreAssignAuteurCommand cmd = new LivreAssignAuteurCommand(id, auteur.getId().toString());
        this.commandGateway.sendAndWait(cmd);

        return Response.ok().build();
    }

    @DELETE
    @Path("{id}/" + RestPathConstants.AUTEURS_BASE_PATH + "/{auteurId}")
    public Response removeAuteur(@PathParam("id") String isbn, @PathParam("auteurId") String auteurId) {
        LivreUnassignAuteurCommand cmd = new LivreUnassignAuteurCommand(isbn, auteurId);
        this.commandGateway.sendAndWait(cmd);

        return Response.ok().build();
    }

    private void validateAndPrepareEntry(LivreEntryDTO entry) throws ValidationException {
        if (entry.getIsbn() == null || entry.getIsbn().trim().isEmpty()) {
            throw new ValidationException("Livre 'ISBN' cannot be null or empty.");
        }

        if (entry.getIsbn().length() != 10 && entry.getIsbn().length() != 13) {
            throw new ValidationException("Livre 'ISBN' is not valid, format must be '10' or '13' chars.");
        }

        if (entry.getEditeur() == null || entry.getEditeur().trim().isEmpty()) {
            throw new ValidationException("Livre 'editeur' cannot be null or empty.");
        }

        if (entry.getFormat() == null) {
            throw new ValidationException("Livre 'format' cannot be null.");
        }

        if (entry.getLangue() == null) {
            throw new ValidationException("Livre 'langue' cannot be null.");
        }

        if (entry.getTitre() == null || entry.getTitre().trim().isEmpty()) {
            throw new ValidationException("Livre 'titre' cannot be null or empty.");
        }

        if (entry.getTitreOriginal() == null || entry.getTitreOriginal().trim().isEmpty()) {
            entry.setTitreOriginal(entry.getTitre());
        }
    }

    @Override
    public QueryRepository<LivreEntry> getQueryRepository() {
        return this.livreQueryRepository;
    }

}
