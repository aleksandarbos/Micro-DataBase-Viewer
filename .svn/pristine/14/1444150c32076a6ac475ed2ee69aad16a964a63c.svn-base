package model.tree.node;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.tree.TreeNode;

/**
 * Rekurzivna struktura koja je u formi stabla.
 * Sluzi kako bi se u formi stabla omogucio prikaz sadrzaja baze podataka.
 * Ova struktura ne sadrzi podatke o kolonama tabela, s toga one nece biti prikazivane.
 * 
 * @author NemanjaM
 * 
 */
public class NodeTree implements TreeNode {
	
	/**
	 * Naziv cvora, tacno onakav kakav se koristi u bazi podataka
	 */
	private String name;
	
	/**
	 * Tip cvora (0 - korenski; 1 - osnovni paket; 2 - paket; 3 - tabela; 4 - kolona tabele; 5 - precica
	 */
	private int type;
	
	/**
	 * Naziv cvora, koji ce biti ispisivan u okviru interfejsa
	 */
	private String displayName;
	
	/**
	 * Cvor koji je direktni nadredjeni datom cvoru. Ukoliko mu je vrednost {@code null} radi se o pocetnom cvoru (root)
	 */
	private NodeTree parent;
	/**
	 * Spisak cvorova (klase {@code NodeTree}) koji su direktno podredjeni datom cvoru, a nisu kolone.
	 * Ovaj element pravi ugnezdenu strukturu.
	 */
	private ArrayList<NodeTree> children;
	
	public NodeTree() {
		super();
	}
	
	/**	
	 * Konstruktor kod kog se ne prosludjuje vrednost parametra {@code parent}, td ce za njegovu vrednost biti postavljena vrednost {@code null}
	 */
	public NodeTree(String name, int type, String displayName, ArrayList<NodeTree> children) {
		super();
		this.name = name;
		this.type = type;
		this.displayName = displayName;
		this.parent = null;
		this.children = children;
	}
	
	public NodeTree(String name, int type, String displayName, NodeTree parent, ArrayList<NodeTree> children) {
		super();
		this.name = name;
		this.type = type;
		this.displayName = displayName;
		this.parent = parent;
		this.children = children;
	}
	
	/**
	 * Metoda ima ulogu da na osnovu podataka u JSON datoteci, koja reprezentuje bazu podataka, formira stablo.
	 * Stablo sadrzi hijerarhijsku strukturu baze podataka, koja ukljucuje korenski cvor, cvorove pakete, cvorove tabele i 
	 * cvorove precice tabela.<br/>
	 * Svaki od cvorova ove strukture je objekat klase {@code NodeTree}, vise informacija o ovoj klasi na {@link NodeTree}<br/>
	 * Metoda je staticka, sto znaci da se poziva na sledeci nacin: <br/> {@code NodeTree ime_korenskog_cvora = NodeTree.createTree();}
	 * 
	 * @return Povratna vrednost je pocetni cvor stabla, klase {@code NodeTree}. 
	 * Ukoliko je mapa popunjena, unutar povratne vrednosti je smesten ostatak stabla.
	 * Ukoliko mapa nije popunjena, vrednosti je {@code null}.
	 */
	public static NodeTree createTree(HashMap<String, Node> map) {
		NodeTree root = null;
		if (map == null || map.size() == 0)		// proverava se da li je mapa popunjena
			return root;		
		
		for (Node node : map.values()) {	// trazi se korenski cvor, i postavlja se kao pocetni
			if (node.getType() == 0) {
				root = node.toTreeNode();
				break;
			}
		}
		
		if (root == null)
			return root;
		
		int rootChildCount = map.get(root.getName()).getChildCount();	// pamti se koliko korenski cvor ima direktno podredjenih, ovaj podatak ce se koristiti kao uslov izlaska iz petlje
		
		if (rootChildCount == 0)
			return root;
		
		int i=1, j=0;
		
		NodeTree working = root;	// pocetni cvor se postavlja kao radni
		do {
			if (map.get(working.getName()).getChildCount() != 0) {	// ukoliko radni cvor ima podcvorova, uvodi ih; ukoliko nema, vrati se na roditeljski
				if (working.getChildCount() != map.get(working.getName()).getChildCount()) {	// ukoliko radni cvor vec sadrzi sve podcvorove, uvodi ih; ukoliko sadrzi, vrati se na roditeljski
					String childName = null;
					boolean found;
					for (String child : map.get(working.getName()).getChildren()) {		// prolazi se kroz sve podredjene cvorove radnog cvora unutar mape, za svaki od njih bice provereno da li se vec nalazi u spisku direktno pordejenih cvorova radnog cvora
						found = false;
						for (NodeTree node : working.getChildren()) {
							if (child.equals(node.getName())) {		// ukoliko je cvor vec unet kao direktno podredjeni, izlazi se iz petlje kako bi se vrsila provera za naredni cvor
								found = true;
								break;
							}
						}
						if (!found) {		// ukoliko je pronadjen cvor koji nedostaje, pamti se i izlazi se iz petlje
							childName = child;
							break;
						}
					}
					NodeTree child = map.get(childName).toTreeNode();	// kreira se cvor koji nedostaje
					if (working.getType() == 3)		// ukoliko je cvor tabela-precica, postavlja se indikator
						child.setType(5);
					child.setParent(working);
					working.addChild(child);		// cvor se dodaje kao direktno podredjeni radnom
					if (working.getType() != 3)		// ukoliko je cvor tabela-precica, postavlja se indikator
						working = child;			// novododati cvor se postavlja kao radni
					i++;
				} else {
					working = working.getParent();
				}
			} else {
				working = working.getParent();
			}
			j++;
		} while (!(working == root && root.getChildCount() == rootChildCount));		// uslov kraja je da smo se vratili u korenski cvor, i da on vec ima unete sve svoje direktno podredjene cvorove
		System.out.println("## added: " + i +" nodes, in: " + j + " iterations ##");
		
		return root;
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

	public NodeTree getParent() {
		return this.parent;
	} 

	public ArrayList<NodeTree> getChildren() {
		return children;
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

	public void setParent(NodeTree parent) {
		this.parent = parent;
	} 
	
	public void setChildren(ArrayList<NodeTree> children) {
		this.children = children;
	}
	
	public void addChild(NodeTree child) {
		this.children.add(child);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	@Override
	public int getChildCount() {
		if (children == null)
			return 0;
		else
			return children.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isLeaf() {
		if (children == null)
			return true;
		else
			return children.isEmpty();
	}
	
	public String toString(){
		return displayName;		
	}
}
