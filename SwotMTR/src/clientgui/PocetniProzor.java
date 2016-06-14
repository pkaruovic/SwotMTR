package clientgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontrola.Kontroler;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JButton;

public class PocetniProzor extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private JPanel panelSaDugmicima;
	//private FONPanel cp;

	

	/**
	 * Create the frame.
	 */
	public PocetniProzor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 50, 450, 300);

		contentPane = new JPanel();
		contentPane.paintComponents(getGraphics());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(getLabel());
		pack();
		
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			ImageIcon icon = new ImageIcon("/src/clientgui/Logotip-FON-Negativ-Eng.jpg");
			label.setIcon(new ImageIcon(PocetniProzor.class.getResource("/clientgui/Logotip-FON-Negativ-Eng.jpg")));
			label.setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
			label.setLayout(new BorderLayout());
//			JButton klijent = new JButton("Klijent");
//			JButton server = new JButton("Server");
//			klijent.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
//					Kontroler.otvoriKlijentProzor();
//					
//				}
//			});
//			
//			server.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
//					Kontroler.otvoriServerProzor();
//					
//				}
//			});
			label.add(getPanelSaDugmicima(), BorderLayout.SOUTH);
//			label.add(klijent);
//			label.add(server);
			label.setVisible(true);
		}
		return label;
	}
	
	private JPanel getPanelSaDugmicima(){
		if(panelSaDugmicima == null){
			panelSaDugmicima = new JPanel();
			panelSaDugmicima.setLayout(new FlowLayout());
			JButton klijent = new JButton("Klijent");
			JButton server = new JButton("Server");
			klijent.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.otvoriKlijentProzor();
					
				}
			});
			
			server.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Kontroler.otvoriServerProzor();
					
				}
			});
			
			panelSaDugmicima.add(klijent);
			panelSaDugmicima.add(server);
			panelSaDugmicima.setVisible(true);
		}
		return panelSaDugmicima;
	}
	
}
