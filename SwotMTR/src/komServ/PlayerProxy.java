package komServ;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.InetAddress;

/**
 *
 * @author POOP
 */
public class PlayerProxy {
	private final InetAddress clientAddress;
	private final int clientPort;
	private String receivedMessage;
	private final SocketCommunicator outputCommunicator;

	public PlayerProxy(SocketCommunicator outputCommunicator, InetAddress clientAddress, int clientPort) {
		this.outputCommunicator = outputCommunicator;
		this.clientAddress = clientAddress;
		this.clientPort = clientPort;
	}

	public void send(String message) throws IOException {
		outputCommunicator.send(message, clientAddress, clientPort);
	}

	synchronized void receivedMessage(String msg) {
		receivedMessage = msg;
		notify();
	}

	public synchronized String receive() throws InterruptedException {
		while (receivedMessage == null)
			wait();

		String retMessage = receivedMessage;
		receivedMessage = null;
		return retMessage;
	}
}
