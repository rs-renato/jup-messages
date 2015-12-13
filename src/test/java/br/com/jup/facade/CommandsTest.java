package br.com.jup.facade;

import br.com.jup.command.ReceiveMessage;
import br.com.jup.command.RequestCode;
import br.com.jup.command.SendMessage;
import br.com.jup.command.exception.CommandException;
import br.com.jup.command.impl.Message;
import br.com.jup.command.impl.Registration;
import br.com.jup.command.RegisterDevice;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class CommandsTest extends TestCase{

	private CommandsFacade commandsFacade;
	
	private Message message;
	private SendMessage sendMessage;
	private ReceiveMessage receiveMessage;
	
	private Registration registration;
	private RegisterDevice registerDevice;
	private RequestCode requestCode;
	
	
	@Before
	public void setUp() throws Exception {
		
		commandsFacade = new CommandsFacade();
		message = new Message("Hey ho!", "62XXXXX83");
		registration = new Registration("123-456", Registration.REQUEST_TYPE.SMS);
	}

	@Test
	public void testSendMessage() throws CommandException {
		sendMessage = new SendMessage(message);
		commandsFacade.sendMessage(sendMessage);
	}

	@Test
	public void testReceiveMessage() throws CommandException {
		receiveMessage = new ReceiveMessage(message);
		commandsFacade.receiveMessage(receiveMessage);
	}

	@Test
	public void testRegisterDevice() throws CommandException {
		registerDevice = new RegisterDevice(registration);
		commandsFacade.registerDevice(registerDevice);
	}

	@Test
	public void testRequestCode() throws CommandException {
		requestCode = new RequestCode(registration);
		commandsFacade.requestCode(requestCode);
	}

}
