package testmodel.testtable;
import static org.junit.Assert.*;

import java.util.ArrayList;

import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.PortableInterceptor.ACTIVE;

import enums.TableLocation;


public class TestActiveTablesModel {

	static ArrayList<MyDefaultTableModel> list;
	static MyDefaultTableModel mdf, mdf1, mdf2, mdf3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		list = new ArrayList<MyDefaultTableModel>();
		mdf = new MyDefaultTableModel();
		mdf1 = new MyDefaultTableModel();
		mdf2 = new MyDefaultTableModel();
		mdf.setTableName("Prvi");
		mdf1.setTableName("Drugi");
		mdf2.setTableName("Treci");
		mdf.setTableLocation(TableLocation.UPPER_TABLE);
		mdf1.setTableLocation(TableLocation.UPPER_TABLE);
		mdf2.setTableLocation(TableLocation.BOTTOM_TABLE);
		
		mdf1.incActiveReferences();
		mdf1.incActiveReferences();
		
		mdf2.incActiveReferences();
		
		mdf.decActiveReferences();
		
		list.add(mdf);
		list.add(mdf1);
		
		ActiveTablesModel.getInstance().setList(list);
		//list.add(mdf2);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		list.clear();
	}

	@Test
	public void testAddNewTableModel() {
		//fail("Not yet implemented");
		ActiveTablesModel.getInstance().addNewTableModel(mdf2);
		list.size();
		assertEquals(3, list.size());
		assertEquals("Treci", ActiveTablesModel.getInstance().currentBottomTable.getTableName());
	}
	
	@Test
	public void testSetCurrentTableByName(){
		ActiveTablesModel.getInstance().setCurrentTableByName("Prvi", TableLocation.UPPER_TABLE);
		assertEquals(TableLocation.UPPER_TABLE, ActiveTablesModel.getInstance().currentUpperTable.getTableLocation());
	}

	@Test
	public void testGetTableModelByName(){
		assertEquals("Drugi", ActiveTablesModel.getInstance().getTableModelByName("Drugi").getTableName());
	}
	
	@Test
	public void testDeleteOpenedTableByName(){
		mdf3 = new MyDefaultTableModel();
		mdf3.setTableName("Cetvrti");
		ActiveTablesModel.getInstance().addNewTableModel(mdf3);
		
		ActiveTablesModel.getInstance().deleteOpenedTableByName("Cetvrti");
		assertEquals(3,ActiveTablesModel.getInstance().getList().size());
	}
	
	@Test
	public void testCheckForReferences(){
		ActiveTablesModel.getInstance().checkForReferences(mdf1);
		//ako se pri proveri referenci izbrisao neki model, to cemo sa sledecom linijom proveriti
		assertEquals(3, ActiveTablesModel.getInstance().getList().size());
	}
}
