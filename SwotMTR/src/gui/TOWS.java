package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logika.Strategija;
import logika.Swot;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class TOWS extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextArea textArea;
	private JTextArea textAreaS;
	private JTextArea textAreaW;
	private JTextArea textAreaO;
	private JTextArea textAreaSO;
	private JTextArea textAreaWO;
	private JTextArea textAreaT;
	private JTextArea textAreaST;
	private JTextArea textAreaWT;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	private JScrollPane scrollPane_6;
	private JScrollPane scrollPane_7;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TOWS frame = new TOWS();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TOWS(ArrayList<Swot> sanse, ArrayList<Swot> pretnje, ArrayList<Swot> snage,ArrayList<Swot> slabosti, ArrayList<Strategija> strategije) {
		setTitle("TOWS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		srediTekst();
		srediSanse(sanse);
		srediPretnje(pretnje);
		srediSnage(snage);
		srediSlabosti(slabosti);
		srediSO(sanse, snage, strategije);
		srediWO(sanse, slabosti, strategije);
		srediST(pretnje, snage, strategije);
		srediWT(pretnje, slabosti, strategije);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(3, 3, 0, 0));
			panel.add(getTextArea());
			panel.add(getScrollPane());
			panel.add(getScrollPane_1());
			panel.add(getScrollPane_2());
			panel.add(getScrollPane_3());
			panel.add(getScrollPane_4());
			panel.add(getScrollPane_5());
			panel.add(getScrollPane_6());
			panel.add(getScrollPane_7());
		}
		return panel;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}
	private JTextArea getTextAreaS() {
		if (textAreaS == null) {
			textAreaS = new JTextArea();
			textAreaS.setLineWrap(true);
		}
		return textAreaS;
	}
	private JTextArea getTextAreaW() {
		if (textAreaW == null) {
			textAreaW = new JTextArea();
			textAreaW.setLineWrap(true);
		}
		return textAreaW;
	}
	private JTextArea getTextAreaO() {
		if (textAreaO == null) {
			textAreaO = new JTextArea();
			textAreaO.setLineWrap(true);
		}
		return textAreaO;
	}
	private JTextArea getTextAreaSO() {
		if (textAreaSO == null) {
			textAreaSO = new JTextArea();
			textAreaSO.setLineWrap(true);
		}
		return textAreaSO;
	}
	private JTextArea getTextAreaWO() {
		if (textAreaWO == null) {
			textAreaWO = new JTextArea();
			textAreaWO.setLineWrap(true);
		}
		return textAreaWO;
	}
	private JTextArea getTextAreaT() {
		if (textAreaT == null) {
			textAreaT = new JTextArea();
			textAreaT.setLineWrap(true);
		}
		return textAreaT;
	}
	private JTextArea getTextAreaST() {
		if (textAreaST == null) {
			textAreaST = new JTextArea();
			textAreaST.setLineWrap(true);
		}
		return textAreaST;
	}
	private JTextArea getTextAreaWT() {
		if (textAreaWT == null) {
			textAreaWT = new JTextArea();
			textAreaWT.setLineWrap(true);
		}
		return textAreaWT;
	}
	
	private void srediTekst(){
		textAreaO.setText("Sanse:\n");
		textAreaW.setText("Slabosti:\n");
		textAreaS.setText("Snage:\n");
		textAreaT.setText("Pretnje:\n");
		textAreaWO.setText("WO Strategije:\n");
		textAreaSO.setText("SO Strategije:\n");
		textAreaWT.setText("WT Strategije:\n");
		textAreaST.setText("ST Strategije:\n");
	}
	
	private void srediSanse(List<Swot> sanse){
		for(Swot s : sanse){
			textAreaO.append("-" + s.getNaziv() + "\n");
		}
	}
	private void srediSnage(List<Swot> snage){
		for(Swot s : snage){
			textAreaS.append("-" + s.getNaziv() + "\n");
		}
	}
	private void srediSlabosti(List<Swot> slabosti){
		for(Swot s : slabosti){
			textAreaW.append("-" + s.getNaziv() + "\n");
		}
	}
	private void srediPretnje(List<Swot> pretnje){
		for(Swot s : pretnje){
			textAreaT.append("-" + s.getNaziv() + "\n");
		}
	}
	
	private void srediSO(List<Swot> sanse, List<Swot> snage, List<Strategija> strategije){
		String str = "";
		int i = 1;

		for(Strategija s : strategije){
			if(!s.getSanse().isEmpty() && !s.getSnage().isEmpty()){
				for(Swot swot : snage){
					if(s.getSnage().contains(swot))
						str = str + "S" + i +  ", ";
					i++;
				}
				i = 1;
				
				for(Swot swot : sanse){
					if(s.getSanse().contains(swot))
						str = str + "O" + i + ", ";
					i++;
				}
				str = str.substring(0, str.length()-2);
				textAreaSO.append("-" + s.getNaziv() + "(" + str + ")\n");
			}
		}
	}
	private void srediWO(List<Swot> sanse, List<Swot> slabosti, List<Strategija> strategije){
		String str = "";
		int i = 1;

		for(Strategija s : strategije){
			if(!s.getSanse().isEmpty() && !s.getSlabosti().isEmpty()){
				for(Swot swot : slabosti){
					if(s.getSlabosti().contains(swot))
						str = str + "W" + i +  ", ";
					i++;
				}
				i = 1;
				
				for(Swot swot : sanse){
					if(s.getSanse().contains(swot))
						str = str + "O" + i + ", ";
					i++;
				}
				str = str.substring(0, str.length()-2);
				textAreaWO.append("-" + s.getNaziv() + "(" + str + ")\n");
			}
		}
	}
	private void srediST(List<Swot> pretnje, List<Swot> snage, List<Strategija> strategije){
		String str = "";
		int i = 1;

		for(Strategija s : strategije){
			if(!s.getPretnje().isEmpty() && !s.getSnage().isEmpty()){
				for(Swot swot : snage){
					if(s.getSnage().contains(swot))
						str = str + "S" + i +  ", ";
					i++;
				}
				i = 1;
				
				for(Swot swot : pretnje){
					if(s.getPretnje().contains(swot))
						str = str + "T" + i + ", ";
					i++;
				}
				str = str.substring(0, str.length()-2);
				textAreaST.append("-" + s.getNaziv() + "(" + str + ")\n");
			}
		}
	}
	private void srediWT(List<Swot> pretnje, List<Swot> slabosti, List<Strategija> strategije){
		String str = "";
		int i = 1;

		for(Strategija s : strategije){
			if(!s.getPretnje().isEmpty() && !s.getSlabosti().isEmpty()){
				for(Swot swot : slabosti){
					if(s.getSlabosti().contains(swot))
						str = str + "W" + i +  ", ";
					i++;
				}
				i = 1;
				
				for(Swot swot : pretnje){
					if(s.getPretnje().contains(swot))
						str = str + "T" + i + ", ";
					i++;
				}
				str = str.substring(0, str.length()-2);
				textAreaWT.append("-" + s.getNaziv() + "(" + str + ")\n");
			}
		}
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextAreaS());
		}
		return scrollPane;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setViewportView(getTextAreaW());
		}
		return scrollPane_1;
	}
	private JScrollPane getScrollPane_2() {
		if (scrollPane_2 == null) {
			scrollPane_2 = new JScrollPane();
			scrollPane_2.setViewportView(getTextAreaO());
		}
		return scrollPane_2;
	}
	private JScrollPane getScrollPane_3() {
		if (scrollPane_3 == null) {
			scrollPane_3 = new JScrollPane();
			scrollPane_3.setViewportView(getTextAreaSO());
		}
		return scrollPane_3;
	}
	private JScrollPane getScrollPane_4() {
		if (scrollPane_4 == null) {
			scrollPane_4 = new JScrollPane();
			scrollPane_4.setViewportView(getTextAreaWO());
		}
		return scrollPane_4;
	}
	private JScrollPane getScrollPane_5() {
		if (scrollPane_5 == null) {
			scrollPane_5 = new JScrollPane();
			scrollPane_5.setViewportView(getTextAreaT());
		}
		return scrollPane_5;
	}
	private JScrollPane getScrollPane_6() {
		if (scrollPane_6 == null) {
			scrollPane_6 = new JScrollPane();
			scrollPane_6.setViewportView(getTextAreaST());
		}
		return scrollPane_6;
	}
	private JScrollPane getScrollPane_7() {
		if (scrollPane_7 == null) {
			scrollPane_7 = new JScrollPane();
			scrollPane_7.setViewportView(getTextAreaWT());
		}
		return scrollPane_7;
	}
}
