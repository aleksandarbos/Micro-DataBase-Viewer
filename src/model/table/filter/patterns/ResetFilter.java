package model.table.filter.patterns;

import javax.swing.RowFilter;

import model.table.MyDefaultTableModel;

/**
 * Filter koji ima za cilj, da unutar prikaza tabele vrati sve torke(resetuje) koje su se nalazile u modelu.
 * @author Aleksandar
 *
 */
public class ResetFilter extends RowFilter<MyDefaultTableModel, Integer> {

	@Override
	public boolean include(javax.swing.RowFilter.Entry<? extends MyDefaultTableModel, ? extends Integer> entry) {
		return true;
	}

}
