package br.com.jup.timedtask;

import br.com.jup.utils.enuns.COMMAND_TYPE;
import br.com.jup.command.impl.MasterCommand.SCRIPT;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

import org.apache.log4j.Logger;

/**
 * Classe executora de comandos timedout
 * @author renatorodrigues
 *
 */
public class CommandTimedTask implements Task<String>{

	private SCRIPT script;
	private File yowsupHome;
	private String[] processCommand;
	private Process proccess;
	private ProcessBuilder processBuilder;

	private static Logger logger = Logger.getLogger(CommandTimedTask.class);

	public CommandTimedTask(SCRIPT script, File yowsuphome,String[] processCommand) {
	
		this.script = script;
		this.yowsupHome = yowsuphome;
		this.processCommand = processCommand;
		
		processBuilder = new ProcessBuilder();
		processBuilder.directory(yowsupHome);
		processBuilder.environment().put("PYTHONPATH", yowsupHome.getPath());
		
		logger.debug("Variáveis de ambiente do ProcessBuilder: " + processBuilder.environment());
	}

	@Override
	public String call() throws Exception {
		return processBuilder(processCommand);
	}

	@Override
	public void stopCall() {
		if (proccess!= null) {
			proccess.destroy();
		}
	}

	/**
	 * Inicializa o ProcessBuilder para execução do comando.
	 * @param processCommand Array com parâmetros do comando.
	 * @return ProcessBuilder configurado com os parâmetros informados.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private String processBuilder(String[] processCommand) throws IOException, InterruptedException{

		logger.info("Inicializando ProcessBuilder..");

		boolean isReceiveMessage = script.getCommandType().equals(COMMAND_TYPE.RECEIVE_MESSAGE);

		processBuilder.redirectOutput(isReceiveMessage ? Redirect.INHERIT : Redirect.PIPE);
		processBuilder.redirectError(isReceiveMessage ? Redirect.INHERIT : Redirect.PIPE);

		processBuilder.command(processCommand);

		proccess = processBuilder.start();
		
		if (isReceiveMessage) {
			Thread.sleep(5 * 1000);
		}

		return readStream(proccess);

	}

	/**
	 * Extrai os streams do Process
	 * @param proccess processo em execução
	 * @return String contendo os streams do comando 
	 * @throws IOException
	 */
	private String readStream(Process proccess) throws IOException{

		logger.debug("Lendo Process stream..");
		
		StringBuilder response = new StringBuilder();

		InputStream stream = proccess.getErrorStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String str = null;

		while ((str = br.readLine()) != null) {
			response.append(str).append("\n");
		}
		
		br.close();

		stream = proccess.getInputStream();

		br = new BufferedReader(new InputStreamReader(stream));
		str = null;

		while ((str = br.readLine()) != null) {
			response.append(str).append("\n");
		}

		br.close();
		
		logger.debug("InputStream/ErrorStream: " + response);
		
		return response.toString();

	}
}
