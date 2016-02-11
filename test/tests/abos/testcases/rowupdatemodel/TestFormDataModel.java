package tests.abos.testcases.rowupdatemodel;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Vector;

import org.junit.Test;

import localisation.Loc;
import model.table.MyDefaultTableModel;
import model.table.row.ColumnEntryPair;
import model.table.row.RowUpdateModel;
/**
 * Test klasa za testiranje ispravnosti formiranja objekta tipa {@link MyDefaultTableModel} u
 * zavisnosti od prosledjenih vrednosti.
 * @author Aleksandar
 *
 */
public class TestFormDataModel {
	
	/**
	 * Testiranje metode: {@link RowUpdateModel#formDataModel(Vector)}.
	 * <ul>
	 * 	<li>
	 * 		Revizija klase: 181
	 * 	</li>
	 * 	<li>
	 * 		Trajanje metode: 1.292s
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
	public void testFormDataModel() {
		MyDefaultTableModel testTableModel = new MyDefaultTableModel();
		Vector<ColumnEntryPair> testEnteredPairValues = new Vector<ColumnEntryPair>();
		
		RowUpdateModel testUpdateModel;
		Vector<Object> expectedResults = new Vector<>();
		String testTableName = "ISTORIJA_UREDJENJA";
		Calendar cal = Calendar.getInstance();
		cal.clear();

		testTableModel.setTableName(testTableName);
		testEnteredPairValues.add(new ColumnEntryPair(testTableName, "DR_OZNAKA", "srb"));
		testEnteredPairValues.add(new ColumnEntryPair(testTableName, "DUR_OZNAKA", "F"));
		testEnteredPairValues.add(new ColumnEntryPair(testTableName, "URE_BROJ", "11,6"));
		testEnteredPairValues.add(new ColumnEntryPair(testTableName, "URE_OD_KADA", "01/01/1450"));
		testEnteredPairValues.add(new ColumnEntryPair(testTableName, "URE_DO_KADA", "06/04/1942"));
		
		expectedResults.add("srb");
		expectedResults.add("F");
		expectedResults.add(11.6);
		
		if(Loc.getInstance().getLanguage().toString().equals("en_US")) {
			cal.set(1450, 0, 1);
			expectedResults.add(cal.getTime());
			cal.set(1942, 5, 4);
			expectedResults.add(cal.getTime());

		} else {
			cal.set(1942, 3, 6);
			expectedResults.add(cal.getTime());
			cal.set(1942, 3, 6);
			expectedResults.add(cal.getTime());
		}
		
		testUpdateModel = new RowUpdateModel(testTableModel, testEnteredPairValues);
		
		assertEquals(testUpdateModel.getEnteredValues(), expectedResults);
		
	}

}
