package controller.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import gui.frame.MainFrame;
import localisation.Loc;
import localisation.LocaliseName;
import main.Main;

/**
 * Klasa koja modeluje osluskivanje dogadjaja u okviru glavnog prozora aplikacije i poziva odgovarajuce metode.
 * 
 * @author NemanjaM, Ivana
 *
 */
public class MainFormListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Metoda se poziva prilikom kliktanja dugmeta za zatvaranje aplikacije.<br/>
	 * Korisniku ce biti ponudjeno da potvrdi zadvaranje aplikacije ili da od njega odustane. Ukoliko korsinik potvrdi zatvaranje aplikacije
	 * svi resursi korisceni od strane aplikacije bice uredno zatvoreni, a rad sa aplikacijom prekinut.
	 */
	public void windowClosing(WindowEvent e) {
		//deo za lokalizaciju
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

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
