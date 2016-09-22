package info.san.books.app.command.handler;

import info.san.books.app.command.saga.SagaCreateCommand;
import info.san.books.app.command.saga.SagaDeleteCommand;
import info.san.books.app.command.saga.SagaUpdateCommand;
import info.san.books.app.ddd.livre.Saga;

import org.axonframework.commandhandling.annotation.CommandHandler;

public class SagaCommandHandler extends AbstractBasicCommandHandler<Saga, String> {

	public SagaCommandHandler() {
		super(Saga.class);
	}

	@CommandHandler
	public void handle(SagaCreateCommand cmd) {
		Saga s = new Saga(cmd.getId(), cmd.getNom());
		this.getRepository().add(s);
	}

	@CommandHandler
	public void handle(SagaUpdateCommand cmd) {
		Saga s = this.getRepository().load(cmd.getId());
		s.update(cmd.getNom());
	}

	@CommandHandler
	public void handle(SagaDeleteCommand cmd) {
		Saga s = this.getRepository().load(cmd.getId());
		s.delete();
	}

}
