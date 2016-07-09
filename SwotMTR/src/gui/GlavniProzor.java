package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logika.Logika;
import logika.Swot;
import tablemodel.ModelTabele;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.itextpdf.text.log.SysoLogger;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JPopupMenu;
import java.awt.Component;

/**
 * Klasa koja predstavlja pocetni prozor aplikacije i glavnu radnu povrsinu za
 * korisnika.
 * 
 * @author Petar Karuovic
 *
 */
public class GlavniProzor extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmAbout;
	private JPanel panel;
	private JTable Snage;
	private JTable Slabosti;
	private JTable Sanse;
	private JTable Pretnje;
	private JMenuItem mntmNew;
	private JPanel panel_1;
	private JButton btnUporediStrategije;
	private JButton btnKreirajStrategiju;
	private JButton btnDodaj;
	private JMenuItem mntmExit;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JButton btnSwot;
	private JButton btnPokreniServer;
	private JButton btnUgasiServer;
	private JButton btnTows;
	private JMenuItem mntmSaveAsPdf;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblT;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JTextField textSnageSlabosti;
	private JLabel lblSanseIPretnje;
	private JTextField textSansePretnje;
	private JPopupMenu popupMenu;
	private JMenuItem mntmIzmeni;
	private JMenuItem mntmObrisi;
	private JPopupMenu popupMenu_1;
	private JMenuItem menuItem;
	private JMenuItem menuItem_1;
	private JPopupMenu popupMenu_2;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_3;
	private JPopupMenu popupMenu_3;
	private JMenuItem menuItem_4;
	private JMenuItem menuItem_5;
	
	public GlavniProzor() {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				/**
				 * Metoda koja poziva prozor za gasenje aplikacije.
				 * 
				 * @see kontrola.Kontroler#ugasiAplikaciju()
				 */
				Kontroler.ugasiAplikaciju();
			}
		});

		setTitle("Server");

		setPreferredSize(new Dimension(800, 600));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 927, 700);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.CENTER);
		contentPane.add(getPanel_1(), BorderLayout.EAST);
		contentPane.add(getPanel_2(), BorderLayout.SOUTH);
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmNew());
			mnFile.add(getMntmOpen());
			mnFile.add(getMntmSave());
			mnFile.add(getMntmSaveAsPdf());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	/**
	 * Stavka menija koja poziva prozor za izbor i importovanje binarnog fajla
	 * sa podacima o swot-ovima.
	 * 
	 * @see gui.Kontroler#deserijalizuj()
	 * @return JMenuItem
	 */
	private JMenuItem getMntmOpen() {
		if (mntmOpen == null) {
			mntmOpen = new JMenuItem("Open");
			mntmOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.deserijalizuj();
				}
			});

			mntmOpen.setIcon(new ImageIcon(
					GlavniProzor.class.getResource("/com/sun/java/swing/plaf/windows/icons/TreeOpen.gif")));
		}
		return mntmOpen;
	}

	/**
	 * Stavka menija koja poziva prozor za cuvanje podataka o swot-ovima.
	 * 
	 * @see gui.Kontroler#serijalizuj(String)
	 * @return JMenuItem
	 */
	private JMenuItem getMntmSave() {
		if (mntmSave == null) {
			mntmSave = new JMenuItem("Save");
			mntmSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					JDialog saveDialog = new JDialog();
//					saveDialog.setVisible(true);
//					saveDialog.setBounds(100, 100, 250, 100);
//					JPanel panelD = new JPanel();
//					JTextField nazivFajla = new JTextField();
//					nazivFajla.setBounds(10, 10, 100, 25);
//					nazivFajla.setPreferredSize(new Dimension(100, 25));
//					JButton btnOk = new JButton("Ok");
//					panelD.setLayout(new FlowLayout());
//					// panelD.add(new JLabel("Naziv fajla:"));
//					panelD.add(nazivFajla);
//					panelD.add(btnOk);
					Kontroler.serijalizuj();
//					btnOk.addActionListener(new ActionListener() {
//						public void actionPerformed(ActionEvent e) {
//							Kontroler.serijalizuj(nazivFajla);
//							saveDialog.dispose();
//							// saveDialog.setEnabled(false);
//							// saveDialog.setVisible(false);
//
//						}
//					});
//					saveDialog.getContentPane().add(panelD);
//					saveDialog.setTitle("Naziv fajla");
//					saveDialog.setVisible(true);
//					saveDialog.setResizable(false);
//					// saveDialog.setModal(true);
				}
			});
			mntmSave.setIcon(new ImageIcon(
					GlavniProzor.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		}
		return mntmSave;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(2, 2, 8, 8));
			panel.add(getScrollPane());
			panel.add(getScrollPane_1());
			panel.add(getScrollPane_2());
			panel.add(getScrollPane_3());
			
		}
		return panel;
	}

	private JTable getSnage() {
		if (Snage == null) {
			Snage = new JTable();
			Snage.setModel(new ModelTabele(Kontroler.getListaSnage(), new String[]{"Naziv snage", "Ponder"}));
			Snage.setComponentPopupMenu(popupMenu);
			Snage.setShowGrid(true);
			Snage.setShowVerticalLines(true);
			Snage.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			Snage.getColumnModel().getColumn(0).setPreferredWidth(450);
		    Snage.setRowSelectionAllowed(true);
		}
		return Snage;
	}

	private JTable getSlabosti() {
		if (Slabosti == null) {
			Slabosti = new JTable();
			Slabosti.setModel(new ModelTabele(Kontroler.getListaSlabosti(), new String[]{"Naziv slabosti", "Ponder"}));
			Slabosti.setComponentPopupMenu(popupMenu_1);
			Slabosti.setShowGrid(true);
			Slabosti.setShowVerticalLines(true);
			Slabosti.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			Slabosti.getColumnModel().getColumn(0).setPreferredWidth(450);
		    Slabosti.setRowSelectionAllowed(true);
		}
		return Slabosti;
	}

	private JTable getSanse() {
		if (Sanse == null) {
			Sanse = new JTable();
			Sanse.setModel(new ModelTabele(Kontroler.getListaSanse(), new String[]{"Naziv sanse", "Ponder"}));
			Sanse.setComponentPopupMenu(popupMenu_2);
			Sanse.setShowGrid(true);
			Sanse.setShowVerticalLines(true);
			Sanse.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			Sanse.getColumnModel().getColumn(0).setPreferredWidth(450);
		    Sanse.setRowSelectionAllowed(true);
		}
		return Sanse;
	}

	private JTable getPretnje() {
		if (Pretnje == null) {
			Pretnje = new JTable();
			Pretnje.setModel(new ModelTabele(Kontroler.getListaPretnje(), new String[]{"Naziv pretnje", "Ponder"}));
			Pretnje.setComponentPopupMenu(popupMenu_3);
			Pretnje.setShowGrid(true);
			Pretnje.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			Pretnje.getColumnModel().getColumn(0).setPreferredWidth(450);
			Pretnje.setRowSelectionAllowed(true);
		}
		return Pretnje;
	}

	/**
	 * Stavka menija koja kreira novi prozor sa informacijama o autorima i
	 * godini izdanja.
	 * 
	 * @see gui.Kontroler#prikaziPodatkeOAutorima
	 * @return jMenuItem
	 */
	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.prikaziPodatkeOAutorima();
				}
			});
		}
		return mntmAbout;
	}

	/**
	 * Stavka menija za ponovno pokretanje programa
	 * 
	 * @see gui.Kontroler#refresuj()
	 * @return JMenuItem
	 */
	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem("New");
			mntmNew.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					Kontroler.refresuj();
				}
			});
			mntmNew.setIcon(new ImageIcon(
					GlavniProzor.class.getResource("/com/sun/java/swing/plaf/windows/icons/UpFolder.gif")));
		}

		return mntmNew;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(150, 10));
			panel_1.setLayout(new MigLayout("", "[108.00px,left]", "[97.00,top][177.00px,grow][98.00px,center][196.00,grow][grow,bottom]"));
			panel_1.add(getPanel_3_1(), "cell 0 0,grow");
			panel_1.add(getPanel_4(), "cell 0 2,growx,aligny center");
			panel_1.add(getPanel_5(), "cell 0 4,grow");
			// panel_1.add(getBtnDodaj());
		}
		return panel_1;
	}

	/**
	 * Dugme za kreiranje novog prozora za prikaz strategija
	 * 
	 * @see gui.Kontroler#napraviProzorUporediStrategije()
	 * @return
	 */
	private JButton getBtnUporediStrategije() {
		if (btnUporediStrategije == null) {
			btnUporediStrategije = new JButton("Strategije");
			btnUporediStrategije.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.napraviProzorUporediStrategije();
				}
			});
			btnUporediStrategije.setPreferredSize(new Dimension(100, 23));
		}
		return btnUporediStrategije;
	}

	/**
	 * Dugme koje kreira novi prozor za dodavanje strategije.
	 * 
	 * @see gui.GUIStrategija
	 * @return JButton
	 */
	private JButton getBtnKreirajStrategiju() {
		if (btnKreirajStrategiju == null) {
			btnKreirajStrategiju = new JButton("Strategiju");
			btnKreirajStrategiju.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.napraviProzorNovaStrat();
				}
			});
			btnKreirajStrategiju.setPreferredSize(new Dimension(100, 23));
		}
		return btnKreirajStrategiju;
	}

	private JButton getBtnDodaj() {
		if (btnDodaj == null) {
			btnDodaj = new JButton("Dodaj");
			btnDodaj.setPreferredSize(new Dimension(130, 23));
		}
		return btnDodaj;
	}

	/**
	 * Dugme koje kreira novi prozor za dodavanje swot-a.
	 * 
	 * @see gui.ProzorNoviSwot
	 * @return
	 */
	private JButton getBtnSwot() {
		if (btnSwot == null) {
			btnSwot = new JButton("SWOT");
			btnSwot.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.napraviProzorNoviSwot();
				}
			});
			btnSwot.setPreferredSize(new Dimension(100, 20));
		}
		return btnSwot;

	}

	/**
	 * Stavka za gasenje programa
	 * 
	 * @return JMenuItem
	 */
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.ugasiAplikaciju();
				}
			});
			mntmExit.setIcon(
					new ImageIcon(GlavniProzor.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		}
		return mntmExit;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			addPopup(scrollPane, getPopupMenu());
			scrollPane.setViewportView(getSnage());
		}
		return scrollPane;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			addPopup(scrollPane_1, getPopupMenu_1());
			scrollPane_1.setViewportView(getSlabosti());
		}
		return scrollPane_1;
	}

	private JScrollPane getScrollPane_2() {
		if (scrollPane_2 == null) {
			scrollPane_2 = new JScrollPane();
			addPopup(scrollPane_2, getPopupMenu_2());
			scrollPane_2.setViewportView(getSanse());
		}
		return scrollPane_2;
	}

	private JScrollPane getScrollPane_3() {
		if (scrollPane_3 == null) {
			scrollPane_3 = new JScrollPane();
			addPopup(scrollPane_3, getPopupMenu_3());
			scrollPane_3.setViewportView(getPretnje());
		}
		return scrollPane_3;
	}

	/**
	 * Metoda za apdejtovanje tabele sa snagama.
	 * 
	 * @param listaSnage
	 *            (lista sa snagama)
	 */
	public void srediTabeluSnage(ArrayList<Swot> listaSnage) {
		ModelTabele mt = (ModelTabele) Snage.getModel();
		mt.osveziTabelu(listaSnage);
	}

	/**
	 * Metoda za apdejtovanje tabele sa slabostima.
	 * 
	 * @param listaSlabosti
	 *            (lista sa slabostima)
	 */
	public void srediTabeluSlabosti(ArrayList<Swot> listaSlabosti) {
		ModelTabele mt = (ModelTabele) Slabosti.getModel();
		mt.osveziTabelu(listaSlabosti);
	}

	/**
	 * Metoda za apdejtovanje tabele sa sansama.
	 * 
	 * @param listaSanse
	 *            (lista sa sansama)
	 */
	public void srediTabeluSanse(ArrayList<Swot> listaSanse) {
		ModelTabele mt = (ModelTabele) Sanse.getModel();
		mt.osveziTabelu(listaSanse);
	}

	/**
	 * Metoda za apdejtovanje tabele sa pretnjama.
	 * 
	 * @param listaPretnje
	 *            (lista sa pretnjama)
	 */
	public void srediTabeluPretnje(ArrayList<Swot> listaPretnje) {
		ModelTabele mt = (ModelTabele) Pretnje.getModel();
		mt.osveziTabelu(listaPretnje);
	}
	private JButton getBtnPokreniServer() {
		if (btnPokreniServer == null) {
			btnPokreniServer = new JButton("Pokreni server");
			btnPokreniServer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.pokreniServer();
				}
			});
			btnPokreniServer.setPreferredSize(new Dimension(100, 23));
		}
		return btnPokreniServer;
	}
	private JButton getBtnUgasiServer() {
		if (btnUgasiServer == null) {
			btnUgasiServer = new JButton("Ugasi server");
			btnUgasiServer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.ugasiServer();
				}
			});
			btnUgasiServer.setPreferredSize(new Dimension(100, 23));
		}
		return btnUgasiServer;
	}
	private JButton getBtnTows() {
		if (btnTows == null) {
			btnTows = new JButton("TOWS");
			btnTows.setPreferredSize(new Dimension(100, 23));
			btnTows.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.napraviTows();
				}
			});
		}
		return btnTows;
	}
	private JMenuItem getMntmSaveAsPdf() {
		if (mntmSaveAsPdf == null) {
			mntmSaveAsPdf = new JMenuItem("Save as PDF");
			mntmSaveAsPdf.setIcon(new ImageIcon(GlavniProzor.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
			mntmSaveAsPdf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.upisiUPDF();
				}
			});
		}
		return mntmSaveAsPdf;
	}
	private JPanel getPanel_3_1() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBorder(new TitledBorder(null, "NAPRAVI", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_3.setLayout(new MigLayout("", "[118.00px]", "[][23px][20px]"));
			panel_3.add(getBtnSwot(), "flowy,cell 0 1,grow");
			panel_3.add(getBtnKreirajStrategiju(), "cell 0 1,growx,aligny top");
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PRIKAZI", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_4.setLayout(new MigLayout("", "[120.00,fill]", "[grow][fill][fill][grow]"));
			panel_4.add(getBtnUporediStrategije(), "cell 0 1,growx,aligny center");
			panel_4.add(getBtnTows(), "cell 0 2,growx,aligny center");
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "KLIJENT-SERVER", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_5.setLayout(new MigLayout("", "[]", "[][][][]"));
			panel_5.add(getBtnPokreniServer(), "flowy,cell 0 1,growx,aligny center");
			panel_5.add(getBtnUgasiServer(), "cell 0 2,growx,aligny center");
		}
		return panel_5;
	}
	private JLabel getLblT() {
		if (lblT == null) {
			lblT = new JLabel("T");
		}
		return lblT;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new MigLayout("", "[grow][][56.00][76.00][78.00][63.00][][62.00][74px][grow]", "[14px]"));
			panel_2.add(getLblNewLabel(), "flowy,cell 1 0,alignx center,aligny top");
			panel_2.add(getLblSanseIPretnje(), "flowy,cell 6 0,alignx center");
			panel_2.add(getTextSansePretnje(), "cell 6 0,alignx center");
			panel_2.add(getTextSnageSlabosti(), "cell 1 0,alignx center");
		}
		return panel_2;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("snage + slabosti");
		}
		return lblNewLabel;
	}
	private JTextField getTextSnageSlabosti() {
		if (textSnageSlabosti == null) {
			textSnageSlabosti = new JTextField();
			textSnageSlabosti.setHorizontalAlignment(SwingConstants.CENTER);
			textSnageSlabosti.setPreferredSize(new Dimension(5, 20));
			textSnageSlabosti.setEditable(false);
			textSnageSlabosti.setColumns(10);
		}
		return textSnageSlabosti;
	}
	private JLabel getLblSanseIPretnje() {
		if (lblSanseIPretnje == null) {
			lblSanseIPretnje = new JLabel("sanse + pretnje");
		}
		return lblSanseIPretnje;
	}
	private JTextField getTextSansePretnje() {
		if (textSansePretnje == null) {
			textSansePretnje = new JTextField();
			textSansePretnje.setHorizontalAlignment(SwingConstants.CENTER);
			textSansePretnje.setPreferredSize(new Dimension(5, 20));
			textSansePretnje.setEditable(false);
			textSansePretnje.setColumns(10);
		}
		return textSansePretnje;
	}
	
	public void textVrednostiSumaSS(double vrednost){
		textSnageSlabosti.setText(String.format("%.2f", vrednost));
	}
	
	public void textVrednostiSumaSP(double vrednost){
		textSansePretnje.setText(String.format("%.2f", vrednost));
	}
	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.add(getMntmIzmeni());
			popupMenu.add(getMntmObrisi());
			popupMenu.addPopupMenuListener(new PopupMenuListener() {
				
				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
					 SwingUtilities.invokeLater(new Runnable() {
		                    @Override
		                    public void run() {
		                        int rowAtPoint = Snage.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), Snage));
		                        if (rowAtPoint > -1) {
		                            Snage.setRowSelectionInterval(rowAtPoint, rowAtPoint);
		                            Snage.setColumnSelectionInterval(0, 0);
		                        }
		                    }
		                });
					
				}
				
				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void popupMenuCanceled(PopupMenuEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return popupMenu;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private JMenuItem getMntmIzmeni() {
		if (mntmIzmeni == null) {
			mntmIzmeni = new JMenuItem("Izmeni");
			mntmIzmeni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String naziv = (String) Snage.getValueAt(Snage.getSelectedRow(), 0);
					String ponder = "" + Snage.getValueAt(Snage.getSelectedRow(), 1);
					Kontroler.napraviPopunjenProzorNoviSwot(naziv, ponder, 1);
				}
			});
		}
		return mntmIzmeni;
	}
	private JMenuItem getMntmObrisi() {
		if (mntmObrisi == null) {
			mntmObrisi = new JMenuItem("Obrisi");
			mntmObrisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.obrisiSnagu((String) Snage.getValueAt(Snage.getSelectedRow(), Snage.getSelectedColumn()));
				}
			});
		}
		return mntmObrisi;
	}
	private JPopupMenu getPopupMenu_1() {
		if (popupMenu_1 == null) {
			popupMenu_1 = new JPopupMenu();
			popupMenu_1.add(getMenuItem());
			popupMenu_1.add(getMenuItem_1());
			popupMenu_1.addPopupMenuListener(new PopupMenuListener() {
				
				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
	                    @Override
	                    public void run() {
	                        int rowAtPoint = Slabosti.rowAtPoint(SwingUtilities.convertPoint(popupMenu_1, new Point(0, 0), Slabosti));
	                        if (rowAtPoint > -1) {
	                            Slabosti.setRowSelectionInterval(rowAtPoint, rowAtPoint);
	                            Slabosti.setColumnSelectionInterval(0, 0);
	                        }
	                    }
	                });
				}
				
				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return popupMenu_1;
	}
	private JMenuItem getMenuItem() {
		if (menuItem == null) {
			menuItem = new JMenuItem("Izmeni");
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String naziv = (String) Slabosti.getValueAt(Slabosti.getSelectedRow(), 0);
					String ponder = "" + Slabosti.getValueAt(Slabosti.getSelectedRow(), 1);
					Kontroler.napraviPopunjenProzorNoviSwot(naziv, ponder, 2);
				}
			});
		}
		return menuItem;
	}
	private JMenuItem getMenuItem_1() {
		if (menuItem_1 == null) {
			menuItem_1 = new JMenuItem("Obrisi");
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.obrisiSlabost((String) Slabosti.getValueAt(Slabosti.getSelectedRow(), Slabosti.getSelectedColumn()));
				}
			});
		}
		return menuItem_1;
	}
	private JPopupMenu getPopupMenu_2() {
		if (popupMenu_2 == null) {
			popupMenu_2 = new JPopupMenu();
			popupMenu_2.add(getMenuItem_2());
			popupMenu_2.add(getMenuItem_3());
			popupMenu_2.addPopupMenuListener(new PopupMenuListener() {
				
				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
	                    @Override
	                    public void run() {
	                        int rowAtPoint = Sanse.rowAtPoint(SwingUtilities.convertPoint(popupMenu_2, new Point(0, 0), Sanse));
	                        if (rowAtPoint > -1) {
	                            Sanse.setRowSelectionInterval(rowAtPoint, rowAtPoint);
	                            Sanse.setColumnSelectionInterval(0, 0);
	                        }
	                    }
	                });
					
				}
				
				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return popupMenu_2;
	}
	private JMenuItem getMenuItem_2() {
		if (menuItem_2 == null) {
			menuItem_2 = new JMenuItem("Izmeni");
			menuItem_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String naziv = (String) Sanse.getValueAt(Sanse.getSelectedRow(), 0);
					String ponder = "" + Sanse.getValueAt(Sanse.getSelectedRow(), 1);
					Kontroler.napraviPopunjenProzorNoviSwot(naziv, ponder, 3);
				}
			});
		}
		return menuItem_2;
	}
	private JMenuItem getMenuItem_3() {
		if (menuItem_3 == null) {
			menuItem_3 = new JMenuItem("Obrisi");
			menuItem_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.obrisiSansu((String) Sanse.getValueAt(Sanse.getSelectedRow(), Sanse.getSelectedColumn()));
				}
			});
		}
		return menuItem_3;
	}
	private JPopupMenu getPopupMenu_3() {
		if (popupMenu_3 == null) {
			popupMenu_3 = new JPopupMenu();
			popupMenu_3.add(getMenuItem_4());
			popupMenu_3.add(getMenuItem_5());
			popupMenu_3.addPopupMenuListener(new PopupMenuListener() {
				
				@Override
				public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
					SwingUtilities.invokeLater(new Runnable() {
	                    @Override
	                    public void run() {
	                        int rowAtPoint = Pretnje.rowAtPoint(SwingUtilities.convertPoint(popupMenu_3, new Point(0, 0), Pretnje));
	                        if (rowAtPoint > -1) {
	                            Pretnje.setRowSelectionInterval(rowAtPoint, rowAtPoint);
	                            Pretnje.setColumnSelectionInterval(0, 0);
	                        }
	                    }
	                });
					
				}
				
				@Override
				public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void popupMenuCanceled(PopupMenuEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		return popupMenu_3;
	}
	private JMenuItem getMenuItem_4() {
		if (menuItem_4 == null) {
			menuItem_4 = new JMenuItem("Izmeni");
			menuItem_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String naziv = (String) Pretnje.getValueAt(Pretnje.getSelectedRow(), 0);
					String ponder = "" + Pretnje.getValueAt(Pretnje.getSelectedRow(), 1);
					Kontroler.napraviPopunjenProzorNoviSwot(naziv, ponder, 4);
				}
			});
		}
		return menuItem_4;
	}
	private JMenuItem getMenuItem_5() {
		if (menuItem_5 == null) {
			menuItem_5 = new JMenuItem("Obrisi");
			menuItem_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Kontroler.obrisiPretnju((String) Pretnje.getValueAt(Pretnje.getSelectedRow(), Pretnje.getSelectedColumn()));
				}
			});
		}
		return menuItem_5;
	}
}
