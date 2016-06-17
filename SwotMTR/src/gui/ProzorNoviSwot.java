package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontrola.Kontroler;
import logika.Swot;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Klasa koja predstavlja prozor za dodavanje novog SWOT-a.
 * 
 * @author Andrija Djordjevic
 *
 */
public class ProzorNoviSwot extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNaziv;
	private JTextField txtNaziv;
	private JLabel lblPonder;
	private JTextField txtPonder;
	private JRadioButton rdbtnSnaga;
	private JRadioButton rdbtnSlabost;
	private JRadioButton rdbtnSansa;
	private JRadioButton rdbtnPretnja;
	private JPanel panel_1;
	private JButton btnSacuvaj;
	private JButton btnOtkazi;
	private ButtonGroup grupnjak;

	/**
	 * Create the frame.
	 */
	public ProzorNoviSwot() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 302, 187);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);
		setLocationRelativeTo(null);
		getGrupnjak();
		getRootPane().setDefaultButton(btnSacuvaj);

	}

	private ButtonGroup getGrupnjak() {
		if (grupnjak == null) {
			grupnjak = new ButtonGroup();
			grupnjak.add(getRdbtnSnaga());
			grupnjak.add(getRdbtnSlabost());
			grupnjak.add(getRdbtnSansa());
			grupnjak.add(getRdbtnPretnja());

		}
		return grupnjak;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getLblNaziv());
			panel.add(getTxtNaziv());
			panel.add(getLblPonder());
			panel.add(getTxtPonder());
			panel.add(getRdbtnSnaga());
			panel.add(getRdbtnSlabost());
			panel.add(getRdbtnSansa());
			panel.add(getRdbtnPretnja());
		}
		return panel;
	}

	private JLabel getLblNaziv() {
		if (lblNaziv == null) {
			lblNaziv = new JLabel("Naziv");
			lblNaziv.setPreferredSize(new Dimension(100, 14));
		}
		return lblNaziv;
	}

	private JTextField getTxtNaziv() {
		if (txtNaziv == null) {
			txtNaziv = new JTextField();
			txtNaziv.setPreferredSize(new Dimension(100, 20));
			txtNaziv.setColumns(10);
		}
		return txtNaziv;
	}

	private JLabel getLblPonder() {
		if (lblPonder == null) {
			lblPonder = new JLabel("Ponder");
			lblPonder.setPreferredSize(new Dimension(100, 14));
		}
		return lblPonder;
	}

	private JTextField getTxtPonder() {
		if (txtPonder == null) {
			txtPonder = new JTextField();
			txtPonder.setColumns(10);
		}
		return txtPonder;
	}

	private JRadioButton getRdbtnSnaga() {
		if (rdbtnSnaga == null) {
			rdbtnSnaga = new JRadioButton("Snaga");
		}
		return rdbtnSnaga;
	}

	private JRadioButton getRdbtnSlabost() {
		if (rdbtnSlabost == null) {
			rdbtnSlabost = new JRadioButton("Slabost");
		}
		return rdbtnSlabost;
	}

	private JRadioButton getRdbtnSansa() {
		if (rdbtnSansa == null) {
			rdbtnSansa = new JRadioButton("Sansa");
		}
		return rdbtnSansa;
	}

	private JRadioButton getRdbtnPretnja() {
		if (rdbtnPretnja == null) {
			rdbtnPretnja = new JRadioButton("Pretnja");
		}
		return rdbtnPretnja;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(10, 40));
			panel_1.add(getBtnSacuvaj());
			panel_1.add(getBtnOtkazi());
		}
		return panel_1;
	}

	/**
	 * Dugme koje sluzi za dodavanje novog SWOT-a u odgovarajucu listu. Ukoliko
	 * je korisnik nepravilno popunio trazena polja aktivira se novi prozor sa
	 * porukom o gresci.
	 * 
	 * @return JButton
	 */
	private JButton getBtnSacuvaj() {
		if (btnSacuvaj == null) {
			btnSacuvaj = new JButton("Sacuvaj");

			btnSacuvaj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Swot s = new Swot(txtNaziv.getText(), Double.parseDouble(txtPonder.getText()));
						if (rdbtnSnaga.isSelected() && !Kontroler.getListaSnage().contains(s)) {
							Kontroler.azurirajBrojacSnageSlabosti(Double.parseDouble(txtPonder.getText()));
							Kontroler.dodajSnagu(txtNaziv.getText(), Double.parseDouble(txtPonder.getText()));
						} else if (rdbtnSlabost.isSelected() && !Kontroler.getListaSlabosti().contains(s)) {
							Kontroler.azurirajBrojacSnageSlabosti(Double.parseDouble(txtPonder.getText()));
							Kontroler.dodajSlabost(txtNaziv.getText(), Double.parseDouble(txtPonder.getText()));
						} else if (rdbtnSansa.isSelected() && !Kontroler.getListaSanse().contains(s)) {
							Kontroler.azurirajBrojacSansePretnje(Double.parseDouble(txtPonder.getText()));
							Kontroler.dodajSansu(txtNaziv.getText(), Double.parseDouble(txtPonder.getText()));
						} else if (rdbtnPretnja.isSelected() && !Kontroler.getListaPretnje().contains(s)) {
							Kontroler.azurirajBrojacSansePretnje(Double.parseDouble(txtPonder.getText()));
							Kontroler.dodajPretnju(txtNaziv.getText(), Double.parseDouble(txtPonder.getText()));
						} else {
							JOptionPane.showMessageDialog(rootPane, "Nepravilno ste popunili formu.", "Greska",
									JOptionPane.ERROR_MESSAGE, null);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(rootPane,
								"Nepravilno ste popunili naziv swot-a ili je doslo je do prekoracenja zbira pondera",
								"Greska", JOptionPane.ERROR_MESSAGE, null);
					}
				}
			});
		}
		return btnSacuvaj;
	}

	/**
	 * Dugme koje sluzi za gasenje prozora.
	 * 
	 * @return JButton
	 */
	private JButton getBtnOtkazi() {
		if (btnOtkazi == null) {
			btnOtkazi = new JButton("Otkazi");
			btnOtkazi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnOtkazi;
	}
}
