package testmodel.testtable;
import static org.junit.Assert.*;

import java.util.Vector;

import model.table.MyDefaultTableModel;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestMyDefaultTableModel {

	static MyDefaultTableModel mdf, mdf1, mdf2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Vector<String> nazivi = new Vector<String>();
		Vector<String> semantickiN = new Vector<String>();
		nazivi.add("Prvi");
		nazivi.add("Drugi");
		nazivi.add("Treci");
		semantickiN.add("PrviS");
		semantickiN.add("DrugiS");
		semantickiN.add("TreciS");
		
		mdf = new MyDefaultTableModel();
		mdf1 = new MyDefaultTableModel(nazivi, semantickiN, 3);
		mdf2 = new MyDefaultTableModel(3,3);
		mdf.setTableName("Prvi model");
		mdf1.setTableName("Drugi model");
		mdf2.setTableName("Treci model");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testClearModelData() {
		//fail("Not yet implemented");
		assertEquals(mdf.getDataVector().size(), 0);
	}
	
	@Test
	public void testToString(){

		assertEquals(mdf.getTableName(), mdf.toString());
		
	}
	
	@Test
	public void testActiveReferences(){
		mdf.getActiveReferences();
//		assertEquals(1,mdf.getActiveReferences());
	}
	
	@Test
	public void testIncActiveReferences(){
		mdf.incActiveReferences();
		assertEquals(2,mdf.getActiveReferences());
	}
	
	@Test
	public void testDecActiveReferences(){
		mdf.decActiveReferences();
		assertEquals(1,mdf.getActiveReferences());
	
	}
	
	@Test
	public void testConst1 (){
		assertEquals("Drugi", mdf1.getHeaders().get(1));
		assertEquals("PrviS", mdf1.getSemanticHeaders().get(0));
	}
}
