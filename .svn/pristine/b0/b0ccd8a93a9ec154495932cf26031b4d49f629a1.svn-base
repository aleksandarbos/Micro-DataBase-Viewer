package controller.actions.frame;

import java.awt.event.ActionEvent;


import javax.swing.ImageIcon;

import controller.actions.AbstractActionPrototype;

import localisation.Loc;

/**
 * Klasa koja definise Apstraktnu Akciju za meni item Help.
 * @author Ivana
 *
 */

@SuppressWarnings("serial")
public class ActionHelpMt extends AbstractActionPrototype{

	/**
	 * 
	 */

	public ActionHelpMt(){
		
		Loc.getInstance().register(this, "putValue", 1, String.class, String.class, NAME, Object.class, "action.helpmt");
	//	Loc.getInstance().register(this, "putValue", 1, Integer.class, String.class, MNEMONIC_KEY, Object.class,
	//			"action.new.mnemonic");
	//	Loc.getInstance().register(this, "putValue", 1, KeyStroke.class, String.class, ACCELERATOR_KEY, Object.class,
			//	"action.new.accel");
		Loc.getInstance().register(this, "putValue", 1, ImageIcon.class, String.class, SMALL_ICON, Object.class, "action.helpmt.image");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
