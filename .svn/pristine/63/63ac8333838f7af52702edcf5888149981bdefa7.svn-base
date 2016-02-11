package controller.actions.frame;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import gui.MainTabbedPane;
import gui.table.TableFrame;
import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;
import model.table.filter.FilterBottomTables;
import model.table.filter.patterns.RowByKeyFilter;

/**
 * Klasa koja modeluje poslednje dugme iz trake sa alatima. Uloga ove klase je da tokom razvoja aplikacije
 * omoguci testiranje razlicitih akcija, koje ce biti pokrenute akcijom ove klase.
 * Ova klasa nece postojati u zavrsnom kodu.
 * 
 * @author NemanjaM
 */
@SuppressWarnings("serial")
public class ActionRandom extends AbstractAction {
	
	public ActionRandom(String title, String icon, String tooltip, String accelertor, int mnemonic) {
		super(title, new ImageIcon(icon));
		putValue(SHORT_DESCRIPTION, tooltip);
		putValue(MNEMONIC_KEY, mnemonic);
		putValue(ACCELERATOR_KEY, accelertor);
	}
	
	public ActionRandom(String title, String icon, String tooltip, String accelertor) {
		super(title, new ImageIcon(icon));
		putValue(SHORT_DESCRIPTION, tooltip);
		putValue(ACCELERATOR_KEY, accelertor);
	}
	
	public ActionRandom(String title, String icon, String tooltip) {
		super(title, new ImageIcon(icon));
		putValue(SHORT_DESCRIPTION, tooltip);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*Integer value = (int) (Math.random()*10000);
		Vector<String> headers = new Vector<String>();
		headers.addElement("DR_OZNAKA");
		headers.addElement("DUR_OZNAKA");
		headers.addElement("DRZ_DR_OZNAKA");
		headers.addElement("NAS_DR_OZNAKA");
		headers.addElement("NM_IDENTIFIKATOR");
		headers.addElement("DR_NAZIV");
		Vector<String> data = new Vector<String>();
		data.addElement("%s%");
		data.addElement(null);
		data.addElement(null);
		data.addElement(null);
		data.addElement(null);
		data.addElement(null);
		data.addElement(null);*/
//		data.addElement("srb");
//		data.addElement(value.toString());
//		data.addElement("647");
//		data.addElement("Veternik-pasuljiste");
//		data.addElement(null);
		//update NASELJENO_MESTO set NM_NAZIV = 'Veternik', NM_PTT_OZNAKA = null where DR_OZNAKA = 'srb' and NM_IDENTIFIKATOR = '640'
//		DataBase.getInstance().insertToDatabase("DESTINACIJA", headers, data);
		//DataBase.getInstance().getPrimaryKeys("NASELJENO_MESTO");
		
//		DataBase.getInstance().deleteFromDatabase("NASELJENO_MESTO", headers, data);
		
//		DataBase.getInstance().filterTableData(null, "NASELJENO_MESTO", headers, data);
//		String type = DataBase.getInstance().getColumnType("AKT_O_ORGANIZACIJI", "AO_REDNI_BROJ2").toString();

//		DataBase.getInstance().filterTableData(null, "DRZAVA", headers, data);
		//int year, int month, int date, int hrs, int min, int sec
		//String date = DataBase.getInstance().parseDate(new Date(2010,10,10,10,10,10));
		//System.out.println("datum " + date);
		
		//ActiveTablesModel.getInstance().getCurrentUpperTable().addRow(new Object[]{"a","a","a"});
		/*
		MyDefaultTableModel upperModel = ActiveTablesModel.getInstance().getCurrentUpperTable();
		MyDefaultTableModel model = ActiveTablesModel.getInstance().getCurrentBottomTable();

		ArrayList<RowByKeyFilter> filters = new ArrayList<>();
		
		RowByKeyFilter rowByKeysFilter = new RowByKeyFilter("Naziv naseljenog mesta", "s");
		RowByKeyFilter rowByKeyFilter1 = new RowByKeyFilter("PTT oznaka", "000");
		
		filters.add(rowByKeysFilter);
		filters.add(rowByKeyFilter1);
		
		RowFilter<MyDefaultTableModel, Integer> andFilter = RowFilter.andFilter(filters);

		
		TableRowSorter<MyDefaultTableModel> tableRowSorter = new TableRowSorter<MyDefaultTableModel>(model);
		
		TableFrame table = MainTabbedPane.getBottomTableFrameByName(upperModel.getTableName(), "NASELJENO_MESTO");
		
		tableRowSorter.setRowFilter(andFilter);
		table.getTable().setRowSorter(tableRowSorter);
		
		for(int i = 0; i < table.getTable().getRowCount(); i++) {
			System.out.println(table.getModel().getDataVector().get(table.getTable().convertRowIndexToModel(i)));
		}*/
		/*TableFrame upperTableFrame = MainTabbedPane.getUpperTableFrameByName(ActiveTablesModel.currentUpperTable.getTableName());
		System.out.println("active tables model idx: " + ActiveTablesModel.currentUpperTable.getSelectedIdx());
		System.out.println("active tables model idx + view: " + upperTableFrame.getTable().convertColumnIndexToModel(ActiveTablesModel.currentUpperTable.getSelectedIdx()));
		ActiveTablesModel.currentUpperTable.setSelectedIdx(upperTableFrame.getTable().convertColumnIndexToModel(upperTableFrame.getTable().getSelectedRow()));
		System.out.println("active tableframe tableselection idx: " + upperTableFrame.getTable().getSelectedRow());
		System.out.println("active tables converted model idx: " +  ActiveTablesModel.currentUpperTable.getSelectedIdx());
		if(ActiveTablesModel.currentUpperTable.getSubTables().size() > 0) {
			FilterBottomTables filterBottomTables = new FilterBottomTables(ActiveTablesModel.currentUpperTable, ActiveTablesModel.currentUpperTable.getSubTables());
			filterBottomTables.filter();
		}*/
		
		//System.exit(0);
//		System.out.println(type);
	}
	
}
