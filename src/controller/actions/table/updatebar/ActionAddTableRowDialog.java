package controller.actions.table.updatebar;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import controller.actions.AbstractActionPrototype;
import enums.TableLocation;
import enums.TableUpdateMode;
import gui.MainTabbedPane;
import gui.RowUpdateDialog;
import gui.table.TableToolbar;
import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;

/**
 * Akcija koja se poziva kada se aktivira dugme za dodavanje novog reda. Dugme koje je pozvano
 * se nalazi na toolbar-u, vezano je za operacije nad tabelom.
 * 
 * @author Aleksandar
 */
@SuppressWarnings("serial")
public class ActionAddTableRowDialog extends AbstractActionPrototype {

	public ActionAddTableRowDialog(String title, String icon, String tooltip, String accelertor, int mnemonic) {
		super(title, icon, tooltip, accelertor, mnemonic);
		// TODO Auto-generated constructor stub
	}

	public ActionAddTableRowDialog(String title, String icon, String tooltip, String accelertor) {
		super(title, icon, tooltip, accelertor);
		// TODO Auto-generated constructor stub
	}

	public ActionAddTableRowDialog(String title, String icon, String tooltip) {
		super(title, icon, tooltip);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton btnAddRow = (JButton) e.getSource();
		JToolBar toolbar = (JToolBar) btnAddRow.getParent();
		TableToolbar tableToolbar = (TableToolbar) toolbar.getParent();
		
		MyDefaultTableModel currentTableModel;
		
		if(tableToolbar.getTableLocation() == TableLocation.UPPER_TABLE){
			currentTableModel = ActiveTablesModel.getInstance().currentUpperTable;
		} else {		// bottom
			currentTableModel = ActiveTablesModel.getInstance().currentBottomTable;
		}

		RowUpdateDialog ruf = new RowUpdateDialog(currentTableModel ,  TableUpdateMode.ADD_ROW);
		ruf.setVisible(true);
	}

}
