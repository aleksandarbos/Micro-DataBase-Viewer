package model.table;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import enums.TableLocation;
import model.table.row.ColumnEntryPair;


/**
 * Klasa MyDefaultTableModel se koristi u svrhu modelovanja modela tabele.
 * 
 * @author Aleksandar
 *
 */
public class MyDefaultTableModel extends DefaultTableModel {

	/**
	 * Predstavlja niz naziva kolona interpretiranih kodom, odnosno ova imena su ujedno i
	 * kljucevi.
	 */
	private Vector<String> headers;
	
	/**
	 * Predstavlja niz naziva kolona interpretiranih nazivom za prikaz.
	 */
	private Vector<String> semanticHeaders;

	/**
	 * Kolekcija koja sadrzi nazive svih child tabela.
	 */
	private Vector<String> subTables = new Vector<>();
	
	private String tableName;
	
	// ispitati dal je neophodno...
	private int selectedIdx = 0;
			
	/*
	 * Atribut koji broji koliko aktivnih GUI tabela koristi ovaj table model, tj. koliko je otvoreno ovih
	 * tabela u aplikaciji.
	 */
	private int activeReferences = 1;		// cim se inicijalizuje objekat, znaci neko vec koristi ref na njega..
	
	/**
	 * Spisak stranih kljuceva koji su referencirani na ovaj table model.
	 */
	private Vector<ColumnEntryPair> foreignKeys = new Vector<>();
	
	private TableLocation location;

	public MyDefaultTableModel() {
		// TODO Auto-generated constructor stub
		this.headers = new Vector<>();
		this.semanticHeaders = new Vector<>();
	}

	public MyDefaultTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		// TODO Auto-generated constructor stub
	}

	public MyDefaultTableModel(Vector<String> columnNames, Vector<String> semanticHeaders, int rowCount) {
		super(semanticHeaders, rowCount);
		this.headers = columnNames;
		this.semanticHeaders = semanticHeaders;
		// TODO Auto-generated constructor stub
	}

	public MyDefaultTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public MyDefaultTableModel(Vector<String> data, Vector<String> columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public MyDefaultTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}
	

	public void clearModelData() {
		this.setRowCount(0);
		getDataVector().clear();		// ocisti datavector
		//setSelectedIdx(-1);
	}
	

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public Vector<String> getHeaders() {
		return headers;
	}

	public int getSelectedIdx() {
		return selectedIdx;
	}

	public void setSelectedIdx(int selectedIdx) {
		this.selectedIdx = selectedIdx;
	}

	public Vector<String> getSubTables() {
		return subTables;
	}

	public void setSubTables(Vector<String> subTables) {
		this.subTables = subTables;
	}

	public Vector<String> getSemanticHeaders() {
		return semanticHeaders;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.tableName;
	}

	/**
	 * Funkcija koja uvecava aktivne reference za 1;
	 * @return
	 */
	public int incActiveReferences() {
		return this.activeReferences++;
	}
	/**
	 * Funkcija koja brise aktivnu referencu, i proverava da li je preostalo jos referenci.
	 * Ukoliko nije, brise otvoreni table model iz liste modela.
	 * @return
	 */
	public int decActiveReferences() {
		this.activeReferences--;
		ActiveTablesModel.getInstance().checkForReferences(this);		// proveri da li je za brisanje iz liste
		return this.activeReferences;
	}

	public int getActiveReferences() {
		return activeReferences;
	}

	public TableLocation getTableLocation() {
		return location;
	}

	public void setTableLocation(TableLocation location) {
		this.location = location;
	}

	public void addForeignKey(ColumnEntryPair pair) {
		this.foreignKeys.addElement(pair);
	}
	
	public Vector<ColumnEntryPair> getForeignKeysList() {
		return this.foreignKeys;
	}

}
