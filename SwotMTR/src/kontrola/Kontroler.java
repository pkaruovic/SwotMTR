package kontrola;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import clientgui.ClientGUI;
import clientgui.PocetniProzor;
import clientgui.ProzorOceniStrategiju;
import communication.Client;
import gui.GUIDodajSWOT;
import gui.GUIUporediStrategije;
import gui.GlavniProzor;
import gui.ProzorNoviSwot;
import komServ.Server;
import logika.Logika;
import logika.Strategija;
import logika.Swot;
import logika.SwotStrat;
/**
 * 
 * @author Petar Karuovic, Miljan Jovic, Andrija Djordjevic
 * 
 * Klasa koja povezuje korisnicki interfejs i poslovnu logiku i koristi se za pokretanje aplikacije.
 * 
 */
public class Kontroler {
	
	private static GlavniProzor frame;
	private static Logika logika;
	public static Client client;
	public static PocetniProzor pocetni;
	public static ClientGUI clientFrame;
	public static Server server;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logika = new Logika();
					pocetni = new PocetniProzor();
					pocetni.setVisible(true);
//					clientFrame = new ClientGUI();
//					clientFrame.setVisible(true);
//					frame = new GlavniProzor();
//
//					frame.setVisible(true);
					
//					Kontroler.poveziSeNaServer("192.168.8.103");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Metoda koja cuva stanje programa (sve strategije, snage, slabosti, sanse i pretnje koje je korisnik uneo).
	 * 
	 * 
	 * @param nazivFajla - naziv fajla koji se kreira na disku, korisnik ga unosi sam
	 */
	public static void serijalizuj(){
//		String naziv;
		JFileChooser jfcSave = new JFileChooser();
	//	jfcSave.setCurrentDirectory(new File(".\\saved"));
		jfcSave.setCurrentDirectory(new File(System.getProperty("user.home")));
		jfcSave.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		//jfcSave.showSaveDialog(null);
		int povratnaVrednost = jfcSave.showSaveDialog(frame.getContentPane());
		
		if (povratnaVrednost == JFileChooser.APPROVE_OPTION) {
			
//			String kosaCrta = System.getProperty("file.separator");
			String path = jfcSave.getSelectedFile().getAbsolutePath();
//			File file = new File(path+kosaCrta+nazivFajla);
			File file = new File(path);
		//	String absPathFajla = file.getAbsolutePath();

			try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){
				out.writeObject(logika);
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JOptionPane.showMessageDialog(frame.getContentPane(), "Uspesno ste sacuvali fajl!", "Poruka",
					JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	/**
	 * Metoda koja omogucava korisniku da vrati sacuvano stanje programa.
	 */
	public static void deserijalizuj(){
		
		JFileChooser jfcOpen = new JFileChooser();
		jfcOpen.setCurrentDirectory(new File(System.getProperty("user.home")));
		int povratnaVrednost = jfcOpen.showOpenDialog(frame.getContentPane());
		if (povratnaVrednost == JFileChooser.APPROVE_OPTION) {
			String nazivFajla = jfcOpen.getSelectedFile().getAbsolutePath();
			try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(nazivFajla)))){
				logika = (Logika) in.readObject();//mora posebno?
				//popuni sve tabele itd
				popuniTabeluSnage();
				popuniTabeluPretnje();
				popuniTabeluSanse();
				popuniTabeluSlabosti();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		
	}
	/**
	 * Proverava da li je korisnik siguran da zeli da izadje iz aplikacije, ukoliko jeste,
	 * aplikacija prestaje sa radom.
	 */
	public static void ugasiAplikaciju() {
		int ugasi = JOptionPane.showConfirmDialog(frame.getContentPane(), "Da li zelite da ugasite program?", "Ugasi", JOptionPane.YES_NO_OPTION);
		
		if(ugasi == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	/**
	 * Poziva konstruktor klase Strategija i vraca objekat iste klase.
	 * @param naziv - konstruktor unosi naziv preko grafickog korisnickog interfejsa
	 * @return
	 */
	public static Strategija kreirajStrategiju(String naziv) {
		return new Strategija(naziv);
	}
	/**
	 * Poziva metodu za dodavanje strategije u listu 
	 * @param strategija 
	 */
	public static void dodajStrategiju(Strategija strategija) {
		logika.dodajStrategijuUListu(strategija);
	}
	/**
	 * Poziva metodu za dodavanje snage u listu kao i metodu za naknadno popunavanje tabele snaga
	 * @param naziv
	 * @param ponder
	 */
	public static void dodajSnagu(String naziv, double ponder) {
		logika.dodajSnaguUListu(naziv, ponder);
		popuniTabeluSnage();
	}
	/**
	 * Poziva metodu za dodavanje slabosti u listu kao i metodu za naknadno popunavanje tabele slabosti
	 * @param naziv
	 * @param ponder
	 */
	public static void dodajSlabost(String naziv, double ponder) {
		logika.dodajSlabostUListu(naziv, ponder);
		popuniTabeluSlabosti();
	}
	/**
	 * Poziva metodu za dodavanje sanse u listu kao i metodu za naknadno popunavanje tabele sansi
	 * @param naziv
	 * @param ponder
	 */
	public static void dodajSansu(String naziv, double ponder) {
		logika.dodajSansuUListu(naziv, ponder);
		popuniTabeluSanse();
	}
	/**
	 * Poziva metodu za dodavanje pretnje u listu kao i metodu za naknadno popunavanje tabele pretnji
	 * @param naziv
	 * @param ponder
	 */
	public static void dodajPretnju(String naziv, double ponder) {
		logika.dodajPretnjuUListu(naziv, ponder);
		popuniTabeluPretnje();
	}
	/**
	 * Poziva se svaki put kada se doda nova snaga. Prosledjuje se lista snaga koje ce biti prikazane u tabeli
	 * 
	 */
	public static void popuniTabeluSnage() {
		frame.srediTabeluSnage(logika.getListaSnage());
		
	}
	/**
	 * Poziva se svaki put kada se doda nova slabost. Prosledjuje se lista slabosti koje ce biti prikazane u tabeli
	 */
	public static void popuniTabeluSlabosti() {
		frame.srediTabeluSlabosti(logika.getListaSlabosti());
	}
	/**
	 * Poziva se svaki put kada se doda nova sansa. Prosledjuje se lista sansi koje ce biti prikazane u tabeli
	 */
	public static void popuniTabeluSanse() {
		frame.srediTabeluSanse(logika.getListaSanse());
	}
	/**
	 * Poziva se svaki put kada se doda nova pretnja. Prosledjuje se lista pretnji koje ce biti prikazane u tabeli
	 */
	public static void popuniTabeluPretnje() {
		frame.srediTabeluPretnje(logika.getListaPretnje());
	}
	/**
	 * Prosledjuje listu strategija iz logike u grafiku gde ce biti prikazane.
	 * @return ArrayList
	 */
	public static ArrayList<Strategija> getListaStrategija(){
		return logika.getStrategije();
	}
	/**
	 * Prosledjuje listu snaga iz logike u grafiku gde ce biti prikazane.
	 * @return ArrayList
	 */
	public static ArrayList<Swot> getListaSnage() {
		// TODO Auto-generated method stub
		return logika.getListaSnage();
	}
	/**
	 * Prosledjuje listu slabosti iz logike u grafiku gde ce biti prikazane.
	 * @return ArrayList
	 */
	public static ArrayList<Swot> getListaSlabosti() {
		// TODO Auto-generated method stub
		return logika.getListaSlabosti();
	}
	/**
	 * Prosledjuje listu sansi iz logike u grafiku gde ce biti prikazane.
	 * @return ArrayList
	 */
	public static ArrayList<Swot> getListaSanse() {
		// TODO Auto-generated method stub
		return logika.getListaSanse();
	}
	/**
	 * Prosledjuje listu pretnji iz logike u grafiku gde ce biti prikazane.
	 * @return ArrayList
	 */
	public static ArrayList<Swot> getListaPretnje() {
		// TODO Auto-generated method stub
		return logika.getListaPretnje();
	}
	/**
	 * Prosledjuje ponder iz logike u grafiku gde ce se koristiti za racunanje ukupne atraktivnosti.
	 * @return double ponder
	 */
	public static double getPonderSnaga(String naziv) {
		// TODO Auto-generated method stub
		return logika.vratiPonderSnagaNaziv(naziv);
	}
	/**
	 * Prosledjuje ponder iz logike u grafiku gde ce se koristiti za racunanje ukupne atraktivnosti.
	 * @return double ponder
	 */
	public static double getPonderSlabost(String naziv){
		return logika.vratiPonderSlabostNaziv(naziv);
	}
	/**
	 * Prosledjuje ponder iz logike u grafiku gde ce se koristiti za racunanje ukupne atraktivnosti.
	 * @return double ponder
	 */
	public static double getPonderSansa(String naziv){
		return logika.vratiPonderSansaNaziv(naziv);
	}
	/**
	 * Prosledjuje ponder iz logike u grafiku gde ce se koristiti za racunanje ukupne atraktivnosti.
	 * @return double ponder
	 */
	public static double getPonderPretnja(String naziv){
		return logika.vratiPonderPretnjaNaziv(naziv);
	}
	/**
	 * Kreira prozor za unosenje snaga, slabosti, sansi i pretnji.
	 */
	public static void napraviProzorNoviSwot() {
		ProzorNoviSwot prozorNoviSwot = new ProzorNoviSwot();
		prozorNoviSwot.setVisible(true);
		prozorNoviSwot.setLocationRelativeTo(null);
	}

	/**
	 * Kreira i prikazuje prozor za prikaz i poredjenje strategija.
	 */
	public static void napraviProzorUporediStrategije(){
		GUIUporediStrategije prozorZaStrategije = new GUIUporediStrategije();
		prozorZaStrategije.setVisible(true);
		prozorZaStrategije.setLocationRelativeTo(null);
	}
	/**
	 * Kreira prozor za prikaz podataka o autorima.
	 */
	public static void prikaziPodatkeOAutorima() {

		JOptionPane.showMessageDialog(frame, "Autori:\nAndrija Djordjevic\nPetar Karuovic\nMiljan Jovic\n\nFON 2016");
		
	}
	
	/**
	 * Poziva se klikom na dugme New. Pravi nove objekte klasa Logika i GlavniProzor sa praznim listama.
	 */
	public static void refresuj(){
		frame.dispose();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logika = new Logika();
					frame = new GlavniProzor();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	public static void prikaziProzorZaPovezivanjeNaServer(){
		
		final JDialog poveziSeNaServerProzor;
		poveziSeNaServerProzor = new JDialog(clientFrame, "Povezivanje", true);
		poveziSeNaServerProzor.setBounds(150, 150, 300, 75);
		
		JPanel contentPane = new JPanel(new FlowLayout());
		final JTextField adresa = new JTextField();
		adresa.setPreferredSize(new Dimension(150, 25));
		JButton povezi = new JButton("Povezi se");
		
		
		povezi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ip = adresa.getText();
				Pattern regex = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
						"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
				Matcher matcher = regex.matcher(ip);
				if(matcher.matches()){
					if(poveziSeNaServer(adresa.getText())){
						JOptionPane.showMessageDialog(poveziSeNaServerProzor, "Uspesno povezivanje!", "Povezano!", JOptionPane.INFORMATION_MESSAGE);
						poveziSeNaServerProzor.dispose();
					}else{
						JOptionPane.showMessageDialog(poveziSeNaServerProzor, "Greska pri povezivanju, pokusajte ponovo!", "Greska!", JOptionPane.ERROR_MESSAGE);
						
					}
				}else{
					JOptionPane.showMessageDialog(poveziSeNaServerProzor, "Neodgovarajuci format IP adrese!");
				}
				
				
			}
		});
		
		contentPane.add(adresa);
		contentPane.add(povezi);
		poveziSeNaServerProzor.setContentPane(contentPane);
		poveziSeNaServerProzor.setVisible(true);
	}
	
	public static boolean poveziSeNaServer(String ip){
		
		try {
			client = new Client(InetAddress.getByName(ip));
			client.send("CaoPoyy");
			
			String primljeno = client.receive();
			popuniListePodataka(primljeno);
			return true;
		} catch (Exception e) {
			return false;
		} 
		
		
	}
	
	private static void popuniListePodataka(String primljeno) {
		String [] strategije = primljeno.split("\n");
		String [] podaciJednaStrategija;
		String [] snage;
		String [] slabosti;
		String [] sanse;
		String [] pretnje;
		
		Strategija nova;
		for (String strategija : strategije) {
			podaciJednaStrategija = strategija.split("\t");
			nova = new Strategija(podaciJednaStrategija[0]);//prvi podstring pre \t je ime strategije, ostali su redom: snage, slabosti, sanse, pretnje
			snage = podaciJednaStrategija[1].split(" ");//nazivi i ponderi snaga su odvojeni razmakom, npr: naziv1 ponder1 naziv2 ponder2
			slabosti = podaciJednaStrategija[2].split(" ");
			sanse = podaciJednaStrategija[3].split(" ");
			pretnje = podaciJednaStrategija[4].split(" ");
			
			//proveravamo da li nema snaga u strategiji, isto ce biti i za ostale swotove
			if(!podaciJednaStrategija[1].equals("nema")){
				for(int i = 0 ; i < snage.length ; i = i + 2){
					nova.dodajSnaguBezAtraktivnosti(snage[i],Double.parseDouble(snage[i+1]));
				}
			}
			//dodavanje slabosti
			if(!podaciJednaStrategija[2].equals("nema")){
				for(int i = 0 ; i < slabosti.length ; i = i + 2){
					nova.dodajSlabostBezAtraktivnosti(slabosti[i],Double.parseDouble(slabosti[i+1]));
				}
			}
			//dodavanje sansi
			if(!podaciJednaStrategija[3].equals("nema")){
				for(int i = 0 ; i < sanse.length ; i = i + 2){
					nova.dodajSansuBezAtraktivnosti(sanse[i],Double.parseDouble(sanse[i+1]));
				}
			}
			
			//dodavanje pretnji
			if(!podaciJednaStrategija[4].equals("nema")){
				for(int i = 0 ; i < pretnje.length ; i = i + 2){
					nova.dodajPretnjuBezAtraktivnosti(pretnje[i],Double.parseDouble(pretnje[i+1]));
				}
			}
			
			logika.dodajStrategiju(nova);
		}
				
	}
	public static boolean posaljiPodatkeServeru(){
		try {
			client.send(vratiStringSaPodacima());
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	private static String vratiStringSaPodacima() {
		String povratna = "";
		ArrayList<SwotStrat> swot;
		ArrayList<Strategija> listaStrategija = logika.getStrategije();
		for (Strategija strategija : listaStrategija) {
			swot = (ArrayList<SwotStrat>)strategija.getSnage();
			for (SwotStrat swotStrat : swot) {
				povratna += swotStrat.getAtraktivnost()+" ";
			}
			swot = (ArrayList<SwotStrat>)strategija.getSlabosti();
			for (SwotStrat swotStrat : swot) {
				povratna += swotStrat.getAtraktivnost()+" ";
			}
			swot = (ArrayList<SwotStrat>)strategija.getSanse();
			for (SwotStrat swotStrat : swot) {
				povratna += swotStrat.getAtraktivnost()+" ";
			}
			swot = (ArrayList<SwotStrat>)strategija.getPretnje();
			for (SwotStrat swotStrat : swot) {
				povratna += swotStrat.getAtraktivnost()+" ";
			}
		}
		return povratna;
	}
	public static void otvoriKlijentProzor() {
		clientFrame = new ClientGUI();
		clientFrame.setVisible(true);
		pocetni.dispose();
		
	}
	public static void otvoriServerProzor() {
		frame = new GlavniProzor();
		frame.setVisible(true);
		pocetni.dispose();
		
	}
	//server kod
	public static void pokreniServer() {
		try {
			InetAddress ip = InetAddress.getLocalHost();
			JOptionPane.showMessageDialog(frame, "Vasa adresa je: \n" + ip.getHostAddress(), "Obavestenje",
					JOptionPane.INFORMATION_MESSAGE);
			server = new Server(logika.getStrategije());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Doslo je do greske prilikom pokretanja servera", "Greska",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public static void ugasiServer() {
		server.ugasiNit();
		logika.setStrategije(server.vratiStrategije());
		JOptionPane.showMessageDialog(frame, "Server je ugasen", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void upisiUPDF() {
		String nazivFajla = " ";
		JFileChooser upis = new JFileChooser();
		upis.setCurrentDirectory(new File(System.getProperty("user.home")));
		upis.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int povratnaVrednost = upis.showSaveDialog(frame.getContentPane());
		if (povratnaVrednost == JFileChooser.APPROVE_OPTION) 
			nazivFajla = upis.getSelectedFile().getAbsolutePath();
			
		String snage = "", slabost = "", pretnje = "", sanse = "", strategije = "";
		for (int i = 0; i < getListaSnage().size(); i++) {
			snage += "-" + getListaSnage().get(i).getNaziv() + "\n";
		}
		for (int i = 0; i < getListaSlabosti().size(); i++) {
			slabost += "-" + getListaSlabosti().get(i).getNaziv() + "\n";
		}
		for (int i = 0; i < getListaSanse().size(); i++) {
			sanse += "-" + getListaSanse().get(i).getNaziv() + "\n";
		}
		for (int i = 0; i < getListaPretnje().size(); i++) {
			pretnje += "-" + getListaPretnje().get(i).getNaziv() + "\n";
		}
		for (int i = 0; i < getListaStrategija().size(); i++) {
			strategije += (i + 1) + ". " + getListaStrategija().get(i).getNaziv() + (" (");
			if (getListaStrategija().get(i).prikaziSnage() != "") {
				strategije += " Snage: " + getListaStrategija().get(i).prikaziSnage();
			}
			if (getListaStrategija().get(i).prikaziSlabosti() != "") {
				strategije += " Slabosti: " + getListaStrategija().get(i).prikaziSlabosti();
			}
			if (getListaStrategija().get(i).prikaziSanse() != "") {
				strategije += " Sanse: " + getListaStrategija().get(i).prikaziSanse();
			}
			if (getListaStrategija().get(i).prikaziPretnje() != "") {
				strategije += " Pretnje: " + getListaStrategija().get(i).prikaziPretnje();
			}
			strategije += " ) \n";
		}
		Document dok = new Document();
		PdfPTable t = new PdfPTable(2);
//		GregorianCalendar datum = new GregorianCalendar();
//		String d = "" + datum.get(GregorianCalendar.DAY_OF_MONTH) + "." + datum.get(GregorianCalendar.MONTH) + "."
//				+ datum.get(GregorianCalendar.YEAR) + ", " + datum.get(GregorianCalendar.HOUR_OF_DAY) + ":"
//				+ datum.get(GregorianCalendar.MINUTE);
		Date datum = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		String d = sdf.format(datum);
		try {
			PdfWriter.getInstance(dok, new FileOutputStream(nazivFajla+".pdf"));
			dok.open();
			Paragraph par = new Paragraph();
			Paragraph par2 = new Paragraph();
			par.add("                                                             SWOT - " + d + "\n\n\n");
			// par.add("\nSnage: \n");
			// par.add(snage);
			// par.add("\n Slabosti: \n");
			// par.add(slabost);
			// par.add("\n Sanse: \n");
			// par.add(sanse);
			// par.add("\n Pretnje: \n");
			// par.add(pretnje);
			par.add("                                             S              "
					+ "                                                 W");
			t.addCell(snage);
			t.addCell(slabost);
			t.addCell(sanse);
			t.addCell(pretnje);
			par2.add("                                             O              "
					+ "                                                 T");
			par2.add("\n\n Uporedjene strategije po atraktivnosti: \n\n");
			par2.add(strategije);

			dok.add(par);
			dok.add(t);
			dok.add(par2);
			dok.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void oceniStrategiju(int selectedRow) {
		ProzorOceniStrategiju prozor = new ProzorOceniStrategiju(logika.getStrategije().get(selectedRow), selectedRow);
		
	}
	//pisao u 4 ujutru, ne znam zasto iz guia ne dodajem atraktivnost direktno u snagu, 
	//izbegavam semanticke vratolomije xexe, iako mozak ne radi, nadam se da aplikacija hoce
	
	public static void oceniSnagu(int rb, String naziv, int atraktivnost) {
		ArrayList<SwotStrat> pom = (ArrayList<SwotStrat>) logika.getStrategije().get(rb).getSnage();
		for (SwotStrat swotStrat : pom) {
			if(swotStrat.getNaziv().equals(naziv))
				swotStrat.setAtraktivnost(atraktivnost);
		}
		
	}
	public static void oceniSlabost(int rb, String naziv, int atraktivnost) {
		ArrayList<SwotStrat> pom = (ArrayList<SwotStrat>) logika.getStrategije().get(rb).getSlabosti();
		for (SwotStrat swotStrat : pom) {
			if(swotStrat.getNaziv().equals(naziv))
				swotStrat.setAtraktivnost(atraktivnost);
		}
		
	}
	public static void oceniSansu(int rb, String naziv, int atraktivnost) {
		ArrayList<SwotStrat> pom = (ArrayList<SwotStrat>) logika.getStrategije().get(rb).getSanse();
		for (SwotStrat swotStrat : pom) {
			if(swotStrat.getNaziv().equals(naziv))
				swotStrat.setAtraktivnost(atraktivnost);
		}
		
	}
	public static void oceniPretnje(int rb, String naziv, int atraktivnost) {
		ArrayList<SwotStrat> pom = (ArrayList<SwotStrat>) logika.getStrategije().get(rb).getPretnje();
		for (SwotStrat swotStrat : pom) {
			if(swotStrat.getNaziv().equals(naziv))
				swotStrat.setAtraktivnost(atraktivnost);
		}
		
	}
}
