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
