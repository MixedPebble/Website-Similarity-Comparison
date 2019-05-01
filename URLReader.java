package database_project;

import java.net.*;
import java.io.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
/*
This class is used explicitly to get all the words from a webpage.
This does NOT put the values into the hashable.
Since this class contains only a single method and no variables it could be replaced with a method in another class.
However, this is probably expanded upon in future assignments so I left it as it's own class because in the future it
will deserve to be its own class.
 */

public class URLReader{

    public static String[] getWords(String address){
		//This method starts off by attempting to get the URL page the user inputted
		//Then, it attempts to parse the webpage and grab every word it sees in the webpage.
		//When it is done it returns all the words in an array.
	String[] words = null;
	try{
	    URL url = new URL(address);
	
	    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
	    
	    String lineOfURL; 
	    String fullPageURL = "";
	    //Throw the entire webpage into one string
	    while((lineOfURL = in.readLine()) != null){
		fullPageURL += lineOfURL;
	    }
	    //I originally parsed line by line.
	    //Parsing the entire page at once is much more efficient
	    Document doc = Jsoup.parse(fullPageURL);
	    String text = doc.body().text();
	    //Add "-"
	    text = text.replaceAll("[[^a-zA-Z']]", " ");
	    words = text.split(" +");
		for(int i =0;i<words.length;i++){
			words[i].replaceAll("[ ]", "");

		}

	}catch(MalformedURLException MURLE){
	    System.out.println("Can not get URL. Bad formatting. Fix it.");
	    return null;
	}catch(IOException ioe){
	    System.out.println("IO Exception. Not able to get URL");
	    return null;
	}
	
	return words;
    }

}
