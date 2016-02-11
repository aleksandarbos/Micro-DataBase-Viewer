package controller.actions.frame;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import controller.actions.AbstractActionPrototype;
import localisation.Loc;

public class ActionNewMt extends AbstractActionPrototype{

	/**
	 * Klasa koja definise Apstraktnu Akciju za meni item New.
	 * @author Ivana
	 *
	 */
	private static final long serialVersionUID = 2266679465320252791L;

	public ActionNewMt(){
		
		Loc.getInstance().register(this, "putValue", 1, String.class, String.class, NAME, Object.class, "action.new");
		Loc.getInstance().register(this, "putValue", 1, Integer.class, String.class, MNEMONIC_KEY, Object.class,
				"action.new.mnemonic");
	//	Loc.getInstance().register(this, "putValue", 1, KeyStroke.class, String.class, ACCELERATOR_KEY, Object.class,
			//	"action.new.accel");
		Loc.getInstance().register(this, "putValue", 1, ImageIcon.class, String.class, SMALL_ICON, Object.class, "action.new.image");
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
	}
}
