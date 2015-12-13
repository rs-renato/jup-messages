package br.com.jup.command;

import br.com.jup.command.exception.CommandException;
import br.com.jup.command.impl.Message;

/**
 * Classe que define um comando mensagem de envio.
 * @author renatorodrigues
 *
 */
public class SendMessage implements ICommand {

	private Message message;
	
	public SendMessage(Message message) {
		this.message = message;
	}
	
	public boolean execute() throws CommandException {
		return message.send();
	}
}
