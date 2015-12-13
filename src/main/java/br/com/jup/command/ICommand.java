package br.com.jup.command;

import br.com.jup.command.exception.CommandException;

/**
 * Interface que define o contrato de um comando.
 * @author renatorodrigues
 *
 */
public interface ICommand {
	
	/**
	 * Executa um comando.
	 */
	public boolean execute() throws CommandException;
	
}
