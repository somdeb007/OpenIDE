package org.open.ide;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.plaf.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rsyntaxtextarea.templates.*;
import net.miginfocom.swing.MigLayout;

class CloseIcon implements Icon {
	  public void paintIcon(Component c, Graphics g, int x, int y) {
	    g.setColor(Color.RED);
	    g.drawLine(6, 6, getIconWidth() - 7, getIconHeight() - 7);
	    g.drawLine(getIconWidth() - 7, 6, 6, getIconHeight() - 7);
	  }
	  public int getIconWidth() {
	    return 17;
	  }
	  public int getIconHeight() {
	    return 17;
	  }
	}

class CloseTabButton extends JPanel {
	private JTabbedPane pane;
	  public CloseTabButton(JTabbedPane pane, int index) {
	    this.pane = pane;
	    setOpaque(false);
	    add(new JLabel(
	        pane.getTitleAt(index),
	        pane.getIconAt(index),
	        JLabel.LEFT));
	    Icon closeIcon = new CloseIcon();
	    JButton btClose = new JButton(closeIcon);
	    btClose.setPreferredSize(new Dimension(
	        closeIcon.getIconWidth(), closeIcon.getIconHeight()));
	    add(btClose);
	    //btClose.addActionListener(this);
	    pane.setTabComponentAt(index, this);
	  }
	 /* public void actionPerformed(ActionEvent e) {
	    int i = pane.indexOfTabComponent(this);
	    if (i != -1) {
	      pane.remove(i);
	    }
	  }*/
	}

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame("Open BI");
	JPanel panel = new JPanel();
	JMenuBar bar ;
	JMenu menu ;
	JMenu menu2;
	JMenu menu3;
	JMenu menu4;
	JMenuItem item ;
	JMenuItem fileItem;
	JMenuItem endOfLine;
	JMenuItem versionGit;
	JMenuItem brs;
	JMenuItem timeTracker;
	JMenuItem run;
	
	JTabbedPane tbbdPn;
	JTextArea txtArea;
	JScrollPane scrollPane;
	Icon closeIcon;
	JButton btnClose;
	
	
	
	JTree  m_tree;
	DefaultTreeModel m_model;
	JTextField m_display;
	
	public static final ImageIcon ICON_COMPUTER = new ImageIcon("rsc/computer.png");
    public static final ImageIcon ICON_DISK = new ImageIcon("rsc/hard_drive.png");
    public static final ImageIcon ICON_FOLDER = new ImageIcon("rsc/normal_folder.png");
    public static final ImageIcon ICON_EXPANDEDFOLDER = new ImageIcon("rsc/opened_folder.png");
	
	//View Constructor
	public MainView(){
		
		//Initialize Menu
		bar = new JMenuBar();
		
		//File
		menu = new JMenu("File");
		
		//New Project, SQL, Script
		fileItem = new JMenu("New");
		fileItem.add(new JMenuItem("Project"));
		fileItem.add(new JMenuItem("Sql File"));
		fileItem.add(new JMenuItem("Shell Script"));
		menu.add(fileItem);
		//New Project, SQL, Script
		
		menu.add(new JMenuItem("Open"));
		menu.add(new JMenuItem("Save"));
		menu.add(new JMenuItem("Save As..."));
		menu.add(new JMenuItem("Exit"));
		//File
		
		//Edit
		menu3 = new JMenu("Edit");
		menu3.add(new JMenuItem("Undo"));
		menu3.add(new JMenuItem("Redo"));
		menu3.add(new JMenuItem("Cut"));
		menu3.add(new JMenuItem("Copy"));
		menu3.add(new JMenuItem("Paste"));
		menu3.add(new JMenuItem("Find/Replace"));
		menu3.add(new JMenuItem("Find Next"));
		menu3.add(new JMenuItem("Find Previous"));
		//menu3.add(new JMenuItem("End Of Line"));
		
		//End Of Line
		endOfLine = new JMenu("End of Line");
		endOfLine.add(new JMenuItem("Unix Format"));
		endOfLine.add(new JMenuItem("Windows Format"));
		menu3.add(endOfLine);
		//End Of Line
        
		//Language Sub Menu
		item = new JMenu("Language");
		item.add(new JMenuItem("Sql"));
		item.add(new JMenuItem("Shell"));
		menu3.add(item);
		//Language Sub Menu
		
		//Edit
		
		//Project
		menu4 = new JMenu("Project");
		
		//Git Hub
		versionGit = new JMenu("GIT");
		versionGit.add(new JMenuItem("Fork"));
		versionGit.add(new JMenuItem("Commit"));
		versionGit.add(new JMenuItem("Push to Origin"));
		versionGit.add(new JMenuItem("Push to Upstream"));
		menu4.add(versionGit);
		//Git Hub
		
		//BRS
		brs = new JMenu("Business Requirement Document");
		brs.add(new JMenuItem("Map Path"));
		brs.add(new JMenuItem("Open"));
		brs.add(new JMenuItem("Save"));
		brs.add(new JMenuItem("Save As..."));
		menu4.add(brs);
		//BRS
		
		
		//Time Tracker
		timeTracker = new JMenu("IceScrum");
		timeTracker.add(new JMenuItem("Map Project"));
		timeTracker.add(new JMenuItem("Map Task"));
		timeTracker.add(new JMenuItem("Update Task Time"));
		timeTracker.add(new JMenuItem("Post Comment"));
		timeTracker.add(new JMenuItem("Create New Task"));
		menu4.add(timeTracker);
		//Time Tracker
		
		//Project
		
		//Run
		run = new JMenu("Run");
		run.add(new JMenuItem("Run Sql"));
		run.add(new JMenuItem("Run Shell Script"));
		//Run
		
		//About
		menu2 = new JMenu("Help");
		menu2.add(new JMenuItem("About"));
		//About
		
		bar.add(menu);
		bar.add(menu3);
		bar.add(menu4);
		bar.add(run);
		bar.add(menu2);
		//Initialize Menu
		
		//Project Explorer
		
		//Add Code Later for Project Creation
	    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Sample Project");
	    
	    //Add Script Directory
	    DefaultMutableTreeNode Script = new DefaultMutableTreeNode("Script");
	    root.insert(Script, 0);
	    DefaultMutableTreeNode Script1 = new DefaultMutableTreeNode("Script1");
	    Script.insert(Script1, 0);
	    //Add Script Directory
	    
	    //Add Sql Directory
	    DefaultMutableTreeNode Sql = new DefaultMutableTreeNode("Sql");
	    root.insert(Sql, 1);
	    DefaultMutableTreeNode sqlScript1 = new DefaultMutableTreeNode("sqlScript1");
	    Sql.insert(sqlScript1,0);
	    DefaultMutableTreeNode sqlScript2 = new DefaultMutableTreeNode("sqlScript2");
	    Sql.insert(sqlScript2,1);
	    //Add Sql Directory
	    JTree tree = new JTree(root);
	    JScrollPane scrollPane = new JScrollPane(tree);
	    
		//Project Explorer
	    
	    //Tabbed Pane
	    
/*	    tbbdPn = new JTabbedPane();
	    txtArea = new JTextArea();
	    
	    tbbdPn.add("Script", txtArea);
	    new CloseTabButton(tbbdPn,0);
	    
	    txtArea = new JTextArea();
	    tbbdPn.add("Sql", txtArea);
	    new CloseTabButton(tbbdPn,1);*/
	    
	    tbbdPn = new JTabbedPane();
	    
	      RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
	      textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
	      textArea.setCodeFoldingEnabled(true);
	      textArea.setAntiAliasingEnabled(true);
	      RTextScrollPane sp = new RTextScrollPane(textArea);
	      
	    tbbdPn.add("Sql", sp);
	    new CloseTabButton(tbbdPn,0);
	    
	      RSyntaxTextArea textArea1 = new RSyntaxTextArea(20, 60);
	      textArea1.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_UNIX_SHELL);
	      textArea1.setCodeFoldingEnabled(true);
	      textArea1.setAntiAliasingEnabled(true);
	      RTextScrollPane sp1 = new RTextScrollPane(textArea1);
	    
	    tbbdPn.add("Script", sp1);
	    new CloseTabButton(tbbdPn,1);
	    //Tabbed Pane
		
		
		//Add Components to Panel
		panel.setLayout(new MigLayout("insets 10"));
		panel.add(bar, "w 1400!, north");
		panel.add(scrollPane, "h 500!, w 200!, west");
		panel.add(tbbdPn, "h 500!, w 950!, west");
		//Add Components to Panel
		
		//Add Panel to Frame
		frame.add(panel);
		//Add Panel toFrame
		
		//Set Frame Defaults
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(1400, 800);
		frame.setVisible(true);
		//Set Frame Defaults
		

		
	}
}
