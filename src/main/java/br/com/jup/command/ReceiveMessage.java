package br.com.jup.command;

import br.com.jup.command.exception.CommandException;
import br.com.jup.command.impl.Message;

/**
 * Classe que define um comando de mensagem de recepção.
 * @author renatorodrigues
 *
 */
public class ReceiveMessage implements ICommand {

	private Message message;
	
	public ReceiveMessage(Message message) {
		this.message = message;
	}
	
	public boolean execute() throws CommandException {
		return this.message.receive();
	}

}
