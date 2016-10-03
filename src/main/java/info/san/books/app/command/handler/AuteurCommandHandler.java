/**
 *
 */
package info.san.books.app.command.handler;

import info.san.books.app.command.auteur.AuteurCreateCommand;
import info.san.books.app.command.auteur.AuteurDeleteCommand;
import info.san.books.app.command.auteur.AuteurUpdateCommand;
import info.san.books.app.ddd.livre.Auteur;
import info.san.books.app.exception.NameAlreadyTakenException;
import info.san.books.app.query.AuteurQueryRepository;

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
 * Handle command that impact the Auteur business case.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
public class AuteurCommandHandler extends AbstractBasicCommandHandler<Auteur, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuteurCommandHandler.class);

    private AuteurQueryRepository auteurQueryRepository = new AuteurQueryRepository();

    public AuteurCommandHandler() {
        super(Auteur.class);
    }

    @CommandHandler
    public void handle(AuteurCreateCommand cmd) {
        if (!this.auteurQueryRepository.isAuteurExists(cmd.getNom(), cmd.getPrenom())) {
            Auteur a = new Auteur(cmd.getId(), cmd.getNom(), cmd.getPrenom());
            this.getRepository().add(a);
        } else {
            AuteurCommandHandler.LOGGER.error("The auteur with [nom={}], [prenom={}] already exists.", cmd.getNom(), cmd.getPrenom());
            throw new NameAlreadyTakenException("The auteur with [nom=" + cmd.getNom() + "], [prenom=" + cmd.getPrenom() + "] already exists.");
        }
    }

    @CommandHandler
    public void handle(AuteurUpdateCommand cmd) {
        if (!this.auteurQueryRepository.isAuteurExists(cmd.getNom(), cmd.getPrenom())) {
            Auteur a = this.getRepository().load(cmd.getId());
            a.update(cmd.getNom(), cmd.getPrenom());
        } else {
            AuteurCommandHandler.LOGGER.error("The auteur with [nom={}], [prenom={}] already exists.", cmd.getNom(), cmd.getPrenom());
            throw new NameAlreadyTakenException("The auteur with [nom=" + cmd.getNom() + "], [prenom=" + cmd.getPrenom() + "] already exists.");
        }
    }

    @CommandHandler
    public void handle(AuteurDeleteCommand cmd) {
        Auteur a = this.getRepository().load(cmd.getId());
        a.delete();
    }

}
