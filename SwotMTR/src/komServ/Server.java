package komServ;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import logika.Strategija;

/**
 *
 * @author POOP
 */
public class Server extends SocketCommunicator implements Runnable {
	private final Hashtable<Integer, PlayerProxy> connectedPlayers = new Hashtable<>();
	private final Thread serverThread = new Thread(this);
	private int clientID = 1;
	private ArrayList<Strategija> strategije;

	public Server(ArrayList<Strategija> strategije) throws SocketException {
		super(SERVER_PORT);
		serverThread.start();
		this.strategije = strategije;
	}

	@Override
	public void run() {
		System.out.println("Game server started, listening on port " + SERVER_PORT + " ...");
		while (!Thread.interrupted()) {
			try {
				String message = receive();
				System.out.println("primljeno");
				processMessage(message);
			} catch (IOException e) {
			}
		}
		System.out.println("... game server ended.");
	}

	private void processMessage(String message) throws IOException {
		if (message.equals("CaoPoyy"))
		// ako je serveru stigla poruka JOIN
		{
			PlayerProxy pp = new PlayerProxy(this, receivePacket.getAddress(), receivePacket.getPort());
			
			connectedPlayers.put(clientID, pp);
			//posalji listu strategija
			pp.send(KomunikacijaSaKlijentima.prebaciPodatkeUString(strategije));
			System.out.println("Povezani smo <3");
		} else {
			//metoda za prikupljanje podataka
			KomunikacijaSaKlijentima.prebaciPodatkeUListu(message, strategije);
			clientID++;
		}
	}
	
	public ArrayList<Strategija> vratiStrategije(){
		return strategije;
	}
	
	public void ugasiNit(){
		KomunikacijaSaKlijentima.sumirajPodatke(strategije, clientID);
		serverThread.interrupt();
		datagramSocket.close();
		System.out.println("Server je ugasen...");
	}
}
