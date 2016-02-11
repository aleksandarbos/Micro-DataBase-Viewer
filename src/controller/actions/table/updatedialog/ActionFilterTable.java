package controller.actions.table.updatedialog;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;

import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;
import model.table.filter.FilterUpperTable;
import model.table.filter.patterns.RowByKeyFilter;
import model.table.row.ColumnEntryPair;
import model.table.row.RowUpdateModel;
import controller.actions.AbstractActionPrototype;
import controller.database.DataBase;
import controller.listeners.TableSelectionListener;
import gui.RowUpdateDialog;

/**
 * Akcija koja se poziva iz dialoga vezanog za filtriranje podataka u trenutnoj selektovanoj
 * tabeli. Rezultat akcije je izmenjena tabela sa svim onim torkama koje zadovoljavaju postavljene uslove u filter formi.
 * 
 * @author Aleksandar
 */
public class ActionFilterTable extends AbstractActionPrototype {
	
	RowUpdateDialog rowUpdateDialog;
	
	public ActionFilterTable(String title, String icon, String tooltip, RowUpdateDialog rowUpdateDialog) {
		super(title, icon, tooltip);
		
		this.rowUpdateDialog = rowUpdateDialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MyDefaultTableModel currentTableModel = this.rowUpdateDialog.getActiveTableModel();

		Vector<ColumnEntryPair> values = this.rowUpdateDialog.getEnteredData();
		RowUpdateModel rowUpdateModel = new RowUpdateModel(currentTableModel, values);
		//Vector<Object> filterEntry = rowUpdateModel.getEnteredValues(); cista torka..
		Vector<ColumnEntryPair> filterEntryPairs = rowUpdateModel.getEnteredValuePairs();

		FilterUpperTable filterUpperTable = new FilterUpperTable(currentTableModel, filterEntryPairs);
		filterUpperTable.filter();
		
		this.rowUpdateDialog.dispose();
	}
}