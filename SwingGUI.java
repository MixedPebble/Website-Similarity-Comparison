package database_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.*;
//This is the GUI. The only interesting part is the ActionListener compare and the comparrison method.
//Everything else just initializes the GUI.
public class SwingGUI extends JFrame{

	private static final long serialVersionUID = 1L;



	public static void MakeGUI(){
	//BEGIN INITIALIZE GUI
	final ArrayList<HashTable> sites = new ArrayList<>();
	final URLReader urlReader = new URLReader();

	JFrame myFrame = new JFrame();
	JPanel myPanel = new JPanel();
	myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	
	final JLabel errorLabel = new JLabel();
	final JTextField messages = new JTextField(25);
	final JLabel urlFieldLabel = new JLabel();
	final JTextField urlField = new JTextField(25);
	final JButton startComparrisonButton = new JButton("Run Comparison");
	final JLabel listOfStoredSites = new JLabel();
	final JTextArea area = new JTextArea();
	
	errorLabel.setText("Errors Posted here:");
	messages.setEditable(false);
	urlField.setEditable(true);
	urlField.setText("Enter a URL. Start with http://");
	listOfStoredSites.setText("Sites currently stored in the system:");
	area.setEditable(false);
	area.setLineWrap(true);
	area.setColumns(144);
	area.setRows(24);
	area.setText("Will be populated after initialization is finished.");
	//Put everything on the screen.
	myPanel.add(errorLabel);
	myPanel.add(messages);
	myPanel.add(urlFieldLabel);
	myPanel.add(urlField);
	myPanel.add(startComparrisonButton);
	myPanel.add(listOfStoredSites);
	myPanel.add(area);
	//END INITIALIZE GUI
	ActionListener compare = new ActionListener(){
			//This makes the comparrison between the webstite the user inputted and the websites currently in the database.
		    //it starts by grabbing the URL from the user and giving it to the URLReader object.
		    //It then Hashes the words from the user's URL in the same way that the preloaded websites are hashed.
		    //It then makes the comparrison by calculating the percentage of words that are similiar.
		    //Note: The percentage is calculated using the count (value/frequency) of each word.
		    //It repeats this for each and every website.
			@Override
			public void actionPerformed(ActionEvent actionEvent) {


				String[] page = URLReader.getWords(urlField.getText());
				if(page == null)
					messages.setText("Error: The URL you entered could not be fetched");
				else{
					HashTable tempTable = new HashTable(urlField.getText());
					tempTable.addAll(page);
					HashIterator<Bucket> itr = tempTable.getIterator();
					System.out.println("Size: "+sites.size());
					int[] counts = new int[sites.size()];
					for (int j=0;j<sites.size();j++){
						counts[j] = 0;

					}
					while(itr.hasNext()){
						Bucket sb = itr.next();
						for (int i = 0; i <sites.size();i++){
							if(sites.get(i).contains(sb.word)){
								counts[i] += 1;
							}
						}

					}
					area.setText(comparison(tempTable.size, counts, sites));
					messages.setText("Displaying comparison of entered URL to web pages from .sources file.");
				}

			}
		};
	startComparrisonButton.addActionListener(compare);
	
	myFrame.add(myPanel, BorderLayout.CENTER);

	myFrame.pack();
	myFrame.setVisible(true);
	myFrame.setSize(700, 700);
	myFrame.setResizable(true);
	myFrame.setLocationRelativeTo(null);//center frame

	try{ //try to read .sources file and process the addresses inside of it.
		BufferedReader reader = new BufferedReader(new FileReader(".sources"));
		String initialSite = "";
	    String[] wordArray;

	    while((initialSite = reader.readLine()) != null){
		wordArray = urlReader.getWords(initialSite);
		HashTable tmp = new HashTable(initialSite);
		tmp.addAll(wordArray);
		sites.add(tmp);
		messages.setText("Finished adding: '"+initialSite+"' from .sources file.");
	    }
	    messages.setText("Added all pages listed in .sources file.");
	    area.setText(initialize(sites));
	}catch(IOException e){
	    System.out.println("IO Exception.");
	    messages.setText("IO Exception when reading .sources file.");
	}
    }

    private static String initialize(ArrayList<HashTable> l){
	String s = "";
		for (int i =0;i<l.size();i++){
			s += "'" + l.get(i).name + "' - " + l.get(i).wordCount + " words, " + l.get(i).size + " unique.\n";

		}
	return s;
    }
    
    //Compares the strings from the hashtable
    private static String comparison(int z, int[] countable, ArrayList<HashTable> l){
	String returnedString = "";
		for(int i =0;i<l.size();i++){
			int j = (countable[i]*100)/(l.get(i).size);
			returnedString += "'" + l.get(i).name + "' - " +j+ "% special words in the corresponding page were also special words in the page you showed.\n";
		}
	return returnedString;
    }
}
