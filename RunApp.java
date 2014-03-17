package org.open.ide;

import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager.*;

public class RunApp {
	
	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
				
				//Set Nimbus Look and Feel 
				try {
				    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
				} catch (Exception e) {
				    // If Nimbus is not available, you can set the GUI to another look and feel.
				}

				new MainView();
			}
		});
	}

}
