package br.com.jup.command;

import br.com.jup.command.exception.CommandException;
import br.com.jup.command.impl.Registration;

/**
 * Classe que define um comando de requisição de código de autenticação de dispositivo.
 * @author renatorodrigues
 *
 */
public class RequestCode implements ICommand {

	private Registration registration;
	
	public RequestCode(Registration registration) {
		this.registration = registration;
	}
	
	public boolean execute() throws CommandException {
		return this.registration.requestCode();
	}

}
