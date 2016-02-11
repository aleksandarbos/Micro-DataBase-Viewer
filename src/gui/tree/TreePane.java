package gui.tree;

import javax.swing.JTree;

import controller.listeners.TreeMouseListener;

/**
 * Klasa kojom dodajemo listener i renderer na stablo.
 * 
 */

@SuppressWarnings("serial")
public class TreePane extends JTree {

	public TreePane() {
		setToggleClickCount(0);
		addMouseListener(new TreeMouseListener(this));		// dodavane listener-a za upravljanje klikovima na stablo
		setCellRenderer(new TreeCellRenderer());
	}	
}
