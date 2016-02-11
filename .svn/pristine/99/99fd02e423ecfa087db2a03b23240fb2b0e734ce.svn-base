package test.controller.database;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.database.DataBase;
import enums.ColumnType;
import localisation.Loc;
import model.tree.JsonModel;

/**
 * 
 * @author NemanjaM
 *
 */
public class DataBaseTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		@SuppressWarnings("unused")
		JsonModel model = new JsonModel();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	/**
	 * Test za metodu {@link DataBase#logIn}
	 */
	@Test
	public void testLogIn() {
		Assert.assertTrue(DataBase.getInstance().logIn("admin", "admin"));
		Assert.assertFalse(DataBase.getInstance().logIn("pero", "peric"));
		Assert.assertFalse(DataBase.getInstance().logIn("admin", null));
		Assert.assertFalse(DataBase.getInstance().logIn(null, null));
	}
	
	/**
	 * Test za metodu {@link DataBase#insertToDatabase}
	 * Postoji greska u reviziji 172, prilikom pokretanja petog test primera iz metode<br/>
	 * 
	 * Reseno u reviziji 176 pa nadalje:<br/>- dodat je uslov koji proverava da li neki od prosledjenih vektora ima vrednost {@code null}<br/>
	 */
	@Test 
	public void testInsertToDatabase() {
		String[] arrh1 = {"DUR_OZNAKA","DUR_NAZIV"}; 
		String[] arrh3 = {"DR_OZNAKA","DRZ_DR_OZNAKA","SAS_OD"}; 
		String[] arrh4 = {"DR_OZNAKA","NM_IDENTIFIKATOR","NM_NAZIV","NM_PTT_OZNAKA","NM_PTT_OZNAKA_DODATO"};
		String[] arrh5 = {"TR_OZNAKA","TR_NAZIV"};
		Vector<String> headers1 = new Vector<String>(Arrays.asList(arrh1));
		Vector<String> headers3 = new Vector<String>(Arrays.asList(arrh3));
		Vector<String> headers4 = new Vector<String>(Arrays.asList(arrh4));
		Vector<String> headers5 = new Vector<String>(Arrays.asList(arrh5));
		
		String[] arrd1 = {"L","Ludnice"}; 
		String[] arrd2 = {"L","Nesto"};
		String[] arrd3 = {"srb","SRB","2015-12-01"}; 
		String[] arrd4 = {"srb","111","Daleko","88000","88001"};
		Vector<Object> data1 = new Vector<Object>(Arrays.asList(arrd1));
		Vector<String> data11 = new Vector<String>(Arrays.asList(arrd1));
		Vector<Object> data2 = new Vector<Object>(Arrays.asList(arrd2));
		Vector<Object> data3 = new Vector<Object>(Arrays.asList(arrd3));
		Vector<Object> data4 = new Vector<Object>(Arrays.asList(arrd4));
		DataBase.getInstance().deleteFromDatabase("DRZAVNO_UREDJENJE", headers1, data11);
		
		Assert.assertEquals(true, DataBase.getInstance().insertToDatabase("DRZAVNO_UREDJENJE", headers1, data1));		
		Assert.assertEquals(false, DataBase.getInstance().insertToDatabase("DRZAVNO_UREDJENJE", headers1, data2));
		Assert.assertEquals(false, DataBase.getInstance().insertToDatabase("SASTAV_DRZAVE", headers3, data3));
		Assert.assertEquals(false, DataBase.getInstance().insertToDatabase("NASELJENO_MESTO", headers4, data4));
		Assert.assertEquals(false, DataBase.getInstance().insertToDatabase("TIP_REGIONA", headers5, null));			
	}
	
	/**
	 * Test za metodu {@link DataBase#insertToDatabase}
	 * Postoji greska u reviziji 172, prilikom pokretanja petog test primera iz metode<br/>
	 * Reseno u reviziji 176 pa nadalje:<br/>- dodat je uslov koji proverava da li neki od prosledjenih vektora ima vrednost {@code null}<br/>
	 */
	@Test
	public void testUpdateInDatabase() {
		//addToDatabase();
		
		String[] arrh1 = {"DUR_OZNAKA","DUR_NAZIV"}; 
		String[] arrh2 = {"DUR_OZNAKA"}; 
		String[] arrh3 = {"DR_OZNAKA","DRZ_DR_OZNAKA","SAS_OD","SAS_DO"}; 
		String[] arrh4 = {"DR_OZNAKA","NM_IDENTIFIKATOR","NM_NAZIV","NM_PTT_OZNAKA","NM_PTT_OZNAKA_IZMISLJENA"};
		String[] arrh5 = {"TR_OZNAKA","TR_NAZIV"};
		String[] arrd1 = {"K","KraljevinaBR2"}; 
		String[] arrd2 = {"M"};
		String[] arrd3 = {"srb","SRB","2015-12-01"}; 
		String[] arrd4 = {"srb","1","Sombor","25000","kkaoo"};
		Vector<String> headers1 = new Vector<String>(Arrays.asList(arrh1));
		Vector<String> headers2 = new Vector<String>(Arrays.asList(arrh2));
		Vector<String> headers3 = new Vector<String>(Arrays.asList(arrh3));
		Vector<String> headers4 = new Vector<String>(Arrays.asList(arrh4));
		Vector<String> headers5 = new Vector<String>(Arrays.asList(arrh5));
		Vector<Object> data1 = new Vector<Object>(Arrays.asList(arrd1));
		Vector<Object> data2 = new Vector<Object>(Arrays.asList(arrd2));
		Vector<Object> data3 = new Vector<Object>(Arrays.asList(arrd3));
		Vector<Object> data4 = new Vector<Object>(Arrays.asList(arrd4));

		Assert.assertEquals(true, DataBase.getInstance().updateInDatabase("DRZAVNO_UREDJENJE",  headers1, data1));
		Vector<String> data11 = new Vector<String>(Arrays.asList(arrd1));
		DataBase.getInstance().deleteFromDatabase("DRZAVNO_UREDJENJE", headers1, data11);
		Assert.assertEquals(false, DataBase.getInstance().updateInDatabase("DRZAVNO_UREDJENJE", headers2, data2));
		Assert.assertEquals(false, DataBase.getInstance().updateInDatabase("SASTAV_DRZAVE", headers3, data3));
		Assert.assertEquals(false, DataBase.getInstance().updateInDatabase("NASELJENO_MESTO", headers4, data4));
		Assert.assertEquals(false, DataBase.getInstance().updateInDatabase("TIP_REGIONA", headers5, null));
				
		removeFromDatabase();
	}
	
	/**
	 * Test za metodu {@link DataBase#deleteFromDatabase}
	 * Postoji greska u reviziji 172, prilikom pokretanja drugog test primera iz metode<br/>
	 * Reseno u reviziji 176 pa nadalje:<br/>- dodat je uslov koji proverava da li neki od prosledjenih vektora ima vrednost {@code null}<br/>
	 * - dodat je uslov koji proverava da li je broj prosledjenih kolona jednak ocekivanom
	 *
	 */
	@Test 
	public void testDeleteFromDatabase() {
		//addToDatabase();
		
		String[] arrh1 = {"DUR_OZNAKA","DUR_NAZIV"}; 
		String[] arrh2 = {"DUR_OZNAKA"}; 
		String[] arrh3 = {"DR_OZNAKA","DRZ_DR_OZNAKA","SAS_OD","SAS_DO"}; 
		String[] arrh4 = {"DR_OZNAKA","NM_IDENTIFIKATOR","NM_NAZIV","NM_PTT_OZNAKA","NM_PTT_OZNAKA_IZMISLJENA"};
		String[] arrh5 = {"TR_OZNAKA","TR_NAZIV"};
		String[] arrd1 = {"K","Kraljevina"}; 
		String[] arrd2 = {"M"};
		String[] arrd3 = {"srb","SRB","2015-12-01"}; 
		String[] arrd4 = {"srb","1","Sombor","25000","kkaoo"};
		Vector<String> headers1 = new Vector<String>(Arrays.asList(arrh1));
		Vector<String> headers2 = new Vector<String>(Arrays.asList(arrh2));
		Vector<String> headers3 = new Vector<String>(Arrays.asList(arrh3));
		Vector<String> headers4 = new Vector<String>(Arrays.asList(arrh4));
		Vector<String> headers5 = new Vector<String>(Arrays.asList(arrh5));
		Vector<String> data1 = new Vector<String>(Arrays.asList(arrd1));
		Vector<String> data2 = new Vector<String>(Arrays.asList(arrd2));
		Vector<String> data3 = new Vector<String>(Arrays.asList(arrd3));
		Vector<String> data4 = new Vector<String>(Arrays.asList(arrd4));
		
		Assert.assertEquals(true, DataBase.getInstance().deleteFromDatabase("DRZAVNO_UREDJENJE",  headers1, data1));
		Assert.assertEquals(false, DataBase.getInstance().deleteFromDatabase("DRZAVNO_UREDJENJE", headers2, data2));
		Assert.assertEquals(false, DataBase.getInstance().deleteFromDatabase("SASTAV_DRZAVE", headers3, data3));
		Assert.assertEquals(false, DataBase.getInstance().deleteFromDatabase("NASELJENO_MESTO", headers4, data4));
		Assert.assertEquals(false, DataBase.getInstance().deleteFromDatabase("TIP_REGIONA", headers5, null));
		
		removeFromDatabase();
	}
	
	/**
	 * Test za metodu {@link DataBase#getPrimaryKeys}
	 */
	@Test
	public void testGetPrimaryKeys() {
		String[] arr1 = {"DR_OZNAKA"}; 
		String[] arr2 = {"DR_OZNAKA","DUR_OZNAKA","URE_BROJ"}; 
		String[] arr3 = {"PRO_POS_DR_OZNAKA","PRO_POS_GD_OZNAKA","PRO_POS_PS_OZNAKA","PRO_OBJ_OZNAKA","PRO_PRO_SPRAT","PRO_PRO_PROSTORIJA","REG_ZAP_REDNI_BROJ"}; 
		Vector<String> expected1 = new Vector<String>(Arrays.asList(arr1));
		Vector<String> expected2 = new Vector<String>(Arrays.asList(arr2));
		Vector<String> expected3 = new Vector<String>(Arrays.asList(arr3));
		Vector<String> expected4 = new Vector<String>();
		Vector<String> keys1 = DataBase.getInstance().getPrimaryKeys("DRZAVA");
		Vector<String> keys2 = DataBase.getInstance().getPrimaryKeys("ISTORIJA_UREDJENJA");
		Vector<String> keys3 = DataBase.getInstance().getPrimaryKeys("SEDE_U_PROSTORIJI");
		Vector<String> keys4 = DataBase.getInstance().getPrimaryKeys("SEDE_NA_GLAVI");
		
		Assert.assertTrue(keys1.containsAll(expected1) && expected1.containsAll(keys1));
		Assert.assertTrue(keys2.containsAll(expected2) && expected2.containsAll(keys2));
		Assert.assertTrue(keys3.containsAll(expected3) && expected3.containsAll(keys3));
		Assert.assertTrue(keys4.containsAll(expected4) && expected4.containsAll(keys4));
	}

	
	/**
	 * Test za metodu {@link DataBase#getForeignKeys}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetForeignKeys() {
		String[] arr1 = {"DUR_OZNAKA","DUR_OZNAKA"}; 
		String[] arr2 = {"DR_OZNAKA","DR_OZNAKA"};//NAS_DR_OZNAKA 
		String[] arr3 = {"NM_IDENTIFIKATOR","NM_IDENTIFIKATOR","NAS_DR_OZNAKA", "DR_OZNAKA"};
		Vector<String> expected1 = new Vector<>(Arrays.asList(arr1));
		Vector<String> expected2 = new Vector<>(Arrays.asList(arr2));
		Vector<String> expected3 = new Vector<>(Arrays.asList(arr3));
		
		Vector<Vector> result1 = DataBase.getInstance().getForeignKeys("DRZAVA", "DRZAVNO_UREDJENJE");
		Vector<Vector> result2 = DataBase.getInstance().getForeignKeys("REGION", "DRZAVA");
		Vector<Vector> result3 = DataBase.getInstance().getForeignKeys("REGION", "NASELJENO_MESTO");
		
		Vector<String> converted1 = new Vector<>();
		Vector<String> converted2 = new Vector<>();
		Vector<String> converted3 = new Vector<>();
		
		for (Vector<String> outter : result1) {
			for (String inner : outter) {
				converted1.addElement(inner);
			}
		}
		for (Vector<String> outter : result2) {
			for (String inner : outter) {
				converted2.addElement(inner);
			}
		}
		for (Vector<String> outter : result3) {
			for (String inner : outter) {
				converted3.addElement(inner);
			}
		}

		Assert.assertTrue(converted1.containsAll(expected1) && expected1.containsAll(converted1));		
		Assert.assertTrue(converted2.containsAll(expected2) && expected2.containsAll(converted2));		
		Assert.assertTrue(converted3.containsAll(expected3) && expected3.containsAll(converted3));		
	}
	
	/**
	 * Test za metodu {@link DataBase#isPrimaryKey}
	 */
	@Test
	public void testIsPrimaryKey() {
		boolean test1 = DataBase.getInstance().isPrimaryKey("RADNA_GODINA", "PS_OZNAKA");
		boolean test2 = DataBase.getInstance().isPrimaryKey("NASELJENO_MESTO", "NM_IDENTIFIKATOR");
		boolean test3 = DataBase.getInstance().isPrimaryKey("ORGANIZACIONE_JEDINICE", "NM_IDENTIFIKATOR");
		boolean test4 = DataBase.getInstance().isPrimaryKey("STRUKTURA_PROIZVODA", "PRO_GD_OZNAKA");
		boolean test5 = DataBase.getInstance().isPrimaryKey("OBLAST", "OBL_NAZIV");
		boolean test6 = DataBase.getInstance().isPrimaryKey("DRZAVA", "PRO_POS_GD_OZNAKA");

		Assert.assertEquals(true, test1);
		Assert.assertEquals(true, test2);
		Assert.assertEquals(false, test3);
		Assert.assertEquals(true, test4);
		Assert.assertEquals(false, test5);
		Assert.assertEquals(false, test6);
	}
	
	/**
	 * Test za metodu {@link DataBase#getColumnType}
	 */
	@Test
	public void testGetColumnType() {
		ColumnType type1 = DataBase.getInstance().getColumnType("DRZAVA", "DR_NAZIV");
		ColumnType type2 = DataBase.getInstance().getColumnType("AKT_O_ORGANIZACIJI", "AO_DATUM_USVAJANJA");
		ColumnType type3 = DataBase.getInstance().getColumnType("STRUKTURA_REGIONA", "REG_REG_OZNAKA");
		ColumnType type4 = DataBase.getInstance().getColumnType("SEDE_U_PROSTORIJI", "PRO_PRO_SPRAT");
		ColumnType type5 = DataBase.getInstance().getColumnType("REGISTAR_ZAPOSLENIH", "ZAP_JMBG");
		ColumnType type6 = DataBase.getInstance().getColumnType("ORGANIZACIONA_SEMA", "PS_OZNAKA");
		
		Assert.assertEquals(ColumnType.ALPHABET, type1);
		Assert.assertEquals(ColumnType.DATETIME, type2);
		Assert.assertEquals(ColumnType.DECIMAL, type3);
		Assert.assertEquals(ColumnType.NUMERIC, type4);
		Assert.assertEquals(ColumnType.ALPHABET, type5);
		Assert.assertEquals(ColumnType.NUMERIC, type6);
	}
	
	/**
	 * Test za metodu {@link DataBase#parseDateTimeFromDB}
	 * 
	 * Postoji greska u reviziji 172, prilikom pokretanja prvog i drugog test primera iz metode<br/>
	 * Postoji greska u reviziji 172, prilikom pokretanja prvog i drugog test primera iz metode<br/>
	 * Reseno u reviziji 176 pa nadalje:<br/>- dodat je uslov koji proverava da li prosledjeni datum ima vrednost {@code null}
	 * ili je prazan string
	 */
	@Test
	public void testParseDateTimeFromDB() {	
		String parsed1, parsed2, parsed3, parsed4;

		Loc.getInstance().changeLanguage(new Locale("sr", "RS"), "localisation.bundle.bundle");
		parsed1 = DataBase.getInstance().parseDateTimeFromDB(null);
		parsed2 = DataBase.getInstance().parseDateTimeFromDB("");
		parsed3 = DataBase.getInstance().parseDateTimeFromDB("2064-10-31 14:48:55.30");
		parsed4 = DataBase.getInstance().parseDateTimeFromDB("1897-12-01 00:12:05.97");
		
		Assert.assertEquals("", parsed1);
		Assert.assertEquals("", parsed2);
		Assert.assertEquals("31.10.2064", parsed3);
		Assert.assertEquals("01.12.1897", parsed4);
		

		Loc.getInstance().changeLanguage(new Locale("en", "US"), "localisation.bundle.bundle");
		parsed1 = DataBase.getInstance().parseDateTimeFromDB(null);
		parsed2 = DataBase.getInstance().parseDateTimeFromDB("");
		parsed3 = DataBase.getInstance().parseDateTimeFromDB("2064-10-31 14:48:55.30");
		parsed4 = DataBase.getInstance().parseDateTimeFromDB("1897-12-01 00:12:05.97");
		Assert.assertEquals(parsed1, "");
		Assert.assertEquals(parsed2, "");
		Assert.assertEquals(parsed3, "10/31/2064");
		Assert.assertEquals(parsed4, "12/01/1897");
	}
	
	/**
	 * Test za metodu {@link DataBase#parseDateFromDB}
	 * 
	 * Postoji greska u reviziji 172, prilikom pokretanja prvog i drugog test primera iz metode<br/>
	 * Reseno u reviziji 176 pa nadalje:<br/>- dodat je uslov koji proverava da li prosledjeni datum ima vrednost {@code null}
	 * ili je prazan string
	 */
	@Test
	public void testParseDateFromDB() {
		String parsed1, parsed2, parsed3, parsed4;

		Loc.getInstance().changeLanguage(new Locale("sr", "RS"), "localisation.bundle.bundle");
		parsed1 = DataBase.getInstance().parseDateFromDB(null);
		parsed2 = DataBase.getInstance().parseDateFromDB("");
		parsed3 = DataBase.getInstance().parseDateFromDB("2064-10-31");
		parsed4 = DataBase.getInstance().parseDateFromDB("1897-12-01");
		Assert.assertEquals("", parsed1);
		Assert.assertEquals("", parsed2);
		Assert.assertEquals("31.10.2064", parsed3);
		Assert.assertEquals("01.12.1897", parsed4);


		Loc.getInstance().changeLanguage(new Locale("en", "US"), "localisation.bundle.bundle");
		parsed1 = DataBase.getInstance().parseDateFromDB(null);
		parsed2 = DataBase.getInstance().parseDateFromDB("");
		parsed3 = DataBase.getInstance().parseDateFromDB("2064-10-31");
		parsed4 = DataBase.getInstance().parseDateFromDB("1897-12-01");
		Assert.assertEquals("", parsed1);
		Assert.assertEquals("", parsed2);
		Assert.assertEquals("10/31/2064", parsed3);
		Assert.assertEquals("12/01/1897", parsed4);
		
	}

	
	/**
	 * Test za metodu {@link DataBase#parseDateToDB}
	 * 
	 * Postoji greska u reviziji 172, prilikom pokretanja prvog i drugog test primera iz metode<br/>
	 * Reseno u reviziji 176 pa nadalje:<br/>- dodat je uslov koji proverava da li prosledjeni datum ima vrednost {@code null} 
	*/
	@Test
	public void testParseDateToDB() {
		String parsed1 = "", parsed2 = "", parsed3 = "";

		Loc.getInstance().changeLanguage(new Locale("sr", "RS"), "localisation.bundle.bundle");
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		Date d2 = new Date();
		Date d3 = new Date();
		try {
			d2 = format.parse("31.10.2064");
			d3 = format.parse("01.12.1897");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		parsed1 = DataBase.getInstance().parseDateToDB(null);
		parsed2 = DataBase.getInstance().parseDateToDB(d2);
		parsed3 = DataBase.getInstance().parseDateToDB(d3);
			
		Assert.assertEquals(parsed1, "");
		Assert.assertEquals(parsed2, "2064-10-31");
		Assert.assertEquals(parsed3, "1897-12-01");
		

		Loc.getInstance().changeLanguage(new Locale("en", "US"), "localisation.bundle.bundle");
		format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			d2 = format.parse("10/31/2064");
			d3 = format.parse("12/01/1897");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		parsed1 = DataBase.getInstance().parseDateToDB(null);
		parsed2 = DataBase.getInstance().parseDateToDB(d2);
		parsed3 = DataBase.getInstance().parseDateToDB(d3);
		
		Assert.assertEquals(parsed1, "");
		Assert.assertEquals(parsed2, "2064-10-31");
		Assert.assertEquals(parsed3, "1897-12-01");
	}

	
	/**
	 * Test za metodu {@link DataBase#parseDecimalFromDB}
	 */
	@Test
	public void testParseDecimalFromDB() {
		Loc.getInstance().changeLanguage(new Locale("sr", "RS"), "localisation.bundle.bundle");

		String parsed1 = DataBase.getInstance().parseDecimalFromDB("");
		String parsed2 = DataBase.getInstance().parseDecimalFromDB("-12.02");
		String parsed3 = DataBase.getInstance().parseDecimalFromDB("0.0");
		String parsed4 = DataBase.getInstance().parseDecimalFromDB("12.02");
		
		Assert.assertTrue(parsed1.equals(""));
		Assert.assertTrue(parsed2.equals("-12,02"));
		Assert.assertTrue(parsed3.equals("0,0"));
		Assert.assertTrue(parsed4.equals("12,02"));
		
		
		Loc.getInstance().changeLanguage(new Locale("en", "US"), "localisation.bundle.bundle");
		
		parsed1 = DataBase.getInstance().parseDecimalFromDB("");
		parsed2 = DataBase.getInstance().parseDecimalFromDB("-12.02");
		parsed3 = DataBase.getInstance().parseDecimalFromDB("0.0");
		parsed4 = DataBase.getInstance().parseDecimalFromDB("12.02");
		
		Assert.assertTrue(parsed1.equals(""));
		Assert.assertTrue(parsed2.equals("-12.02"));
		Assert.assertTrue(parsed3.equals("0.0"));
		Assert.assertTrue(parsed4.equals("12.02"));		
	}
	
	@SuppressWarnings("unused")
	private void addToDatabase() {
		String[] arrh1 = {"DUR_OZNAKA","DUR_NAZIV"}; 
		String[] arrh3 = {"DR_OZNAKA","DRZ_DR_OZNAKA","SAS_OD","SAS_DO"}; 
		String[] arrh4 = {"DR_OZNAKA","NM_IDENTIFIKATOR","NM_NAZIV","NM_PTT_OZNAKA"};
		String[] arrh5 = {"TR_OZNAKA","TR_NAZIV"};
		Vector<String> headers1 = new Vector<String>(Arrays.asList(arrh1));
		Vector<String> headers3 = new Vector<String>(Arrays.asList(arrh3));
		Vector<String> headers4 = new Vector<String>(Arrays.asList(arrh4));
		Vector<String> headers5 = new Vector<String>(Arrays.asList(arrh5));
		
		String[] arrd1 = {"K","Kraljevina"}; 
		String[] arrd2 = {"M", "Monarhija"};
		String[] arrd3 = {"srb","SRB","2015-12-01","2020-12-11"}; 
		String[] arrd4 = {"srb","111","Daleko","88000"};
		String[] arrd5 = {"N","Nedodjija"};
		Vector<Object> data1 = new Vector<Object>(Arrays.asList(arrd1));
		Vector<Object> data2 = new Vector<Object>(Arrays.asList(arrd2));
		Vector<Object> data3 = new Vector<Object>(Arrays.asList(arrd3));
		Vector<Object> data4 = new Vector<Object>(Arrays.asList(arrd4));
		Vector<Object> data5 = new Vector<Object>(Arrays.asList(arrd5));
		
		DataBase.getInstance().insertToDatabase("DRZAVNO_UREDJENJE", headers1, data1);
		DataBase.getInstance().insertToDatabase("DRZAVNO_UREDJENJE", headers1, data2);
		DataBase.getInstance().insertToDatabase("SASTAV_DRZAVE", headers3, data3);
		DataBase.getInstance().insertToDatabase("NASELJENO_MESTO", headers4, data4);
		DataBase.getInstance().insertToDatabase("TIP_REGIONA", headers5, data5);
	}
	
	private void removeFromDatabase() {
		String[] arrh1 = {"DUR_OZNAKA","DUR_NAZIV"}; 
		String[] arrh3 = {"DR_OZNAKA","DRZ_DR_OZNAKA","SAS_OD","SAS_DO"}; 
		String[] arrh4 = {"DR_OZNAKA","NM_IDENTIFIKATOR","NM_NAZIV","NM_PTT_OZNAKA"};
		String[] arrh5 = {"TR_OZNAKA","TR_NAZIV"};
		Vector<String> headers1 = new Vector<String>(Arrays.asList(arrh1));
		Vector<String> headers3 = new Vector<String>(Arrays.asList(arrh3));
		Vector<String> headers4 = new Vector<String>(Arrays.asList(arrh4));
		Vector<String> headers5 = new Vector<String>(Arrays.asList(arrh5));
		
		String[] arrd1 = {"K","Kraljevina"}; 
		String[] arrd2 = {"M", "Monarhija"};
		String[] arrd3 = {"srb","SRB","2015-12-01","2020-12-11"}; 
		String[] arrd4 = {"srb","111","Daleko","88000"};
		String[] arrd5 = {"N","Nedodjija"};
		Vector<String> data1 = new Vector<String>(Arrays.asList(arrd1));
		Vector<String> data2 = new Vector<String>(Arrays.asList(arrd2));
		Vector<String> data3 = new Vector<String>(Arrays.asList(arrd3));
		Vector<String> data4 = new Vector<String>(Arrays.asList(arrd4));
		Vector<String> data5 = new Vector<String>(Arrays.asList(arrd5));
		
		DataBase.getInstance().deleteFromDatabase("DRZAVNO_UREDJENJE", headers1, data1);
		DataBase.getInstance().deleteFromDatabase("DRZAVNO_UREDJENJE", headers1, data2);
		DataBase.getInstance().deleteFromDatabase("SASTAV_DRZAVE", headers3, data3);
		DataBase.getInstance().deleteFromDatabase("NASELJENO_MESTO", headers4, data4);
		DataBase.getInstance().deleteFromDatabase("TIP_REGIONA", headers5, data5);
	}
}
