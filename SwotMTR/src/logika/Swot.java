package logika;

import java.io.Serializable;

/**
 * 
 * @author Andrija
 *
 *Klasa swot koja moze da predstavlja snagu, slabost, sansu ili pretnju odredjenog naziva 
 *i tezinskog koeficijenta (atribut ponder).
 */
public class Swot implements Serializable{
	private String naziv;
	private double ponder;
	/**
	 * Parametrizovani konstruktor
	 * @param naziv
	 * @param ponder - realni broj izmedju 0 i 1
	 */
	public Swot(String naziv, double ponder) {
		if (naziv == null || naziv.isEmpty())
			throw new RuntimeException("naziv");
		if (ponder < 0 || ponder > 1)
			throw new RuntimeException("ponder");
		this.naziv = naziv;
		this.ponder = ponder;
	}
	/**
	 * 
	 * @return naziv snage/slabosti/sanse/pretnje
	 */
	public String getNaziv() {
		return naziv;
	}
	/**
	 * Postavlja naziv snage/slabosti/sanse/pretnje
	 * @param naziv
	 */
	public void setNaziv(String naziv) {
		if (naziv == null || naziv.isEmpty())
			throw new RuntimeException("naziv");
		this.naziv = naziv;
	}
	/**
	 * 
	 * @return ponder
	 */
	public double getPonder() {
		return ponder;
	}
	/**
	 * Proverava da li je prosledjeni ponder izmedju 0 i 1, ukoliko jeste, 
	 * postavlja ponder na prosledjenu vrednost
	 * @param ponder
	 */
	public void setPonder(double ponder) {
		if (ponder < 0 || ponder > 1)
			throw new RuntimeException("ponder");
		this.ponder = ponder;
	}
	/**
	 * Redefinisana equals metoda, poredi objekte Swot na osnovu naziva i pondera
	 */
	@Override
	public boolean equals(Object arg0) {
		if (arg0 != null && arg0 instanceof Swot) {
			Swot s = (Swot) arg0;

			if (naziv.equals(s.getNaziv()) && ponder == s.getPonder()) {
				return true;
			}
		}
		return false;
	}
}
