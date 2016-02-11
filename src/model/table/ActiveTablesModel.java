package model.table;

import java.util.ArrayList;

import enums.TableLocation;
import gui.MainTabbedPane;

/**
 * Singleton klasa koja vodi racuna o svim aktivnim tabelama u okviru rada aplikacije. Klasa vezana
 * sa {@link MainTabbedPane}, gde se otvaranjem novog taba formira nova tabela koja se evidentira u atributu
 * ove klase.
 * @author Aleksandar
 *
 */

public class ActiveTablesModel {
	
	private static ActiveTablesModel instance = null;
	/**
	 * Kolekcija koja sadrzi u sebi sve aktivne tabele koje su otvorene kao tabovi, bilo child ili parent.
	 */
	private ArrayList<MyDefaultTableModel> list = new ArrayList<MyDefaultTableModel>();
	/**
	 * Gornja tabela sa kojom korisnik trenutno radi.
	 */
	public static MyDefaultTableModel currentUpperTable = null;
	public static MyDefaultTableModel currentBottomTable = null;
	
	private ActiveTablesModel() {
		
	}

	public ArrayList<MyDefaultTableModel> getList() {
		return list;
	}

	public void setList(ArrayList<MyDefaultTableModel> list){
		this.list = list;
	}

	/**
	 * Dodaje table model u kolekciju aktivnih, i prosledjen table model postavlja za aktivan.
	 * @param newTableModel
	 */
	public void addNewTableModel(MyDefaultTableModel newTableModel) {
		this.list.add(newTableModel);
		
		if(newTableModel.getTableLocation() == TableLocation.UPPER_TABLE) {
			currentUpperTable = newTableModel;
		}
		
		if(newTableModel.getTableLocation() == TableLocation.BOTTOM_TABLE) {
			currentBottomTable = newTableModel;
		}
	}
	
	public static ActiveTablesModel getInstance() {
		if (instance == null) {
			instance = new ActiveTablesModel();
		}
		return instance;
	}
	
	public void setCurrentTableByName(String tableName, TableLocation tableLocation) {		// postavi aktivni na osnovu imena tabele
		for(MyDefaultTableModel model: list) {
			if(model.getTableName().equals(tableName)) {
				if(tableLocation == TableLocation.UPPER_TABLE)
					currentUpperTable = model;
				else		// bottom je
					currentBottomTable = model;
			}
		}
	}
	
	
	/**
	 * Metoda koja vraca table model u zavisnosti od proslenjenog imena. Ona pretrazuje
	 * po listi sve modele sa tim imenom, ukoliko ne nadje ni jedan, vraca <code>null</code>.
	 * @param tableName - Ime po kom trazimo model
	 * @return Table model sa zadatim imenom
	 */
	public MyDefaultTableModel getTableModelByName(String tableName) {
		for(MyDefaultTableModel model: list) {
			if(model.getTableName().equals(tableName)) {
				return model;
			}
		}
		return null;
	}	
	/**
	 * U zavisnosti od prosledjenog imena, metoda brise iz liste svaki aktivni model
	 * koji u sebi sadrzi tabelu sa zadatim imenom.
	 * @param tableName
	 */
	public void deleteOpenedTableByName(String tableName) {		// iskljuci iz kolekcije model tabele sa zadatim imenom
		for (int i = 0; i < this.list.size(); i++) {
			if(this.list.get(i).getTableName().equals(tableName)) {
				this.list.remove(i);
				break;		// nema ih vise...
			}
		}
	}
	/**
	 * Metoda koja proverava da li se tabela sa zadatim imenom nalazi u listi otvorenih tabela.
	 * Ukoliko ne pronadje table model sa zadatim imenom ispisuje <code>null</code>;
	 * @param tableName Naziv na osnovu kog trazimo model
	 * @return tableModel Model sa zadatim nazivom
	 */
	public static MyDefaultTableModel containsTableModel(String tableName) {
		for(MyDefaultTableModel tblModel: instance.list) {
			if(tblModel.getTableName().equals(tableName)) {		// otvorena je vec
				return tblModel;
			}
		}
		return null;
	}
	
	/**
	 * Funkcija koja proverava koliko ima aktivnih referenci na trenutni model tabele. Ukoliko vise nema
	 * aktivnih, automatski brise model iz liste otvorenih modela tabela.
	 * @param model
	 */
	public void checkForReferences(MyDefaultTableModel model) {
		if(model.getActiveReferences() == 0) {
			deleteOpenedTableByName(model.getTableName());
		}
	}
	
}
