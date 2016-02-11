package gui.frame.bar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.actions.frame.ActionCopyMt;
import controller.actions.frame.ActionCreateTable;
import controller.actions.frame.ActionCutMt;
import controller.actions.frame.ActionDeleteMt;
import controller.actions.frame.ActionDetailedReport;
import controller.actions.frame.ActionExitMt;
import controller.actions.frame.ActionNewMt;
import controller.actions.frame.ActionPasteMt;
import controller.actions.frame.ActionQuickReport;
import controller.actions.frame.ActionRandom;
import localisation.Loc;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	
	public ToolBar() {
		super(SwingConstants.HORIZONTAL);
		
		areaTree();
		areaFile();
		areaEdit();
		areaFormat();
		areaReport();

		setFloatable(false);
	}

	private void areaTree() {
		ButtonGroup toggles = new ButtonGroup();
		JLabel lab = new JLabel();
		Loc.getInstance().registerSetText(lab, "tb.showtree");
		
		JToggleButton btAll = new JToggleButton(null, new ImageIcon("images/ico/visible-36.png"));
		JToggleButton btUniversal = new JToggleButton(null, new ImageIcon("images/ico/showGroup.png"));
		JToggleButton btProject = new JToggleButton(null, new ImageIcon("images/ico/showProject.png"));
		
		Loc.getInstance().registerSetText(btAll, "action.all");
		Loc.getInstance().registerSetText(btUniversal, "action.mutual");
		Loc.getInstance().registerSetText(btProject, "action.project");

		
		
		toggles.add(btAll);
		toggles.add(btUniversal);
		toggles.add(btProject);
		
		add(lab);
		add(btAll);
		add(btUniversal);
		add(btProject);
		
		addSeparator();
	}

	private void areaFile() {
		ActionNewMt btNew = new ActionNewMt();
		ActionExitMt btExit = new ActionExitMt();

		//btNew.setBorderPainted(false);
		//btNew.setContentAreaFilled(false);
		add(btNew);
		add(btExit);
		
		addSeparator();
	}
	
	private void areaEdit() {
		
		ActionCutMt btCut = new ActionCutMt();
		ActionCopyMt btCopy = new ActionCopyMt();
		ActionPasteMt btPaste = new ActionPasteMt();
		ActionDeleteMt btDelete = new ActionDeleteMt();

		add(btCut);
		add(btCopy);
		add(btPaste);
		add(btDelete);
		
		addSeparator();
	}
	
	private void areaFormat() {
		
		ActionCreateTable btCreateTable = new ActionCreateTable();

		add(btCreateTable);
		
		addSeparator();
	}
	
	private void areaReport() {
		
		ActionQuickReport mtrQuick = new ActionQuickReport();
		ActionDetailedReport mtrDetailed = new ActionDetailedReport();
	
		
		JButton mtrRandom = new JButton(new ActionRandom("Button","images/ico/createReport.png", ""));
		Loc.getInstance().registerSetText(mtrRandom, "action.button");
		
		add(mtrQuick);
		add(mtrDetailed);
		//add(mtrRandom);
		addSeparator();
	}
	
}
