package model.table.filter.patterns;

import java.util.Date;
import java.util.Vector;

import javax.swing.RowFilter;

import model.table.MyDefaultTableModel;
import model.table.row.RowUpdateModel;

/**
 * Filter koji proverava da li se sve torke zadatog modela na osnovu vrednosi njihovog datuma nalaze u zadatom opsegu(datuma).
 * @author Aleksandar
 *
 */
public class RowByDateFilter extends RowFilter<MyDefaultTableModel, Integer> {

	private Vector<Date> datePair;
	private String columnName;
	
	public RowByDateFilter(Vector<Date> datePair, String columnName) {
		this.datePair = datePair;
		this.columnName = columnName;
	}
	
	@Override
	public boolean include(javax.swing.RowFilter.Entry<? extends MyDefaultTableModel, ? extends Integer> entry) {
		
		Date startDate = this.datePair.get(0);
		Date endDate = this.datePair.get(1);
		
		Vector<Vector<String>> entryRows = entry.getModel().getDataVector();
		String singleEntryValue = "";
		String singleEntryColumnName = "";
		
		for(int i = 0; i < entryRows.get(entry.getIdentifier().intValue()).size(); i++) {
			singleEntryValue = entryRows.get(entry.getIdentifier().intValue()).get(i);
			singleEntryColumnName = entry.getModel().getColumnName(i);
			
			if(entry.getModel().getHeaders().get(i).equals(this.columnName)) {
				
				Date tableDate = RowUpdateModel.dateFromString(singleEntryValue);
				if(tableDate != null)
					if(startDate != null || endDate != null) {		// ima da se filtrira
						if(startDate == null) {
							return tableDate.before(endDate);
						} else if(endDate == null) {
							return tableDate.after(startDate);
						} else
							return tableDate.after(startDate) && tableDate.before(endDate);
					}
			}
		}
		
		return true;
	}

}
