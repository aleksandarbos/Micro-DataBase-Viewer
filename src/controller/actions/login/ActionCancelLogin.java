package controller.actions.login;

import java.awt.event.ActionEvent;

import controller.actions.AbstractActionPrototype;

/**
 * Klasa koja obradjuje akciju otkazivanja prijave prilikom unosenja podataka za prijavu na sistem.
 * Rezultat delovanja je momentalno gasenje aplikacije.
 * 
 * @author Aleksandar
 */
@SuppressWarnings("serial")
public class ActionCancelLogin extends AbstractActionPrototype {
	
	public ActionCancelLogin(String title, String icon, String tooltip) {
		super(title, icon, tooltip);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
