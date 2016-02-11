package model.table.filter;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import controller.database.DataBase;
import gui.MainTabbedPane;
import gui.table.TableFrame;
import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;
import model.table.filter.patterns.RowByKeyFilter;
import model.table.row.ColumnEntryPair;

/**
 * Klasa cija je svrha da u zavisnosti od trenutno prosledjenog parent modela tabele i na osnovu
 * njegovih pod tabela(child tabela), filtrira sve vrednosti u child tabelama, u zavisnost od vrednosti
 * kljuca selektovane parent torke.
 * @author Aleksandar
 *
 */
public class FilterBottomTables extends FilterTable {

	private MyDefaultTableModel bottomModel;
	private MyDefaultTableModel upperModel;
	private Vector<String> subTablesNames;
	
	/**
	 * Kreiranje objekta klase filtracije child tabela.
	 * @param upperModel Parent tabela na osnovu cije selektovane torke filtriramo child torke, svih child tabela.
	 * @param subTablesNames Lista koja u sebi nosi sve nazive tabela koje sadrzi prosledjena parent tabela.
	 */
	public FilterBottomTables(MyDefaultTableModel upperModel, Vector<String> subTablesNames) {
		this.upperModel = upperModel;
		this.subTablesNames = subTablesNames;
	}
	/**
	 * Izvrsava svrhu postojanja ove klase. Pozivom ove metode, izvrsava se celokupan proces filtriranja.
	 */
	public void filter() {
		ArrayList<RowFilter<MyDefaultTableModel, Integer>> filters = new ArrayList<>();
		RowFilter<MyDefaultTableModel, Integer> andFilter = null ;
		Vector<String> subTablesNames = upperModel.getSubTables();
		Vector<Vector> keysList = null;
		TableFrame upperTableFrame = MainTabbedPane.getUpperTableFrameByName(upperModel.getTableName());

		
		for(String tableName: subTablesNames) { // ako je parent
			this.bottomModel = ActiveTablesModel.getInstance().getTableModelByName(tableName);
			this.bottomModel.getForeignKeysList().clear();
			
			if(tableName.equals(upperModel.getTableName()))
				continue;
			
			TableRowSorter<MyDefaultTableModel> tableRowSorter = new TableRowSorter<MyDefaultTableModel>(bottomModel);
			TableFrame bottomTableFrame = MainTabbedPane.getBottomTableFrameByName(upperModel.getTableName(), bottomModel.getTableName());
			//System.out.println("------" + bottomModel + "------");
			filters.clear();
			
			// zakljucaj svaki put - primoraj da se selektuje donja torka kako bi se omogucio rad nad istom
			//bottomTableFrame.getTableToolbar().handleEmptyTable(false);
			
			keysList = DataBase.getInstance().getForeignKeys(bottomModel.getTableName(), upperModel.getTableName());
			for(Vector keys: keysList) {
				String keyValue = "";
				String key = "";
				for(Object val: keys) {
					key = val.toString();
					Vector<String> row = (Vector<String>) upperModel.getDataVector().get(upperModel.getSelectedIdx());
					if(!upperModel.getHeaders().contains(key) || !bottomModel.getHeaders().contains(key))	// ako je kljuc tamo gde mu nije mesto..
						continue;
					keyValue = row.get(upperModel.getHeaders().indexOf(key));
					key = bottomModel.getSemanticHeaders().get(bottomModel.getHeaders().indexOf(key));
					filters.add(new RowByKeyFilter(key, keyValue));
					
					ColumnEntryPair foreignKey = new ColumnEntryPair(tableName, key, keyValue);		// strani kljuc
					bottomModel.addForeignKey(foreignKey);
					
					//System.out.println("[" + key + "]" + "--" + "[" + keyValue + "]");
					
				}
			}
			bottomTableFrame.getTableToolbar().handleEmptyTable(false);
			andFilter = RowFilter.andFilter(filters);		// logicki saberi sve kriterijume
			tableRowSorter.setRowFilter(andFilter);
			bottomTableFrame.getTable().setRowSorter(tableRowSorter);
			//System.out.println("------" + bottomModel + "------");
		}

	}
	
}
