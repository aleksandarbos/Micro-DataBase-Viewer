package model.tree;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.tree.node.Node;

/**
 * Struktura klase odgovara strukturi objekta JSON datoteke. 
 * Njena uloga je da prikupi sve podatke koji se nalaze u ovoj datoteci, i da ih prosledi na dalju obradu.
 * 
 * @author NemanjaM
 *
 */
public class NodeList {
	
	/**
	 * Parametar koji u sebi sadrzi listu objekata klase {@code Node}, vise informacija o ovoj klasi {@link Node}
	 */
	private ArrayList<Node> list;
	
	public NodeList() {
		super();
	}
	
	public NodeList(ArrayList<Node> list) {
		super();
		this.list = list;
	}
	
	/**
	 * Metoda sluzi kako bi se procitao JSON fajl i vratio objekat klase {@code NodeList}, vise informacija o ovoj klsi {@link NodeList}
	 * Metoda je staticka, sto znaci da se poziva na sledeci nacin: <br/> {@code NodeList ime_liste = NodeList.readJSON(putanja_do_JSON_fajla);}
	 * 
	 * @param  path Putanja do datoteke u kojoj se nalazi JSON string
	 * @return Objekat klase {@code NodeList} koji u sebi sadrzi listu svih cvorova definisanih u JSON datoteci
	 */
	public static NodeList readJSON(String path) {		
		ObjectMapper mapper = new ObjectMapper();
		NodeList nl = null;
		try {
			nl = mapper.readValue(new File(path), NodeList.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nl;
	}
	
	/**
	 * Metoda sluzi kako bi se elementi iz {@code list} atributa ove klase smestili u mapu. Razlog prebacivanja elemenata iz strukture
	 * liste u strukturu mape je povecanje brzine pretrage i pristupa sadrzaju cvorova na osnovu njihovih imena ({@code name} atributa). 
	 * 
	 * @return Ukoliko je {@code list} atribut ove klase popunjen metoda vraca objekat klase {@code HashMap<String, Node>}
	 *  gde je kljuc {@code name} atribut cvora koji je tu smesten kao vrednost. U suprotnom, povratna vrednost metode je {@code null}.
	 * @see {@link java.util.HashMap}
	 * @see {@link Node}
	 */
	public HashMap<String, Node> toMap() {
		if (this.list == null)
			return null;
		
		HashMap<String, Node> map = new HashMap<String, Node>();
		
		for (Node node : this.list)
			if (!map.containsKey(node.getName()))
				map.put(node.getName(), node);
		
		return map;
	}

	public ArrayList<Node> getList() {
		return list;
	}

	public void setList(ArrayList<Node> list) {
		this.list = list;
	}
	
	/**
	 * Metoda vrsi proveru da li su element kalse {@code NodeList} nad kojim se poziva metoda, i prosledjeni
	 * element identicni. Funkcija vraca {@code true} ukoliko su identicni, a u suprotnom vraca {@code false}.
	 * 
	 * @param nodeList element sa kojim se poredi {@code this} objekat
	 * @return povratna vrednost {@code boolean} tipa koja da je informaciju da li su elementi identicni
	 */
	public boolean equals(NodeList nodeList) {
		boolean found = false;
		for (Node nodeForein : nodeList.getList()) {
			found = false;
			for (Node nodeOriginal: this.list) {
				if (nodeOriginal.equals(nodeForein)) {
					found = true;
					break;
				}
			}
			if (!found)
				return false;
		}
		for (Node nodeForein : this.list) {
			found = false;
			for (Node nodeOriginal : nodeList.getList()) {
				if (nodeOriginal.equals(nodeForein)) {
					found = true;
					break;
				}
			}
			if (!found)
				return false;
		}
		return true;
	}
	

	
		
		
	
}