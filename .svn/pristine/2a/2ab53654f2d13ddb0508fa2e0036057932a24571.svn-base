package controller.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import enums.TableLocation;
import gui.MainTabbedPane;
import gui.frame.MainFrame;
import gui.frame.bar.StatusBar;
import gui.tree.TreePane;
import localisation.Loc;
import model.table.ActiveTablesModel;
import model.tree.node.NodeTree;

/**
 * Klasa koja modeluje osluskivanje dogadjaja u okviru stabla iz glavnog prozora aplikacije i poziva odgovarajuce metode
 * klase {@link MainTabbedPane} za instanciranje novog taba u zavisnosti od toga koja je stavka stabla selektovana.
 *
 * @author Aleksandar
 */
public class TreeMouseListener implements MouseListener {
	
	private JTree tree;
	private ArrayList<NodeTree> subTables = new ArrayList<>();
	private MainTabbedPane mainHalfTab = MainFrame.getInstance().getMainTabHalf();

	
	public TreeMouseListener(TreePane treePane) {
		this.tree = treePane;
	}

	/**
	 * Metoda se poziva prilikom dogadjaja klika na stablo aplikacije.
	 * Implementirani su 1-click, i 2-click, gde se kod 1-clicka selektuje istoimeni tab kao i cvor,
	 * dok se kod 2-clicka, otvara novi tab ukoliko vec nije otvoren
	 * u protivnom se isti selektuje.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		TreePath treePath = tree.getPathForLocation(e.getX(), e.getY());
		NodeTree selectedNode;
		Loc.getInstance().registerSetText(StatusBar.getStatLab2(), "state.busy");
		

		
		if(treePath != null) {
			selectedNode = (NodeTree)treePath.getLastPathComponent();
		} else 
			return;
		
		if(e.getClickCount() == 1) {	// obican click
			for(int i = 0; i < mainHalfTab.getTabCount(); i++) {
				if(mainHalfTab.getTabComponentAt(i).getName().equals(selectedNode.getName())) {		// ako je vec otvoren selektuj ga
					mainHalfTab.setSelectedIndex(i);
					return;
				}
			}
		}
		
		if(e.getClickCount() == 2 && selectedNode.getType() > 2) {					//doubleclick

			if(!mainHalfTab.isVisible())
				mainHalfTab.setVisible(true);	// otvorena je novi tab, prikazi celu pomponentu

			for(int i = 0; i < mainHalfTab.getTabCount(); i++) {
				
				if(mainHalfTab.getTabComponentAt(i).getName().equals(selectedNode.getName())) {		// ako je vec otvoren selektuj ga
					mainHalfTab.setSelectedIndex(i);
					return;
				}
			}
			
			subTables.clear();									// reset liste podtabela
			calcSubTables(tree.getModel(), selectedNode);		// pronadji sve podtabele
			
			if(subTables.size() > 0) {
				mainHalfTab.addNewTab(selectedNode.getName(), subTables);	// dodaj split za gornji tabi tabelu, i donje tabovei tabele
			} else {
				mainHalfTab.addNewTab(selectedNode.getName());				// dodaj samo gornji tab i tabelu
			}
			Loc.getInstance().registerSetText(StatusBar.getStatLab2(), "state.active");
			mainHalfTab.setSelectedIndex(mainHalfTab.getTabCount()-1);

		}
	}
	
	/**
	 * Pomocna metoda, koja za otvorenu tabelu proverava da li ona im svoje podtabele. One koje je pronasla
	 * vezuje na odgovarajuce mesto u podtabu.
	 */
	private void calcSubTables(TreeModel treeModel, Object o) {			// calculate Sub Tables
		int count = treeModel.getChildCount(o);
		for( int i = 0; i < count; i++) {
			NodeTree child = (NodeTree) treeModel.getChild(o, i);
			if(treeModel.isLeaf(child) && child.getType() == 5) {		// nije list stabla, a dubina je veca od 2(nije wspace, nazproj, i paket)
				this.subTables.add(child);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
