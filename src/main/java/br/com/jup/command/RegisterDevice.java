package br.com.jup.command;

import br.com.jup.command.exception.CommandException;
import br.com.jup.command.impl.Registration;

/**
 * Classe que define um comando de registro de dispositivo
 * @author renatorodrigues
 *
 */
public class RegisterDevice implements ICommand {

	private Registration registration;
	
	public RegisterDevice(Registration registration) {
		this.registration = registration;
	}
	
	public boolean execute() throws CommandException {
		return this.registration.register();
	}

}
