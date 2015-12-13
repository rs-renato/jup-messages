package br.com.jup.timedexecutor;

import br.com.jup.command.exception.TimedExecutorException;
import br.com.jup.timedtask.Task;

/**
 * Interface que define a viabilização de execução de Tasks
 * @author renatorodrigues
 *
 * @param <T> Tipo parametrizado do executor
 */
public interface Executor<T> {
	
	/**
	 * Executa uma task
	 * @param task Tarefa a ser executada
	 * @return Tipo parametrizado do executor
	 * @throws TimedExecutorException
	 */
	public T executeTask(Task<T> task) throws TimedExecutorException;
}
