package model.tree;

import javax.swing.tree.DefaultTreeModel;

/** 
 * TreeModel je kao sto i samo ime kazuje model buduceg stabla koji se kreira
 * na osnovu prosledjenih vrednosti dobijenih iscitavanjem iz JSON fajla.
 * Ove iscitane vrednosti predstavljaju cvorove buduceg stabla. 
 * Nastali model se prosledjuje klasi stabla, koja generise isto.
 * 
 * @author Violeta
 *
 */

@SuppressWarnings("serial")
public class TreeModel  extends DefaultTreeModel {

	/**
	 * Sadrzi u sebi objekat sa neophodnim podacima o samom cvoru,
	 * naziv kljuca, ime za ispis, tip, listu dece, listu kolona.
	 */
	 static JsonModel wsp = new JsonModel();
	
	public TreeModel() {
		super(wsp.getNodes());
	}
	
	public TreeModel(JsonModel jsn)
	{
		super(jsn.getNodes());
	}
	
	public JsonModel getWorksapce() {
		return wsp;
	}
	
	public void setWorkspace(){
		TreeModel.wsp = new JsonModel();
	}
	

}

