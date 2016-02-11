package controller.actions.frame;

import java.awt.event.ActionEvent;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.actions.AbstractActionPrototype;
import gui.frame.MainFrame;
import localisation.Loc;
import localisation.LocaliseName;
import main.Main;

/**
 * Klasa koja definise Apstraktnu Akciju za meni item Exit.
 * Izlazi iz aplikacije.
 * @author Ivana
 *
 */

@SuppressWarnings("serial")
public class ActionExitMt extends AbstractActionPrototype{

	/**
	 * Konstruktor postavlja lokalizovane parametere za ovu apstraktnu akciju
	 * 
	 */

	public ActionExitMt(){
		
		Loc.getInstance().register(this, "putValue", 1, String.class, String.class, NAME, Object.class, "action.exit");

	//	Loc.getInstance().register(this, "putValue", 1, Integer.class, String.class, MNEMONIC_KEY, Object.class,
	//			"action.new.mnemonic");
	//	Loc.getInstance().register(this, "putValue", 1, KeyStroke.class, String.class, ACCELERATOR_KEY, Object.class,
			//	"action.new.accel");
		Loc.getInstance().register(this, "putValue", 1, ImageIcon.class, String.class, SMALL_ICON, Object.class, "action.exit.image");
	}
	
	/**
	 * Metoda koja poziva dijalog za potvrdu izlaska iz aplikacije
	 * @param ActionEvent e  koji je dogadjaj koji je prouzrokovao ovu akciju
	 * 
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String ok = "";
		String cancel = "";
		String desc = "";
		String close="";
		LocaliseName yesL = new LocaliseName(ok);
		LocaliseName noL = new LocaliseName(cancel);
		LocaliseName descL = new LocaliseName(desc);
		LocaliseName closeL = new LocaliseName(close);
		Loc.getInstance().register(yesL, "setName", "ok");
		Loc.getInstance().register(noL, "setName", "cancel");
		Loc.getInstance().register(descL, "setName", "desc.close_app");
		Loc.getInstance().register(closeL, "setName", "desc.close");
		ok=yesL.getName();
		cancel= noL.getName();
		desc = descL.getName();
		close = closeL.getName();
		
		
		//lokalizovani option pane
		Object[] options = {ok, cancel};
		int response = JOptionPane.showOptionDialog(MainFrame.getInstance(), desc, close, JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
		null,   
		options,  //nazivi button-a
		options[0]); //defaultni naziv buttona
		
		
		if (response == JOptionPane.OK_OPTION) {
			Main.exitApplication();
		} else if (response == JOptionPane.CANCEL_OPTION) {
			return;
		} 
		

	}
}
