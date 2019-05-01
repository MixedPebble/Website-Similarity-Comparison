package database_project;
//This is a basic hashtable. There is nothing unusual. The Bucket that goes with this uses a word
// as the key and the times that word has occured as the value.
public class HashTable{

    public String name;
    public int size; //Number of unique strings, or number of full buckets. Note: UNIQUE number of words
    public int wordCount; //Total number of words, including duplicates.
    public  int maxSize; //number of total buckets.
    public Bucket[] buckets;

    public HashTable(String n){

	name = n;
	//How many unique words this has
	size = 0;
	//Total amount of words. Arbitrary. It's used for the print line at the end.
	wordCount = 0;
	maxSize = 128;//initial size
	buckets = new Bucket[maxSize];

    }


    public boolean contains(String s){
	int index = (s.hashCode()&(maxSize-1));
	int initial = index;
	
	if(buckets[index] == null)
	    return false;
	else if(s.equalsIgnoreCase(buckets[index].word))
	    return true;
	else{
	    while(true){
		index++;
		if(index == initial)
		    return false;//terminating condition
		if(index == maxSize)
		    index = 0;
		if(buckets[index] != null&&s.equalsIgnoreCase(buckets[index].word)){
			return true;
		}
	    }
	}
    }
    //Adds to the table
    private void insert(String s, int i, int index){
	buckets[index] = new Bucket(s, i);
    }
    //Used to add an array retrieved from the webpage. Alternatively just put a loop in
    //as this method is called less than 3 times.
    public void addAll(String[] s){
        for (int i =0;i<s.length;i++){
            this.add(s[i]);
        }
    }

    public void add(String s){
	//This adds a word to the hash. If the word already exists, it increments the value by 1.
	wordCount++;
	int index = (s.hashCode()&(maxSize-1));

	if(buckets[index] == null){ //Bucket is empty. 
	    insert(s, 1, index);
	    size++;
	    if(size >= (maxSize/4)*3)
		rehash();
	}

	else if(s.equalsIgnoreCase(buckets[index].word)){ //Reoccurring word.
	    buckets[index].count++;//Increments the value for the key value pair

	}else{ //Iterate until an empty is found.
	    while(true){
		index++;
		if(index == maxSize)
		    index = 0;
		if(buckets[index] != null){
		    if(s.equalsIgnoreCase(buckets[index].word)){
			buckets[index].count++;
			break;
		    }
		}else{//buckets[index] == null
		    size++;
		    insert(s, 1, index);
		    break;
		}
	    }
	    if(size >= (maxSize/4)*3)
		rehash();
	}
    }
    //I use a method to rehash from a previous project I worked on.
    private void rehash(){
	Bucket tempBuckets[] = buckets;
	int tempSize = maxSize;
	maxSize = maxSize<<1;
	buckets = new Bucket[maxSize];
	size = 0;
	wordCount = 0;
	
	    
	for(int i = 0; i < tempSize; i++){
	    if(tempBuckets[i] != null){
		int j = tempBuckets[i].count;
		while(j != 0){
		    this.add(tempBuckets[i].word);
		    j--;
		}
	    }
	}
	
    }

    //? Returns a new iterator over a table of buckets
    public HashIterator<Bucket> getIterator(){
	return(new HashIterator<>(this.buckets));
    }
	
}
