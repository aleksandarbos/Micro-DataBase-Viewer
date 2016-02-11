package model.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;

import model.tree.node.Node;
import model.tree.node.NodeTree;

@SuppressWarnings("serial")
public class JsonModel extends DefaultMutableTreeNode {

	public static HashMap<String, Node> map;
	public NodeTree nodes;
	private NodeList list = new NodeList();
	
	public JsonModel() {
		nodes = new NodeTree();
		map = new HashMap<String, Node>();
		list = NodeList.readJSON("JsonTim.json");
		map = list.toMap();
		// provera nije potrebna
		//if (!NodeList.isMapNodesCorrect(map))
		//	System.exit(0);
		nodes = NodeTree.createTree(map);
	}
	
	public NodeTree getNodes() {
		return nodes;
	}
	
	public NodeList getNodeList(){
		return this.list;
	}
	

	public static Vector<String> getCommonColumns(String table1, String table2) {
		Vector<String> result = new Vector<String>();
		
		ArrayList<String> columns1 = map.get(table1).getChildren();
		ArrayList<String> columns2 = map.get(table2).getChildren();
		
		for (String column1 : columns1)
			for (String column2 : columns2)
				if (column1.equals(column2) && !result.contains(column1))
					result.addElement(column1);
		
		if (result.size() == 0)
			result = null;
		
		return result;
	}
}
