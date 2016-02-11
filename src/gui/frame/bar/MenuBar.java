package gui.frame.bar;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import controller.actions.frame.ActionAboutMt;
import controller.actions.frame.ActionCopyMt;
import controller.actions.frame.ActionCreateTable;
import controller.actions.frame.ActionCutMt;
import controller.actions.frame.ActionDeleteMt;
import controller.actions.frame.ActionDetailedReport;
import controller.actions.frame.ActionExitMt;
import controller.actions.frame.ActionFullScreen;
import controller.actions.frame.ActionHelpMt;
import controller.actions.frame.ActionNewMt;
import controller.actions.frame.ActionPasteMt;
import controller.actions.frame.ActionPreferences;
import controller.actions.frame.ActionQuickReport;
import controller.actions.frame.ActionSelectAllMt;
import controller.actions.frame.localisation.ActionEnglishMt;
import controller.actions.frame.localisation.ActionSerbianMt;
import localisation.Loc;


@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	public MenuBar() {
		super();
		addMenuBarFile();
		addMenuBarEdit();
		addMenuBarView();
		addMenuBarFormat();
		addMenuBarTools();
		addMenuBarReports();
		addMenuBarHelp();
	}
	
	private void addMenuBarFile() {
		JMenu mFile = new JMenu();
		Loc.getInstance().registerSetText(mFile, "action.file");
		
		ActionNewMt mfNew = new ActionNewMt();
		
		ActionExitMt mfExit = new ActionExitMt();

		mFile.add(mfNew);
		mFile.addSeparator();		
		mFile.add(mfExit);		
				
		add(mFile);
	}
	
	private void addMenuBarEdit() {
		JMenu mEdit = new JMenu();
		Loc.getInstance().registerSetText(mEdit, "action.edit");
		
		ActionCutMt meCut = new ActionCutMt();
		ActionCopyMt meCopy = new ActionCopyMt();
		ActionPasteMt mePaste = new ActionPasteMt();
		ActionDeleteMt meDelete = new ActionDeleteMt();
		ActionSelectAllMt meSelectAll = new ActionSelectAllMt();
	
		mEdit.add(meSelectAll);
		mEdit.add(meDelete);
		mEdit.addSeparator();
		mEdit.add(meCut);
		mEdit.add(meCopy);
		mEdit.add(mePaste);	
				
		add(mEdit);
	}

	private void addMenuBarView() {
		JMenu mView = new JMenu();
		Loc.getInstance().registerSetText(mView, "action.view");
		
		ActionFullScreen mvFullScreen = new ActionFullScreen();

		mView.add(mvFullScreen);
		
		mView.addSeparator();
				
		add(mView);
	}
	private void addMenuBarFormat() {
		JMenu mFormat = new JMenu();
		Loc.getInstance().registerSetText(mFormat, "action.format");
		
		ActionCreateTable mfCreateTable = new ActionCreateTable();

		mFormat.add(mfCreateTable);
		
		mFormat.addSeparator();
				
		add(mFormat);		
	}
	
	private void addMenuBarTools() {
		JMenu mTools = new JMenu();
		Loc.getInstance().registerSetText(mTools, "action.tools");
		
		ActionPreferences mtPreferences = new ActionPreferences();
		JMenu mtLanguages = new JMenu();
		Loc.getInstance().registerSetText(mtLanguages, "action.languages");
		mtLanguages.setIcon(new ImageIcon("images/ico/geography.png"));

		ActionSerbianMt mtSerbian = new ActionSerbianMt();
		ActionEnglishMt mtEnglish = new ActionEnglishMt();
		mtLanguages.add(mtSerbian);
		mtLanguages.add(mtEnglish);
		mTools.add(mtLanguages);
		mTools.add(mtPreferences);
		
		mTools.addSeparator();
			
		add(mTools);
	}
	
	private void addMenuBarReports() {
		JMenu mReports = new JMenu();
		Loc.getInstance().registerSetText(mReports, "action.reports");

		JMenu mtReport = new JMenu();
		Loc.getInstance().registerSetText(mtReport, "action.create_report");
		
	
		ActionQuickReport mtrQuick = new ActionQuickReport();
		ActionDetailedReport mtrDetailed = new ActionDetailedReport();
		
		mtReport.add(mtrQuick);
		mtReport.add(mtrDetailed);		
		mReports.add(mtReport);
		
		mReports.addSeparator();
			
		add(mReports);
	}
	
	private void addMenuBarHelp() {
		JMenu mHelp = new JMenu();
		Loc.getInstance().registerSetText(mHelp, "action.help");
		
	
		ActionHelpMt mhHelp = new ActionHelpMt();
		ActionAboutMt mhAbout = new ActionAboutMt();
		
		mHelp.add(mhHelp);
		mHelp.add(mhAbout);

		mHelp.addSeparator();
		
		add(mHelp);
	}

}
