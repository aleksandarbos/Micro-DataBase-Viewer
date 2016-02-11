package test.model.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.tree.NodeList;
import model.tree.node.Node;

/**
 * 
 * @author NemanjaM
 *
 */
@RunWith(Parameterized.class)
public class NodeListTest {
	
	private NodeList nodeList;
	private HashMap<String, Node> map;
	private String path;

	@Parameters
	public static Collection<Object[]> data() {
		Node node1 = new Node("ime1", 0, "Ime1");
		Node node2 = new Node("ime2", 1, "Ime2");
		Node node3 = new Node("ime3", 1, "Ime3");
		node2.addChild("ime3");

		ArrayList<Node> list1 = new ArrayList<>();
		ArrayList<Node> list2 = new ArrayList<>();
		ArrayList<Node> list3 = new ArrayList<>();
		list2.add(node1);
		list3.add(node1);
		list3.add(node2);
		list3.add(node3);
		NodeList nodeList1 = new NodeList();
		NodeList nodeList2 = new NodeList();
		NodeList nodeList3 = new NodeList();
		nodeList1.setList(list1);
		nodeList2.setList(list2);
		nodeList3.setList(list3);

		HashMap<String, Node> map1 = new HashMap<>();
		HashMap<String, Node> map2 = new HashMap<>();
		HashMap<String, Node> map3 = new HashMap<>();
		map2.put(node1.getName(), node1);
		map3.put(node1.getName(), node1);
		map3.put(node2.getName(), node2);
		map3.put(node3.getName(), node3);
		
		String path1 = "testData/test1.json";
		String path2 = "testData/test2.json";
		String path3 = "testData/test3.json";
		
		return Arrays.asList(new Object[]{nodeList1, map1, path1},
							 new Object[]{nodeList2, map2, path2},
							 new Object[]{nodeList3, map3, path3});
	}
	
	public NodeListTest(NodeList nodeList, HashMap<String, Node> map, String path) {
		this.nodeList = nodeList;
		this.map = map;
		this.path = path;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadJSON() {
		NodeList nodeListResult = null;
		nodeListResult = NodeList.readJSON(path);
		Assert.assertTrue(nodeListResult.equals(nodeList));
	}
	
	/**
	 * Test za metodu {@link NodeList#toMap}
	 */
	@Test
	public void testToMap() {
		HashMap<String, Node> mapResult = new HashMap<>();
		mapResult = nodeList.toMap();		
		Assert.assertEquals(map, mapResult);
	}

	/**
	 * Test za metodu {@link NodeList#equals}
	 * 
	 * Postoji greska u reviziji 171, prilikom pokretanja prvog, drugog i treceg test primera<br/>
	 * Reseno u reviziji 172 pa nadalje:<br/>- dodata je druga {@code foreach} petlja
	 */
	@Test
	public void testEquals() {
		Node node1 = new Node("ime1", 0, "Ime1");
		Node node2 = new Node("ime2", 1, "Ime2");
		Node node3 = new Node("ime3", 1, "Ime3");
		ArrayList<Node> list1 = new ArrayList<>();
		ArrayList<Node> list2 = new ArrayList<>();
		ArrayList<Node> list3 = new ArrayList<>();
		ArrayList<Node> list4 = new ArrayList<>();
		list1.add(node1); 
		list1.add(node2);
		list1.add(node3);
		list2.add(node1); 
		list2.add(node3);
		list3.add(node1); 
		list3.add(node2);
		list3.add(node3);		
		
		NodeList nodeList1 = new NodeList();
		NodeList nodeList2 = new NodeList();
		NodeList nodeList3 = new NodeList();
		NodeList nodeList4 = new NodeList();
		nodeList1.setList(list1);
		nodeList2.setList(list2);
		nodeList3.setList(list3);
		nodeList4.setList(list4);
		
		Assert.assertFalse(nodeList1.equals(nodeList2));
		Assert.assertTrue(nodeList1.equals(nodeList3));
		Assert.assertFalse(nodeList1.equals(nodeList4));
		Assert.assertFalse(nodeList2.equals(nodeList3));
	}

}
