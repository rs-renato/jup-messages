package br.com.jup.utils.enuns;

import org.apache.log4j.Logger;

/**
 * Enum de representação do OS
 * @author renatorodrigues
 *
 */
public enum OPERATION_SYSTEM {
	

	WINDOWS ("cmd"),
	UNIX("/bin/bash"),
	MAC("/bin/bash"),
	SOLARIS ("/bin/bash"),
	UNDEFINED(null);

	private static Logger logger = Logger.getLogger(OPERATION_SYSTEM.class);
	private String shell;

	/**
	 * Obtêm o shell para o OS
	 * @param shell
	 */
	private OPERATION_SYSTEM(String shell) {
		this.shell = shell;
	}

	/**
	 * Obtêm OS
	 * @param osNAme
	 * @return
	 */
	public static OPERATION_SYSTEM getOS(String osNAme) {

		OPERATION_SYSTEM OS;
		
		osNAme = osNAme.toLowerCase();

		if (osNAme.indexOf("win") >= 0){
			
			OS =  WINDOWS;

		}else if (osNAme.indexOf("nix") >= 0
				|| osNAme.indexOf("nux") >= 0
				|| osNAme.indexOf("aix") >= 0){

			OS =  UNIX;

		}else if (osNAme.indexOf("mac") >= 0){

			OS =  MAC;

		}else if (osNAme.indexOf("sunos") >= 0){

			OS =  SOLARIS;
			
		}else{
			OS = UNDEFINED;
		}
		
		logger.info("Sistema Operacional identificado: " + OS);

		return OS;
	}

	public String getShell() {
		return shell;
	}

	/**
	 * Retorna o argumento para OS específico
	 * @return
	 */
	public String getArguemnt() {

		switch (this) {

			case WINDOWS: return "/c";
	
			default: return "-c";

		}
	}
}
