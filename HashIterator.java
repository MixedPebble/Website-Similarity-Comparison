package database_project;

import java.util.ArrayList;
import java.util.Iterator;

// Used to iterate through the hashtable.
// This is unneeded as a class. It could have been just added to the hashtable but this was better for serpation of concerns. 
// It in a seperate class because it is easier to see what is going on.

public class HashIterator<StringBucket> implements Iterator<StringBucket>{
    
    private ArrayList<StringBucket> l;
    private int pos;

    public HashIterator(StringBucket[] s){
	l = new ArrayList<StringBucket>();
	for(int i = 0;i<s.length;i++){
        if(s[i] != null)
            l.add(s[i]);
        }
    }

    public boolean hasNext(){
	return( pos < l.size());
    }

    public StringBucket next(){
	pos++;
	return(l.get((pos - 1)));
    }
}
