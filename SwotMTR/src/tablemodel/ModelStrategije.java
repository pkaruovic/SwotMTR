package tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import logika.Strategija;

/**
 * Klasa koja sadrzi podatke iz tabele za poredjenje strategija i metode za
 * manipulaciju tim podacima.
 * 
 * @author Petar Karuovic
 *
 */
public class ModelStrategije extends AbstractTableModel {
	private ArrayList<Strategija> podaci;
	private String[] naziv = new String[] { "Strategija", "Ukupna atraktivnost" };

	/**
	 * Konstruktor koji inicijalizuje listu podataka na prosledjenu listu,
	 * ukoliko je lista prazna inicijalizuje novu listu.
	 * 
	 * @param strategije
	 */
	public ModelStrategije(ArrayList<Strategija> strategije) {
		if (strategije == null) {
			podaci = new ArrayList<Strategija>();
		} else {
			podaci = strategije;
		}
	}

	/**
	 * Redefinisana metoda getColumnName koja postavlja nazive kolona na
	 * "Strategija" i "Atraktivnost".
	 * 
	 * @return vraca nazive kolona
	 */
	@Override
	public String getColumnName(int column) {
		return naziv[column];
	}

	/**
	 * Redefinisana metoda getColumnCount koja vraca broj kolona tabele.
	 * 
	 * @return broj kolona tabele
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return naziv.length;
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
	 * strategija.
	 * 
	 * @return selektovanu vrednost
	 */
	@Override
	public Object getValueAt(int red, int kolona) {
		Strategija s = podaci.get(red);

		switch (kolona) {
		case 0:
			return s.getNaziv();
		case 1:
			return s.getSumaUkupnihAtraktivnosti();
		}
		return null;
	}

}
