package controller.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

/**
 * Klasa koja nasledjuje {@code AbstractAction} klasu, i modeluje nekoliko razlicitih konstruktora,
 * koji su na raspolaganju klasama koje nasledjuju ovu klasu.
 * 
 * @author NemanjaM
 */
@SuppressWarnings("serial")
public class AbstractActionPrototype extends AbstractAction {
	
	public AbstractActionPrototype(){
		super();
	}
	
	public AbstractActionPrototype(String title, String icon, String tooltip, String accelertor, int mnemonic) {
		super(title, new ImageIcon(icon));
		putValue(SHORT_DESCRIPTION, tooltip);
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, accelertor);
	}
	
	public AbstractActionPrototype(String title, String icon, String tooltip, String accelertor) {
		super(title, new ImageIcon(icon));
		putValue(SHORT_DESCRIPTION, tooltip);
		putValue(ACCELERATOR_KEY, accelertor);
	}
	
	public AbstractActionPrototype(String title, String icon, String tooltip) {
		super(title, new ImageIcon(icon));
		putValue(SHORT_DESCRIPTION, tooltip);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
