package controller.listeners.tabbedpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import gui.MainTabbedPane;
import model.table.ActiveTablesModel;
import model.table.MyDefaultTableModel;

/**
 * Klasa modeluje pojedinacne lisenere, za sve otvorene tabove.
 * 
 * @author Aleksandar
 */
public class CloseTabListener implements ActionListener{
	
	private boolean closingUpperTab = true;
	private String closingTabNamea;

	public CloseTabListener(boolean closingUpperTab, String tabName) {
		this.closingUpperTab = closingUpperTab;
		//this.closingTabName = tabName;
	}
	
	/**
	 * Funkcija koja prepoznaje tab koji je potrebno zatvoriti, pomocu imena dugmeta koje je kliknuto.
	 * Nakon sto je prepoznato ime, prolazi kroz listu otvorenih tabova u gornjem delu. Ukoliko pronadje odgovarajuci, iskljucuje ga.
	 * Ukoliko u gornjem delu nije pronadjen tab, isti proces ce ponoviti i u donjem delu. 
	 * Prilikom zatvaranja tabova proverava da li ima jos preostalih tabova, ukoliko nema omogucava prikaz
	 * za celu parent tabelu.
	 */
	public void actionPerformed(ActionEvent evt) {
		
		
		String closingTabname = ((JButton) evt.getSource()).getName();		// uzima se ime taba koji se zatvara
		MainTabbedPane mainTabHalf = MainTabbedPane.getInstance();		// prolaz kroz gornje tabove
		MyDefaultTableModel closingTableModel = ActiveTablesModel.getInstance().getTableModelByName(closingTabname);
		
		int deletingIndex = -1;
		
		if(closingUpperTab) {
			for (int i = 0; i < mainTabHalf.getTabCount(); i++) {
				if (mainTabHalf.getTabComponentAt(i).getName().equals(closingTabname)) {
					deletingIndex = i;
					break;
				}
			}
			
			if(deletingIndex != -1) {
				mainTabHalf.removeTabAt(deletingIndex);
				closingTableModel.decActiveReferences();
				
				if(closingTableModel.getActiveReferences() == 0) {		// ako vise nema aktivnih referenci na taj model
					ActiveTablesModel.getInstance().deleteOpenedTableByName(closingTabname);
				}
				
			}
			
			if(!closingTableModel.getSubTables().isEmpty()) {			// ako ima podtabela
				JSplitPane tabSplitPane = MainTabbedPane.getOpenedTabbedPanes().get(closingTableModel.getTableName());
				JTabbedPane bottomTabbedPane = (JTabbedPane) tabSplitPane.getBottomComponent();
				
				for(int i = 0; i < bottomTabbedPane.getTabCount(); i++) {
					MyDefaultTableModel bottomTableModel = ActiveTablesModel.getInstance().getTableModelByName(bottomTabbedPane.getTabComponentAt(i).getName());
					bottomTableModel.decActiveReferences();
					
				}
			}
		} else {
			JSplitPane selectedTabbedSplitPane = (JSplitPane) mainTabHalf.getComponentAt(mainTabHalf.getSelectedIndex());
			JTabbedPane bottomPane = (JTabbedPane) selectedTabbedSplitPane.getBottomComponent();
			
			for (int i = 0; i < bottomPane.getTabCount(); i++) {
				if (bottomPane.getTabComponentAt(i).getName().equals(closingTabname)) {
					deletingIndex = i;
					break;
				}
			}
			
			if(deletingIndex != -1) {	// ako se brise neki tab - u cilju zastite
				bottomPane.removeTabAt(deletingIndex);
				closingTableModel.decActiveReferences();				// umanji jednu aktivnu referencu, ako je na 0, obrisi je..
	
				if(bottomPane.getTabCount() == 0 ) {
					selectedTabbedSplitPane.setDividerLocation(1.0);	// postavi divider na maximum
				}
				
			}

		}
		if(MainTabbedPane.getInstance().getTabCount() == 0) {
			MainTabbedPane.getInstance().setVisible(false);
		}

	}

}
