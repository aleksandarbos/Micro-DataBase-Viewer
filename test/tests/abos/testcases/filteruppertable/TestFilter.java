package tests.abos.testcases.filteruppertable;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.BeforeClass;
import org.junit.Test;

import controller.listeners.TableSelectionListener;
import enums.TableLocation;
import gui.table.TableFrame;
import model.table.MyDefaultTableModel;
import model.table.filter.FilterUpperTable;
import model.table.row.ColumnEntryPair;
import model.tree.JsonModel;
/**
 * Test klasa za testiranje funkcionalnosti metode {@link FilterUpperTable#filter()} u zavisnosti
 * od prodledjenih kriterijuma.
 * @author Aleksandar
 *
 */
public class TestFilter {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		JsonModel model = new JsonModel();
	}
	
	/**
	 * Testiranje ispravnosti metode za filtriranje parent tabele.
	 * Link ka metodi: {@link FilterUpperTable#filter()}.
	 * <ul>
	 * 	<li>
	 * 		Revizija klase: 181
	 * 	</li>
	 * 	<li>
	 * 		Trajanje metode: 3.719
	 * 	</li>
	 * 	<li>
	 * 		Uspesnost: Uspesno
	 * 	</li>
	 * 	<li>
	 * 		(Opciono) Opis greske:
	 * 	</li>
	 * </ul>
	 */
	@Test
	public void testFilter() {
		String tableName = "NASELJENO_MESTO";
		TableFrame tableFrame = new TableFrame(tableName, TableLocation.UPPER_TABLE, null);
		MyDefaultTableModel upperTableModel = tableFrame.getModel();
		
		Vector<Vector<String>> correctData = new Vector<>();
		
		Vector<ColumnEntryPair> criteria = new Vector<>();
		criteria.add(new ColumnEntryPair(tableName, "DR_OZNAKA", "srb"));
		criteria.add(new ColumnEntryPair(tableName, "NM_IDENTIFIKATOR", 1));
		criteria.add(new ColumnEntryPair(tableName, "NM_IDENTIFIKATOR", 6));
		criteria.add(new ColumnEntryPair(tableName, "NM_NAZIV", "s"));
		criteria.add(new ColumnEntryPair(tableName, "NM_PTT_OZNAKA", null));
		
		Vector<String> row1 = new Vector<>();
		row1.add("srb");
		row1.add("1");
		row1.add("Sombor");
		row1.add("25000");
		
		Vector<String> row2 = new Vector<>();
		row2.add("srb");
		row2.add("2");
		row2.add("Subotica");
		row2.add("24000");

		Vector<String> row3 = new Vector<>();
		row3.add("srb");
		row3.add("3");
		row3.add("Novi Sad");
		row3.add("21000");

		correctData.add(row1);
		correctData.add(row2);
		correctData.add(row3);
		
		FilterUpperTable filterUpperTable = new FilterUpperTable(upperTableModel, criteria);
		filterUpperTable.setTableFrame(tableFrame);
		filterUpperTable.setTesting(true);			// zbog tableSelectionListenera...
		filterUpperTable.filter();
		
		for(int i = 0; i < tableFrame.getTable().getRowCount(); i++) {
			assertEquals(correctData.get(i), upperTableModel.getDataVector().get(tableFrame.getTable().convertRowIndexToModel(i)));
		}

	}

}
