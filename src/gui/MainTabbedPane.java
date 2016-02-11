package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import controller.listeners.TableSelectionListener;
import controller.listeners.TreeMouseListener;
import controller.listeners.tabbedpane.CloseTabListener;
import controller.listeners.tabbedpane.TabChangeListener;
import enums.TableLocation;
import gui.frame.MainFrame;
import gui.frame.bar.StatusBar;
import gui.table.TableFrame;
import gui.table.TableToolbar;
import localisation.Loc;
import localisation.LocaliseName;
import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;
import model.tree.JsonModel;
import model.tree.TreeModel;
import model.tree.node.Node;
import model.tree.node.NodeTree;

/**
 * Klasa koja nasledjuje {@link JTabbedPane}, i unutar koje se nalaze svi tabovi relevantni za prikaz
 * podataka pokrenutih za prikaz od strane korisnika, pogledati {@link TreeMouseListener}.
 * @author Aleksandar
 *
 */

@SuppressWarnings("serial")
public class MainTabbedPane extends JTabbedPane {
	
	/**
	 * Mapa koja u zavisnosti od imena tabele cuva sve njene child-tabove za pristup.
	 */
	private static Map<String, JSplitPane> openedTabbedPanes = new HashMap<String, JSplitPane>();
	
	/**
	 * Singleton objekat.
	 */
	private static MainTabbedPane instance = null;

	private ActiveTablesModel activeTablesModel;
	
	private MainTabbedPane() {
		super();	
	}
	
	public static MainTabbedPane getInstance() {
		if (instance == null) {
			instance = new MainTabbedPane();
			instance.initialize();
		}
		return instance;
	}
	
	private void initialize() {
		
		this.activeTablesModel = ActiveTablesModel.getInstance();
		
		addChangeListener(new TabChangeListener(TableLocation.UPPER_TABLE));
	}
	
	/**
	 * Metoda za dodavanje novog parent taba u koji se ubacuje tabela, skrol i table toolbar.
	 * @param name Naziv tabele koja se koristi(kljuc).
	 * @return void
	 */
	public void addNewTab(String tableName) { //ime je kljuc
		TableToolbar tableToolbar = new TableToolbar(tableName, TableLocation.UPPER_TABLE);		// je gornji
		JSplitPane splitPane;
		TableFrame table = null;
		MyDefaultTableModel tableModel = null;
		
		// prvo proveri da li je vec otvorena tabela, ako jeste koristi je, ako ne, kreiraj novu
		if(ActiveTablesModel.containsTableModel(tableName) != null) {			// otvorena je vec(sadrzi se u listi)
			tableModel = ActiveTablesModel.containsTableModel(tableName);		// postavi aktivnu referencu
			tableModel.incActiveReferences();									// jos jedan novi table GUI koristi vec otvoren table model
			table = new TableFrame(tableName, tableModel, TableLocation.UPPER_TABLE, tableToolbar);							// instanciraj novi gui sa istom tabelom
		} else {
			// nije nasao ni jednu otvorenu tabelu sa tim imenom
			table = new TableFrame(tableName, TableLocation.UPPER_TABLE, tableToolbar);		// napravi novi GUI i !**model(pravi se u konstruktoru)**!
			activeTablesModel.addNewTableModel(table.getModel());// dodaj u otvorene tabele.
			//System.out.println("Aktivne tabele: " + activeTablesModel.getList());
		}
		
		tableToolbar.addTableFrame(table);
		tableToolbar.setTableLocation(TableLocation.UPPER_TABLE);
		
		try{
			table.getTable().setRowSelectionInterval(0, 0);
			tableToolbar.handleEmptyTable(true);
		} catch(Exception e) {
			tableToolbar.handleEmptyTable(false);
		}
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableToolbar, null);
		
		openedTabbedPanes.put(tableName, splitPane);
		
		addTab(tableName, splitPane);		// dodaj tabelu sa toolbar-om

		LocaliseName tabNameLoc = new LocaliseName("");
		Loc.getInstance().register(tabNameLoc, "setName", "table."+tableName);
		System.out.println("tavle."+tableName);
		System.out.println(tabNameLoc.getName());   //lokalizuje samo naziv taba
		JButton btnClose;
		btnClose = new JButton("x");
		btnClose.setPreferredSize(new Dimension(40,20));
		btnClose.setName(tableName);
		btnClose.addActionListener(new CloseTabListener(true, tableName));
		JLabel lbName = new JLabel(" " + tabNameLoc.getName() + "    ");;

		JPanel pnlClose;
		pnlClose = new JPanel(new GridBagLayout());
		pnlClose.setBackground(new Color(110, 110, 110, 0));
		pnlClose.setName(tableName);
		pnlClose.add(lbName);
		pnlClose.add(btnClose);
				
		if (getTabCount()-1 != 0) {
			setTabComponentAt(getTabCount()-1, pnlClose);
			setSelectedIndex(getTabCount()-2);
		
		} else {
			setTabComponentAt(0, pnlClose);
			setSelectedIndex(0);
		}

		
				
	}
	
	/**
	 * Metoda za dodavanje novog parent taba koji se sastoji iz 2 dela, gde je gornji deo
	 * tab-ovani prikaz za parent tabelu, a dole tabovani prikaz za child tabele.
	 * @param name
	 * @param subTables
	 * @return void
	 */
	public void addNewTab(String tableName, ArrayList<NodeTree> subTables) { //ime je kljuc
		JSplitPane splitPane;
		JTabbedPane bottomTablesPane = new JTabbedPane();
		Vector<String> subTablesList = new Vector<String>();
		
		bottomTablesPane.addChangeListener(new TabChangeListener(TableLocation.BOTTOM_TABLE));
		
		for(NodeTree childTable: subTables) {
			String bottomTableName = childTable.getName();
			
			TableToolbar tableToolbar = new TableToolbar(bottomTableName, TableLocation.BOTTOM_TABLE);		// je donji
			TableFrame bottomTable = null;
			MyDefaultTableModel bottomTableModel = null;

			// ako je vec otvorena negde kao upper ili down tabela
			if(ActiveTablesModel.getInstance().getTableModelByName(bottomTableName) != null) {
				bottomTableModel = ActiveTablesModel.getInstance().getTableModelByName(bottomTableName);
				bottomTableModel.incActiveReferences();
				bottomTable = new TableFrame(bottomTableName, bottomTableModel, TableLocation.BOTTOM_TABLE, tableToolbar);
			} else {
				bottomTable = new TableFrame(bottomTableName, TableLocation.BOTTOM_TABLE, tableToolbar);				// kreiraj novi GUI i model za donji bottomTable
				activeTablesModel.addNewTableModel(bottomTable.getModel());				// dodaj u otvorene tabele..
			}
			
			tableToolbar.addTableFrame(bottomTable);
			tableToolbar.setTableLocation(TableLocation.BOTTOM_TABLE);
			
			subTablesList.add(bottomTableName);
			
			if(bottomTableModel != null)
				ActiveTablesModel.getInstance().currentBottomTable = bottomTableModel;		// postavi poslednje otvorenu na aktivnu
			else
				ActiveTablesModel.getInstance().currentBottomTable = bottomTable.getModel();		// novododati je aktivan
			
			
			if(bottomTable.getTable().getSelectedRowCount() == 0) {		// ako nista nije selektovano
				tableToolbar.handleEmptyTable(false);
			}
			
			bottomTablesPane.addTab(childTable.getName(), tableToolbar);					// dodaj tab u donji TabbedPane 
	
			makeCloseBtn(bottomTablesPane, childTable.getName(), false);
		}
		
		TableToolbar tableToolbar = new TableToolbar(tableName, TableLocation.UPPER_TABLE);		// je gornji
		TableFrame table = null;
		MyDefaultTableModel tableModel = null;
		
		if(ActiveTablesModel.containsTableModel(tableName) != null) {			// ako je vec otvorena tabela
			tableModel = ActiveTablesModel.containsTableModel(tableName);
			tableModel.incActiveReferences();
			table = new TableFrame(tableName, tableModel, TableLocation.UPPER_TABLE, tableToolbar);
		} else {
			table = new TableFrame(tableName, TableLocation.UPPER_TABLE, tableToolbar);
			activeTablesModel.addNewTableModel(table.getModel());
		}
		
		tableToolbar.addTableFrame(table);
		tableToolbar.setTableLocation(TableLocation.UPPER_TABLE);
		//tableToolbar.getToolbar().setName("upperToolbar");
		table.getModel().setSubTables(subTablesList);				// vodi evidenciju o tome da li tabela ima child tabele

		try{
			table.getTable().setRowSelectionInterval(0, 0);
			tableToolbar.handleEmptyTable(true);
		} catch(Exception e) {
			//System.out.println("IZUZETAK: Pokusao selektovati prvi red tabele, tabela prazna.");
			//tableToolbar.handleEmptyTable(false);
		}
		
		
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableToolbar, bottomTablesPane);
		splitPane.setDividerLocation(200);
		
		openedTabbedPanes.put(tableName, splitPane);		// u zavisnosti od imena gornjeg taba, preuzmi split pane
		
		addTab(tableName,splitPane);
		
		makeCloseBtn(this, tableName, true);
		
		table.getModel().fireTableDataChanged();
		table.getTable().setRowSelectionInterval(0, 0);
		TableSelectionListener.setFilterEnabled(true);

	}
	
		
	/**
	 * Metoda za formiranje dugmeta "X" na otvorenim tabovima prosledjenog JTabbedPane-a
	 * @param tabp
	 * @param name
	 * @param closingUpperTab 
	 * @return void
	 */
	private void makeCloseBtn(JTabbedPane tabp, String tabName, boolean closingUpperTab){

		LocaliseName tabNameLoc = new LocaliseName("");
		Loc.getInstance().register(tabNameLoc, "setName", "table."+tabName);
		System.out.println("tavle."+tabName);
		System.out.println(tabNameLoc.getName());
		
		
		JButton btnClose;
		btnClose = new JButton("x");
		btnClose.setPreferredSize(new Dimension(40,20));
		btnClose.setName(tabName);
		btnClose.addActionListener(new CloseTabListener(closingUpperTab, tabName));

		JLabel lbName = new JLabel(" " + tabNameLoc.getName() + "    ");;

		JPanel pnlClose;
		pnlClose = new JPanel(new GridBagLayout());
		pnlClose.setBackground(new Color(110, 110, 110, 0));
		pnlClose.setName(tabName);
		pnlClose.add(lbName);
		pnlClose.add(btnClose);
				
		if (tabp.getTabCount()-1 != 0) {
			tabp.setTabComponentAt(tabp.getTabCount()-1, pnlClose);
			tabp.setSelectedIndex(tabp.getTabCount()-1);
		
		} else {
			tabp.setTabComponentAt(0, pnlClose);
			tabp.setSelectedIndex(0);
		}
	}

	public static Map<String, JSplitPane> getOpenedTabbedPanes() {
		return openedTabbedPanes;
	}

	/**
	 * Metoda koja vraca komponentu TableFrame koja se nalazi u child tabelama na osnovu zadatog parent-a i datog child-a.
	 * @param tabName naziv parent tabele koja sadrzi child tabelu
	 * @param naziv child tabele koju trazimo
	 * @return Vraca tabelu koja odgovara modelu, ukoliko tab nema bottom tabele, vraca <code>null</code>
	 */
	public static TableFrame getBottomTableFrameByName(String tabName, String childTableName) {
		
		JSplitPane splitPane = openedTabbedPanes.get(tabName);
		JTabbedPane tabbedPane = (JTabbedPane) splitPane.getBottomComponent();
		TableToolbar tableToolbar = null;
		
		for(int i = 0; i < tabbedPane.getTabCount(); i++) {
			if(tabbedPane.getTitleAt(i).equals(childTableName))
				tableToolbar = (TableToolbar) tabbedPane.getComponentAt(i);
		}
		
		return tableToolbar.getTableFrame();
	}
	
	/**
	 * Metoda koja za zadato ime gorenjeg taba, vraca otvoren objekat klase {@link TableFrame}.
	 * @param tabName Naziv taba u kom je otvoren objekat.
	 * @return objekat klase {@link TableFrame} koji je otvoren.
	 */
	public static TableFrame getUpperTableFrameByName(String tabName) {
		JSplitPane splitPane = openedTabbedPanes.get(tabName);
		TableToolbar tableToolbar = (TableToolbar) splitPane.getTopComponent();
		TableFrame tableFrame = tableToolbar.getTableFrame();
		
		return tableFrame;
	}
	
	public ActiveTablesModel getActiveTablesModel(){
		return this.activeTablesModel;
	}
	
	/**
	 * Metoda koja zatvara sve otvorene tabove.
	 * @author Ivana
	 */
	
	public void closeAllTabs(){
		
		while(getTabCount() != 0) {					// cisti tabove
			removeTabAt(0);
		}
		
		ActiveTablesModel.getInstance().getList().clear();		// model prati stanje
		
	}
	


}
