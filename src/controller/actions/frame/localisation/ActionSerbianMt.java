package controller.actions.frame.localisation;

import gui.frame.MainFrame;
import gui.frame.bar.StatusBar;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import controller.actions.AbstractActionPrototype;
import gui.frame.MainFrame;
import localisation.Loc;
import localisation.LocException;
import localisation.LocaliseName;
import model.tree.TreeModel;

/**
 * Klasa koja definise Apstraktnu Akciju za meni item Serbian.
 * Menja jezik aplikacije na Srpski
 * @author Ivana
 *
 */

@SuppressWarnings("serial")
public class ActionSerbianMt extends AbstractActionPrototype {

	public ActionSerbianMt(String title, String icon, String tooltip, String accelertor, int mnemonic) {
		super(title, icon, tooltip, accelertor, mnemonic);
		// TODO Auto-generated constructor stub
	}

	public ActionSerbianMt(String title, String icon, String tooltip, String accelertor) {
		super(title, icon, tooltip, accelertor);
		// TODO Auto-generated constructor stub
	}

	public ActionSerbianMt(String title, String icon, String tooltip) {
		super(title, icon, tooltip);
		// TODO Auto-generated constructor stub
	}
	
	public ActionSerbianMt(){
		Loc.getInstance().register(this, "putValue", 1, String.class, String.class, NAME, Object.class, "action.serbian");
		Loc.getInstance().register(this, "putValue", 1, Integer.class, String.class, MNEMONIC_KEY, Object.class,
				"action.serbian.mnemonic");
		Loc.getInstance().register(this, "putValue", 1, KeyStroke.class, String.class, ACCELERATOR_KEY, Object.class,
				"action.serbian.accel");
		Loc.getInstance().register(this, "putValue", 1, ImageIcon.class, String.class, SMALL_ICON, Object.class, "action.serbian.image");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String yes = "";
		String no = "";
		String desc = "";
		String delete_row="";
		LocaliseName yesL = new LocaliseName(yes);
		LocaliseName noL = new LocaliseName(no);
		LocaliseName descL = new LocaliseName(desc);
		LocaliseName delete_rowL = new LocaliseName(delete_row);
		Loc.getInstance().register(yesL, "setName", "yes");
		Loc.getInstance().register(noL, "setName", "no");
		Loc.getInstance().register(descL, "setName", "desc.close_tabs");
		Loc.getInstance().register(delete_rowL, "setName", "close_tabs.name");
		yes=yesL.getName();
		no= noL.getName();
		desc = descL.getName();
		delete_row = delete_rowL.getName();
		
		
		//lokalizovani option pane
		Object[] options = {yes, no};
		int response = JOptionPane.showOptionDialog(MainFrame.getInstance(), desc, delete_row, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
		null,   
		options,  //nazivi button-a
		options[0]); //defaultni naziv buttona
		
		if(response == JOptionPane.NO_OPTION){
			return;
		}
		
		
		
		Loc.getInstance().changeLanguage(new Locale("sr", "RS"), "localisation.bundle.bundle");
		MainFrame.getInstance().getStatusBar();
		StatusBar.setMessage5("Trenutni jezik: Srpski");
		DateFormat format = DateFormat.getDateInstance(DateFormat.LONG, Loc.getInstance().getLanguage());
		Date datum = new Date();
	
		StatusBar.setMessage4("Datum je: "+ format.format(datum));
	
		try {
			Loc.getInstance().localizeAndReload();
			MainFrame.getInstance().getMainTabHalf().closeAllTabs();
			MainFrame.getInstance().setTreePane();
			
			
			
		} catch (LocException e1) {
			e1.printStackTrace();
		}
	}

}
