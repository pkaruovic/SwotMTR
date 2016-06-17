package clientgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontrola.Kontroler;
import logika.Strategija;
import tablemodel.ClientTableModel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClientGUI extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmPoveziSeNa;
	private JScrollPane scrollPane;
	private JTable table;
	//private JDialog poveziSeNaServerProzor;
	/**
	 * Create the frame.
	 */
	public ClientGUI() {
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
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
		}
		return menuBar;
	}
	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmPoveziSeNa());
		}
		return mnFile;
	}
	private JMenuItem getMntmPoveziSeNa() {
		if (mntmPoveziSeNa == null) {
			mntmPoveziSeNa = new JMenuItem("Povezi se na server");
			mntmPoveziSeNa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.prikaziProzorZaPovezivanjeNaServer();
					osveziTabelu(Kontroler.getListaStrategija());
				}

				
			});
		}
		return mntmPoveziSeNa;
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
}
