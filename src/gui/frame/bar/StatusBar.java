package gui.frame.bar;

import java.awt.GridLayout;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import gui.frame.MainFrame;
import localisation.Loc;
import localisation.LocException;

@SuppressWarnings("serial")
public class StatusBar extends JPanel {
	private static JLabel statLab1 = new JLabel();
	private static JLabel statLab2 = new JLabel();
	private static JLabel statLab3 = new JLabel();
	private static JLabel statLab4 = new JLabel();
	private static JLabel statLab5 = new JLabel();
	
	
	public static JLabel getStatLab1() {
		return statLab1;
	}

	public static void setStatLab1(JLabel statLab1) {
		StatusBar.statLab1 = statLab1;
	}

	public static JLabel getStatLab2() {
		return statLab2;
	}

	public static void setStatLab2(JLabel statLab2) {
		StatusBar.statLab2 = statLab2;
	}

	public static JLabel getStatLab3() {
		return statLab3;
	}

	public static void setStatLab3(JLabel statLab3) {
		StatusBar.statLab3 = statLab3;
	}

	public static JLabel getStatLab4() {
		return statLab4;
	}

	public static void setStatLab4(JLabel statLab4) {
		StatusBar.statLab4 = statLab4;
	}

	public static JLabel getStatLab5() {
		return statLab5;
	}

	public static void setStatLab5(JLabel statLab5) {
		StatusBar.statLab5 = statLab5;
	}
	/**
	 * Metoda koja namesta status bar.
	 * U trećem polju se nalazi informacija o datumu zapisana na na�?in 
	 * na koji se �?ini vezano za aktuelni jezik.
	 * U �?etvrtm polju se nalazi informacija o trenutno aktivnom jeziku
	 * 
	 * @author Ivana
	 */
	public StatusBar() {
		super();
        setLayout(new GridLayout(1,5,1,1));
        Loc.getInstance().registerSetText(statLab1, "statusbar.team");
      
        statLab1.setHorizontalAlignment(SwingConstants.CENTER);
        statLab1.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statLab1);

        statLab2.setText("Random1");
        statLab2.setHorizontalAlignment(SwingConstants.CENTER);
        statLab2.setBorder(BorderFactory.createLoweredBevelBorder());
        add(statLab2);

        statLab3.setText("Random2");
        statLab3.setHorizontalAlignment(SwingConstants.CENTER);
        statLab3.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statLab3);

        statLab4.setHorizontalAlignment(SwingConstants.CENTER);
        statLab4.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statLab4);

        if(Loc.getInstance().getLanguage().toString().equals("en_US")){
        	statLab5.setText("Current language: English");
        	DateFormat format = DateFormat.getDateInstance(DateFormat.LONG, Loc.getInstance().getLanguage());
    		Date datum = new Date();
    	
    		statLab4.setText("The Date is: "+ format.format(datum));
        	MainFrame.getInstance();
        } else if (Loc.getInstance().getLanguage().toString().equals("sr_RS")){
        	statLab5.setText("Trenutni jezik je: Srpski");
        	DateFormat format = DateFormat.getDateInstance(DateFormat.LONG, Loc.getInstance().getLanguage());
    		Date datum = new Date();
    	
    		statLab4.setText("Datum je: "+ format.format(datum));;
    	
    		try {
    			Loc.getInstance().localizeAndReload();
    		} catch (LocException e1) {
    			e1.printStackTrace();
    		}
        	this.updateUI();
        } 
        
        statLab5.setHorizontalAlignment(SwingConstants.CENTER);
        statLab5.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statLab5);
	}
	
	public static void setMessage1(String message) {
		statLab1.setText(message);
	}
	public static void setMessage2(String message) {
		statLab2.setText(message);
	}
	public static void setMessage3(String message) {
		statLab3.setText(message);
	}
	public static void setMessage4(String message) {
		statLab4.setText(message);
	}
	public static void setMessage5(String message) {
		statLab5.setText(message);
	}
}
