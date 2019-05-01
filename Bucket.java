package database_project;

// A String is the key and the times that string has occurred is the value.
class Bucket{

    public String word;
    public int count;

    public Bucket(String s, int i){
	word = s;
	count = i;
    }
}
