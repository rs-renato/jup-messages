package br.com.jup.command.exception;

/**
 * Classe que representa timeout de comandos
 * @author renatorodrigues
 *
 */
public class TimedExecutorException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public TimedExecutorException(String msg) {
		super(msg);
	}

}
