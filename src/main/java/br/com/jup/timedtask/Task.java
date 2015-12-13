package br.com.jup.timedtask;

import java.util.concurrent.Callable;

/**
 * Interface que define as responsabilidades das Tasks
 *  @author renatorodrigues
 *
 * @param <T> Tipo parametrizado da Task
 */
public interface Task<T> extends Callable<T> {

	/**
	 * Indica que uma task deve ser iniciada
	 */
	public T call() throws Exception;
	
	/**
	 * Indica que uma task deve ser finalizada
	 */
	public void stopCall();
}
