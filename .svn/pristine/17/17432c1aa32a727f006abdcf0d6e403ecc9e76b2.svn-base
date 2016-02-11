package controller.actions.table.updatebar;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import controller.actions.AbstractActionPrototype;
import controller.database.DataBase;
import enums.TableLocation;
import gui.MainTabbedPane;
import gui.RowUpdateDialog;
import gui.frame.MainFrame;
import gui.table.TableFrame;
import gui.table.TableToolbar;
import localisation.Loc;
import localisation.LocaliseName;
import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;


/**
 * Akcija koja se poziva iz glavnog prozora aplikacije, za trenutno aktivnu tabelu
 * Rezultat je pokusaj brisanja trenutno selektovanog reda u aktivnoj tabeli iz baze podataka.
 * 
 * @author Aleksandar
 */
public class ActionDeleteTableRow extends AbstractActionPrototype {

	public ActionDeleteTableRow(String title, String icon, String tooltip, String accelertor, int mnemonic, RowUpdateDialog rowUpdateDialog) {
		super(title, icon, tooltip, accelertor, mnemonic);
		// TODO Auto-generated constructor stub
	}

	public ActionDeleteTableRow(String title, String icon, String tooltip, String accelertor) {
		super(title, icon, tooltip, accelertor);
		// TODO Auto-generated constructor stub
	}

	public ActionDeleteTableRow(String title, String icon, String tooltip) {
		super(title, icon, tooltip);
		// TODO Auto-generated constructor stub
	}


	
	@Override
	public void actionPerformed(ActionEvent e) {
		//deo za lokalizaciju
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
		Loc.getInstance().register(descL, "setName", "desc.delete_row");
		Loc.getInstance().register(delete_rowL, "setName", "delete_row.name");
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
		
		
		if  (response != 0)
			return;
		
		//DataBase
		JButton btnDeleteRow = (JButton) e.getSource();
		JToolBar toolbar = (JToolBar) btnDeleteRow.getParent();
		TableToolbar tableToolbar = (TableToolbar) toolbar.getParent();

		MyDefaultTableModel currentTableModel;
		String tableName;
		Vector<String> tableHeaders;
		Vector<String> tableRow;
		TableFrame tableFrame;
		
		if(tableToolbar.getTableLocation() == TableLocation.UPPER_TABLE){
			currentTableModel = ActiveTablesModel.getInstance().currentUpperTable;
		} else {		// bottom
			currentTableModel = ActiveTablesModel.getInstance().currentBottomTable;
		}
		
		
		tableHeaders = currentTableModel.getHeaders();
		tableRow = (Vector<String>) currentTableModel.getDataVector().get(currentTableModel.getSelectedIdx());
		tableName = currentTableModel.getTableName();
		
		if(currentTableModel.getTableLocation() == TableLocation.UPPER_TABLE)
			 tableFrame= MainTabbedPane.getUpperTableFrameByName(tableName);
		else
			tableFrame= MainTabbedPane.getBottomTableFrameByName(ActiveTablesModel.currentUpperTable.getTableName(), tableName);

		
		if(DataBase.getInstance().deleteFromDatabase(tableName, tableHeaders, tableRow)) {		// pisi na server
			currentTableModel.getDataVector().remove(currentTableModel.getSelectedIdx());	// dodaj novi unos na kraj
			currentTableModel.fireTableRowsDeleted(currentTableModel.getSelectedIdx(), currentTableModel.getSelectedIdx());		// obavesti da je doslo do izmene podataka
			if(tableFrame.getTable().getRowCount() == 0)
				tableFrame.getTableToolbar().handleEmptyTable(false);
		}

	}

}
