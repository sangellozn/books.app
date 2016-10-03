package info.san.books.app.command.handler;

import info.san.books.app.command.saga.SagaCreateCommand;
import info.san.books.app.command.saga.SagaDeleteCommand;
import info.san.books.app.command.saga.SagaUpdateCommand;
import info.san.books.app.ddd.livre.Saga;

import org.axonframework.commandhandling.annotation.CommandHandler;

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
 * Command handler for the Saga business cases.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
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
