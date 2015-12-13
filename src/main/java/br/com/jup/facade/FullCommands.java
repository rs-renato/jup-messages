package br.com.jup.facade;

import br.com.jup.command.ReceiveMessage;
import br.com.jup.command.RegisterDevice;
import br.com.jup.command.RequestCode;
import br.com.jup.command.SendMessage;
import br.com.jup.command.exception.CommandException;

/**
 * Interface que define todos os comandos para o Facade.
 * @author renatorodrigues
 *
 */
public interface FullCommands {

	/**
	 * Enviar um comando {@link SendMessage}.
	 * @param sendMessage Comando de envio de mensagem.
	 * @throws CommandException
	 */
	public boolean sendMessage(SendMessage sendMessage) throws CommandException;
	
	/**
	 * Envia um comando {@link ReceiveMessage}.
	 * @param receiveMessage Comando de recepção de mensagem.
	 * @throws CommandException
	 */
	public boolean receiveMessage(ReceiveMessage receiveMessage) throws CommandException;;
	
	/**
	 * Envia um comando {@link RegisterDevice}
	 * @param registerDevice
	 * @throws CommandException
	 */
	public boolean registerDevice(RegisterDevice registerDevice) throws CommandException;
	
	
	/**
	 * Envia um comando {@link RequestCode}
	 * @param requestCode
	 * @throws CommandException
	 */
	public boolean requestCode(RequestCode requestCode) throws CommandException;;
}
