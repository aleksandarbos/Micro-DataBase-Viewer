package controller.actions.table.updatedialog;

import java.awt.event.ActionEvent;
import java.util.Vector;

import controller.actions.AbstractActionPrototype;
import controller.database.DataBase;
import gui.RowUpdateDialog;

/**
 * Akcija koja se poziva iz dialoga vezanog za editovanje podataka trenutno selektovanog reda
 * u tabeli. Rezultat je momentalno zatvaranje otvorenog dialoga za azuriranje.
 * 
 * @author Aleksandar
 */
public class ActionCancelUpdateRow extends AbstractActionPrototype {
	
	private RowUpdateDialog rowUpdateDialog;
	
	public ActionCancelUpdateRow(String title, String icon, String tooltip, RowUpdateDialog rowUpdateDialog) {
		super(title, icon, tooltip);
		this.rowUpdateDialog = rowUpdateDialog;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.rowUpdateDialog.dispose();
		//System.out.println("closing dialog...");

	}
}