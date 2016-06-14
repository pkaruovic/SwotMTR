package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIDodajSWOT extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField textNaziv;
	private JLabel lblNaziv;
	private JPanel panel_2;
	private JRadioButton rdbtnSnaga;
	private JRadioButton rdbtnSlabost;
	private JRadioButton rdbtnSansa;
	private JRadioButton rdbtnPretnja;
	private JLabel lblPonder;
	private JTextField textPonder;
	private JPanel panel_3;
	private JButton btnDodaj;
	private JButton btnIzadji;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIDodajSWOT frame = new GUIDodajSWOT();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIDodajSWOT() {
		setTitle(" Dodaj SWOT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 259);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getPanel_3(), BorderLayout.SOUTH);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_1(), BorderLayout.NORTH);
			panel.add(getPanel_2(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("56px"),
					ColumnSpec.decode("255px"),},
				new RowSpec[] {
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,
					FormSpecs.RELATED_GAP_ROWSPEC,
					FormSpecs.DEFAULT_ROWSPEC,}));
			panel_1.add(getLblNaziv(), "1, 2, center, fill");
			panel_1.add(getTextNaziv(), "2, 2, fill, fill");
			panel_1.add(getLblPonder(), "1, 4, center, default");
			panel_1.add(getTextPonder(), "2, 4");
		}
		return panel_1;
	}
	private JTextField getTextNaziv() {
		if (textNaziv == null) {
			textNaziv = new JTextField();
			textNaziv.setPreferredSize(new Dimension(150, 20));
			textNaziv.setColumns(10);
		}
		return textNaziv;
	}
	private JLabel getLblNaziv() {
		if (lblNaziv == null) {
			lblNaziv = new JLabel("Naziv: ");
		}
		return lblNaziv;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.add(getRdbtnSlabost());
			panel_2.add(getRdbtnSnaga());
			panel_2.add(getRdbtnSansa());
			panel_2.add(getRdbtnPretnja());
		}
		return panel_2;
	}
	private JRadioButton getRdbtnSnaga() {
		if (rdbtnSnaga == null) {
			rdbtnSnaga = new JRadioButton("Snaga");
			buttonGroup.add(rdbtnSnaga);
		}
		return rdbtnSnaga;
	}
	private JRadioButton getRdbtnSlabost() {
		if (rdbtnSlabost == null) {
			rdbtnSlabost = new JRadioButton("Slabost");
			buttonGroup.add(rdbtnSlabost);
		}
		return rdbtnSlabost;
	}
	private JRadioButton getRdbtnSansa() {
		if (rdbtnSansa == null) {
			rdbtnSansa = new JRadioButton("Sansa");
			buttonGroup.add(rdbtnSansa);
		}
		return rdbtnSansa;
	}
	private JRadioButton getRdbtnPretnja() {
		if (rdbtnPretnja == null) {
			rdbtnPretnja = new JRadioButton("Pretnja");
			buttonGroup.add(rdbtnPretnja);
		}
		return rdbtnPretnja;
	}
	private JLabel getLblPonder() {
		if (lblPonder == null) {
			lblPonder = new JLabel("Ponder:");
		}
		return lblPonder;
	}
	private JTextField getTextPonder() {
		if (textPonder == null) {
			textPonder = new JTextField();
			textPonder.setColumns(10);
		}
		return textPonder;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.add(getBtnDodaj());
			panel_3.add(getBtnIzadji());
		}
		return panel_3;
	}
	private JButton getBtnDodaj() {
		if (btnDodaj == null) {
			btnDodaj = new JButton("Dodaj");
		}
		return btnDodaj;
	}
	private JButton getBtnIzadji() {
		if (btnIzadji == null) {
			btnIzadji = new JButton("Izadji");
			btnIzadji.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
//					System.exit(1);
				}
			});
		}
		return btnIzadji;
	}
}
