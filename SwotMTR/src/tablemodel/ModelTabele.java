package tablemodel;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import logika.Swot;

/**
 * Klasa koja sadrzi podatke za prikaz u swot tabelama i odgovarajuce metode za
 * manipulaciju nad njima.
 * 
 * @author Petar Karuovic
 *
 */
public class ModelTabele extends AbstractTableModel {
	private ArrayList<Swot> podaci;
	private String[] nazivKolone;

	/**
	 * Konstruktor koji inicijalizuje listu podataka na prosledjenu listu,
	 * ukoliko je lista prazna inicijalizuje novu listu.
	 * 
	 * @param lista
	 *            (lista podataka za prikaz u tabeli)
	 */
	public ModelTabele(ArrayList<Swot> lista, String[] nazivKolona) {
		if (lista == null) {
			lista = new ArrayList<Swot>();
		} else {
			podaci = lista;
		}
		this.nazivKolone = nazivKolona;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * Redefinisana metoda getColumnName koja postavlja naziv kolona na "Naziv"
	 * i "Ponder".
	 */
	@Override
	public String getColumnName(int column) {
		return nazivKolone[column];
	}

	/**
	 * Redefinisana metoda getColumnCount koja vraca broj kolona tabele.
	 * 
	 * @return broj kolona tabele
	 */
	@Override
	public int getColumnCount() {
		return nazivKolone.length;
	}

	/**
	 * Redefinisana metoda getRowCount koja vraca broj redova tabele.
	 * 
	 * @return broj redova tabele
	 */
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return podaci.size();
	}

	/**
	 * Redefinisana metoda getValueAt koja vraca odgovarajuce podatke iz liste
	 * swot-a.
	 * 
	 * @return selektovanu vrednost
	 */
	@Override
	public Object getValueAt(int red, int kolona) {
		Swot pom = podaci.get(red);

		switch (kolona) {
		case 0:
			return pom.getNaziv();
		case 1:
			return pom.getPonder();
		}
		return null;
	}

	/**
	 * Metoda koja sluzi za update tabele
	 * 
	 * @param lista
	 *            novih podataka
	 */

	public void osveziTabelu(ArrayList<Swot> lista) {
		podaci = lista;
		fireTableDataChanged();
	}
}
