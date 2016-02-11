package model.table.filter.patterns;

import java.util.Vector;

import javax.swing.RowFilter;

import model.table.MyDefaultTableModel;

/**
 * Filter za proveru vrednosti torki koje su brojnog tipa. Da bi torka bila usvojna u kriterijum
 * vrednost te torke mora biti identicna kao zadata vrednost kriterijuma. Za razliku od {@link RowByDecimalFilter}-a,
 * ovaj filter radi iskljucivo nad celobrojnim vrednostima.
 * @author Aleksandar
 *
 */
public class RowByIntegerFilter extends RowFilter<MyDefaultTableModel, Integer> {

	private Vector<Integer> range;
	private String columnName;
	
	public RowByIntegerFilter(String columnName, Vector<Integer> range) {
		this.columnName = columnName;
		this.range = range;
	}
	
	@Override
	public boolean include(javax.swing.RowFilter.Entry<? extends MyDefaultTableModel, ? extends Integer> entry) {
		
		Integer startRange = this.range.get(0);
		Integer endRange = this.range.get(1);
		
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
