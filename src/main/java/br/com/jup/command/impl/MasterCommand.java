package br.com.jup.command.impl;

import br.com.jup.command.exception.CommandException;
import br.com.jup.command.exception.TimedExecutorException;
import br.com.jup.timedexecutor.TimedExecutor;
import br.com.jup.timedtask.CommandTimedTask;
import br.com.jup.utils.TextUtils;
import br.com.jup.utils.enuns.COMMAND_TYPE;
import br.com.jup.utils.enuns.OPERATION_SYSTEM;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Superclasse Command que define parâmetros iniciais de configuração.
 * @author renatorodrigues
 *
 */
public abstract class MasterCommand{

	protected static Properties properties = new Properties();

	static{

		InputStream inputStream = MasterCommand.class.getClassLoader().getResourceAsStream("config.properties");

		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	protected final static OPERATION_SYSTEM OS = OPERATION_SYSTEM.getOS(System.getProperty("os.name"));
	protected final static File yowsupHome = new File(properties.getProperty("jup.messages.yowsup.client.path"));
	protected final static long commadTimeout = Long.parseLong(properties.getProperty("jup.messages.command.timeout"));
	
	private static boolean debugCommand = Boolean.valueOf(properties.getProperty("jup.messages.command.debug"));
	
	protected SCRIPT script;
	
	private static Logger logger = Logger.getLogger(MasterCommand.class);
	
	/**
	 * Scripts de envio de comandos
	 * @author renatorodrigues
	 *
	 */
	public enum SCRIPT{
		
		SEND_MESSAGE(properties.getProperty("jup.messages.command.sendMessage"), COMMAND_TYPE.SEND_MESSAGE),
		RECEIVE_MESSAGE(properties.getProperty("jup.messages.command.receiveMessage"),COMMAND_TYPE.RECEIVE_MESSAGE),
		REGISTER_DEVICE(properties.getProperty("jup.messages.command.registerDevice"),COMMAND_TYPE.REGISTER_DEVICE),
		REQUEST_CODE(properties.getProperty("jup.messages.command.requestCode"),COMMAND_TYPE.REQUEST_CODE);

		private String command;
		
		private COMMAND_TYPE commandType;
		
		private SCRIPT(String command,COMMAND_TYPE commandType) {
			this.command = command;
			this.commandType = commandType;
		}
		
		/**
		 * Retorna um array contendo os comandos a serem executados,
		 * considerando o shell/argumentos específicos de cada OS
		 * @param values
		 * @return
		 */
		public String[] getCommand(String ...values) {
			
			return new String[]{
						OS.getShell(),
						OS.getArguemnt(),
						TextUtils.removerAcentos(String.format(this.command, (Object[]) values).concat(debugCommand ? " -d" : ""))
					};
		}
		
		public COMMAND_TYPE getCommandType() {
			return commandType;
		}
	}
	
	/**
	 * Processa um comando
	 * @param command
	 * @throws CommandException
	 */
	protected String process(String[] command) throws CommandException {
		
		logger.info("Parâmetros do comando: " + Arrays.asList(command));
		
		CommandTimedTask commandTask = new CommandTimedTask(script,yowsupHome,command);
		TimedExecutor<String> timedRequest = new TimedExecutor<String>(commadTimeout);
		
		try {
			
			return timedRequest.executeTask(commandTask);
			
		} catch (TimedExecutorException e) {
			logger.error(e.getMessage(),e);
			throw new CommandException(e.getMessage());
		}finally{
			commandTask.stopCall();
		}
	}
}
