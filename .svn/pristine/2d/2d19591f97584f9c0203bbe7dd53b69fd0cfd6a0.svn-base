package controller.actions.table.updatedialog;

import java.awt.event.ActionEvent;
import java.util.Vector;

import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;
import model.table.row.ColumnEntryPair;
import model.table.row.RowUpdateModel;
import controller.actions.AbstractActionPrototype;
import controller.database.DataBase;
import enums.TableLocation;
import gui.MainTabbedPane;
import gui.RowUpdateDialog;
import gui.table.TableFrame;
import main.Main;

/**
 * Akcija koja se poziva iz dialoga vezanog za izmenu podataka trenutno selektovanog reda
 * u tabeli. Rezultat je pokusaj izmene vrednosti trenutno selektovanog reda u bazi podataka.
 * 
 * @author Aleksandar
 */
public class ActionEditTableRow extends AbstractActionPrototype {
	
	RowUpdateDialog rowUpdateDialog;
	
	public ActionEditTableRow(String title, String icon, String tooltip, RowUpdateDialog rowUpdateDialog) {
		super(title, icon, tooltip);
		
		this.rowUpdateDialog = rowUpdateDialog;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Vector<String> data = rowUpdateDialog.getData();
		Vector<String> headers = rowUpdateDialog.getHeaders();
		String tableName = rowUpdateDialog.getActiveTableModel().getTableName();
		MyDefaultTableModel currentTable = ActiveTablesModel.getInstance().getTableModelByName(tableName);
		
		RowUpdateModel rowUpdateModel = new RowUpdateModel(currentTable, this.rowUpdateDialog.getEnteredData());
		@SuppressWarnings("unused")
		Vector<Object> entryData = rowUpdateModel.getEnteredValues();		// torka objekata u izvornom obliku
		@SuppressWarnings("unused")
		Vector<ColumnEntryPair> pairEntryData = rowUpdateModel.getEnteredValuePairs();		// isto kao i ovo gore, samo sa nazivom kolone i tipom ColumnType u sebi
		

		//DataBase
		//if(DataBase.getInstance().updateInDatabase(tableName, headers, data)) {		// pisi na server
		if(DataBase.getInstance().updateInDatabase(tableName, headers, entryData)) {		// pisi na server
			currentTable.insertRow(currentTable.getSelectedIdx(), data);
			try{
				currentTable.removeRow(currentTable.getSelectedIdx()); 				// obrisi staru vrednost..
			} catch(Exception e1) {				
			}
			this.rowUpdateDialog.dispose();		// zatvori prozor
		}
	}
}