package controller.listeners;

import java.util.Vector;

import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import enums.TableLocation;
import gui.MainTabbedPane;
import gui.table.TableFrame;
import gui.table.TableToolbar;
import model.table.ActiveTablesModel;
import model.table.filter.FilterBottomTables;


/**
 * Klasa koja ima za primarni cilj detekciju selekcije redova u okviru tabele, i da na osnovu 
 * detektovane selekcije, filtrira sve torke u odnosu na parent torku.
 * @author Aleksandar
 * 
 */
public class TableSelectionListener implements ListSelectionListener {
	/**
	 * Trenuto selektovana tabela nad kojom se detektuju selekcije.
	 */
	private TableFrame selectedTable;
	
	private static boolean isFilterEnabled = false;

	
	public TableSelectionListener(TableFrame table) {
		this.selectedTable = table;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void valueChanged(ListSelectionEvent e) {
				
		if (e.getValueIsAdjusting() == true)
			return;

		// vodi racuna o tome ako je filtriran view, pa u zavisnosti od toga prati selekciju u modelu..
		try{
			selectedTable.getModel().setSelectedIdx(selectedTable.getTable().convertRowIndexToModel(selectedTable.getTable().getSelectedRow()));
			if(selectedTable.getTable().getSelectedRow() != -1)
				selectedTable.getTableToolbar().handleEmptyTable(true);
		} catch(Exception e1) {
			selectedTable.getTableToolbar().handleEmptyTable(false);
		}
		
		if(selectedTable.getTableLocation() == TableLocation.UPPER_TABLE)
			ActiveTablesModel.currentUpperTable = this.selectedTable.getModel();
		
		Vector<String> subTablesNames = this.selectedTable.getModel().getSubTables();
		
		// ako je parent, isfiltriraj na osnovu istog...
		if(subTablesNames.size() > 0 && selectedTable.getTableLocation() == TableLocation.UPPER_TABLE && selectedTable.getModel().getSelectedIdx() != -1) {
			FilterBottomTables filterBottomTables = new FilterBottomTables(this.selectedTable.getModel(), subTablesNames);
			filterBottomTables.filter();
		}		
	}

	public static boolean isFilterEnabled() {
		return isFilterEnabled;
	}

	public static void setFilterEnabled(boolean isFilterEnabledd) {
		isFilterEnabled = isFilterEnabledd;
	}
	
	
	
}
