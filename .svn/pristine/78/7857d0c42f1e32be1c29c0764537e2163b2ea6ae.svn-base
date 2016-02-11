package controller.actions.table.updatedialog;

import java.awt.event.ActionEvent;
import java.util.Vector;

import controller.actions.AbstractActionPrototype;
import controller.database.DataBase;
import enums.TableLocation;
import gui.MainTabbedPane;
import gui.RowUpdateDialog;
import gui.table.TableFrame;
import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;
import model.table.row.ColumnEntryPair;
import model.table.row.RowUpdateModel;

/**
 * Akcija koja se poziva iz dialoga vezanog za dodavanje podataka u trenutno selektovanu
 * tabelu. Rezultat je pokusaj dodavanja novog reda u bazu podataka.
 * 
 * @author Aleksandar
 */
public class ActionAddTableRow extends AbstractActionPrototype {
	
	RowUpdateDialog rowUpdateDialog;
	
	public ActionAddTableRow(String title, String icon, String tooltip, RowUpdateDialog rowUpdateDialog) {
		super(title, icon, tooltip);
		
		this.rowUpdateDialog = rowUpdateDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Vector<String> data = rowUpdateDialog.getData();
		Vector<String> headers = rowUpdateDialog.getHeaders();
		String tableName = rowUpdateDialog.getActiveTableModel().getTableName();
		MyDefaultTableModel currentTable = ActiveTablesModel.getInstance().getTableModelByName(tableName);
		
		RowUpdateModel rowUpdateModel = new RowUpdateModel(currentTable, this.rowUpdateDialog.getEnteredData());
		Vector<Object> entryData = rowUpdateModel.getEnteredValues();		// torka objekata u izvornom obliku
		@SuppressWarnings("unused")
		Vector<ColumnEntryPair> pairEntryData = rowUpdateModel.getEnteredValuePairs();		// isto kao i ovo gore, samo sa nazivom kolone i tipom ColumnType u sebi
		TableFrame tableFrame;
		
		if(currentTable.getTableLocation() == TableLocation.UPPER_TABLE)
			 tableFrame= MainTabbedPane.getUpperTableFrameByName(tableName);
		else
			tableFrame= MainTabbedPane.getBottomTableFrameByName(ActiveTablesModel.currentUpperTable.getTableName(), tableName);
		
		//DataBase
		if(DataBase.getInstance().insertToDatabase(tableName, headers, entryData)) {		// pisi na server
			currentTable.insertRow(currentTable.getRowCount(), data);	// dodaj novi unos na kraj
			if(tableFrame.getTable().getRowCount() > 0)
				tableFrame.getTableToolbar().handleEmptyTable(true);
			this.rowUpdateDialog.dispose();		// zatvori prozor
		}
	}
}