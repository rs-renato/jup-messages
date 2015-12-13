package br.com.jup.command.impl;

import br.com.jup.command.exception.CommandException;
import br.com.jup.utils.Constantes;

import org.apache.log4j.Logger;


/**
 * Classe de implementação de um comando de registro.
 * @author renatorodrigues
 *
 */
public class Registration extends MasterCommand {

	private String code;
	private REQUEST_TYPE requestType;
	
	private static Logger logger = Logger.getLogger(Registration.class);
	
	/**
	 * Tipo de requisição para registro (SMS | VOICE)
	 * @author renatorodrigues
	 *
	 */
	public static enum REQUEST_TYPE{
		
		SMS("sms"),
		VOICE("voice");
		
		private String description;
		
		private REQUEST_TYPE(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
		
	}
	
	/**
	 * Construtor padrão para {@link Registration}
	 */
	public Registration() { 
		this("");
	}
	
	
	/**
	 * Contrutor que encapsula um código e tipo de requisição de registro
	 * @param code
	 * @param requestType
	 */
	public Registration(String code, REQUEST_TYPE requestType){
		this.code = code;
		this.requestType = requestType;
	}

	/**
	 * Contrutor que encapsula o tipo de requisição de registro
	 * @param type
	 */
	public Registration(REQUEST_TYPE type){
		this(null, type);
	}

	/**
	 * Contrutor que encapsula um código de registro.
	 * @param code
	 */
	public Registration(String code){
		this(code, null);
	}

	/**
	 * Solicita registro de um dispositivo.
	 * @throws CommandException
	 */
	public boolean register() throws CommandException {
		
		if (code == null || code.isEmpty()) {
			throw new CommandException(Constantes.ERRO_CODIGO_REGISTRO_OBRIGATORIO);
		}
		
		this.script = SCRIPT.REGISTER_DEVICE;
		
		String[] registerCommand = SCRIPT.RECEIVE_MESSAGE.getCommand(code);

		logger.info("Enviando comando de registro de dispositivo. Code: " + code);
		
		String response = process(registerCommand) ;
		
		logger.info(response);
		
		return response != null;
	}

	/**
	 * Solicita código de registo de um dispositivo.
	 * @throws CommandException 
	 */
	public boolean requestCode() throws CommandException {
		
		if (requestType == null) {
			throw new CommandException(Constantes.ERRO_TIPO_REQUEST_OBRIGATORIO);
		}
		
		this.script = SCRIPT.REQUEST_CODE;
		
		String[] requestCodeCommand = SCRIPT.RECEIVE_MESSAGE.getCommand(requestType.getDescription());
		
		logger.info("Enviando comando de requisição de código de registro. Type: " + requestType.getDescription());
		
		String response = process(requestCodeCommand) ;
		
		logger.info(response);
		
		return response != null;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public REQUEST_TYPE getRequestType() {
		return requestType;
	}

	public void setRequestType(REQUEST_TYPE requestType) {
		this.requestType = requestType;
	}

}
