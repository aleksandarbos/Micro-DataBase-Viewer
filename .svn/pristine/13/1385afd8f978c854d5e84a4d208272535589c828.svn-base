package model.table.filter.patterns;

import java.util.Vector;

import javax.swing.RowFilter;

import model.table.MyDefaultTableModel;
/**
 * Filter za filtriranje tekstualnih vrednosti polja. Provera je case insenzitivna, i vrsi se
 * u odnosu na to da li vrednost za koju filtriramo sadrzi zadati kriterijum u sebi.
 * @author Aleksandar
 *
 */
public class RowByAlphabetFilter extends RowFilter<MyDefaultTableModel, Integer> {

	private String columnName;
	private String value;
	
	public RowByAlphabetFilter(String columnName, String value) {
		this.columnName = columnName;
		this.value = value;
	}
	
	@Override
	public boolean include(javax.swing.RowFilter.Entry<? extends MyDefaultTableModel, ? extends Integer> entry) {
		
		Vector<Vector<String>> entryRows = entry.getModel().getDataVector();
		String singleEntryValue = "";
		String columnName = "";
		
		for(int i = 0; i < entryRows.get(entry.getIdentifier().intValue()).size(); i++) {
			singleEntryValue = entryRows.get(entry.getIdentifier().intValue()).get(i);
			columnName = entry.getModel().getHeaders().get(i);
			if(getColumnName().equals(columnName))
				return singleEntryValue.toLowerCase().contains(getValue().toLowerCase());
		}
		
		return false;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getValue() {
		return value;
	}
	

}
