package model.table.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import enums.ColumnType;
import gui.MainTabbedPane;
import gui.RowUpdateDialog;
import gui.table.TableFrame;
import model.table.MyDefaultTableModel;
import model.table.filter.patterns.RowByAlphabetFilter;
import model.table.filter.patterns.RowByDateFilter;
import model.table.filter.patterns.RowByDecimalFilter;
import model.table.filter.patterns.RowByIntegerFilter;
import model.table.row.ColumnEntryPair;

/**
 * Klasa koja ima za cilj da filtria podatke u zavisnost od toga sta je korisnik uneo
 * u formu za filtriranje - {@link RowUpdateDialog}. Na osnovu toga se formira model
 * {@link RowUpdateModel} i vrsi filtriranje na osnovu unesenih podataka i lokalizacije.
 * @author Aleksandar
 *
 */
public class FilterUpperTable extends FilterTable {
	/**
	 * Model na osnovu kog se vrsi filtriranje prikaza.
	 */
	private MyDefaultTableModel upperModel;
	/**
	 * Prikazna komponenta tabele u kojoj ce se vrsiti izmena prikaza torki(filtriranje).
	 */
	private TableFrame tableFrame;
	/**
	 * Kriterijumi po kojima se vrsti filtriranje.
	 */
	private Vector<ColumnEntryPair> criterias;
	private boolean isTesting = false; 		// u slucaju testiranja isklucivanje listenera..
	
	public FilterUpperTable(MyDefaultTableModel upperTableModel, Vector<ColumnEntryPair> criterias) {
		this.upperModel = upperTableModel;
		this.criterias = criterias;
	}

	/**
	 * Metoda koja okida izvrsavanje celokupnog procesa filtriranja.
	 */
	public void filter() {
		ArrayList<RowFilter<MyDefaultTableModel, Integer>> filterList = new ArrayList<>();
		RowFilter<MyDefaultTableModel, Integer> andFilter;
		
		TableRowSorter<MyDefaultTableModel> tableRowSorter = new TableRowSorter<MyDefaultTableModel>(upperModel);
		
		if(this.tableFrame == null)
			this.tableFrame = MainTabbedPane.getUpperTableFrameByName(upperModel.getTableName());
		
		String columnName;
		ColumnType columnType;
		
		ColumnEntryPair pair;
		for(int i = 0; i < criterias.size(); i++) {
			pair = criterias.get(i);
			columnType = pair.getDataType();
			columnName = pair.getColumnName();
			
				switch(columnType){
					case DATE:
					case DATETIME:
						Vector<Date> dateRange = new Vector<>();
						for(ColumnEntryPair p: criterias) {
							if(p.getColumnName().equals(columnName)) {
								dateRange.add((Date) p.getDataValue());
							}
						}
						if(criterias.get(i+1).getColumnName().equals(columnName))
							i++;		// preskoci sledeci (do datuma podatak..)					
						RowByDateFilter df  = new RowByDateFilter(dateRange, columnName);
						if(dateRange.get(0) != null || dateRange.get(1) != null)		// ako ima bar jedan uslov
							filterList.add(df);
						break;
					case ALPHABET:
						RowByAlphabetFilter af = new RowByAlphabetFilter(columnName, (String) pair.getDataValue());
						if(pair.getDataValue() != null)
							filterList.add(af);
						break;
					case DECIMAL:
						Vector<Double> doubleRange = new Vector<>();
						for(ColumnEntryPair p: criterias) {
							if(p.getColumnName().equals(columnName)) {
								doubleRange.add((Double) p.getDataValue());
							}
						}
						if(criterias.get(i+1).getColumnName().equals(columnName))
							i++;			// preskoci sledeci (do datuma podatak..)					
						RowByDecimalFilter decFilter = new RowByDecimalFilter(columnName, doubleRange);
						if(doubleRange.get(0) != null || doubleRange.get(1) != null)		// ako ima bar jedan uslov
							filterList.add(decFilter);
						break;
					case NUMERIC:
						Vector<Integer> intRange = new Vector<>();
						for(ColumnEntryPair p: criterias) {
							if(p.getColumnName().equals(columnName)) {
								intRange.add((Integer) p.getDataValue());
							}
						}
						if(criterias.get(i+1).getColumnName().equals(columnName))
							i++;			// preskoci sledeci (do datuma podatak..)					
						RowByIntegerFilter intFilter = new RowByIntegerFilter(columnName, intRange);
						if(intRange.get(0) != null || intRange.get(1) != null)		// ako ima bar jedan uslov
							filterList.add(intFilter);
						break;
				}
		}
		
		andFilter = RowFilter.andFilter(filterList);
		tableRowSorter.setRowFilter(andFilter);
		tableFrame.getTable().setRowSorter(tableRowSorter);
		
		if(!isTesting)												// iskljuceno zbog listenera kod testiranja
			tableFrame.getTable().setRowSelectionInterval(0 , 0);
		
	}

	public TableFrame getTableFrame() {
		return tableFrame;
	}

	public void setTableFrame(TableFrame tableFrame) {
		this.tableFrame = tableFrame;
	}

	public boolean isTesting() {
		return isTesting;
	}

	public void setTesting(boolean isTesting) {
		this.isTesting = isTesting;
	}
	
}
