package br.com.jup.command.impl;

import br.com.jup.command.exception.CommandException;
import br.com.jup.utils.Constantes;

import java.util.Arrays;

import org.apache.log4j.Logger;


/**
 * Classe de implementação de um comando de mensagem
 * @author renatorodrigues
 *
 */
public class Message extends MasterCommand{

	private String messageContent;
	private String[] receiversNumbers;
	
	private static Logger logger = Logger.getLogger(Message.class);

	/**
	 * Construtor padrão para {@link Message}
	 */
	public Message() { 
		this(null);
	}
	
	/**
	 * Contrutor que encapsula uma mensagem para um ou vários receptores
	 * @param messageContent mensagem de envio
	 * @param receiverNumber número receptor
	 */
	public Message(String messageContent, String ...receiverNumber){
		this.messageContent = messageContent;
		this.receiversNumbers = receiverNumber;
	}

	/**
	 * Contrutor que encapsula uma mensagem para um ou vários receptores
	 * definidos em arquivo de configuração
	 * @param messageContent mensagem de envio
	 */
	public Message(String messageContent) {
		this.messageContent = messageContent;
		this.receiversNumbers = properties.getProperty("jup.messages.default.receivers").split(",");
	}

	/**
	 * Envia uma mensagem para um respectivo cliente.
	 * @throws CommandException 
	 */
	public boolean send() throws CommandException{
		
		if (receiversNumbers == null || receiversNumbers.length == 0) {
			
			throw new CommandException(Constantes.ERRO_RECEPTORES_OBRIGATORIOS);
			
		}else if (messageContent == null || messageContent.isEmpty()) {
			
			throw new CommandException(Constantes.ERRO_MENSAGEM_OBRIGATORIA);
		}
		
		this.script = SCRIPT.SEND_MESSAGE;
		
		String response = null;
		
		logger.info("Enviando comando de envio de mensagens.");
		
		logger.warn("Receptores: " + Arrays.asList(receiversNumbers));
		
		for (String receiver : receiversNumbers) {
			
			String[] sendCommand = SCRIPT.SEND_MESSAGE.getCommand(55+receiver,messageContent);
			
			response = process(sendCommand) ;
			
			logger.debug(response);
			
			logger.info("Mensagem enviada: " + receiver + " - " + !response.isEmpty());
			
		}
		
		return response != null;
	}


	/**
	 * Realiza leitura das mensagens para o dispositivo registrado.
	 * @throws CommandException 
	 */
	public boolean receive() throws CommandException{
		
		this.script = SCRIPT.RECEIVE_MESSAGE;
		
		String[] receiveCommand = SCRIPT.RECEIVE_MESSAGE.getCommand();

		logger.info("Enviando comando de recebimento de mensagens.");
		
		String response = process(receiveCommand) ;
		
		logger.debug(response);
		
		return response != null;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String[] getReceiversNumbers() {
		return receiversNumbers;
	}

	public void setReceiversNumbers(String[] receiversNumbers) {
		this.receiversNumbers = receiversNumbers;
	}
	
}
