package model.table.filter.patterns;

import java.util.Vector;

import javax.swing.RowFilter;

import model.table.MyDefaultTableModel;

/**
 * Filter za proveru vrednosti torki koje su brojnog tipa. Da bi torka bila usvojna u kriterijum
 * vrednost te torke mora biti identicna kao zadata vrednost kriterijuma.
 * @author Aleksandar
 *
 */
public class RowByDecimalFilter extends RowFilter<MyDefaultTableModel, Integer> {

	private Vector<Double> range;
	private String columnName;
	
	public RowByDecimalFilter(String columnName, Vector<Double> range) {
		this.columnName = columnName;
		this.range = range;
	}
	
	@Override
	public boolean include(javax.swing.RowFilter.Entry<? extends MyDefaultTableModel, ? extends Integer> entry) {
		
		Double startRange = this.range.get(0);
		Double endRange = this.range.get(1);
		
		Vector<Vector<String>> entryRows = entry.getModel().getDataVector();
		String singleEntryValue = "";
		String singleEntryColumnName = "";
		
		for(int i = 0; i < entryRows.get(entry.getIdentifier().intValue()).size(); i++) {
			singleEntryValue = entryRows.get(entry.getIdentifier().intValue()).get(i);
			
			if(entry.getModel().getHeaders().get(i).equals(this.columnName)) {
				
				Double value = Double.parseDouble(singleEntryValue);
				
				if(value != null)
					if(startRange != null || endRange != null) {		// ima da se filtrira
						if(startRange == null) {
							return value <= endRange;
						} else if(endRange == null) {
							return value >= startRange;
						} else
							return value >= startRange && value <= endRange;
					}
			}
		}

		
		return false;
	}

}
