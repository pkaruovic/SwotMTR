package clientgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.Kontroler;
import logika.Strategija;
import tablemodel.ClientTableModel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.ImageIcon;

public class ClientGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JButton btnPoveziSeNa;
	private JButton btnPosaljiPodatke;
	private int brojStrategija = -1;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNew;

	// private JDialog poveziSeNaServerProzor;
	/**
	 * Create the frame.
	 */
	public ClientGUI() {
		setTitle("Klijent");
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				Kontroler.ugasiAplikacijuKlijent();
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			final JTable tabela = getTable();
			tabela.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					Kontroler.oceniStrategiju(tabela.getSelectedRow());

				}
			});
			scrollPane.setViewportView(tabela);
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			ClientTableModel model = new ClientTableModel(Kontroler.getListaStrategija());
			table.setModel(model);
			table.getColumnModel().getColumn(0).setPreferredWidth(2);
			table.getColumnModel().getColumn(1).setPreferredWidth(370);
		}
		return table;
	}

	public void osveziTabelu(ArrayList<Strategija> listaStrategija) {
		ClientTableModel model = (ClientTableModel) table.getModel();
		model.osveziTabelu(listaStrategija);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getBtnPoveziSeNa());
			panel.add(getBtnPosaljiPodatke());
		}
		return panel;
	}

	private JButton getBtnPoveziSeNa() {
		if (btnPoveziSeNa == null) {
			btnPoveziSeNa = new JButton(" Povezi se na server");
			btnPoveziSeNa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.prikaziProzorZaPovezivanjeNaServer();
					osveziTabelu(Kontroler.getListaStrategija());
					if (Kontroler.daLiJePovezan()) {
						btnPoveziSeNa.setVisible(false);
						btnPosaljiPodatke.setVisible(true);
						brojStrategija = Kontroler.getListaStrategija().size();
					}
				}
			});
			btnPoveziSeNa.setPreferredSize(new Dimension(160, 23));
		}
		return btnPoveziSeNa;
	}

	private JButton getBtnPosaljiPodatke() {
		if (btnPosaljiPodatke == null) {
			btnPosaljiPodatke = new JButton("Posalji podatke");
			btnPosaljiPodatke.setVisible(false);
			btnPosaljiPodatke.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (brojStrategija == 0) {
						Kontroler.posaljiPodatkeServeru();
						btnPosaljiPodatke.setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, "Niste ocenili sve strategije!");
					}
				}
			});
			btnPosaljiPodatke.setPreferredSize(new Dimension(160, 23));
		}
		return btnPosaljiPodatke;
	}

	public void smanjiBrojNeocenjenihStrategija() {
		brojStrategija--;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
		}
		return menuBar;
	}
	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("File");
			mnNewMenu.add(getMntmNew());
		}
		return mnNewMenu;
	}
	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem("New");
			mntmNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.refresujKlijent();
				}
			});
			mntmNew.setIcon(new ImageIcon(ClientGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/upFolder.gif")));
		}
		return mntmNew;
	}
}
