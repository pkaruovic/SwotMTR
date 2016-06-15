package clientgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.GUIStrategija;
import kontrola.Kontroler;
import logika.Strategija;
import logika.SwotStrat;

public class ProzorOceniStrategiju extends JFrame {

	private JPanel contentPane;
	private Strategija strategija;
	private JPanel panel;
	private JComboBox comboSnage;
	private JComboBox comboAtraktivnostSnage;
	private JComboBox comboSlabosti;
	private JComboBox comboAtraktivnostSlabosti;
	private JComboBox comboSanse;
	private JComboBox comboAtraktivnostSanse;
	private JComboBox comboPretnje;
	private JComboBox comboAtraktivnostPretnje;
	private JPanel panel_1;
	private JLabel lblNazivStrategije;
	private JTextField textNazivStrategije;
	private JButton btnDodajPretnju;
	private JButton btnDodaj;
	private JButton btnDodajSnagu;
	private JButton btnDodajSlabost;
	private JButton btnDodajSansu;
	private int rb;//mnogo olaksava, nema pretrage strategije kada treba ubaciti atraktivnost
	/**
	 * Create the frame.
	 */
	public ProzorOceniStrategiju(Strategija strategija, int rb) {
		this.strategija = strategija;
		this.rb = rb;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
		setContentPane(contentPane);
		setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getComboSnage());
			panel.add(getComboAtraktivnostSnage());
			panel.add(getBtnDodajSnagu());
			panel.add(getComboSlabosti());
			panel.add(getComboAtraktivnostSlabosti());
			panel.add(getBtnDodajSlabost());
			panel.add(getComboSanse());
			panel.add(getComboAtraktivnostSanse());
			panel.add(getBtnDodajSansu());
			panel.add(getComboPretnje());
			panel.add(getComboAtraktivnostPretnje());
			panel.add(getBtnDodajPretnju());
		}
		return panel;
	}
	
	private JComboBox getComboSnage() {
		if (comboSnage == null) {
			comboSnage = new JComboBox();
			comboSnage.setName("");
			comboSnage.setVisible(false);
			comboSnage.setToolTipText("");
			comboSnage.setPreferredSize(new Dimension(300, 40));
			comboSnage.addItem("Snage");
			for (int i = 0; i < strategija.getSnage().size(); i++) {
				comboSnage.addItem(strategija.getSnage().get(i).getNaziv());
			}
		}
		return comboSnage;
	}

	private JComboBox getComboAtraktivnostSnage() {
		if (comboAtraktivnostSnage == null) {
			comboAtraktivnostSnage = new JComboBox();
			comboAtraktivnostSnage.setPreferredSize(new Dimension(40, 22));
			comboAtraktivnostSnage.addItem("");
			comboAtraktivnostSnage.addItem("0");
			comboAtraktivnostSnage.addItem("1");
			comboAtraktivnostSnage.addItem("2");
			comboAtraktivnostSnage.addItem("3");
			comboAtraktivnostSnage.addItem("4");
		}
		return comboAtraktivnostSnage;
	}

	private JComboBox getComboSlabosti() {
		if (comboSlabosti == null) {
			comboSlabosti = new JComboBox();
			comboSlabosti.setPreferredSize(new Dimension(300, 40));
			comboSlabosti.addItem("Slabosti");
			for (int i = 0; i < strategija.getSlabosti().size(); i++) {
				comboSlabosti.addItem(strategija.getSlabosti().get(i).getNaziv());
			}
		}
		return comboSlabosti;
	}

	private JComboBox getComboAtraktivnostSlabosti() {
		if (comboAtraktivnostSlabosti == null) {
			comboAtraktivnostSlabosti = new JComboBox();
			comboAtraktivnostSlabosti.setPreferredSize(new Dimension(40, 22));
			comboAtraktivnostSlabosti.addItem("");
			comboAtraktivnostSlabosti.addItem("0");
			comboAtraktivnostSlabosti.addItem("1");
			comboAtraktivnostSlabosti.addItem("2");
			comboAtraktivnostSlabosti.addItem("3");
			comboAtraktivnostSlabosti.addItem("4");
		}
		return comboAtraktivnostSlabosti;
	}

	/**
	 * Kreira objekat comboSanse koji se popunjava i vraca.
	 * 
	 * @return JComboBox
	 * 
	 */

	private JComboBox getComboSanse() {
		if (comboSanse == null) {
			comboSanse = new JComboBox();
			comboSanse.setPreferredSize(new Dimension(300, 40));
			comboSanse.addItem("Sanse");
			for (int i = 0; i < strategija.getSanse().size(); i++) {
				comboSanse.addItem(strategija.getSanse().get(i).getNaziv());
			}
		}
		return comboSanse;
	}

	/**
	 * Kreira objekat comboAtraktivnostSanse koji se popunjava i vraca.
	 * 
	 * @return JComboBox
	 * 
	 */
	private JComboBox getComboAtraktivnostSanse() {
		if (comboAtraktivnostSanse == null) {
			comboAtraktivnostSanse = new JComboBox();
			comboAtraktivnostSanse.setPreferredSize(new Dimension(40, 22));
			comboAtraktivnostSanse.addItem("");
			comboAtraktivnostSanse.addItem("0");
			comboAtraktivnostSanse.addItem("1");
			comboAtraktivnostSanse.addItem("2");
			comboAtraktivnostSanse.addItem("3");
			comboAtraktivnostSanse.addItem("4");

		}
		return comboAtraktivnostSanse;
	}

	/**
	 * Kreira objekat comboPretnje koji se popunjava i vraca.
	 * 
	 * @return JComboBox
	 * 
	 */
	private JComboBox getComboPretnje() {
		if (comboPretnje == null) {
			comboPretnje = new JComboBox();
			comboPretnje.setPreferredSize(new Dimension(300, 40));
			comboPretnje.addItem("Pretnje");
			for (int i = 0; i < strategija.getPretnje().size(); i++) {
				comboPretnje.addItem(strategija.getPretnje().get(i).getNaziv());
			}
		}
		return comboPretnje;
	}

	/**
	 * Kreira objekat comboAtraktivnostPretnje koji se popunjava i vraca.
	 * 
	 * @return JComboBox
	 * 
	 */
	private JComboBox getComboAtraktivnostPretnje() {
		if (comboAtraktivnostPretnje == null) {
			comboAtraktivnostPretnje = new JComboBox();
			comboAtraktivnostPretnje.setPreferredSize(new Dimension(40, 22));
			comboAtraktivnostPretnje.addItem("");
			comboAtraktivnostPretnje.addItem("0");
			comboAtraktivnostPretnje.addItem("1");
			comboAtraktivnostPretnje.addItem("2");
			comboAtraktivnostPretnje.addItem("3");
			comboAtraktivnostPretnje.addItem("4");
		}
		return comboAtraktivnostPretnje;
	}

	private JButton getBtnDodajSnagu() {
		if (btnDodajSnagu == null) {
			btnDodajSnagu = new JButton("Oceni snagu");
			btnDodajSnagu.setPreferredSize(new Dimension(115, 23));
			btnDodajSnagu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String naziv = (String) comboSnage.getSelectedItem();
					String atraktivnostSnage = (String) comboAtraktivnostSnage.getSelectedItem();
					if (naziv == null || naziv.equals("Snage")) {
						JOptionPane.showMessageDialog(new GUIStrategija(), "Niste odabrali snagu", "Greska",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (atraktivnostSnage.isEmpty()) {
						JOptionPane.showMessageDialog(new GUIStrategija(),
								"Niste odabrali atraktivnost za odredjenu snagu", "Greska",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						int atraktivnost = Integer.parseInt((String) comboAtraktivnostSnage.getSelectedItem());
						
						Kontroler.oceniSnagu(rb, naziv, atraktivnost);
						comboSnage.setSelectedItem("Snage");
						comboAtraktivnostSnage.setSelectedItem("");
					}
				}
			});
		}
		return btnDodajSnagu;
	}

	/**
	 * Kreira i vraca dugme btnDodajSlabost koje kada se klikne odabranu slabost
	 * i atraktivnost ubacuje u vec kreiranu strategiju
	 * 
	 * @return JButton
	 * 
	 */
	private JButton getBtnDodajSlabost() {
		if (btnDodajSlabost == null) {
			btnDodajSlabost = new JButton("Oceni slabost");
			btnDodajSlabost.setPreferredSize(new Dimension(115, 23));
			btnDodajSlabost.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String naziv = (String) comboSlabosti.getSelectedItem();
					String atraktivnostSlabosti = (String) comboAtraktivnostSlabosti.getSelectedItem();
					if (naziv == null || naziv.equals("Slabosti")) {
						JOptionPane.showMessageDialog(new GUIStrategija(), "Niste odabrali slabost", "Greska",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (atraktivnostSlabosti.isEmpty()) {
						JOptionPane.showMessageDialog(new GUIStrategija(),
								"Niste odabrali atraktivnost za odredjenu slabost", "Greska",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						int atraktivnost = Integer.parseInt((String) comboAtraktivnostSlabosti.getSelectedItem());
						Kontroler.oceniSlabost(rb, naziv, atraktivnost);
						comboSlabosti.setSelectedItem("Slabosti");
						comboAtraktivnostSlabosti.setSelectedItem("");
					}
				}
			});
		}
		return btnDodajSlabost;
	}

	/**
	 * Kreira i vraca dugme btnDodajSansu koje kada se klikne odabranu sansu i
	 * atraktivnost ubacuje u vec kreiranu strategiju
	 * 
	 * @return JButton
	 * 
	 */
	private JButton getBtnDodajSansu() {
		if (btnDodajSansu == null) {
			btnDodajSansu = new JButton("Oceni sansu");
			btnDodajSansu.setPreferredSize(new Dimension(115, 23));
			btnDodajSansu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String naziv = (String) comboSanse.getSelectedItem();
					String atraktivnostSanse = (String) comboAtraktivnostSanse.getSelectedItem();
					if (naziv == null || naziv.equals("Sanse")) {
						JOptionPane.showMessageDialog(new GUIStrategija(), "Niste odabrali sansu", "Greska",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (atraktivnostSanse.isEmpty()) {
						JOptionPane.showMessageDialog(new GUIStrategija(),
								"Niste odabrali atraktivnost za odredjenu sansu", "Greska",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						int atraktivnost = Integer.parseInt((String) comboAtraktivnostSanse.getSelectedItem());
						Kontroler.oceniSansu(rb, naziv, atraktivnost);
						comboSanse.setSelectedItem("Sanse");
						comboAtraktivnostSanse.setSelectedItem("");
					}
				}
			});
		}
		return btnDodajSansu;
	}

	/**
	 * Kreira i vraca dugme btnDodajPretnju koje kada se klikne odabranu pretnju
	 * i atraktivnost ubacuje u vec kreiranu strategiju
	 * 
	 * @return JButton
	 * 
	 */
	private JButton getBtnDodajPretnju() {
		if (btnDodajPretnju == null) {
			btnDodajPretnju = new JButton("Oceni pretnju");
			btnDodajPretnju.setPreferredSize(new Dimension(115, 23));
			btnDodajPretnju.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String naziv = (String) comboPretnje.getSelectedItem();
					String atraktivnostPretnje = (String) comboAtraktivnostPretnje.getSelectedItem();
					if (naziv == null || naziv.equals("Pretnje")) {
						JOptionPane.showMessageDialog(new GUIStrategija(), "Niste odabrali pretnju", "Greska",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (atraktivnostPretnje.isEmpty()) {
						JOptionPane.showMessageDialog(new GUIStrategija(),
								"Niste odabrali atraktivnost za odredjenu pretnju", "Greska",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						int atraktivnost = Integer.parseInt((String) comboAtraktivnostPretnje.getSelectedItem());

						Kontroler.oceniPretnje(rb, naziv, atraktivnost);
						comboPretnje.setSelectedItem("Pretnje");
						comboAtraktivnostPretnje.setSelectedItem("");
					}
				}
			});
		}
		return btnDodajPretnju;
	}
}
