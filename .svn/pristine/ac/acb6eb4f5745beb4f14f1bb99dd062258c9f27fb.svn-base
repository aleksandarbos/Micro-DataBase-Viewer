package tests.abos.testcases.rowupdatemodel;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import org.junit.Test;

import localisation.Loc;
import model.table.row.RowUpdateModel;

/**
 * Test klasa koja testira metodu konverzije unetog datuma u javin {@link Date} objekat tj.
 * konverziju ulaznog stringa tipa: "<i>mm/dd/yyyy</i>" ili "<i>dd/mm/yyyy</i>" u zavisnosti od trenutne lokalizacije {@link Loc}.
 * @author Aleksandar
 *
 */
public class TestDateFromString {

	/**
	 * Testirane metode {@link RowUpdateModel#dateFromString(String)} po lokalizaciji en_US.
	 * <ul>
	 * 	<li>
	 * 		Revizija klase: 181
	 * 	</li>
	 * 	<li>
	 * 		Trajanje metode: 0.138s
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
	public void testDateFormingEN_US() {
		
		Loc.getInstance().changeLanguage(new Locale("en", "US"), "localisation.bundle.bundle");
		
		Calendar cal = Calendar.getInstance();
		
		cal.clear();

		String[] tests = {"06/07/1993", "5/4/2011", "5/03/2016", "3/06/1944"};
		Vector<Date> enUScalendarTests = new Vector<Date>();
		
		cal.set(1993, 5, 7);
		enUScalendarTests.add(cal.getTime());
		
		cal.set(2011, 4, 4);
		enUScalendarTests.add(cal.getTime());
		
		cal.set(2016, 4, 3);
		enUScalendarTests.add(cal.getTime());
		
		cal.set(1944, 2, 6);
		enUScalendarTests.add(cal.getTime());
		
		for (int i = 0; i < tests.length; i++) {
			
			Date resultDate = RowUpdateModel.dateFromString(tests[i]);
			
			Date correctDate = enUScalendarTests.get(i);

			assertEquals(correctDate, resultDate);

		}
		
	}

	/**
	 * Testirane metode {@link RowUpdateModel#dateFromString(String)} po lokalizaciji sr_RS.
	 * INFO:<br/>
	 * <ul>
	 * 	<li>
	 * 		Revizija klase: 181
	 * 	</li>
	 * 	<li>
	 * 		Trajanje metode: 0.103s
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
	public void testDateFormingSR_RS() {
		
		Loc.getInstance().changeLanguage(new Locale("sr", "RS"), "localisation.bundle.bundle");
		
		Calendar cal = Calendar.getInstance();
		
		cal.clear();

		String[] tests = {"06.07.1993", "5.4.2011", "5.03.2016", "3.06.1944"};
		Vector<Date> srRScalendarTests = new Vector<Date>();
		
		cal.set(1993, 6, 6);
		srRScalendarTests.add(cal.getTime());
		
		cal.set(2011, 3, 5);
		srRScalendarTests.add(cal.getTime());
		
		cal.set(2016, 2, 5);
		srRScalendarTests.add(cal.getTime());

		cal.set(1944, 5, 3);
		srRScalendarTests.add(cal.getTime());
		
		for (int i = 0; i < tests.length; i++) {
			
			Date resultDate = RowUpdateModel.dateFromString(tests[i]);
			
			Date correctDate = srRScalendarTests.get(i);

			assertEquals(correctDate, resultDate);

		}
		
		
	}


}
