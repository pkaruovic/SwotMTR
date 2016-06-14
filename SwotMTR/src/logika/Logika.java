package logika;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Andrija 
 * Klasa u kojoj se nalaze svi podaci potrebni za rad aplikacije, 
 * kao i metode kojima se ti podaci obradjuju.
 *
 *	Lista strategije sadrzi sve strategije koje korisnik kreira kombinovanjem snaga,
 * slabosti, sansi  i pretnji.
 * 
 * Liste listaSnage, listaSlabosti, listaSanse, listaPretnje sadrze objekte klase Swot koji predstavljaju
 * snage, slabosti, sanse i pretnje.
 */

public class Logika implements Serializable{
	
	private ArrayList<Strategija> strategije = new ArrayList<Strategija>();
	private ArrayList<Swot> listaSnage = new ArrayList<Swot>();
	private ArrayList<Swot> listaSlabosti = new ArrayList<Swot>();
	private ArrayList<Swot> listaSanse = new ArrayList<Swot>();
	private ArrayList<Swot> listaPretnje = new ArrayList<Swot>();

//	public Logika() {
//		strategije;
//		listaSnage = new ArrayList<Swot>();
//		listaSlabosti = new ArrayList<Swot>();
//		listaSanse = new ArrayList<Swot>();
//		listaPretnje;
//	}
	/**
	 * 
	 * @return ArrayList Metoda vraca listu strategija koje je korisnik kreirao
	 */
	public ArrayList<Strategija> getStrategije() {
		return strategije;
	}

	/**
	 * Metoda se koristi samo u slucaju deserijalizacije. Kada korisnik izabere datoteku 
	 * u kojoj je prethodno sacuvao podatke iz ove liste, listi iz ovog objekta 
	 * se dodeljuje prethodno sacuvana lista. Svi podaci koje je lista prethodno sadrzala se brisu.
	 * @param strategije Lista strategija koje ce se prikazivati i koje ce korisnik koristiti.
	 */
	public void setStrategije(ArrayList<Strategija> strategije) {
		this.strategije = strategije;
	}
	/**
	 * 
	 * @return ArrayList Metoda vraca listu snaga koje je korisnik kreirao.
	 */
	public ArrayList<Swot> getListaSnage() {
		return listaSnage;
	}

	/**
	 * Metoda se koristi samo u slucaju deserijalizacije. Kada korisnik izabere datoteku 
	 * u kojoj je prethodno sacuvao podatke iz ove liste, listi iz ovog objekta 
	 * se dodeljuje prethodno sacuvana lista. Svi podaci koje je lista prethodno sadrzala se brisu.
	 * @param listaSnage Lista snaga koje ce se prikazivati i koje ce korisnik koristiti.
	 */
	public void setListaSnage(ArrayList<Swot> listaSnage) {
		this.listaSnage = listaSnage;
	}
	
	/**
	 * 
	 * @return ArrayList lista slabosti koje je korisnik kreirao.
	 */
	public ArrayList<Swot> getListaSlabosti() {
		return listaSlabosti;
	}

	/**
	 * Metoda se koristi samo u slucaju deserijalizacije. Kada korisnik izabere datoteku 
	 * u kojoj je prethodno sacuvao podatke iz ove liste, listi iz ovog objekta 
	 * se dodeljuje prethodno sacuvana lista. Svi podaci koje je lista prethodno sadrzala se brisu.
	 * @param listaSlabosti Lista slabosti koje ce se prikazivati i koje ce korisnik koristiti.
	 */
	public void setListaSlabosti(ArrayList<Swot> listaSlabosti) {
		this.listaSlabosti = listaSlabosti;
	}

	/**
	 * 
	 * @return ArrayList lista sansi koje je korisnik kreirao.
	 */
	public ArrayList<Swot> getListaSanse() {
		return listaSanse;
	}

	/**
	 * Metoda se koristi samo u slucaju deserijalizacije. Kada korisnik izabere datoteku 
	 * u kojoj je prethodno sacuvao podatke iz ove liste, listi iz ovog objekta 
	 * se dodeljuje prethodno sacuvana lista. Svi podaci koje je lista prethodno sadrzala se brisu.
	 * @param listaSanse Lista sansi koje ce se prikazivati i koje ce korisnik koristiti.
	 */
	public void setListaSanse(ArrayList<Swot> listaSanse) {
		this.listaSanse = listaSanse;
	}
	/**
	 * 
	 * @return ArrayList lista pretnji koje je korisnik kreirao.
	 */
	public ArrayList<Swot> getListaPretnje() {
		return listaPretnje;
	}

	/**
	 * Metoda se koristi samo u slucaju deserijalizacije. Kada korisnik izabere datoteku 
	 * u kojoj je prethodno sacuvao podatke iz ove liste, listi iz ovog objekta 
	 * se dodeljuje prethodno sacuvana lista. Svi podaci koje je lista prethodno sadrzala se brisu.
	 * @param listaPretnje Lista pretnji koje ce se prikazivati i koje ce korisnik koristiti.
	 */
	public void setListaPretnje(ArrayList<Swot> listaPretnje) {
		this.listaPretnje = listaPretnje;
	}
	
	/**
	 * Metoda dodaje strategiju u listu strategija tako da strategije ostanu sortirane u opadajucem redosledu
	 * prema atraktivnosti kako bi korisnik mogao da ih lakse uporedjuje.
	 * @param strategija - objekat koji je korisnik kreirao
	 */
	public void dodajStrategijuUListu(Strategija strategija){
		for(int i=0; i<strategije.size(); i++){
			if(strategija.getSumaUkupnihAtraktivnosti() > strategije.get(i).getSumaUkupnihAtraktivnosti()){
				strategije.add(i, strategija);
				return;
			}
		}
		strategije.add(strategija);
	}
	/**
	 * Kreira objekat klase swot koji predstavlja snagu i dodaje ga u listu
	 * @param naziv - naziv snage
	 * @param ponder - realan broj izmedju 0 i 1 koji predstavlja tezinski koeficijent snage, odnosno,
	 * koliki znacaj ima ova snaga 
	 */
	public void dodajSnaguUListu(String naziv, double ponder){
		listaSnage.add(new Swot(naziv, ponder));
	}
	/**
	 * Kreira objekat klase swot koji predstavlja sansu i dodaje ga u listu
	 * @param naziv - naziv sanse
	 * @param ponder - realan broj izmedju 0 i 1 koji predstavlja tezinski koeficijent sanse, odnosno,
	 * koliki znacaj ima ova sansa 
	 */
	public void dodajSansuUListu(String naziv, double ponder){
		listaSanse.add(new Swot(naziv, ponder));
	}
	/**
	 * Kreira objekat klase swot koji predstavlja slabost i dodaje ga u listu
	 * @param naziv - naziv slabosti
	 * @param ponder - realan broj izmedju 0 i 1 koji predstavlja tezinski koeficijent slabosti, odnosno,
	 * koliki znacaj ima ova slabost 
	 */
	public void dodajSlabostUListu(String naziv, double ponder){
		listaSlabosti.add(new Swot(naziv, ponder));
	}
	
	/**
	 * Kreira objekat klase swot koji predstavlja pretnju i dodaje ga u listu
	 * @param naziv - naziv pretnje
	 * @param ponder - realan broj izmedju 0 i 1 koji predstavlja tezinski koeficijent pretnje, odnosno,
	 * koliki znacaj ima ova pretnja 
	 */
	public void dodajPretnjuUListu(String naziv, double ponder){
		listaPretnje.add(new Swot(naziv, ponder));
	}
	/**
	 * Pretrazuje listu snaga i vraca ponder odgovarajuce snage
	 * @param naziv - naziv snage ciji ponder treba da vrati metoda
	 * @return double ponder odgovarajuce snage
	 */
	public double vratiPonderSnagaNaziv(String naziv){
		//dodaj kontrolu u gui-u da uopste ne moze da pozove ako je naziv null ili prazan
		if(naziv != null && naziv != "")
			for (Swot swot : listaSnage) {
				if(swot.getNaziv().equals(naziv))
					return swot.getPonder();
			}

		return 0;//nece doci do ovoga kada se uvede kontrola
	}
	/**
	 * Pretrazuje listu sansi i vraca ponder odgovarajuce sanse
	 * @param naziv - naziv sanse ciji ponder treba da vrati metoda
	 * @return double ponder odgovarajuce sanse
	 */
	public double vratiPonderSansaNaziv(String naziv){
		// dodaj kontrolu u gui-u da uopste ne moze da pozove ako je naziv null
		// ili prazan
		if (naziv != null && naziv != "")
			for (Swot swot : listaSanse) {
				if (swot.getNaziv().equals(naziv))
					return swot.getPonder();
			}
		return 0;// nece doci do ovoga kada se uvede kontrola
	}
	/**
	 * Pretrazuje listu slabosti i vraca ponder odgovarajuce slabosti
	 * @param naziv - naziv slabosti ciji ponder treba da vrati metoda
	 * @return double ponder odgovarajuce slabosti
	 */
	public double vratiPonderSlabostNaziv(String naziv){
		//dodaj kontrolu u gui-u da uopste ne moze da pozove ako je naziv null ili prazan
		if (naziv != null && naziv != "")
			for (Swot swot : listaSlabosti) {
				if (swot.getNaziv().equals(naziv))
					return swot.getPonder();
			}
		return 0;// nece doci do ovoga kada se uvede kontrola
	}
	/**
	 * Pretrazuje listu pretnji i vraca ponder odgovarajuce pretnje
	 * @param naziv - naziv pretnje ciji ponder treba da vrati metoda
	 * @return double ponder odgovarajuce pretnje
	 */
	public double vratiPonderPretnjaNaziv(String naziv){
		//dodaj kontrolu u gui-u da uopste ne moze da pozove ako je naziv null ili prazan
		if (naziv != null && naziv != "")
			for (Swot swot : listaPretnje) {
				if (swot.getNaziv().equals(naziv))
					return swot.getPonder();
			}
		return 0;// nece doci do ovoga kada se uvede kontrola
	}
	
	public void dodajStrategiju(Strategija nova){
		strategije.add(nova);
	}
}
