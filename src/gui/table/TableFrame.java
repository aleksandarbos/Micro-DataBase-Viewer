package gui.table;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.database.DataBase;
import controller.listeners.TableSelectionListener;
import enums.TableLocation;
import localisation.Loc;
import localisation.LocaliseName;
import model.table.MyDefaultTableModel;
import model.tree.JsonModel;
import model.tree.node.Node;

/**
 * Klasa Table sluzi za kreiranje tabela za prikaz podataka iz baze uz pomoc klasa 
 * {@see MyDefaultTableModel} i {@see JTable}, i postavljanje iste u okviru glavnog 
 * prozora.<br/>
 * Ova klasa trenutno kreira samo prazne tabele sa odgovarajucim zaglavljima koja 
 * se dobijaju iscitavanjem JSON fajla.
 * 
 * @author Violeta
 *
 */
@SuppressWarnings("serial")
public class TableFrame extends JPanel{

	private MyDefaultTableModel model;
	
	private JTable table;
	
	private JScrollPane sc;
	
	private ListSelectionModel selectionModel = null;
	
	private TableLocation location;
	
	private TableToolbar tableToolbar;
	
	/**
	 * Kreira objekat klase Table, na osnovu <code>tableName</code>, <code>isUpperTable</code> postavlja se da je tabela
	 * u gornjim tabovima, <code>isBottomTable</code> postavlja se da je tabela u donjim tabovima. Tabela moze istovremeno
	 * da bude otvorena i u gornjim i u donjim tabovima.
	 * @param tableName
	 * @param isUpperTable
	 * @param isBottomTable
	 * @author Aleksandar
	 */
	public TableFrame(String tableName, TableLocation loc, TableToolbar tableToolbar){
		ArrayList<ArrayList<String>> returnHeaders = getColumnsForKey(tableName);
		
		Vector<String> dataBaseHeaders = new Vector<>(returnHeaders.get(0));
		Vector<String> semanticHeaders = new Vector<>(returnHeaders.get(1));
		
		setTableLocation(loc);
		
		this.model = new MyDefaultTableModel(dataBaseHeaders, semanticHeaders, 0);
		this.tableToolbar = tableToolbar;
		//ubacivanje podataka u glavnu tabelu
		if(DataBase.getInstance().readTableData(this.model, tableName) == false) {
			System.out.println("Nije dobro ucitavanje podataka iz baze za tabelu: " + tableName);
		}
		
		this.model.setTableLocation(loc);		// postavi da li je gornja ili donja tabela
		
		this.model.setTableName(tableName);
		
		this.table = new JTable(model);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setPreferredScrollableViewportSize(new java.awt.Dimension(750, 200));
		
		this.sc = new JScrollPane(table);
		
		this.selectionModel = table.getSelectionModel();
		this.selectionModel.addListSelectionListener(new TableSelectionListener(this));
		
		setLayout(new BorderLayout());
		add(this.sc);
	}
	
	public TableFrame(String tableName, MyDefaultTableModel model, TableLocation loc, TableToolbar tableToolbar) {
		this.model = model;
		
		setTableLocation(loc);
		
		this.table = new JTable(model);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setPreferredScrollableViewportSize(new java.awt.Dimension(750, 200));
		this.tableToolbar = tableToolbar;
		
		this.sc = new JScrollPane(table);
		
		this.selectionModel = table.getSelectionModel();
		this.selectionModel.addListSelectionListener(new TableSelectionListener(this));
		
		setLayout(new BorderLayout());
		add(this.sc);

	}
	
	public MyDefaultTableModel getModel() {
		return model;
	}
	
	public JTable getTable() {
		return table;
	}
	

	/**
	 * Ovoj metodi se prosledjuje kljuc objekta iz JSON fajla i kreira se niz 
	 * sa nazivima kolona koja ce u tabeli predstavljati zaglavlje.
	 * 
	 * @param key
	 * @return vraca se struktura tipa {@code ArrayList<String>} koja sadrzi nazive kolona
	 * @author Violeta
	 * 
	 */
	private ArrayList<ArrayList<String>> getColumnsForKey(String key) {		
		HashMap<String, Node> map = JsonModel.map;
		
		ArrayList<ArrayList<String>> returnList = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> dataBaseHeaders = new ArrayList<String>();
		ArrayList<String> semanticHeaders = new ArrayList<String>();
		
		for(String s: map.keySet())
			if(s.equals(key)) 
				dataBaseHeaders=(map.get(s).getColumns());
		
		for(String s: dataBaseHeaders) //nik kljuceva kolona jedne tabele
			for(String k: map.keySet()) //niz svih kljuceva
				if(s.equals(k)) {//ukoliko je kljuc kolone pronadjen u hasmapi, uzima se ime za ispis
					LocaliseName kolona = new LocaliseName("");
					Loc.getInstance().register(kolona,"setName","table.column."+s);
					semanticHeaders.add(kolona.getName());
					break;	
				}

		returnList.add(dataBaseHeaders);	// 0- database headers
		returnList.add(semanticHeaders);	// 1- semantic headers
		
		return returnList;
	}

	public TableLocation getTableLocation() {
		return location;
	}

	public void setTableLocation(TableLocation location) {
		this.location = location;
	}

	public TableToolbar getTableToolbar() {
		return tableToolbar;
	}
	
}
