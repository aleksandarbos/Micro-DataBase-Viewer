package gui.table;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.actions.table.updatebar.ActionAddTableRowDialog;
import controller.actions.table.updatebar.ActionClearTableFilter;
import controller.actions.table.updatebar.ActionDeleteTableRow;
import controller.actions.table.updatebar.ActionEditTableRowDialog;
import controller.actions.table.updatebar.ActionFilterTableDialog;
import enums.TableLocation;
import localisation.Loc;

@SuppressWarnings("serial")
public class TableToolbar extends JPanel {
	private String name;
    private JPanel tabelaPanel;
	private JToolBar toolbar;
	private TableFrame tableFrame;
	
	private JButton btEdit;
	private JButton btDelete;
	private JButton btAdd;
	private JButton btFilter;
	private JButton btClearFilter;
	
	private TableLocation tableLocation;

	
	public TableToolbar(String name, TableLocation tableLoc){
		super(new BorderLayout());
		this.name = name;
		this.tableLocation = tableLoc;
		setToolbar();
		this.add(toolbar, BorderLayout.NORTH);
		
		tabelaPanel = new JPanel();
		this.add(tabelaPanel, BorderLayout.CENTER);
	
	}
	
	/**
	 * Postavlja toolbar za editovanje tabela koji je lokalizovan
	 * 
	 * @return void
	 * @author Aleksandar, Ivana
	 * 
	 */
	private void setToolbar(){
		this.toolbar = new JToolBar();
		btAdd = new JButton(new ActionAddTableRowDialog(null, "images/ico/addRow.png", null));
		btFilter = new JButton(new ActionFilterTableDialog(null, "images/ico/filter.png",null));
		btClearFilter = new JButton(new ActionClearTableFilter(null, "images/ico/clearFilter.png", null));
		btEdit = new JButton(new ActionEditTableRowDialog(null, "images/ico/editRow.png", null));
		btDelete = new JButton(new ActionDeleteTableRow(null, "images/ico/deleteRow.png", null));
		
		Loc.getInstance().registerSetText(btAdd, "action.add");
		Loc.getInstance().registerSetText(btDelete, "action.delete");
		Loc.getInstance().registerSetText(btEdit, "action.edit");
		Loc.getInstance().registerSetText(btFilter, "filter");
		Loc.getInstance().registerSetText(btClearFilter, "filter.clear_filter");
		
		Loc.getInstance().registerSetTooltipText(btAdd, "action.add.tooltip");
		Loc.getInstance().registerSetTooltipText(btDelete, "action.delete.tooltip");
		Loc.getInstance().registerSetTooltipText(btEdit, "action.edit.tooltip");
		Loc.getInstance().registerSetTooltipText(btFilter, "filter.toolbar.tooltip");
		Loc.getInstance().registerSetTooltipText(btClearFilter, "filter.clear_filter.tooltip");
		
		this.toolbar.add(btAdd);
		this.toolbar.add(btDelete);
		this.toolbar.add(btEdit);
		
		if(tableLocation == tableLocation.UPPER_TABLE) {
			this.toolbar.addSeparator();
			this.toolbar.add(btFilter);
			this.toolbar.add(btClearFilter);
		}
		
		this.toolbar.setFloatable(false);
		
	}

	public JPanel getTabelaPanel() {
		return tabelaPanel;
	}

	public void setTabelaPanel(JPanel tabelaPanel) {
		this.tabelaPanel = tabelaPanel;
	}

	public JToolBar getToolbar() {
		return toolbar;
	}
	
	public void setToolbar(JToolBar toolbar) {
		this.toolbar = toolbar;
	}

	public JButton getBtEdit() {
		return btEdit;
	}

	public void setBtEdit(JButton btEdit) {
		this.btEdit = btEdit;
	}

	public JButton getBtDelete() {
		return btDelete;
	}

	public void setBtDelete(JButton btDelete) {
		this.btDelete = btDelete;
	}
	
	/**
	 * Metoda koja gasi sve dugmice u zavisnosti od toga da li ima elemenata u tabeli. Ukoliko
	 * nema, gase se dugmici Filter, Edit, Delete, i Clear Filter.
	 */
	public void handleEmptyTable(boolean value) {
		btClearFilter.setEnabled(value);
		btDelete.setEnabled(value);
		btFilter.setEnabled(value);
		btEdit.setEnabled(value);
	}
	
	
	public void addTableFrame(TableFrame table) {
		this.tableFrame = table;
		add(this.tableFrame, BorderLayout.CENTER);
	}

	public TableFrame getTableFrame() {
		return tableFrame;
	}

	public TableLocation getTableLocation() {
		return tableLocation;
	}

	public void setTableLocation(TableLocation tableLocation) {
		this.tableLocation = tableLocation;
	}
	
	
}
