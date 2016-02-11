package gui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.TreeSelectionModel;

import controller.listeners.MainFormListener;
import gui.MainTabbedPane;
import gui.frame.bar.MenuBar;
import gui.frame.bar.StatusBar;
import gui.frame.bar.ToolBar;
import gui.imagebackgrounds.ImageBackgroundPanel;
import gui.tree.TreePane;
import localisation.Loc;
import model.tree.JsonModel;
import model.tree.TreeModel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static MainFrame instance = null;
	private MenuBar menu;
	private ToolBar toolbar;
	private StatusBar statusBar;
	private JSplitPane splitterV;
	private JScrollPane leftPane;
	private TreePane tree;
	private MainTabbedPane mainTabHalf;
	private TreeModel treeModel;
	
	private MainFrame() {
		super();
	}

	private void initialize() {
		setFrame();
		Loc.getInstance().registerSetTitle(this, "mainframe.title");
		setIconImage(new ImageIcon("images/database.png").getImage());
		
		menu = new MenuBar();
		setJMenuBar(menu);
		
		toolbar = new ToolBar();
		add(toolbar, BorderLayout.NORTH);
		
		statusBar = new StatusBar();
		add(statusBar, BorderLayout.SOUTH);

		mainTabHalf = MainTabbedPane.getInstance();
		mainTabHalf.setVisible(false);
		
		ImageBackgroundPanel background = new ImageBackgroundPanel("images/background.jpg");
		background.add(mainTabHalf);
		
		treeModel = new TreeModel();
		tree = new TreePane();
		tree.setModel(treeModel);
		
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		leftPane = new JScrollPane(tree);		
		splitterV = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftPane,background);
		splitterV.setDividerLocation(200);
		add(splitterV);
		
	}
	
	private void setFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenDimensions = kit.getScreenSize();
		
	
		setSize(screenDimensions.width*3/4,screenDimensions.height*5/6);
	
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Windows look and feel unsupported.");
		}
		addWindowListener(new MainFormListener());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public static MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.initialize();
		}
		return instance;
	}

	public TreePane getTree() {
		return tree;
	}

	public void setTreeModel(){
		this.tree.setModel(new TreeModel());
		
	}
	
	public void setTreePane(){
		//this.tree = new TreePane();
		
		TreeModel tr = new TreeModel();
		tr.setWorkspace();
		JsonModel jsm = tr.getWorksapce();
		TreeModel tr1 = new TreeModel(jsm);
		
		
		this.tree.setModel(tr1);
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		SwingUtilities.updateComponentTreeUI(tree);
	}
	
	public MainTabbedPane getMainTabHalf() {
		return mainTabHalf;
	}
	
	public StatusBar getStatusBar(){
		return statusBar;
	}

	public TreeModel getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(TreeModel treeModel) {
		this.treeModel = treeModel;
	}
	
	
}
