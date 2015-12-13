package br.com.jup.facade;

import br.com.jup.command.exception.CommandException;
import br.com.jup.command.ICommand;
import br.com.jup.command.ReceiveMessage;
import br.com.jup.command.RegisterDevice;
import br.com.jup.command.RequestCode;
import br.com.jup.command.SendMessage;

/**
 * Classe Facade que encapsula todos os comandos.
 * @author renatorodrigues
 *
 */
public class CommandsFacade implements FullCommands{

	public boolean sendMessage(SendMessage sendMessage) throws CommandException {
		return execute(sendMessage);
	}
	
	public boolean receiveMessage(ReceiveMessage receiveMessage) throws CommandException{
		return execute(receiveMessage);
	}

	public boolean registerDevice(RegisterDevice registerDevice) throws CommandException {
		return execute(registerDevice);
	}

	public boolean requestCode(RequestCode requestCode) throws CommandException {
		return execute(requestCode);
	}
	
	/**
	 * Executa um conjunto de comandos {@link ICommand}
	 * @param commands
	 * @return true caso todos comandos sejam executados, caso algum comando 
	 * não seja executado, {@link CommandException} é lançada
	 * @throws CommandException 
	 */
	private boolean execute(ICommand ...commands) throws CommandException{
		
		boolean success = true;
		String simpleName;
		
		for (ICommand c : commands) {

			simpleName = c.getClass().getSimpleName();
			success = c.execute() && success;
			
			if (!success) {
				throw new CommandException("Não foi possível executar o comando " + simpleName);
			}
		}
		
		return success;
	}
}
