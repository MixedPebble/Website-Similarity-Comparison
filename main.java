package database_project;
/**
 * Test from others work. permissions given
 * Used to see how the GUI works
 */
import javax.swing.*;


//NOTE: To get this program to work you MUST create a ".sources.txt" file \
//Fll it with at least one website. Each website gets its own line.
//Example: in .sources.txt
//https://www.technologyreview.com/s/520446/the-decline-of-wikipedia/

public class main {
	public static void main(String[] a) {
		SwingUtilities.invokeLater(new Runnable() {
									   public void run() {
										   SwingGUI.MakeGUI();
									   }
								   }
		);
	}
}
