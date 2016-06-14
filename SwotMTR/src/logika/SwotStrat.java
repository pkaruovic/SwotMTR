package logika;

import java.io.Serializable;
/**
 * 
 * @author Andrija
 *
 * Klasa koja predstavlja snagu/slabost/sansu/pretnju odredjene strategije. Kreira se na osnovu postojece
 * snage/slabosti/sanse/pretnje. Omogucava da vise strategija ima istu snagu/slabost/sansu/pretnju
 * sa razlicitom atraktivnoscu u okviru te strategije.
 */
public class SwotStrat extends Swot implements Serializable{
	private int atraktivnost;
	private double ukupnaAtraktivnost;
	/**
	 * Konstruktor. Izracunava ukupnu atraktivnost kao proizvod pondera iz objekta Swot i atraktivnosti
	 * koju korisnik unosi kada kreira strategiju.
	 * @param naziv
	 * @param ponder
	 * @param atraktivnost
	 */
	public SwotStrat(String naziv, double ponder, int atraktivnost) {
		super(naziv, ponder);
		this.atraktivnost = atraktivnost;
		ukupnaAtraktivnost = super.getPonder() * atraktivnost;
	}
	
	public SwotStrat(String naziv, double ponder){
		super(naziv, ponder);
	}
	/**
	 * 
	 * @return atraktivnost - ceo broj koji predstavlja atraktivnost swot-a u okviru strategije
	 */
	public int getAtraktivnost() {
		return atraktivnost;
	}
	/**
	 * Menja atraktivnost prosledjenom atraktivnoscu i ponovno izracunava ukupnu atraktivnost
	 * @param atraktivnost
	 */
	public void setAtraktivnost(int atraktivnost) {
		if(atraktivnost < 0 || atraktivnost > 4)
			throw new RuntimeException("atraktivnost je van granica");
		this.atraktivnost = atraktivnost;
		ukupnaAtraktivnost = super.getPonder() * atraktivnost;
	}
	/**
	 * 
	 * @return izracunatu ukupnu atraktivnost
	 */
	public double getUkupnaAtraktivnost() {
		return ukupnaAtraktivnost;
	}

}
