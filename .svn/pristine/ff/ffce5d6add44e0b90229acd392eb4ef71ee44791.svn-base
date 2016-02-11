package test.model.tree.node;

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

import model.tree.node.Node;
import model.tree.node.NodeTree;

/**
 * 
 * @author NemanjaM
 *
 */
@RunWith(Parameterized.class)
public class NodeTreeTest {

	private NodeTree node;
	private boolean nullExpected;
	private HashMap<String, Node> map;
	private int childrenCount;
	private boolean isLeaf;
	
	@Parameters
	public static Collection<Object[]> data() {
		// ZA PRVI TEST
		ArrayList<NodeTree> list11 = new ArrayList<>();
		ArrayList<NodeTree> list12 = new ArrayList<>();
		ArrayList<NodeTree> list13 = new ArrayList<>();
		ArrayList<NodeTree> list14 = new ArrayList<>();
		ArrayList<NodeTree> list15 = new ArrayList<>();
		ArrayList<NodeTree> list16 = new ArrayList<>();		
		NodeTree node11 = new NodeTree("ime1", 0, "Ime1", list11);
		NodeTree node12 = new NodeTree("ime2", 1, "Ime2", node11, list12);
		NodeTree node13 = new NodeTree("ime3", 1, "Ime3", node11, list13);
		NodeTree node14 = new NodeTree("ime3", 2, "Ime3", node12, list14);
		NodeTree node15 = new NodeTree("ime3", 2, "Ime3", node12, list15);
		NodeTree node16 = new NodeTree("ime3", 2, "Ime3", node12, list16);
		list11.add(node12);
		list11.add(node13);
		list12.add(node14);
		list12.add(node15);
		list12.add(node16);

		Node node11b = new Node("ime1", 0, "Ime1");
		Node node12b = new Node("ime2", 1, "Ime2");
		Node node13b = new Node("ime3", 1, "Ime3");
		Node node14b = new Node("ime4", 2, "Ime4");
		Node node15b = new Node("ime5", 2, "Ime5");
		Node node16b = new Node("ime6", 2, "Ime6");
		ArrayList<String> list11b = new ArrayList<>();
		ArrayList<String> list12b = new ArrayList<>();
		ArrayList<String> list13b = new ArrayList<>();
		ArrayList<String> list14b = new ArrayList<>();
		ArrayList<String> list15b = new ArrayList<>();
		ArrayList<String> list16b = new ArrayList<>();
		list11b.add("ime2");
		list11b.add("ime3");
		list12b.add("ime4");
		list12b.add("ime5");
		list12b.add("ime6");
		node11b.setChildren(list11b);
		node12b.setChildren(list12b);
		node13b.setChildren(list13b);
		node14b.setChildren(list14b);
		node15b.setChildren(list15b);
		node16b.setChildren(list16b);

		HashMap<String, Node> map1 = new HashMap<>();
		map1.put(node11b.getName(), node11b);
		map1.put(node12b.getName(), node12b);
		map1.put(node13b.getName(), node13b);
		map1.put(node14b.getName(), node14b);
		map1.put(node15b.getName(), node15b);
		map1.put(node16b.getName(), node16b);
		
		// ZA DRUGI TEST
		ArrayList<NodeTree> list21 = new ArrayList<>();		
		NodeTree node21 = new NodeTree("ime1", 0, "Ime1", list21);

		Node node21b = new Node("ime1", 0, "Ime1");
		ArrayList<String> list21b = new ArrayList<>();
		node21b.setChildren(list21b);

		HashMap<String, Node> map2 = new HashMap<>();
		map2.put(node21b.getName(), node21b);
		
		// ZA TRECI TEST		
		NodeTree node31 = new NodeTree();
		HashMap<String, Node> map3 = new HashMap<>();

		return Arrays.asList(new Object[]{node11, false, map1, 2, false},
							 new Object[]{node21, false, map2, 0, true},
							 new Object[]{node31, true, map3, 0, true});
	}
	
	public NodeTreeTest(NodeTree nodeTree, boolean assuming, HashMap<String, Node> map, int children, boolean leaf) {
		this.node = nodeTree;
		this.nullExpected = assuming;
		this.map = map;
		this.childrenCount = children;
		this.isLeaf = leaf;
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
	
	/**
	 * Buduci da mi je bilo izuzetno komplikovano da napisem {@code equals} metodu za rekurzivnu strukturu kakva je {@code {@link NodeTree}
	 * vrsim asproksimaciju. Proveravam samo da li metoda vraca {@code null} ili nekakav objekat.<br/>
	 * Test za metodu {@link NodeTree#createTree}
	 * 
	 * Postoji greska u reviziji 171, prilikom pokretanja drugog i treceg test primera<br/>
	 * Reseno u reviziji 172 pa nadalje:<br/>- dopunjena je pocetna provera da li prosledjena {@code map}-a ima elemenata;<br/>
	 * - pre ulaska u {@code do - while} petlju dodata je provera da li prosledjeni korenski cvor ima dece 
	 */
	@Test
	public void testCreateTree() {
		NodeTree nodeTree;
		nodeTree = NodeTree.createTree(map);
		if (nullExpected)
			Assert.assertNull(nodeTree);
		else
			Assert.assertNotNull(nodeTree);
	}

	/**
	 * Test za metodu {@link NodeTree#getChildCount}
	 */
	@Test
	public void testGetChildCount() {
		int result;
		result = node.getChildCount();
		Assert.assertEquals(childrenCount, result);
	}

	/**
	 * Test za metodu {@link NodeTree#isLeaf}
	 * 
	 * Postoji greska u reviziji 171, prilikom pokretanja treceg test primera<br/>
	 * Reseno u reviziji 172 pa nadalje:<br/>- dodata je provera da li je lista dece ({@code children}) jednaka {@code null}
	 */
	@Test
	public void testIsLeaf() {
		boolean result;
		result = node.isLeaf();
		Assert.assertEquals(isLeaf, result);
	}

}
