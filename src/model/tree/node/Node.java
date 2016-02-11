package model.tree.node;

import java.util.ArrayList;

import localisation.Loc;
import localisation.LocaliseName;

/**
 * Klasa modeluje izgled pojedinacnog cvora. 
 * Sadrzi odgovarajuce atribute kako bi ispratila strukturu objekta unutar JSON datoteke.
 * Osnovna je struktura pomocu koje se dobijaju podaci o cvorovima.
 * 
 * @author NemanjaM
 */
public class Node {
	
	/**
	 * Naziv cvora, tacno onakav kakav se koristi u bazi podataka
	 */
	private String name;
	
	/**
	 * Tip cvora (0 - korenski; 1 - osnovni paket; 2 - paket; 3 - tabela; 4 - kolona tabele)
	 */
	private int type;
	
	/**
	 * Naziv cvora, koji ce biti ispisivan u okviru interfejsa
	 */
	private String displayName;
	
	/**
	 * Spisak {@code name} atributa svih cvorova koji su direktno podredjeni datom cvoru, a nisu kolone 
	 */
	private ArrayList<String> children;	
	
	/**
	 * Spisak {@code name} atributa svih kolona koje su direktno podredjene datom cvoru
	 */
	private ArrayList<String> columns;	
	
	public Node() {
		super();
	}

	public Node(String name, int type, String displayName) {
		super();
		this.name = name;
		this.type = type;
		this.displayName = displayName;
		this.children = new ArrayList<String>();
		this.columns = new ArrayList<String>();
	}

	/**
	 * Metoda ima ulogu da na osnovu {@code Node} objekta nad kojim se izvrsava, kreira reciprocan objekat klase {@code NodeTree}.
	 * Atributi koje ce novonastali objekat preuzeti od pocetnog su: {@code name}, {@code type} ,a {@code displayName} ce da se generise na 
	 * osnovu {@code name} u zavisnosti od aktivnog jezika, dok ce
	 * atribut {@code children} dobiti vrednost {@code null}.
	 * 
	 * @return Novonastali objekat klase {@code NodeTree}.
	 */
	public NodeTree toTreeNode() {
		ArrayList<NodeTree> children = new ArrayList<NodeTree>();
	//	LocaliseName imeLoc = new LocaliseName("");
	
	//	NodeTree element = new NodeTree(this.name, this.type, this.displayName, children);
		
		
		LocaliseName locName = new LocaliseName("");
		
		if(this.type == 3){
			Loc.getInstance().register(locName, "setName", "table."+this.name);
			NodeTree element = new NodeTree(this.name, this.type, locName.getName(), children);
			return element;
		}else if(this.type== 4){
			Loc.getInstance().register(locName, "setName", "table.column."+this.name);
			NodeTree element = new NodeTree(this.name, this.type, locName.getName(), children);
			return element;
		}else if((this.type == 1) || (this.type==2) || (this.type==0)){
			Loc.getInstance().register(locName, "setName", ""+this.name);
			NodeTree element = new NodeTree(this.name, this.type, locName.getName(), children);

			return element;
		}
		else{
			NodeTree element = new NodeTree(this.name, this.type, this.displayName, children);
			return element;
		}

		
		//return element;
	}
	
	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public String getDisplayName() {
		return displayName;
	}

	public ArrayList<String> getChildren() {
		return children;
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public int getChildCount() {
		return children.size();
	}

	public int getColumnCount() {
		return columns.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setChildren(ArrayList<String> children) {
		this.children = children;
	}

	public void setColumns(ArrayList<String> columns) {
		this.columns = columns;
	}

	public void addChild(String child) {
		this.children.add(child);
	}

	public void addColumn(String column) {
		this.columns.add(column);
	}
	
	public boolean equals(Node node) {
		if ((this == null && node != null) || (this != null && node == null))
			return false;
		if (this.name.equals(node.getName()) && this.type == node.getType() && this.displayName.equals(node.getDisplayName())) {
			for (String string : this.children) {
				if (!node.getChildren().contains(string))
					return false;
			}
			for (String string : this.columns) {
				if (!node.getColumns().contains(string))
					return false;
			}
			return true;
		}
		return false;
	}
}
