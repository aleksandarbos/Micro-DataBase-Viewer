package model.table.filter.patterns;

import java.util.Vector;

import javax.swing.RowFilter;

import model.table.MyDefaultTableModel;
import model.table.filter.FilterBottomTables;

/**
 * Filter koji na osnovu vrednosti kljuca, i vrednosti torke, proverava da li data torka zadovoljava
 * kriterijum. Filter usko vezan za klasu {@link FilterBottomTables}.
 * @author Aleksandar
 *
 */
public class RowByKeyFilter extends RowFilter<MyDefaultTableModel, Integer> {

	private String columnName;
	private String value;
	
	public RowByKeyFilter(String columnName, String value) {
		setValue(value);
		setColumnName(columnName);
	}
	
	@Override
	public boolean include(RowFilter.Entry<? extends MyDefaultTableModel, ? extends Integer> entry) {
		
		@SuppressWarnings("unchecked")
		Vector<Vector<String>> entryRows = entry.getModel().getDataVector();
		String singleEntryValue = "";
		String columnName = "";
		
		for(int i = 0; i < entryRows.get(entry.getIdentifier().intValue()).size(); i++) {
			singleEntryValue = entryRows.get(entry.getIdentifier().intValue()).get(i);
			columnName = entry.getModel().getColumnName(i);
			
			if((getColumnName().toLowerCase()).equals(columnName.toLowerCase()) &&
				singleEntryValue.replaceAll("\\s+","").toLowerCase().equals(getValue().replaceAll("\\s+","").toLowerCase())) {
				return true;		// izignorisi razmake koji se preuzimaju iz tabela...
			}
		}
		
		return false;
		
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getValue() {
		if(value == null)
			return "";
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
