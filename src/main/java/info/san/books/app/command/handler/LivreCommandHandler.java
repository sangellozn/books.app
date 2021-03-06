package info.san.books.app.command.handler;

import info.san.books.app.command.livre.LivreAssignAuteurCommand;
import info.san.books.app.command.livre.LivreCreateCommand;
import info.san.books.app.command.livre.LivreDeleteCommand;
import info.san.books.app.command.livre.LivreUnassignAuteurCommand;
import info.san.books.app.command.livre.LivreUpdateCommand;
import info.san.books.app.ddd.livre.Livre;
import info.san.books.app.exception.ISBNAlreadyTakenException;
import info.san.books.app.exception.ObjectNotFoundException;
import info.san.books.app.query.AuteurQueryRepository;
import info.san.books.app.query.LivreQueryRepository;
import info.san.books.app.query.SagaQueryRepository;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Handle command that impact the Livre business case.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 */
public class LivreCommandHandler extends AbstractBasicCommandHandler<Livre, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LivreCommandHandler.class);

    private LivreQueryRepository livreQueryRepository = new LivreQueryRepository();

    private AuteurQueryRepository auteurQueryRepository = new AuteurQueryRepository();

    private SagaQueryRepository sagaQueryRepository = new SagaQueryRepository();

    public LivreCommandHandler() {
        super(Livre.class);
    }

    @CommandHandler
    public void handle(LivreCreateCommand cmd) {
        if (!this.livreQueryRepository.isLivreExists(cmd.getIsbn())) {
            if (cmd.getSagaId() == null || cmd.getSagaId().trim().isEmpty()
                    || (cmd.getSagaId() != null && !cmd.getSagaId().trim().isEmpty() && this.sagaQueryRepository.isSagaExists(cmd.getSagaId()))) {
                Livre l = new Livre(cmd);
                this.getRepository().add(l);
            } else {
                LivreCommandHandler.LOGGER.error("The saga with [id={}] does not exist.", cmd.getSagaId());
                throw new ObjectNotFoundException("The saga with [id=" + cmd.getSagaId() + "] does not exist.");
            }
        } else {
            LivreCommandHandler.LOGGER.error("The livre with [ISBN={}] already exists.", cmd.getIsbn());
            throw new ISBNAlreadyTakenException("The livre with [ISBN=" + cmd.getIsbn() + "] already exists.");
        }
    }

    @CommandHandler
    public void handle(LivreUpdateCommand cmd) {
        if (cmd.getSagaId() == null || cmd.getSagaId().trim().isEmpty()
                || (cmd.getSagaId() != null && !cmd.getSagaId().trim().isEmpty() && this.sagaQueryRepository.isSagaExists(cmd.getSagaId()))) {
            Livre l = this.getRepository().load(cmd.getIsbn());
            l.update(cmd);
        } else {
            LivreCommandHandler.LOGGER.error("The saga with [id={}] does not exist.", cmd.getSagaId());
            throw new ObjectNotFoundException("The saga with [id=" + cmd.getSagaId() + "] does not exist.");
        }
    }

    @CommandHandler
    public void handle(LivreDeleteCommand cmd) {
        Livre l = this.getRepository().load(cmd.getId());
        l.delete();
    }

    @CommandHandler
    public void handle(LivreAssignAuteurCommand cmd) {
        Livre l = this.getRepository().load(cmd.getIsbn());

        if (this.auteurQueryRepository.isAuteurExists(cmd.getAuteurId())) {
            l.assignAuteur(cmd.getAuteurId());
        } else {
            LivreCommandHandler.LOGGER.error("The auteur with [id={}] does not exists.", cmd.getAuteurId());
            throw new ObjectNotFoundException("The auteur with [id=" + cmd.getAuteurId() + "] does not exists.");
        }
    }

    @CommandHandler
    public void handle(LivreUnassignAuteurCommand cmd) {
        Livre l = this.getRepository().load(cmd.getIsbn());

        if (this.auteurQueryRepository.isAuteurExists(cmd.getAuteurId())) {
            l.unassignAuteur(cmd.getAuteurId());
        } else {
            LivreCommandHandler.LOGGER.error("The auteur with [id={}] does not exists.", cmd.getAuteurId());
            throw new ObjectNotFoundException("The auteur with [id=" + cmd.getAuteurId() + "] does not exists.");
        }
    }

}
