package tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import logika.Strategija;

public class ClientTableModel extends AbstractTableModel {
	private String[] kolone = new String[] {"#", "Strategija"};
	private ArrayList<Strategija> strategije;
	
	public ClientTableModel(ArrayList<Strategija> strategije) {
		if(strategije == null)
			this.strategije = new ArrayList<Strategija>();
		else
			this.strategije = strategije;
	}
	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public int getRowCount() {
		return strategije.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		switch(arg1){
		case 0: return arg0 + 1;//redni broj strategije
		case 1: return strategije.get(arg0).getNaziv();
		}
			
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void osveziTabelu(ArrayList<Strategija> strategije){
		this.strategije = strategije;
		fireTableDataChanged();
	}
}
