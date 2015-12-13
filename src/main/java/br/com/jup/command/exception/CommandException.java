package br.com.jup.command.exception;

/**
 * Classe que representa erros de comandos
 * @author renatorodrigues
 *
 */
public class CommandException extends Exception{

	private static final long serialVersionUID = 1L;

	public CommandException(String message) {
		super(message);
	}
}
