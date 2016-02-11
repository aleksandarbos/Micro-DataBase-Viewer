package controller.actions.table.updatebar;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.TableRowSorter;

import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;
import model.table.filter.patterns.ResetFilter;
import controller.actions.AbstractActionPrototype;
import controller.database.DataBase;
import controller.listeners.TableSelectionListener;
import gui.MainTabbedPane;
import gui.RowUpdateDialog;
import gui.table.TableFrame;

/**
 * Predstavlja akciju ciscenja svakog trenutno postavljenog filtera na aktivnu parent tabelu.
 * 
 * @author Aleksandar
 */
public class ActionClearTableFilter extends AbstractActionPrototype {
	
	public ActionClearTableFilter(String title, String icon, String tooltip) {
		super(title, icon, tooltip);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		TableRowSorter<MyDefaultTableModel> tableRowSorter;
		TableFrame tableFrame;
		MyDefaultTableModel currentTableModel; 
		
		currentTableModel = ActiveTablesModel.getInstance().currentUpperTable;
		
		tableRowSorter = new TableRowSorter<MyDefaultTableModel>(currentTableModel);
		tableRowSorter.setRowFilter(new ResetFilter());
		tableFrame = MainTabbedPane.getUpperTableFrameByName(currentTableModel.getTableName());
		
		tableFrame.getTable().setRowSorter(tableRowSorter);
		
	}
}