package br.com.jup.timedexecutor;

import br.com.jup.command.exception.TimedExecutorException;
import br.com.jup.timedtask.Task;
import br.com.jup.utils.Constantes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

/**
 * Implementação genérica do executor
 * @author renatorodrigues
 *
 * @param <T> Tipo parametrizado do executor
 */
public class TimedExecutor <T> implements Executor<T> {

	private long timeout;
	
	private static Logger logger = Logger.getLogger(TimedExecutor.class);
	
	/**
	 * Construtor TimedExecutor. Timeout padrão definido em 5s.
	 */
	public TimedExecutor() {
		timeout = 5;
	}
	
	/**
	 *  Construtor TimedExecutor
	 * @param timeout tempo máximo de espera do executor
	 */
	public TimedExecutor(long timeout) {
		this.timeout = timeout;
	}


	/**
	 * Executa uma task
	 * @param task Tarefa a ser executada
	 * @return Tipo parametrizado do executor
	 * @throws TimedExecutorException
	 */
	public T executeTask(Task<T> task) throws TimedExecutorException{

		ExecutorService executor = Executors.newSingleThreadExecutor();

		T t = null;

		Future<T> future = executor.submit(task);

		try {
			t = future.get(timeout, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			throw new TimedExecutorException(Constantes.ERRO_TEMPO_EXCEDIDO_REQUISICAO);
		} catch (Exception e) {
			logger.error(e);
			throw new TimedExecutorException(Constantes.ERRO_EXECUCAO_PARALELA);
		}finally{
			executor.shutdownNow();
		}

		return t;
	}
}
